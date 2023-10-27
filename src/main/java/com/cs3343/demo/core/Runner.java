package com.cs3343.demo.core;

import com.cs3343.demo.entity.CookEntity;
import com.cs3343.demo.impls.CookImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cs3343.demo.core.Cook;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
//@Scope(SCOPE_PROTOTYPE)

public class Runner implements CommandLineRunner {
    private final static String COOK_INPUT = "C:/Users/WENYi/3343-latest/3343-2023semA-group8-courseproject/src/main/java/com/cs3343/demo/core/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT ="C:/Users/WENYi/3343-latest/3343-2023semA-group8-courseproject/src/main/java/com/cs3343/demo/core/dish.txt";

    private final static String ORDER_INPUT = "C:/Users/WENYi/3343-latest/3343-2023semA-group8-courseproject/src/main/java/com/cs3343/demo/core/order.txt";

    @Autowired
    private Cook cook;

    @Override
    public void run(String... args) throws Exception {
        int totalOrder = 0;

        ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
        ArrayList<Cook> cooks = cook.inputCookInfo(COOK_INPUT);
        for(Cook c: cooks){
            System.out.println(c.getInfo());
        }
        ArrayList<Order> order = Order.inputOrderInfo(ORDER_INPUT, dishes);
//        System.out.println(order);

        ArrayList<String> schedule = KitchenSchedule.generateSchedule1_3(order, cooks);
        for (String s : schedule) {
            System.out.println(s);
        }

    }
}
