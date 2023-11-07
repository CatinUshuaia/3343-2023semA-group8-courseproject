package demo.core;

import demo.core.Cook;

import java.util.ArrayList;
import java.util.List;


//@Scope(SCOPE_PROTOTYPE)

public class Runner {
    private final static String COOK_INPUT = "src/demo/core/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT ="src/demo/core/dish.txt";

    private final static String ORDER_INPUT = "src/demo/core/order.txt";


    public static String getKitchenSchedules(String dish_path, String cook_path, String order_path) throws Exception {

        ArrayList<Dish> dishes = Dish.inputDishInfo(dish_path);
        ArrayList<Cook> cooks = Cook.inputCookInfo(cook_path);
        ArrayList<Order> orders = Order.inputOrderInfo(order_path, dishes);
        String delimiter = ",";
        String result = String.join(delimiter, KitchenSchedule.generateSchedule1_3(orders, cooks));
        return result;
    }
}