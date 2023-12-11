package com.cs3343.demo.core;


import org.springframework.boot.CommandLineRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@ShellComponent
public class Runner implements CommandLineRunner {

    private final static String COOK_INPUT = "src/main/java/com/cs3343/demo/core/cook.txt";

    private final static String DISH_INPUT = "src/main/java/com/cs3343/demo/core/dish.txt";

    private final static String ORDER_INPUT = "src/main/java/com/cs3343/demo/core/order.txt";
    private final static String DELIVERER_INPUT = "src/main/java/com/cs3343/demo/core/deliverers.xml";

    private final ArrayList<Dish> dishes = Dish.inputDishInfo(DISH_INPUT);
    private final ArrayList<Cook> cooks = Cook.inputInfo(COOK_INPUT);
    private final ArrayList<Deliverer> deliverers = Deliverer.inputDelivererInfo(DELIVERER_INPUT);
    private final ArrayList<Order> orders = new ArrayList<>();
    private final ArrayList<Delivery> ds = new ArrayList<>();


    public Runner() throws IOException {
        System.out.println("Welcome to the restaurant management system!");

        System.out.println("This programme has started successfully, please enter 'help' for more information.");
    }

    private static boolean isValidFormat(String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    @ShellMethod(value = "Run main", key = "run")
    @Override
    public void run(
            @ShellOption(defaultValue = "1") String... args
    ) throws Exception {
    }

    @ShellMethod(value =
"""
    Usage: new-order [Options]
    
    Description: Order dishes
    
    Options:
        -t, --time          Assign order time, formatting with "HH:mm"
        -d, --dish          Assign order dish(es), separate dishes by ","
        -x, --xLocation     Assign x-coordinate of delivery location
        -y, --yLocation     Assign y-coordinate of delivery location
        
    Example:
        new-order -t 15:49 -d RoastedDuck,RoastedDuck -x 5 -y 4
            - An order was placed at 15:49, where RoastedDuck and RoastedDuck were ordered, and the delivery location was at (5, 4).
"""
            , key = "new-order")
    public void newOrder(
            @ShellOption(value = {"-t","--time"}) String timeStr,
            @ShellOption(value = {"-d", "--dish"}) String dishStr,
            @ShellOption(value = {"-x", "--xLocation"}) int xLocation,
            @ShellOption(value = {"-y", "--yLocation"}) int yLocation
    ) throws Exception{

        ArrayList<Order> ordersInList;
        ArrayList<String> dishList = new ArrayList<>(Arrays.asList(dishStr.split(",")));

        int maximumOrders = 5;
        if (dishList.size() > maximumOrders){
            throw new ExceptionExceedMaximum();
        }
        if(!isValidFormat(timeStr)){
            throw new ExceptionInvalidDate();
        }

        DeliveryAssignmentManager NewAssignmentManager = new DeliveryAssignmentManager();

        Order newAddedOrder = Order.newOrder(orders.size()+1, timeStr, dishes, dishStr, xLocation, yLocation);

        orders.add(newAddedOrder);
        KitchenSchedule.generateSchedule(orders, cooks);
        System.out.println("=====================================");
        for(Order o: orders){
            System.out.println("order "+o.getOrderCode()+" is ordered at "+o.getOrderTime()+", is finished cooking at "+o.getCookedTime()+". ");
        }

        var currentTime = newAddedOrder.getOrderTime();

        for(var d :ds) {
            if(currentTime.isAfter(d.getDeliverTime())) {
                NewAssignmentManager.deliverInDeed(d);
            }
        }

        ArrayList<Order> ordersToBeDelivered = orders.stream()
                .filter(order -> (order.getStatus()==Status.COOKED))
                .sorted(Order::compareTo)
                .collect(Collectors.toCollection(ArrayList::new));


            while(!ordersToBeDelivered.isEmpty()){
                var newDelivery = NewAssignmentManager.GenerateDeliveryAssignment(ordersToBeDelivered, deliverers);
                ds.add(newDelivery);
                System.out.println(newDelivery);
                ordersToBeDelivered = ordersToBeDelivered.stream()
                        .filter(order -> order.getStatus()==Status.COOKED)
                        .sorted((o1, o2) -> o1.compareTo(o2))
                        .collect(Collectors.toCollection(ArrayList::new));

            }

        }


    @ShellMethod(value =
"""
    Usage: batch-order [Options]
    
    Description: Assign multiple orders.
    
    Options:
        -f, --filepath      Assign filepath of multiple orders
        
    Example:
        batch-order -f src/core/order.txt
            - Reads the file at that src/core/order.txt and reads the orders in it line by line.
    
    Additional Information:
        - Each oder needs to be separated by new-line key, where the format of the order please refer to the parameters of the new-order command, each parameter needs to be separated by a space, and there is no need to add the command line options and function name.
        - e.g. 15:49 RoastedDuck,RoastedDuck 5 4
"""
            , key = "batch-order")
    public void batchOrder(
            @ShellOption(value = {"-f", "--filepath"}) String filepath
    ) throws Exception {

        File orderList = new File(filepath);
        Scanner reader = new Scanner(orderList);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();

            ArrayList<String> cmd = new ArrayList<>(Arrays.asList(data.split(" ")));
            System.out.println(data);

            newOrder(cmd.get(0), cmd.get(1), Integer.parseInt(cmd.get(2)), Integer.parseInt(cmd.get(3)));
        }

    }

    @ShellMethod(value =
"""
    Usage: batch-cook [Options]
    
    Description: Assign multiple cooks.
    
    Options:
        -f, --filepath      Assign filepath of multiple cooks
        
    Example:
        batch-order -f src/core/cooks.txt
            - Reads the file at that src/core/cooks.txt and reads the cook information in it line by line.
    
    Additional Information:
        - Each cook needs to be separated by new-line key, where the format of the cook is {cook-name: String}, each parameter needs to be separated by a space, and there is no need to add the command line options and function name.
        - e.g. Joy Hunan
"""
            , key = "batch-cook")
    public void batchCook(
            @ShellOption(value = {"-f", "--filepath"}) String filepath
    )throws Exception{
        cooks.addAll(Cook.inputInfo(filepath));
    }

    @ShellMethod(value =
"""
    Usage: batch-dish [Options]
    
    Description: Assign multiple dishes.
    
    Options:
        -f, --filepath      Assign filepath of multiple dishes
        
    Example:
        batch-order -f src/core/dishes.txt
            - Reads the file at that src/core/dishes.txt and reads the dish information in it line by line.
    
    Additional Information:
        - Each dishes needs to be separated by new-line key, where the format of the dishes is {dish-id: Integer, dish-name: String, dish-product-time: Integer, way-to-cook: String}, each parameter needs to be separated by a space, and there is no need to add the command line options and function name.
        - [way-to-cook]: boil, roast, steam (take longest time), fry (take longest time), stew
        - e.g. 1 FriedMeatWithChili 8 fry
"""
            , key = "batch-dish")
    public void batchDish(
            @ShellOption(value = {"-f", "--filepath"}) String filepath
    )throws Exception{
        dishes.addAll(Dish.inputDishInfo(filepath));
    }

    @ShellMethod(value =
"""
    Usage: batch-deliverer [Options]
    
    Description: Assign multiple deliverers.
    
    Options:
        -f, --filepath      Assign filepath of multiple dishes
        
    Example:
        batch-order -f src/core/deliverers.txt
            - Reads the file at that src/core/deliverers.txt and reads the deliverer information in it line by line.
    
    Additional Information:
        - Each deliverers needs to be separated by new-line key, where the format of the deliverers is  {deliverer-name: String}, each parameter needs to be separated by a space, and there is no need to add the command line options and function name.
        - e.g. Alice
"""
            , key = "batch-deliverer")
    public void batchDeliverer(
            @ShellOption(value = {"-f", "--filepath"}) String filepath
    )throws Exception{
        deliverers.addAll(Deliverer.inputDelivererInfo(filepath));
    }

    @ShellMethod(value =
"""
    Usage: print-cook
    
    Description: Print all the exist cooks
"""
            , key = "print-cook")
    public void printAllCook() throws Exception{
        for(Cook c: cooks){
            System.out.println(c.toString());
        }
    }
    @ShellMethod(value =
"""
    Usage: print-dish
    
    Description: Print all the exist dishes
"""
            , key = "print-dish")
    public void printAllDish() throws Exception{
        for(Dish d: dishes){
            System.out.println(d.toString());
        }
    }
    @ShellMethod(value =
"""
    Usage: print-deliverer
    
    Description: Print all the exist deliverers
"""
            , key = "print-deliverer")
    public void printAllDeliverer() throws Exception{
        for (Deliverer d: deliverers){
            System.out.println(d.toString());
        }
    }


}
