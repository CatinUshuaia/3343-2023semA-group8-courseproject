package com.cs3343.demo.core;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class KitchenSchedule {
    //TODO: make it as singleton
    //TODO: allow user to change MAXIMUM

    //一次最多可以炒几道菜
    private static final int MAXIMUM = 3;

    private static final int ADDTIME = 1;//NOTE: this is fixed, and cannot be changed by users

    // find the first dishes that can be cooked by the selected cook
    public static ArrayList<Dish> selectDishes(ArrayList<Dish> uncookedDishes, LocalTime cookTime) {
        ArrayList<Dish> selectedDishes = new ArrayList<>();
        for (Dish d : uncookedDishes) {
            // dish is ordered before the cook is available
            if (d.isNOTEarlierThan(cookTime)) {
                if (selectedDishes.size() == 0) {
                    selectedDishes.add(d);
                } else if (d.sameDish(selectedDishes.get(0))
                        && selectedDishes.size() <= MAXIMUM) { // pick same dish, let them be cooked at once
                    selectedDishes.add(d);
                } else {
                    break;
                }
            }
        }
        return selectedDishes;
    }

    public static ArrayList<Dish> earliestDishes(ArrayList<Dish> uncookedDishes) {
        ArrayList<Dish> earliestDishes = new ArrayList<>();

        Collections.sort(uncookedDishes, Dish.getTimeComparator());

        for (Dish d : uncookedDishes) {
            if (earliestDishes.size() == 0) {
                earliestDishes.add(d);
            } else if (d.isOrderedSameTimeWith(earliestDishes.get(0))
                    && d.sameDish(earliestDishes.get(0))
                    && earliestDishes.size() <= MAXIMUM) {
                earliestDishes.add(d);
            } else {
                break;
            }
        }
        Collections.sort(uncookedDishes); // recover the order of uncookedDishes
        return earliestDishes;
    }

    public static int operateAllTime(ArrayList<Dish> dishes) {
        int operateAllTime = dishes.get(0).getOccupiedTime();
        operateAllTime += ADDTIME * (dishes.size() - 1);
        return operateAllTime;
    }

    public static int cookAllTime(ArrayList<Dish> dishes) {
        int cookAllTime = dishes.get(0).getDishProductTime();
        cookAllTime += ADDTIME * (dishes.size() - 1);
        return cookAllTime;
    }
    // latest version: 3.1
    public static ArrayList<String> generateSchedule(ArrayList<Order> orders, ArrayList<Cook> cooks) {
        ArrayList<String> schedules = new ArrayList<>();
        ArrayList<Dish> uncookedDishes = new ArrayList<>();
        orders.forEach(o -> uncookedDishes.addAll(o.getDishes()));

        Collections.sort(uncookedDishes);// refer to compareTo in Dish.java

        LocalTime time = orders.get(0).getOrderTime();
        cooks.forEach(c -> c.initializeAvailableTime(time));

        LocalTime startTime = time;

        while (uncookedDishes.size() > 0) {
            Cook selectedCook = Cook.selectCook(cooks);

            ArrayList<Dish> selectedDishes = selectDishes(uncookedDishes, selectedCook.getAvailableTime());

            if (selectedDishes.size() != 0) {
                startTime = selectedCook.getAvailableTime();

            } else {
                // find the earliest arrived dish
                selectedDishes = KitchenSchedule.earliestDishes(uncookedDishes);
                startTime = selectedDishes.get(0).getOrderedTime();
            }
            LocalTime finishedTime = startTime.plusMinutes(cookAllTime(selectedDishes));
            // pass expected finish time
            selectedCook.cookFood(startTime.plusMinutes(operateAllTime(selectedDishes)));

            ArrayList<Integer> orderCodes = new ArrayList<>();
            for (Dish d : selectedDishes) {
                d.cooked(finishedTime); // change the cooked time
                uncookedDishes.remove(d);
                Order order = d.getOrder();
                order.updateStatusIfAllDishCooked();
                orderCodes.add(order.getOrderCode());
            }

            System.out.println(startTime + " " + selectedCook + " should start cooking " + selectedDishes.get(0) + " order:"
                    + orderCodes + ". Expected finish at " + finishedTime);
            schedules.add(startTime+" "+selectedCook+" "+selectedDishes.get(0).getDishCode()+" "+ orderCodes);
        }
        return schedules;
    }

}