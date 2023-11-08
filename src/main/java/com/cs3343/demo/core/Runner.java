package com.cs3343.demo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
//@Scope(SCOPE_PROTOTYPE)

public class Runner implements CommandLineRunner {
    private final static String COOK_INPUT = "src/main/java/com/cs3343/demo/core/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT ="src/main/java/com/cs3343/demo/core/dish.txt";

    private final static String ORDER_INPUT = "src/main/java/com/cs3343/demo/core/order.txt";

    @Autowired
    private Cook cook;

    @Override
    public void run(String... args) throws Exception {
        int totalOrder = 0;

        ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
        ArrayList<Cook> cooks = cook.inputCookInfo(COOK_INPUT);
//        for(Cook c: cooks){
//            System.out.println(c.getInfo());
//        }
        ArrayList<Order> orders = Order.inputOrderInfo(ORDER_INPUT, dishes);
//        System.out.println(order);

        ArrayList<String> schedules = KitchenSchedule.generateSchedule1_3(orders, cooks);
        for(String s: schedules){
            System.out.println(s);
        }
        System.out.println("=====================================");
        for(Order o: orders){
            System.out.println("order "+o.getOrderCode()+" is ordered at "+o.getOrderTime()+", is finished cooking at "+o.getCookedTime()+". ");
        }
ArrayList<Order> filteredOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == 1) {
                filteredOrders.add(order);
            }
        }
        Collections.sort(filteredOrders);
    }

}

