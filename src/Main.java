import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    private static String COOK_INPUT = "cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private static String DISH_INPUT ="dish.txt";

    private static String ORDER_INPUT = "order.txt";

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        int totalOrder=0;

        ArrayList<Dish> dishes = Dish.inputDishInfo("src/dish.txt");
        ArrayList<Cook> cooks = Cook.inputCookInfo("src/cook.txt");
//        for(Cook c: cooks){
//            System.out.println(c.getInfo());
//        }
        ArrayList<Order> order = Order.inputOrderInfo("src/order.txt",dishes);
//        System.out.println(order);

        ArrayList<String> schedule = KitchenSchedule.generateSchedule1_3(order,cooks);
        for(String s: schedule){
            System.out.println(s);
        }

    }

}


