package com.cs3343.demo.core;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class KitchenSchedule {
    public static ArrayList<String> generateSchedule1_1(ArrayList<Order> orders,ArrayList<Cook> cooks){
        ArrayList<String> schedule = new ArrayList<String>();
        //Assumed that the orders are sorted by time
        LocalTime time = null;
        for(Order o: orders){
            if(time==null){
                time = o.getOrderTime();
                LocalTime initialTime = time;
                cooks.forEach(c -> c.initializeAvailableTime(initialTime));
            }
            ArrayList<Dish> dishes = o.getDishes();
            Collections.sort(dishes);
            for(Dish selectedDish: dishes){
                Collections.sort(cooks);
                String result = Cook.selectCook(cooks,selectedDish.getOccupiedTime());
                schedule.add(result+" "+selectedDish);
                System.out.println(result+" "+selectedDish);

            }
//            o.allDishedCooked();
//            System.out.println("Order"+o.getOrderCode()+" is done");
        }
        return schedule;
    }
    public static Map.Entry<LocalTime, Dish> selectDish1_2(ArrayList<Order> orders, LocalTime time){
        ArrayList<Dish> dishes_beforeTime = new ArrayList<Dish>();
        int idx_afterTime = -1;

        for(Order o:orders){
            o.updateStatusIfAllDishCooked();
            if(o.getStatus()==0 && o.getOrderTime().compareTo(time)!=1){
                dishes_beforeTime.addAll(o.getDishes());
            }
            else if(o.getOrderTime().compareTo(time)==1){
                idx_afterTime = orders.indexOf(o);
                break;
            }
        }
//        System.out.println("Before Sort: "+dishes_beforeTime);
        if(dishes_beforeTime.size()!=0){
            Collections.sort(dishes_beforeTime);
//            System.out.println("After sort: "+dishes_beforeTime);
            return new AbstractMap.SimpleEntry<>(time,dishes_beforeTime.get(0));
        }else{
            //all dishes have been cooked
            if(idx_afterTime == -1){
                return null;
            }else{
                Order selectedOrder = orders.get(idx_afterTime);
                ArrayList<Dish> dishes_afterTime = selectedOrder.getDishes();
                Collections.sort(dishes_afterTime);
                return new AbstractMap.SimpleEntry<>(selectedOrder.getOrderTime(),dishes_afterTime.get(0));
            }

        }
    }

    public static ArrayList<String> generateSchedule1_2(ArrayList<Order> orders,ArrayList<Cook> cooks){
        ArrayList<String> schedule = new ArrayList<String>();

        //Assumed that the orders are sorted by time
        LocalTime time = orders.get(0).getOrderTime();

        cooks.forEach(c -> c.initializeAvailableTime(time));

        while(true){
            Cook selectedCook = Cook.selectCook(cooks);
            Map.Entry<LocalTime, Dish> result = selectDish1_2(orders,selectedCook.getAvailableTime());

            if(result == null){
                System.out.println("Done.");
                break;
            }

            LocalTime startTime = result.getKey();
            Dish selectedDish = result.getValue();

            selectedCook.cookFood(selectedDish.getOccupiedTime());
            selectedDish.cooked(startTime);
//            System.out.println(startTime+" "+selectedCook+" "+selectedDish+" order"+selectedDish.getOrder().getOrderCode());
            schedule.add(startTime+" "+selectedCook+" "+selectedDish+" order"+selectedDish.getOrder().getOrderCode());
        }
        return schedule;
    }

    //latest version: 1.3
    public static void generateSchedule1_3(ArrayList<Order> orders,ArrayList<Cook> cooks){
        ArrayList<String> schedules = new ArrayList<>();
        ArrayList<Dish> uncookedDishes = new ArrayList<>();
        orders.forEach(o -> uncookedDishes.addAll(o.getDishes()));

        Collections.sort(uncookedDishes);//refer to compareTo in Dish.java

        LocalTime time = orders.get(0).getOrderTime();
        cooks.forEach(c -> c.initializeAvailableTime(time));

        LocalTime startTime = time;

        ArrayList<Order> finishedOrder = new ArrayList<>();

        while(uncookedDishes.size()>0){
            Cook selectedCook = Cook.selectCook(cooks);

            Dish selectedDish = null;
            //find the first dish that can be cooked by the selected cook
            for(Dish d: uncookedDishes){
                if(d.getOrderedTime().compareTo(selectedCook.getAvailableTime())<1) {
                    selectedDish = d;
                    break;
                }
            }

            if(selectedDish != null){
                startTime = selectedCook.getAvailableTime();

            }else{
                //find the earliest arrived dish
                selectedDish = uncookedDishes.get(0);
                for(Dish d: uncookedDishes){
                    if(d.getOrderedTime().compareTo(selectedDish.getOrderedTime())==-1) {
                        selectedDish = d;
                    }
                }
                startTime = selectedDish.getOrderedTime();
            }
            uncookedDishes.remove(selectedDish);
            selectedCook.cookFood(selectedDish.getOccupiedTime());
            selectedDish.cooked(startTime);
            Order order = selectedDish.getOrder();
            order.updateStatusIfAllDishCooked();
            System.out.println(startTime+" "+selectedCook+" start cooking "+selectedDish+" order"+selectedDish.getOrder().getOrderCode());

        }

    }

}