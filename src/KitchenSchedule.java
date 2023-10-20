import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class KitchenSchedule {
    public static void generateSchedule(ArrayList<Order> orders,ArrayList<Cook> cooks){
        ArrayList<String> schedule = new ArrayList<String>();
        //Assumed that the orders are sorted by time
        LocalTime time = null;
        for(Order o: orders){
            if(time==null){
                time = o.getOrderTime();
            }
            ArrayList<Dish> dishes = o.getDishes();
            Collections.sort(dishes);
            for(Dish d: dishes){
                Collections.sort(cooks);
                Cook c = cooks.get(0);
                schedule.add(time+" "+c+""+d);
                c.cookFood();
            }

        }
    }
}
