package com.cs3343.demo.core;


import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

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

    @ShellMethod(value = "Run main", key = "run")
    @Override
    public void run(
            @ShellOption(defaultValue = "1") String... args
    ) throws Exception {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
        ArrayList<Cook> cooks = cook.inputCookInfo(COOK_INPUT);
        ArrayList<Deliverer> deliverers=Deliverer.inputDelivererInfo(DELIVERER_INPUT);

        // ArrayList<Order> orders = Order.inputOrderInfo(ORDER_INPUT, dishes);
        ArrayList<Order> orders = new ArrayList<Order>();
        System.out.println("please input order time, dishes, and location. Input 'exit' to leave.");
        while (true){
            String orderLine = scanner.nextLine(); 
            if(orderLine.equals("exit")){
                break;
            }

            orders.add(Order.newOrder(orders.size()+1, orderLine, dishes));

            KitchenSchedule.generateSchedule(orders, cooks);
            System.out.println("=====================================");
            for(Order o: orders){
                System.out.println("order "+o.getOrderCode()+" is ordered at "+o.getOrderTime()+", is finished cooking at "+o.getCookedTime()+". ");
            }

            ArrayList<Order> filteredOrders=new ArrayList<Order>();
            filteredOrders=orders.stream().filter(order -> order.getStatus()==1).collect(Collectors.toCollection(ArrayList::new));
            while(!filteredOrders.isEmpty()){

                var sortedOrders = new ArrayList<>(filteredOrders.stream().sorted((o1, o2) -> o1.compareTo(o2)).toList());
                var assignmentManager = new DeliveryAssignmentManager();
                System.out.println(assignmentManager.GenerateDeliveryAssignment(sortedOrders, deliverers));
                filteredOrders=filteredOrders.stream().filter(order -> order.getStatus()==1).collect(Collectors.toCollection(ArrayList::new));
            }
        }
        // scanner.close();

    }
}

