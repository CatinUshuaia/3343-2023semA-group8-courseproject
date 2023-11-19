package com.cs3343.demo.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import com.cs3343.demo.core.Cook;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@ShellComponent
public class Runner implements CommandLineRunner {

    private final static String COOK_INPUT = "src/main/java/com/cs3343/demo/core/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT ="src/main/java/com/cs3343/demo/core/dish.txt";

    private final static String ORDER_INPUT = "src/main/java/com/cs3343/demo/core/testSelectDish_2.txt";
    private final static String DELIVERER_INPUT = "src/main/java/com/cs3343/demo/core/deliverers.xml";

    private Cook cook;

    @ShellMethod(value = "Run main", key = "run")
    @Override
    public void run(
            @ShellOption(defaultValue = "1") String... args
    ) throws Exception {

        ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
        ArrayList<Cook> cooks = cook.inputCookInfo(COOK_INPUT);

        ArrayList<Order> orders = Order.inputOrderInfo(ORDER_INPUT, dishes);
        Deliverer.inputDelivererInfo(DELIVERER_INPUT);

//        for(Order o: orders){
//            System.out.println("order "+o.getOrderCode()+" is ordered at "+o.getOrderTime());
//        }



//        KitchenSchedule.testEarliestDishes(orders);
//        KitchenSchedule.test_removeAll();

//        KitchenSchedule.test_get2DDishList_sort(orders);

//        KitchenSchedule.test_get2DDishList_sort_cookabledish(orders);
        KitchenSchedule.test_selectDishes3_2(orders);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        KitchenSchedule.generateSchedule_3_2(orders, cooks);
        System.out.println("=====================================");
        for(Order o: orders){
            System.out.println("order "+o.getOrderCode()+" is ordered at "+o.getOrderTime()+", is finished cooking at "+o.getCookedTime()+". ");
        }
      ArrayList<Order> filteredOrders = new ArrayList<>();
        Collections.sort(filteredOrders);
//
        for (Order order: filteredOrders){
            PerDeliverySchedule newSchedule= new PerDeliverySchedule();
//            newSchedule.generateSchedule(filteredOrders,deliverers);
        }


    }
}

