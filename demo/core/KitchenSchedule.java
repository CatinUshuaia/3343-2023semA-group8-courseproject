package demo.core;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class KitchenSchedule {

    private static final int ADDTIME = 1;

    private static final int MAXIMUM = 3;

    private static final int MAX_TIME_DIFF_FOR_SIMILAR = 3;

//    public static ArrayList<String> generateSchedule1_1(ArrayList<Order> orders,ArrayList<Cook> cooks){
//        ArrayList<String> schedule = new ArrayList<String>();
//        //Assumed that the orders are sorted by time
//        LocalTime time = null;
//        for(Order o: orders){
//            if(time==null){
//                time = o.getOrderTime();
//                LocalTime initialTime = time;
//                cooks.forEach(c -> c.initializeAvailableTime(initialTime));
//            }
//            ArrayList<Dish> dishes = o.getDishes();
//            Collections.sort(dishes);
//            for(Dish selectedDish: dishes){
//                Collections.sort(cooks);
//                String result = Cook.selectCook(cooks,selectedDish.getOccupiedTime());
//                schedule.add(result+" "+selectedDish);
//                System.out.println(result+" "+selectedDish);
//
//            }
////            o.allDishedCooked();
////            System.out.println("Order"+o.getOrderCode()+" is done");
//        }
//        return schedule;
//    }
//    public static Map.Entry<LocalTime, Dish> selectDish1_2(ArrayList<Order> orders, LocalTime time){
//        ArrayList<Dish> dishes_beforeTime = new ArrayList<Dish>();
//        int idx_afterTime = -1;
//
//        for(Order o:orders){
//            o.updateStatusIfAllDishCooked();
//            if(o.getStatus()==0 && o.getOrderTime().compareTo(time)!=1){
//                dishes_beforeTime.addAll(o.getDishes());
//            }
//            else if(o.getOrderTime().compareTo(time)==1){
//                idx_afterTime = orders.indexOf(o);
//                break;
//            }
//        }
////        System.out.println("Before Sort: "+dishes_beforeTime);
//        if(dishes_beforeTime.size()!=0){
//            Collections.sort(dishes_beforeTime);
////            System.out.println("After sort: "+dishes_beforeTime);
//            return new AbstractMap.SimpleEntry<>(time,dishes_beforeTime.get(0));
//        }else{
//            //all dishes have been cooked
//            if(idx_afterTime == -1){
//                return null;
//            }else{
//                Order selectedOrder = orders.get(idx_afterTime);
//                ArrayList<Dish> dishes_afterTime = selectedOrder.getDishes();
//                Collections.sort(dishes_afterTime);
//                return new AbstractMap.SimpleEntry<>(selectedOrder.getOrderTime(),dishes_afterTime.get(0));
//            }
//
//        }
//    }
//
//    public static ArrayList<String> generateSchedule1_2(ArrayList<Order> orders,ArrayList<Cook> cooks){
//        ArrayList<String> schedule = new ArrayList<String>();
//
//        //Assumed that the orders are sorted by time
//        LocalTime time = orders.get(0).getOrderTime();
//
//        cooks.forEach(c -> c.initializeAvailableTime(time));
//
//        while(true){
//            Cook selectedCook = Cook.selectCook(cooks);
//            Map.Entry<LocalTime, Dish> result = selectDish1_2(orders,selectedCook.getAvailableTime());
//
//            if(result == null){
//                System.out.println("Done.");
//                break;
//            }
//
//            LocalTime startTime = result.getKey();
//            Dish selectedDish = result.getValue();
//
//            selectedCook.cookFood(selectedDish.getOccupiedTime());
//            selectedDish.cooked(startTime);
////            System.out.println(startTime+" "+selectedCook+" "+selectedDish+" order"+selectedDish.getOrder().getOrderCode());
//            schedule.add(startTime+" "+selectedCook+" "+selectedDish+" order"+selectedDish.getOrder().getOrderCode());
//        }
//        return schedule;
//    }

    //latest version: 1.3

    //latest version: 1.3
    public static ArrayList<String> generateSchedule1_3(ArrayList<Order> orders,ArrayList<Cook> cooks){
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
            selectedCook.cookFood(startTime.plusMinutes(selectedDish.getOccupiedTime()));
            selectedDish.cooked(startTime);
            Order order = selectedDish.getOrder();
            order.updateStatusIfAllDishCooked();
            System.out.println(startTime+" "+selectedCook+" start cooking "+selectedDish+" order"+selectedDish.getOrder().getOrderCode());
//            System.out.println(startTime+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
            schedules.add(startTime+" "+selectedCook+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
        }
        return schedules;
    }

    //find the first dishes that can be cooked by the selected cook
    public static ArrayList<Dish> selectDishes3_1(ArrayList<Dish> uncookedDishes, LocalTime cookTime){
        ArrayList<Dish> selectedDishes = new ArrayList<>();
        for(Dish d: uncookedDishes){
            //dish is ordered before the cook is available
            if(d.isNOTEarlierThan(cookTime)) {
                if(selectedDishes.size()==0){
                    selectedDishes.add(d);
                }else if(d.sameDish(selectedDishes.get(0))
                        && selectedDishes.size()<=MAXIMUM){ //pick same dish, let them be cooked at once
                    selectedDishes.add(d);
                }else{
                    break;
                }
            }
        }
        return selectedDishes;
    }

    public static void testSelectedDishes3_1(ArrayList<Order> orders){
        ArrayList<Dish> uncookedDishes = new ArrayList<>();
        orders.forEach(o -> uncookedDishes.addAll(o.getDishes()));

        Collections.sort(uncookedDishes);//refer to compareTo in Dish.java
        uncookedDishes.remove(0);
        uncookedDishes.remove(0);

        selectDishes3_1(uncookedDishes,LocalTime.parse("11:02", DateTimeFormatter.ofPattern("HH:mm"))).forEach(d -> System.out.println(d+" "+d.getOrderedTime()));
    }

    public static ArrayList<Dish> earliestDishes_bug(ArrayList<Dish> uncookedDishes){
        ArrayList<Dish> earliestDishes = new ArrayList<>();

        Collections.sort(uncookedDishes, Dish.getTimeComparator());
        earliestDishes.add( uncookedDishes.get(0));

        for(Dish d: uncookedDishes){
            if(d.isOrderedSameTimeWith(earliestDishes.get(0))
                    && d.sameDish(earliestDishes.get(0))
                    && earliestDishes.size()<=MAXIMUM
            ){
                earliestDishes.add(d);
            }else{
                break;
            }
        }
//        earliestDishes.add(earliestDish);
        Collections.sort(uncookedDishes); //recover the order of uncookedDishes
        return earliestDishes;
    }

    public static ArrayList<Dish> earliestDishes(ArrayList<Dish> uncookedDishes){
        ArrayList<Dish> earliestDishes = new ArrayList<>();

        Collections.sort(uncookedDishes, Dish.getTimeComparator());

        for(Dish d: uncookedDishes){
            if(earliestDishes.size()==0){
                earliestDishes.add(d);
            }
            else if(d.isOrderedSameTimeWith(earliestDishes.get(0))
                    && d.sameDish(earliestDishes.get(0))
                    && earliestDishes.size()<=MAXIMUM
            ){
                earliestDishes.add(d);
            }else{
                break;
            }
        }
        Collections.sort(uncookedDishes); //recover the order of uncookedDishes
        return earliestDishes;
    }

    public static void testDishesTimeComparator(ArrayList<Order> orders){
        ArrayList<Dish> uncookedDishes = new ArrayList<>();
        orders.forEach(o -> uncookedDishes.addAll(o.getDishes()));
//        selectDishes3_1(uncookedDishes,LocalTime.parse("11:02", DateTimeFormatter.ofPattern("HH:mm"))).forEach(d -> System.out.println(d+" "+d.getOrderedTime()));
        Collections.sort(uncookedDishes, Dish.getTimeComparator());
        for(Dish d: uncookedDishes){
            System.out.println(d.getOrderedTime()+" "+d);
        }
    }

    public static void testEarliestDishes(ArrayList<Order> orders){
        ArrayList<Dish> uncookedDishes = new ArrayList<>();
        orders.forEach(o -> uncookedDishes.addAll(o.getDishes()));
        ArrayList<Dish> earliestDishes = earliestDishes(uncookedDishes);
        for(Dish d: earliestDishes){
            System.out.println(d.getOrderedTime()+" "+d);
        }
    }

    public static int cookAllTime_bug(ArrayList<Dish> dishes){
        int cookAllTime = dishes.get(0).getOccupiedTime();
        cookAllTime += ADDTIME*(dishes.size()-1);
        return cookAllTime;
    }

    public static int operateAllTime(ArrayList<Dish> dishes){
        int operateAllTime = dishes.get(0).getOccupiedTime();
        operateAllTime += ADDTIME*(dishes.size()-1);
        return operateAllTime;
    }

    public static int cookAllTime(ArrayList<Dish> dishes){
        int cookAllTime = dishes.get(0).getDishProductTime();
        cookAllTime += ADDTIME*(dishes.size()-1);
        return cookAllTime;
    }

    public static ArrayList<String> generateSchedule3_1(ArrayList<Order> orders,ArrayList<Cook> cooks){
        ArrayList<String> schedules = new ArrayList<>();
        ArrayList<Dish> uncookedDishes = new ArrayList<>();
        orders.forEach(o -> uncookedDishes.addAll(o.getDishes()));

        Collections.sort(uncookedDishes);//refer to compareTo in Dish.java

        LocalTime time = orders.get(0).getOrderTime();
        cooks.forEach(c -> c.initializeAvailableTime(time));

        LocalTime startTime = time;

        while(uncookedDishes.size()>0){
            Cook selectedCook = Cook.selectCook(cooks);

            ArrayList<Dish> selectedDishes = selectDishes3_1(uncookedDishes,selectedCook.getAvailableTime());

            if(selectedDishes.size() != 0){
                startTime = selectedCook.getAvailableTime();

            }else{
                //find the earliest arrived dish
                selectedDishes = KitchenSchedule.earliestDishes(uncookedDishes);
                startTime = selectedDishes.get(0).getOrderedTime();
            }
            LocalTime finishedTime = startTime.plusMinutes(cookAllTime(selectedDishes));
            //pass expected finish time
            selectedCook.cookFood(startTime.plusMinutes(operateAllTime(selectedDishes)));

            ArrayList<Integer> orderCodes = new ArrayList<>();
            for(Dish d: selectedDishes){
                d.cooked(finishedTime); //change the cooked time
                uncookedDishes.remove(d);
                Order order = d.getOrder();
                order.updateStatusIfAllDishCooked();
                orderCodes.add(order.getOrderCode());
            }

            System.out.println(startTime+" "+selectedCook+" start cooking "+selectedDishes.get(0)+" order:"+orderCodes+". done at "+finishedTime);
//            System.out.println(startTime+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
//            schedules.add(startTime+" "+selectedCook+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
        }
        return schedules;
    }

    public static ArrayList<Dish> getCookableDishes(ArrayList<Dish> selectedSub, LocalTime cookTime){
        ArrayList<Dish> selectedDishes = new ArrayList<>();
        for(Dish d: ArrayList<Dish> selectedSub){
            if(d.isNOTEarlierThan(cookTime)&&selectedDishes.size() <= MAXIMUM){
                selectedDishes.add(d); 
            }
        }
        return selectedDishes;
    }

    public static boolean similarTimeWith(Dish dish1, Dish dish2){
        return Math.abs(dish1.getOccupiedTime()-dish2.getOccupiedTime())<=MAX_TIME_DIFF_FOR_SIMILAR;
    }

    //find the first dishes that are ordered before the cook is available
    //also, try to select the dish that the cook is good at
    public static ArrayList<Dish> selectDishes3_2(ArrayList<ArrayList<Dish>> uncookedDishes, Cook selectedCook, LocalTime cookTime, LocalTime nextAvailableTime){
        ArrayList<ArrayList<Dish>> possibleSubs = new ArrayList<>();
        ArrayList<Dish> selectedSub = new ArrayList<>();
        for(ArrayList<Dish> dishSub: uncookedDishes){
            //all dishes in the subarray are ordered after the cook is available
            if(!dishSub.get(0).isNOTEarlierThan(cookTime)){
                continue;
            }
            if(possibleSubs.size()==0){
                possibleSubs.add(dishSub);
            }
            if(selectedCook.goodAt(dishSub.get(0))){
                selectedSub = dishSub;
                break;
            }

        }
        return getCookableDishes(selectedSub, cookTime);
    }

    public static void addDishIntosubList(Dish dish, ArrayList<ArrayList<Dish>> dish2DList){
        boolean hasSub = false;
        for(ArrayList<Dish> subList: dish2DList){
            if(subList.get(0).getDishCode() == dish.getDishCode()){
                subList.add(dish);
                hasSub = true;
            }
        }
        if(!hasSub){
            ArrayList<Dish> subList = new ArrayList<Dish>();
            subList.add(dish);
            dish2DList.add(subList);
        }
    }

    public static ArrayList<ArrayList<Dish>> get2DDishList(ArrayList<Order> orders){
        ArrayList<ArrayList<Dish>> dishList = new ArrayList<>();
        for(Order o: orders){
            for(Dish d:o.getDishes()){
                addDishIntosubList(d,dishList);
            }
        }
        return dishList;
    }

    static class DishSubComp implements Comparator<ArrayList<Dish>> {
        @Override
        public int compare(ArrayList<Dish> o1, ArrayList<Dish> o2) {
            return o1.get(0).compareTo(o2.get(0));
        }
    }
        

    //latest version: 3.2
    public static ArrayList<String> generateSchedule3_2(ArrayList<Order> orders,ArrayList<Cook> cooks){
        ArrayList<String> schedules = new ArrayList<>();
        //same dishes are stored into the same sublit, sorted according to the orderedTime
        //As orders is ordered by time, no need to do sorting within dish sub list manually
        ArrayList<ArrayList<Dish>> uncookedDishes = get2DDishList(orders);

        Collections.sort(uncookedDishes,new DishSubComp());

        LocalTime time = orders.get(0).getOrderTime();
        cooks.forEach(c -> c.initializeAvailableTime(time));

        LocalTime startTime = time;

        while(uncookedDishes.size()>0){
            Cook selectedCook = Cook.selectCook(cooks);

            ArrayList<Dish> selectedDishes = selectDishes3_2(uncookedDishes,selectedCook.getAvailableTime());

            if(selectedDishes.size() != 0){
                startTime = selectedCook.getAvailableTime();

            }else{
                //find the earliest arrived dish
                selectedDishes = KitchenSchedule.earliestDishes(uncookedDishes);
                startTime = selectedDishes.get(0).getOrderedTime();
            }
            LocalTime finishedTime = startTime.plusMinutes(cookAllTime(selectedDishes));
            //pass expected finish time
            selectedCook.cookFood(startTime.plusMinutes(operateAllTime(selectedDishes)));

            ArrayList<Integer> orderCodes = new ArrayList<>();
            for(Dish d: selectedDishes){
                d.cooked(finishedTime); //change the cooked time
                uncookedDishes.remove(d);
                Order order = d.getOrder();
                order.updateStatusIfAllDishCooked();
                orderCodes.add(order.getOrderCode());
            }

            System.out.println(startTime+" "+selectedCook+" start cooking "+selectedDishes.get(0)+" order:"+orderCodes+". done at "+finishedTime);
//            System.out.println(startTime+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
//            schedules.add(startTime+" "+selectedCook+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
        }
        return schedules;
    }



}