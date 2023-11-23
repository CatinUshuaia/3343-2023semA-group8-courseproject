package com.cs3343.demo.core;
import java.time.Duration;
import java.time.LocalTime;
//import java.util.AbstractMap;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KitchenSchedule {

    private static final int ADDTIME = 1;
    private static final int MAXIMUM = 3;

    //below two flags cannot be exposed to users, since it's not user-friendly and easy to understand
    private static final int MAX_TIME_DIFF_FOR_SIMILAR = 3;

    private static final int WAITING_COOK = 6;
    private static final double MAX_DISTANCE = 30.0;

    public static int getWaitingCook() {
        return WAITING_COOK;
    }

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
//                String result = Cook.selectCook_before_4(cooks,selectedDish.getOccupiedTime());
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
//            Cook selectedCook = Cook.selectCook_before_4(cooks);
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
            Cook selectedCook = Cook.selectCook_before_4(cooks);

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

    public static ArrayList<Dish> earliestDishes_3_1(ArrayList<Dish> uncookedDishes){
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

    public static void testEarliestDishes_3_1(ArrayList<Order> orders){
        ArrayList<Dish> uncookedDishes = new ArrayList<>();
        orders.forEach(o -> uncookedDishes.addAll(o.getDishes()));
        ArrayList<Dish> earliestDishes = earliestDishes_3_1(uncookedDishes);
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
            Cook selectedCook = Cook.selectCook_before_4(cooks);

            ArrayList<Dish> selectedDishes = selectDishes3_1(uncookedDishes,selectedCook.getAvailableTime());

            if(selectedDishes.size() != 0){
                startTime = selectedCook.getAvailableTime();

            }else{
                //find the earliest arrived dish
                selectedDishes = KitchenSchedule.earliestDishes_3_1(uncookedDishes);
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
        for(Dish d: selectedSub){
            if(d.isNOTEarlierThan(cookTime)&&selectedDishes.size() <= MAXIMUM){
                selectedDishes.add(d);
            }
        }
        return selectedDishes;
    }

//    public static boolean similarTimeWith(Dish dish1, Dish dish2){
//        return Math.abs(dish1.getOccupiedTime()-dish2.getOccupiedTime())<=MAX_TIME_DIFF_FOR_SIMILAR;
//    }


    //find the first dishes that are ordered before the cook is available
    //also, try to select the dish that the cook is good at
    public static ArrayList<Dish> selectDishes3_2(ArrayList<ArrayList<Dish>> uncookedDishes, Cook selectedCook, LocalTime nextCook_Time){
        ArrayList<Dish> selectedSub = new ArrayList<>();
        ArrayList<Dish> candidateSub = new ArrayList<>();
        LocalTime cookTime = selectedCook.getAvailableTime();

        for(ArrayList<Dish> dishSub: uncookedDishes){
            //all dishes in the subarray are ordered after the cook is available
            if(!dishSub.get(0).isNOTEarlierThan(cookTime)){
                continue;
            }
            if(selectedCook.goodAt(dishSub.get(0)) && selectedSub.size()==0){
                selectedSub = dishSub;
            }else if(candidateSub.size()==0){
                candidateSub = dishSub;
            }else{
                break;
            }
        }

        System.out.println("selectedSub: "+selectedSub);
        System.out.println("candidateSub: "+candidateSub);

        if(candidateSub.size()==0 && selectedSub.size()==0){
            //no dish is earlier than the cook's available time
            return new ArrayList<Dish>();
        }else if(candidateSub.size()==0){
            //dish with the highest priority is also the cook good at
            return getCookableDishes(selectedSub, cookTime);
        }else if(selectedSub.size()==0){
            //no dish is good at, so select the dish with the highest priority
            return getCookableDishes(candidateSub, cookTime);
        }else{
            



            // // the dish cook good at is not the one with the highest priority
            // // check if the dish can be cooked at first

            // ArrayList<Dish> cookableSelected = getCookableDishes(selectedSub, cookTime);

            // //get the next available time of the cook
            // //the next time maybe the selectedCook works, maybe the next cook works
            // LocalTime thisCook_nextAval = cookTime.plusMinutes(operateAllTime(cookableSelected));
            // LocalTime nextAval;
            // if(nextCook_Time==null || nextCook_Time.isAfter(thisCook_nextAval)){
            //     nextAval = thisCook_nextAval;
            // }else{
            //     nextAval = nextCook_Time;
            // }

            // //if the next availiable time doesn't satisfy the constraint of the dish with the highest priority
            // Duration duration = Duration.between(nextAval, candidateSub.get(0).getOrderedTime());
            // long timeDiff = Math.abs(duration.toMinutes());
            // int waitingConstraint = candidateSub.get(0).getOrder().getCookTimeConstraint();
            // if(timeDiff>waitingConstraint){
            //     //the dish with the highest priority cannot be cooked
            //     return getCookableDishes(candidateSub, cookTime);
            // }else{
            //     return cookableSelected;
            // }
        }
    }

    public static void test_selectDishes3_2(ArrayList<Order> orders){
        ArrayList<ArrayList<Dish>> dishList = get2DDishList(orders);
        Collections.sort(dishList,new DishSubComp());
        Cook cook = new Cook();
        LocalTime cookTime = LocalTime.parse("11:05", DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime nextAvailableTime = LocalTime.parse("11:09", DateTimeFormatter.ofPattern("HH:mm"));
        cook.makeTestedCook(cookTime,new String[]{"Hunan"});
        ArrayList<Dish> selectedDishes = selectDishes3_2(dishList,cook,nextAvailableTime);
        selectedDishes.forEach(d -> System.out.println(d + " " + d.getOrderedTime()));

    }
    public static ArrayList<Dish> earliestDishes_3_2(ArrayList<ArrayList<Dish>> uncookedDishes){
        ArrayList<Dish> earliestDishes = new ArrayList<>();

        for(ArrayList<Dish> sub: uncookedDishes){
            if(earliestDishes.size()==0){
                //first dish in the sub is the one ordered earlier
                //if there are other dishes ordered at the same time in sub, they are also added
                earliestDishes=getCookableDishes(sub , sub.get(0).getOrderedTime());
            }
            else if(! sub.get(0).isNOTEarlierThan(earliestDishes.get(0).getOrderedTime())){
                earliestDishes = getCookableDishes(sub , sub.get(0).getOrderedTime());
            }

        }
        return earliestDishes;
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

    public static void test_get2DDishList_sort(ArrayList<Order> orders){
        ArrayList<ArrayList<Dish>> dishList = get2DDishList(orders);
        Collections.sort(dishList,new DishSubComp());
        int counter = 0;
        for(ArrayList<Dish> sub: dishList){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("sublist "+counter++);
            sub.forEach(d -> System.out.println(d + " " +d.getWayToCookStr()+" "+ d.getOrderedTime()));
        }
    }

    public static void test_get2DDishList_sort_cookabledish(ArrayList<Order> orders){
        ArrayList<ArrayList<Dish>> dishList = get2DDishList(orders);
        Collections.sort(dishList,new DishSubComp());
        int counter = 0;
//        for(ArrayList<Dish> sub: dishList){
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//            System.out.println("sublist "+counter++);
//            sub.forEach(d -> System.out.println(d + " " +d.getWayToCookStr()+" "+ d.getOrderedTime()));
//        }
        ArrayList<Dish> selectedSub = dishList.get(1);
        LocalTime cookTime = LocalTime.parse("15:51", DateTimeFormatter.ofPattern("HH:mm"));
        ArrayList<Dish> selectedDishes = getCookableDishes(selectedSub, cookTime);
        selectedDishes.forEach(d -> System.out.println(d + " " +d.getWayToCookStr()+" "+ d.getOrderedTime()));
    }

    static class DishSubComp implements Comparator<ArrayList<Dish>> {
        @Override
        public int compare(ArrayList<Dish> o1, ArrayList<Dish> o2) {
            return o1.get(0).compareTo(o2.get(0));
        }
    }

    public static void test_removeAll(){
        ArrayList<Dish> dishes = new ArrayList<>();
        Dish dishObj1 = new Dish(1,"roastedDuck",8,"fry", "Hunan");
        Dish dishObj2 = new Dish(1,"roastedDuck",8,"fry", "Hunan");
        System.out.println(dishObj1.equals(dishObj2));
        System.out.println(dishObj1==dishObj2);
        dishes.add(dishObj1);
        dishes.add(dishObj2);
        ArrayList<Dish> dishes2 = new ArrayList<>();
        dishes2.add(dishObj1);
        dishes.removeAll(dishes2);
        for(Dish d: dishes){
            System.out.println(d);
        }

    }


    //latest version: 3.2
    public static ArrayList<String> generateSchedule_3_2(ArrayList<Order> orders,ArrayList<Cook> cooks){
        ArrayList<String> schedules = new ArrayList<>();
        //same dishes are stored into the same sublit, sorted according to the orderedTime
        //As orders is ordered by time, no need to do sorting within dish sub list manually
        ArrayList<ArrayList<Dish>> uncookedDishes = get2DDishList(orders);
        Collections.sort(uncookedDishes,new DishSubComp());

        LocalTime time = orders.get(0).getOrderTime();
        cooks.forEach(c -> c.initializeAvailableTime(time));

        LocalTime startTime = time;

        while(uncookedDishes.size()>0){
            Cook selectedCook = Cook.selectCook_before_4(cooks);

            LocalTime nextAvailableTime = null;
            if(cooks.size()>=2){
                nextAvailableTime = cooks.get(1).getAvailableTime();
            }

            ArrayList<Dish> selectedDishes = selectDishes3_2(uncookedDishes,selectedCook,nextAvailableTime);

            if(selectedDishes.size() != 0){
                startTime = selectedCook.getAvailableTime();

            }else{
                //find the earliest arrived dish
                selectedDishes = KitchenSchedule.earliestDishes_3_2(uncookedDishes);
                startTime = selectedDishes.get(0).getOrderedTime();
            }
            LocalTime finishedTime = startTime.plusMinutes(cookAllTime(selectedDishes));
            //pass expected finish time
            selectedCook.cookFood(startTime.plusMinutes(operateAllTime(selectedDishes)));

            ArrayList<Integer> orderCodes = new ArrayList<>();
            for(Dish d: selectedDishes){
                d.cooked(finishedTime); //change the cooked time
//                uncookedDishes.remove(d);
                Order order = d.getOrder();
                order.updateStatusIfAllDishCooked();
                orderCodes.add(order.getOrderCode());
            }

            for(ArrayList<Dish> sub: uncookedDishes){
                if(sub.get(0).getDishCode() == selectedDishes.get(0).getDishCode()){
                    sub.removeAll(selectedDishes);
                    if(sub.size()==0){
                        uncookedDishes.remove(sub);
                    }
                    break;
                }
            }

            System.out.println(startTime+" "+selectedCook+" start cooking "+selectedDishes.get(0)+" order:"+orderCodes+". done at "+finishedTime);
//            System.out.println(startTime+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
//            schedules.add(startTime+" "+selectedCook+" "+selectedDish.getDishCode()+" "+selectedDish.getOrder().getOrderCode());
        }
        return schedules;
    }

}