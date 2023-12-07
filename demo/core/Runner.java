package demo.core;


import java.util.ArrayList;
import java.util.stream.Collectors;


//@Scope(SCOPE_PROTOTYPE)

public class Runner {

    public static String getKitchenSchedules(String dish_path, String cook_path, String order_path) throws Exception {

        ArrayList<Dish> dishes = Dish.inputDishInfo(dish_path);
        ArrayList<Cook> cooks = Cook.inputCookInfo(cook_path);
        ArrayList<Order> orders = Order.inputOrderInfo(order_path, dishes);
        String delimiter = ",";
        String result = String.join(delimiter, KitchenSchedule.generateSchedule(orders, cooks));
        return result;
    }
    
    
    public static String getDelivery(String cook_path,String dish_path, String deliverer_path, String order_path) throws Exception {
    	
    	ArrayList<Cook> cooks = Cook.inputCookInfo(cook_path);
   	 	ArrayList<Dish> dishes = Dish.inputDishInfo(dish_path);
        ArrayList<Deliverer> deliverers = Deliverer.inputDelivererInfo(deliverer_path);
        ArrayList<Order> orders = Order.inputOrderInfo(order_path, dishes);
        DeliveryAssignmentManager NewAssignmentManager = new DeliveryAssignmentManager();
        ArrayList<Delivery> ds = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        
        KitchenSchedule.generateSchedule(orders, cooks);
           
        ArrayList<Order> ordersToBeDelivered = orders.stream()
                .sorted((o1, o2) -> o1.compareTo(o2))
                .collect(Collectors.toCollection(ArrayList::new));
        
        while(!ordersToBeDelivered.isEmpty()){
            var newDelivery = NewAssignmentManager.GenerateDeliveryAssignment(ordersToBeDelivered, deliverers);
            ds.add(newDelivery);
            ordersToBeDelivered = ordersToBeDelivered.stream()
                    .filter(order -> order.getStatus()==1)
                    .sorted((o1, o2) -> o1.compareTo(o2))
                    .collect(Collectors.toCollection(ArrayList::new));
            result.append(newDelivery.toString());
        }
           
        return result.toString();
   }
}