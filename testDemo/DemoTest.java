package testDemo;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import demo.core.KitchenSchedule;
import demo.core.Runner;

public class DemoTest {
	 private final static String COOK_INPUT = "src/demo/core/cook.txt";
	    // instead of inputing file, randomly generate the cook info

	    // can also be randomly generated
	    private final static String DISH_INPUT ="src/demo/core/dish.txt";

	    private final static String ORDER_INPUT = "src/demo/core/order.txt";
	
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	@BeforeEach
	public void setUp() throws Exception { }
    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
	
	// Test case 1: n = 0, cards = { }
	@Test
	public void test1() throws Exception {
		boolean result;
		String expectedresult="13:00 Joy 0 1,13:10 Tom 0 2,13:30 Joy 0 3";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
}
