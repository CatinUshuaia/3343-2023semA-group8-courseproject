package demo.testDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import demo.core.Dish;
import demo.core.Location;
import demo.core.Order;

public class OrderTest {

    private Order order;
    private ArrayList<Dish> dishes;

    @BeforeEach
    public void setup() {
        int orderCode = 123;
        dishes = new ArrayList<>();
        Location location = new Location(1, 2);
        String time = "12:34";
        order = new Order(orderCode, dishes, location, time);
    }

    @Test
    public void testGetOrderCode() {
        int actualOrderCode = order.getOrderCode();
        assertEquals(123, actualOrderCode);
    }

    @Test
    public void testGetDishes() {
        ArrayList<Dish> actualDishes = order.getDishes();
        assertEquals(dishes, actualDishes);
    }

    @Test
    public void testGetOrderTime() {
        LocalTime orderTime = LocalTime.parse("12:34");
        assertEquals(orderTime, order.getOrderTime());
    }

    @Test
    public void testGetCookedTime() {
        LocalTime cookedTime = LocalTime.parse("12:45");
        order.setCookedTime(cookedTime);
        assertEquals(cookedTime, order.getCookedTime());
    }

    @Test
    public void testUpdateStatusIfAllDishCooked_AllCooked() {
        Dish dish1 = new Dish(1, "Roasted Duck", 20, "roast");
        Dish dish2 = new Dish(2, "Pork with Corn Dumpling", 30, "boil");

        dishes.add(dish1);
        dishes.add(dish2);

        for (Dish dish : dishes) {
            dish.setIsCooked(true);
            dish.setCookedTime(LocalTime.parse("12:45"));
        }

        order.updateStatusIfAllDishCooked();

        assertEquals(1, order.getStatus());
        assertEquals(LocalTime.parse("12:45"), order.getCookedTime());
    }

    @Test
    public void testUpdateStatusIfAllDishCooked_NotAllCooked() {
        Dish dish1 = new Dish(1, "Roasted Duck", 20, "roast");
        Dish dish2 = new Dish(2, "Pork with Corn Dumpling", 30, "boil");

        dishes.add(dish1);
        dishes.add(dish2);

        dish1.setIsCooked(true);
        dish2.setIsCooked(false);
        dish1.setCookedTime(LocalTime.parse("12:45"));
        dish2.setCookedTime(LocalTime.parse("12:45"));

        order.updateStatusIfAllDishCooked();

        assertEquals(0, order.getStatus());
        assertEquals(null, order.getCookedTime());
    }
    
    //用於 cover if(dishStr.equals(dish.getDishCode()+"")這個分支
    @Test
    public void testNewOrderWithDishCodeMatch() throws IOException, CloneNotSupportedException {
        Dish dish1 = new Dish(1, "Roasted Duck", 20, "roast");
        Dish dish2 = new Dish(2, "Pork with Corn Dumpling", 30, "boil");
        ArrayList<Dish> allDishes = new ArrayList<>();
        allDishes.add(dish1);
        allDishes.add(dish2);

        String line = "12:34 1,2 3 4";
        Order newOrder = Order.newOrder(123, line, allDishes);

        assertEquals(123, newOrder.getOrderCode());
        assertEquals(2, newOrder.getDishes().size());
        assertEquals(dish1.getDishName(), newOrder.getDishes().get(0).toString());
        assertEquals(dish2.getDishName(), newOrder.getDishes().get(1).toString());
    }


}