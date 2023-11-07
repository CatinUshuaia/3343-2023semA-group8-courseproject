package demo.core;
import com.cs3343.demo.core.Cook;
import com.cs3343.demo.core.Dish;
import com.cs3343.demo.core.Order;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class KitchenSchedule {

    //latest version: 1.3
    public static ArrayList<String> generateSchedule1_3(ArrayList<Order> orders, ArrayList<Cook> cooks){
        ArrayList<String> schedules = new ArrayList<>();
        ArrayList<Dish> uncookedDishes = new ArrayList<>();
        orders.forEach(o -> uncookedDishes.addAll(o.getDishes()));

        Collections.sort(uncookedDishes);//refer to compareTo in Dish.java

        LocalTime time = orders.get(0).getOrderTime();
        cooks.forEach(c -> c.initializeAvailableTime(time));

        LocalTime startTime = time;

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
//            System.out.println(startTime+" "+selectedCook+" start cooking "+selectedDish+" order"+selectedDish.getOrder().getOrderCode());
//            System.out.println(startTime+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
            schedules.add(startTime+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
        }
        return schedules;
    }}