package com.cs3343.demo.core;


import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Scanner;

@ShellComponent
public class Runner implements CommandLineRunner {

    private final static String COOK_INPUT = "src/main/java/com/cs3343/demo/core/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT ="src/main/java/com/cs3343/demo/core/dish.txt";

    private final static String ORDER_INPUT = "src/main/java/com/cs3343/demo/core/order.txt";
    private final static String DELIVERER_INPUT = "src/main/java/com/cs3343/demo/core/deliverers.xml";

    private Cook cook;
    Boolean firstInput = true;
    @ShellMethod(value = "Run main", key = "run")
    @Override
    public void run(
            @ShellOption(defaultValue = "1") String... args
    ) throws Exception {

        
        System.out.println("Welcome to the restaurant managment system!");

        System.out.println("please input order time, dishes, and location. Input 'exit' to leave.");
        DeliveryAssignmentManager NewAssignmentManager = new DeliveryAssignmentManager();

        ArrayList<Delivery> ds = new ArrayList<>();

    }

    @ShellMethod(value = "Add new order", key = "new-order")
    public void newOrder(
            @ShellOption(value = {"-t","--time"}) String timeStr,
            @ShellOption(value = {"-d", "--dish"}) String dishStr,
            @ShellOption(value = {"-x", "--xLocation"}) int xLocation,
            @ShellOption(value = {"-y", "--yLocation"}) int yLocation
    ) throws Exception {

        ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
        ArrayList<Cook> cooks = cook.inputCookInfo(COOK_INPUT);
        ArrayList<Deliverer> deliverers=Deliverer.inputDelivererInfo(DELIVERER_INPUT);
        ArrayList<Order> orders = new ArrayList<Order>();
        ArrayList<Order> ordersInList;

        DeliveryAssignmentManager NewAssignmentManager = new DeliveryAssignmentManager();

        ArrayList<Delivery> ds = new ArrayList<>();

        Order newAddedOrder = Order.newOrder(orders.size()+1, timeStr, dishes, dishStr, xLocation, yLocation);

        orders.add(newAddedOrder);
        KitchenSchedule.generateSchedule(orders, cooks);
        System.out.println("=====================================");
        for(Order o: orders){
            System.out.println("order "+o.getOrderCode()+" is ordered at "+o.getOrderTime()+", is finished cooking at "+o.getCookedTime()+". ");
        }


        var currentTime = newAddedOrder.getOrderTime();

        for(var d :ds) {
            if(currentTime.isAfter(d.getDeliverTime())) {
                NewAssignmentManager.deliverInDeed(d);
            }
        }

        ArrayList<Order> ordersToBeDelivered = orders.stream()
                .filter(order -> (order.getStatus()==1))
                .sorted((o1, o2) -> o1.compareTo(o2))
                .collect(Collectors.toCollection(ArrayList::new));

        while(!ordersToBeDelivered.isEmpty()){
            var newDelivery = NewAssignmentManager.GenerateDeliveryAssignment(ordersToBeDelivered, deliverers);
            ds.add(newDelivery);
            System.out.println(newDelivery);
            ordersToBeDelivered = ordersToBeDelivered.stream()
                    .filter(order -> order.getStatus()==1)
                    .sorted((o1, o2) -> o1.compareTo(o2))
                    .collect(Collectors.toCollection(ArrayList::new));

        }
    }

    @ShellMethod(value = "Add batch orders", key = "batch-order")
    public void batchOrder(
            @ShellOption(value = "-f") String filepath
    ) throws Exception{

    }

    @ShellMethod(value = "Change the maximum number of dishes per order", key = "max-dish")
    public void maxDish(
            @ShellOption(value = {"-n", "--max-number"}) int maxNumber
    ) throws Exception{

    }

}

