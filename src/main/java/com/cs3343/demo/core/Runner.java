package com.cs3343.demo.core;


import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@ShellComponent
public class Runner implements CommandLineRunner {

    private final static String COOK_INPUT = "src/main/java/com/cs3343/demo/core/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT ="src/main/java/com/cs3343/demo/core/dish.txt";

    private final static String ORDER_INPUT = "src/main/java/com/cs3343/demo/core/order.txt";
    private final static String DELIVERER_INPUT = "src/main/java/com/cs3343/demo/core/deliverers.xml";

    private Cook cook;

    @ShellMethod(value = "Run main", key = "run")
    @Override
    public void run(
            @ShellOption(defaultValue = "1") String... args
    ) throws Exception {
        int totalOrder = 0;

        ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
        ArrayList<Cook> cooks = cook.inputCookInfo(COOK_INPUT);

        ArrayList<Order> orders = Order.inputOrderInfo(ORDER_INPUT, dishes);

        ArrayList<Deliverer> deliverers=Deliverer.inputDelivererInfo(DELIVERER_INPUT);
        ArrayList<Order> filteredOrders=new ArrayList<Order>();
//        KitchenSchedule.testEarliestDishes(orders);

        KitchenSchedule.generateSchedule3_1(orders, cooks);
        System.out.println("=====================================");
        for(Order o: orders){
            System.out.println("order "+o.getOrderCode()+" is ordered at "+o.getOrderTime()+", is finished cooking at "+o.getCookedTime()+". ");
        }
        filteredOrders=orders.stream().filter(order -> order.getStatus()==1).collect(Collectors.toCollection(ArrayList::new));
        while(!filteredOrders.isEmpty()){

            var sortedOrders = new ArrayList<>(filteredOrders.stream().sorted((o1, o2) -> o1.compareTo(o2)).toList());
            var assignmentManager = new DeliveryAssignmentManager();
            System.out.println(assignmentManager.GenerateDeliveryAssignment(sortedOrders, deliverers));
            filteredOrders=filteredOrders.stream().filter(order -> order.getStatus()==1).collect(Collectors.toCollection(ArrayList::new));
        }

    }
}

