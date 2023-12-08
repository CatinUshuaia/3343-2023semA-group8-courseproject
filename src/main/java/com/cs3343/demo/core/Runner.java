package com.cs3343.demo.core;


import org.springframework.boot.CommandLineRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@ShellComponent
public class Runner implements CommandLineRunner {

    private final static String COOK_INPUT = "src/main/java/com/cs3343/demo/core/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT = "src/main/java/com/cs3343/demo/core/dish.txt";

    private final static String ORDER_INPUT = "src/main/java/com/cs3343/demo/core/order.txt";
    private final static String DELIVERER_INPUT = "src/main/java/com/cs3343/demo/core/deliverers.xml";

    private final ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
    private final ArrayList<Cook> cooks = Cook.inputCookInfo(COOK_INPUT);
    private final ArrayList<Deliverer> deliverers = Deliverer.inputDelivererInfo(DELIVERER_INPUT);
    private final ArrayList<Order> orders = new ArrayList<>();
    private final ArrayList<Delivery> ds = new ArrayList<>();


    public Runner() throws IOException {
        System.out.println("Welcome to the restaurant management system!");

        System.out.println("This programme has started successfully, please enter 'help' for more information.");
    }

    private static boolean isValidFormat(String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    @ShellMethod(value = "Run main", key = "run")
    @Override
    public void run(
            @ShellOption(defaultValue = "1") String... args
    ) throws Exception {
    }

    @ShellMethod(value = "Add new order", key = "new-order")
    public void newOrder(
            @ShellOption(value = {"-t","--time"}) String timeStr,
            @ShellOption(value = {"-d", "--dish"}) String dishStr,
            @ShellOption(value = {"-x", "--xLocation"}) int xLocation,
            @ShellOption(value = {"-y", "--yLocation"}) int yLocation
    ) throws Exception{

        ArrayList<Order> ordersInList;
        ArrayList<String> dishList = new ArrayList<>(Arrays.asList(dishStr.split(",")));

        int maximumOrders = 5;
        if (dishList.size() > maximumOrders){
            throw new ExceptionExceedMaximum();
        }
        if(!isValidFormat(timeStr)){
            throw new ExceptionInvalidDate();
        }

        DeliveryAssignmentManager NewAssignmentManager = new DeliveryAssignmentManager();

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
                .sorted(Order::compareTo)
                .collect(Collectors.toCollection(ArrayList::new));

        while(!ordersToBeDelivered.isEmpty()){
            var newDelivery = NewAssignmentManager.GenerateDeliveryAssignment(ordersToBeDelivered, deliverers);
            ds.add(newDelivery);
            System.out.println(newDelivery);
            ordersToBeDelivered = ordersToBeDelivered.stream()
                    .filter(order -> order.getStatus()==1)
                    .sorted(Order::compareTo)
                    .collect(Collectors.toCollection(ArrayList::new));

        }
    }

    @ShellMethod(value = "Add batch orders", key = "batch-order")
    public void batchOrder(
            @ShellOption(value = {"-f", "--filepath"}) String filepath
    ) throws Exception {

        File orderList = new File(filepath);
        Scanner reader = new Scanner(orderList);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();

            ArrayList<String> cmd = new ArrayList<>(Arrays.asList(data.split(" ")));
            System.out.println(data);

            newOrder(cmd.get(0), cmd.get(1), Integer.parseInt(cmd.get(2)), Integer.parseInt(cmd.get(3)));
        }

    }

    @ShellMethod(value = "Add batch cooks", key = "batch-cook")
    public void batchCook(
            @ShellOption(value = {"-f", "--filepath"}) String filepath
    )throws Exception{
        cooks.addAll(Cook.inputCookInfo(filepath));
    }

    @ShellMethod(value = "Add batch dishes", key = "batch-dish")
    public void batchDish(
            @ShellOption(value = {"-f", "--filepath"}) String filepath
    )throws Exception{
        dishes.addAll(Dish.inputDishInfo(filepath));
    }

    @ShellMethod(value = "Add batch deliverers", key = "batch-deliverer")
    public void batchDeliverer(
            @ShellOption(value = {"-f", "--filepath"}) String filepath
    )throws Exception{
        deliverers.addAll(Deliverer.inputDelivererInfo(filepath));
    }

    @ShellMethod(value = "Print all cooks.", key = "print-cook")
    public void printAllCook() throws Exception{
        for(Cook c: cooks){
            System.out.println(c.toString());
        }
    }
    @ShellMethod(value = "Print all dishes.", key = "print-dish")
    public void printAllDish() throws Exception{
        for(Dish d: dishes){
            System.out.println(d.toString());
        }
    }
    @ShellMethod(value = "Print all deliverers.", key = "print-deliverer")
    public void printAllDeliverer() throws Exception{
        for (Deliverer d: deliverers){
            System.out.println(d.toString());
        }
    }


}
