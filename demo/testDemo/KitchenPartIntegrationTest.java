package demo.testDemo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import demo.core.Runner;

public class KitchenPartIntegrationTest {
	private final static String COOK_INPUT = "src/demo/core/testcase/cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private final static String DISH_INPUT ="src/demo/core/testcase/dish.txt";
    

	//TEST FOR getKitchenSchedules order 1-10
	@Test
	public void test1() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order1.txt";
		String expectedresult="14:00 Joy 1 [1],14:02 Tom 5 [2],16:00 Sandy 2 [3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test2() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order2.txt";
		String expectedresult="13:00 Joy 2 [1],13:10 Tom 1 [2],13:30 Sandy 2 [3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test3() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order3.txt";
		String expectedresult="09:01 Joy 1 [1, 1],09:02 Tom 2 [2],09:02 Sandy 1 [2],09:04 Tom 2 [3],09:06 Tom 1 [3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test4() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order4.txt";
		String expectedresult="00:00 Joy 2 [1],00:00 Tom 1 [1, 1],00:03 Sandy 2 [2],00:30 Joy 2 [3],00:30 Sandy 1 [3],00:31 Tom 1 [4]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test5() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order5.txt";
		String expectedresult="11:00 Joy 2 [1],11:01 Tom 1 [2],11:02 Sandy 2 [3],11:03 Joy 1 [4],11:04 Sandy 2 [5],11:06 Sandy 1 [6]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test6() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order6.txt";
		String expectedresult="11:00 Joy 2 [1],11:00 Tom 1 [1],11:01 Sandy 1 [2],11:02 Joy 2 [3],11:04 Joy 1 [3],11:15 Tom 2 [4]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test7() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order7.txt";
		String expectedresult="14:00 Joy 1 [1],14:02 Tom 5 [2],16:00 Sandy 2 [3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test8() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order8.txt";
		String expectedresult="16:28 Joy 5 [1],16:28 Tom 2 [1],16:28 Sandy 1 [1],16:34 Joy 2 [2, 2],16:34 Tom 1 [2],16:38 Sandy 5 [3],16:38 Joy 1 [3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test9() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order9.txt";
		String expectedresult="13:10 Joy 2 [1],13:12 Tom 1 [2],13:15 Sandy 5 [4, 6],13:15 Joy 2 [3, 5]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test10() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order10.txt";
		String expectedresult="13:10 Joy 2 [1],13:12 Tom 5 [2],13:12 Sandy 1 [2, 2],13:15 Joy 5 [3, 4, 5],13:15 Tom 2 [3, 3, 5, 6],13:18 Joy 1 [4, 6]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}    
	
	//TEST FOR getKitchenSchedules order 11-20
	@Test
	public void test11() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order11.txt";
		String expectedresult="11:00 Joy 2 [1],11:00 Tom 1 [1],11:01 Sandy 1 [2],11:02 Joy 2 [3],11:04 Joy 1 [3],11:15 Tom 2 [4]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test12() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order12.txt";
		String expectedresult="08:00 Joy 2 [1],08:10 Tom 1 [2],08:30 Sandy 5 [3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test13() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order13.txt";
		String expectedresult="21:29 Joy 5 [1, 1],21:29 Tom 2 [1],21:29 Sandy 1 [1],21:34 Joy 5 [2],21:34 Tom 2 [2, 2],21:35 Joy 5 [3],21:36 Joy 2 [3, 3],21:37 Tom 1 [2, 3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test14() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order14.txt";
		String expectedresult="13:09 Joy 5 [3],13:10 Tom 2 [1],13:12 Sandy 1 [2]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test15() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order15.txt";
		String expectedresult="17:05 Joy 5 [1, 1, 1],17:07 Tom 2 [2],17:07 Sandy 1 [2],17:19 Joy 5 [4],17:19 Tom 2 [3, 4],17:21 Sandy 5 [5]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test16() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order16.txt";
		String expectedresult="19:01 Joy 5 [1],19:30 Tom 1 [2],19:31 Sandy 5 [3],19:31 Joy 2 [3],20:01 Sandy 1 [4],20:07 Joy 5 [5],20:07 Tom 2 [5]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test17() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order17.txt";
		String expectedresult="14:00 Joy 1 [1],14:02 Tom 5 [2],16:00 Sandy 2 [3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test18() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order18.txt";
		String expectedresult="16:28 Joy 5 [1],16:28 Tom 2 [1],16:28 Sandy 1 [1],16:34 Joy 2 [2, 2],16:34 Tom 1 [2],16:38 Sandy 5 [3],16:38 Joy 1 [3]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test19() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order19.txt";
		String expectedresult="15:49 Joy 2 [1, 1],15:51 Tom 2 [2],15:51 Sandy 1 [2],15:55 Joy 5 [3],15:59 Tom 2 [4]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test20() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order20.txt";
		String expectedresult="13:10 Joy 2 [1],13:12 Tom 5 [2],13:12 Sandy 1 [2, 2],13:15 Joy 5 [3, 4, 5],13:15 Tom 2 [3, 3, 5, 6],13:18 Joy 1 [4, 6]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	
	@Test
	public void test21() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order21.txt";
		String expectedresult="12:00 Joy 1 [1],12:01 Tom 3 [2],12:02 Sandy 4 [3, 4],12:04 Sandy 4 [5, 6, 7, 8],12:08 Joy 4 [9, 10, 11]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	//selected dishes >Maximum case
	
	@Test
	public void test22() throws Exception {
		String ORDER_INPUT = "src/demo/core/testcase/order22.txt";
		String expectedresult="12:00 Joy 1 [1],12:00 Tom 3 [1],12:09 Sandy 5 [2, 2, 2, 2],12:09 Joy 5 [2, 2, 2, 2]";
		assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
	}
	//earliest Dishes>Maximum case
}
