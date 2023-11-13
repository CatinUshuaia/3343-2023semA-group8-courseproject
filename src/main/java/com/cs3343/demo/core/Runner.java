package com.cs3343.demo.core;

import com.cs3343.demo.entity.CookEntity;
import com.cs3343.demo.impls.CookImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cs3343.demo.core.Cook;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
//@Scope(SCOPE_PROTOTYPE)

public class Runner implements CommandLineRunner {
    private final static String COOK_INPUT = "src/main/java/com/cs3343/demo/core/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT ="src/main/java/com/cs3343/demo/core/dish.txt";

    private final static String ORDER_INPUT = "src/main/java/com/cs3343/demo/core/order1.txt";

    @Autowired
    private Cook cook;

    @Override
    public void run(String... args) throws Exception {
        int totalOrder = 0;



        ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
        ArrayList<Cook> cooks = cook.inputCookInfo(COOK_INPUT);

        ArrayList<Order> orders = Order.inputOrderInfo(ORDER_INPUT, dishes);

//        KitchenSchedule.testEarliestDishes(orders);
        KitchenSchedule kitchenGen = KitchenSchedule.getGenerator();
        kitchenGen.generateSchedule3_1(orders, cooks);

        System.out.println("=====================================");
        for(Order o: orders){
            System.out.println("order "+o.getOrderCode()+" is ordered at "+o.getOrderTime()+", is finished cooking at "+o.getCookedTime()+". ");
        }

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to restaurant backend system!");
//        System.out.println("Please enter the corresponding number to choose the function you want to use:");
//        System.out.println("1 View/Change Setting");
//        System.out.println("2 Input Orders");
//
//        String name = scanner.nextLine();
//        System.out.println("Hello, " + name + "!");
//        scanner.close();


    }
}
