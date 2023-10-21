import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class KitchenSchedule {
    public static void generateSchedule1_1(ArrayList<Order> orders,ArrayList<Cook> cooks){
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
            o.allDishedCooked();
            System.out.println("Order"+o.getOrderCode()+" is done");
        }
    }

    public static void generateSchedule1_2(ArrayList<Order> orders,ArrayList<Cook> cooks){
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
                schedule.add(result+" "+selectedDish.toString());
                System.out.println(result+" "+selectedDish.toString());

            }

        }
    }


}
