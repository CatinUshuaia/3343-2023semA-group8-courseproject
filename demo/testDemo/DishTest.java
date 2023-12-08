package demo.testDemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import demo.core.CookingMethod;
import demo.core.Dish;
import demo.core.Location;
import demo.core.Order;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class DishTest {
    private Dish dish;

    @BeforeEach
    public void setUp() {
        dish = new Dish(1, "Roasted Duck", 20, "roast");
    }

    @Test
    public void testSetAndGetOrder() {
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        Location location = new Location(1,1);
        String time = "10:30";

        Order order = new Order(1, dishes, location, time);
        dish.setOrder(order);
        assertEquals(order, dish.getOrder());
    }

    @Test
    public void testGetDishName() {
        assertEquals("Roasted Duck", dish.getDishName());
    }

    @Test
    public void testGetDishCode() {
        assertEquals(1, dish.getDishCode());
    }

    @Test
    public void testSameDish() {
        Dish otherDish = new Dish(1, " MapoTofu", 20, "stew");
        assertTrue(dish.sameDish(otherDish));
    }

    @Test
    public void testSetWayToCook() {
    	dish.setWayToCook("boil");
        assertEquals(CookingMethod.BOIL, dish.getWayToCook());
    }

    @Test
    public void testToString() {
        assertEquals("Roasted Duck", dish.toString());
    }

    @Test
    public void testInputDishInfo() {
        try {
            ArrayList<Dish> dishes = Dish.inputDishInfo("src/demo/core/testcase/dish.txt");
            assertEquals(11, dishes.size());
            // Perform additional assertions on the dishes if needed
        } catch (IOException e) {
            fail("Failed to read dish info file: " + e.getMessage());
        }
    }

    @Test
    public void testCompareTo() {
        ArrayList<Dish> dishes = new ArrayList<>();

        Dish dish1 = new Dish(1, "Roasted Duck", 20, "roast");
        Dish dish2 = new Dish(2, "PorkwithCornDumpling", 30, "boil");

        dishes.add(dish1);
        dishes.add(dish2);

        Location location = new Location(1, 1);
        String time = "10:30";

        Order order1 = new Order(1, dishes, location, time);
        Order order2 = new Order(2, dishes, location, time);

        dish1.setOrder(order1);
        dish2.setOrder(order2);
        
        assertEquals(dish1.compareTo(dish2) , 1);
        assertEquals(dish2.compareTo(dish1) , -1);
        
        dish1.setOrder(order1);
        dish2.setOrder(order1);
        assertEquals(dish1.compareTo(dish2) , 1);
    }

    @Test
    public void testGetDishProductTime() {
        assertEquals(20, dish.getDishProductTime());
    }

    @Test
    public void testGetOccupiedTime() {
        dish.setWayToCook("roast");
        assertEquals(2, dish.getOccupiedTime());

        dish.setWayToCook("boil");
        assertEquals(1, dish.getOccupiedTime());
    }
    

    @Test
    public void testClone() {
        try {
            Dish clonedDish = dish.clone();
            assertNotSame(dish, clonedDish);
            assertNull(clonedDish.getOrder());
        } catch (CloneNotSupportedException e) {
            fail("Failed to clone dish: " + e.getMessage());
        }
    }

    @Test
    public void testGetIsCooked() {
    	dish.initializeIsCooked();
        assertFalse(dish.getIsCooked());
    }

    @Test
    public void testInitializeIsCooked() {
        dish.initializeIsCooked();
        assertFalse(dish.getIsCooked());
    }

    @Test
    public void testCooked() {
        LocalTime expectedFinishedTime = LocalTime.now();
        dish.cooked(expectedFinishedTime);
        assertTrue(dish.getIsCooked());
        assertEquals(expectedFinishedTime, dish.getCookedTime());
    }

    @Test
    public void testGetOrderedTime() {
    	ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        Location location = new Location(1,1);
        String time = "10:30";

        Order order = new Order(1, dishes, location, time);
        dish.setOrder(order);
        assertEquals(LocalTime.of(10, 30), dish.getOrderedTime());
    }

    @Test
    public void testIsNOTEarlierThan() {
    	ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        Location location = new Location(1,1);
        String time = "10:30";

        Order order = new Order(1, dishes, location, time);
        dish.setOrder(order);
        assertFalse(dish.isNOTEarlierThan(LocalTime.of(10, 0)));
        assertTrue(dish.isNOTEarlierThan(LocalTime.of(11, 0)));
        assertTrue(dish.isNOTEarlierThan(LocalTime.of(10, 30)));
    }
}