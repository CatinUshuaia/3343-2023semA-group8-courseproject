package demo.testDemo;
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
	    
	    private final static String DELIVERER_INPUT ="src/demo/core/deliverers.xml";
	
		//TEST FOR getKitchenSchedules order 1-10
		@Test
		public void test1() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order1.txt";
			String expectedresult="14:00 Joy 1 [1],14:02 Tom 5 [2],16:00 Tom 2 [3]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test2() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order2.txt";
			String expectedresult="13:00 Joy 2 [1],13:10 Tom 1 [2],13:30 Joy 2 [3]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test3() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order3.txt";
			String expectedresult="09:00 Joy 2 [1, 1],09:01 Tom 1 [2, 2],09:03 Tom 2 [4],09:03 Joy 1 [4]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test4() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order4.txt";
			String expectedresult="00:00 Joy 2 [1],00:00 Tom 1 [1, 1],00:03 Joy 2 [2],00:30 Joy 2 [3],00:32 Joy 1 [3, 4]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test5() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order5.txt";
			String expectedresult="11:00 Joy 2 [1],11:01 Tom 1 [2],11:02 Joy 2 [3],11:04 Joy 2 [5],11:06 Joy 1 [4, 6]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test6() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order6.txt";
			String expectedresult="11:00 Joy 2 [1],11:00 Tom 1 [1],11:02 Joy 2 [3],11:04 Joy 1 [2, 3],11:15 Joy 2 [4]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test7() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order7.txt";
			String expectedresult="14:00 Joy 1 [1],14:02 Tom 5 [2],16:00 Tom 2 [3]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test8() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order8.txt";
			String expectedresult="16:28 Joy 5 [1],16:28 Tom 2 [1],16:29 Joy 1 [1],16:34 Tom 2 [2, 2],16:37 Tom 1 [2],18:36 Joy 5 [3],18:37 Joy 1 [3]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test9() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order9.txt";
			String expectedresult="13:10 Joy 2 [1],13:12 Tom 1 [2],13:15 Joy 5 [4, 6],13:17 Joy 2 [3, 5]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test10() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order10.txt";
			String expectedresult="13:10 Joy 2 [1],13:12 Tom 5 [2],13:12 Joy 1 [2, 2],13:15 Joy 5 [3, 4, 5],13:15 Tom 2 [3, 3, 5, 6],13:18 Joy 1 [4, 6]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}    
	    
		//TEST FOR getKitchenSchedules order 11-20
		@Test
		public void test11() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order11.txt";
			String expectedresult="11:00 Joy 2 [1],11:00 Tom 1 [1],11:02 Joy 2 [3],11:04 Joy 1 [2, 3],11:15 Joy 2 [4]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test12() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order12.txt";
			String expectedresult="08:00 Joy 2 [1],08:10 Tom 1 [2],08:30 Joy 5 [3]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test13() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order13.txt";
			String expectedresult="21:29 Joy 5 [1, 1],21:29 Tom 2 [1],21:31 Joy 1 [1],21:34 Tom 5 [2],21:35 Tom 5 [3],21:36 Tom 2 [2, 2, 3, 3],21:41 Tom 1 [2, 3]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test14() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order14.txt";
			String expectedresult="13:10 Joy 5 [3],13:10 Tom 2 [1],13:12 Joy 1 [2]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test15() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order15.txt";
			String expectedresult="17:05 Joy 5 [1, 1, 1],17:07 Tom 2 [2],17:08 Joy 1 [2],17:19 Tom 5 [4],17:20 Tom 2 [3, 4],17:23 Tom 5 [5]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test16() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order16.txt";
			String expectedresult="19:01 Joy 5 [1],19:30 Tom 1 [2],19:31 Joy 5 [3],19:32 Joy 2 [3],20:01 Joy 1 [4],21:37 Tom 5 [5],21:38 Tom 2 [5]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test17() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order17.txt";
			String expectedresult="14:00 Joy 1 [1],14:02 Tom 5 [2],16:00 Tom 2 [3]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test18() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order18.txt";
			String expectedresult="16:28 Joy 5 [1],16:28 Tom 2 [1],16:29 Joy 1 [1],16:34 Tom 2 [2, 2],16:37 Tom 1 [2],18:36 Joy 5 [3],18:37 Joy 1 [3]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test19() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order19.txt";
			String expectedresult="15:49 Joy 2 [1, 1],15:51 Tom 2 [2],15:52 Joy 1 [2],15:55 Tom 5 [3],15:59 Tom 2 [4]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test20() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order20.txt";
			String expectedresult="13:10 Joy 2 [1],13:12 Tom 5 [2],13:12 Joy 1 [2, 2],13:15 Joy 5 [3, 4, 5],13:15 Tom 2 [3, 3, 5, 6],13:18 Joy 1 [4, 6]";
			assertEquals(expectedresult, Runner.getKitchenSchedules(DISH_INPUT, COOK_INPUT, ORDER_INPUT));
		}
	    
	    //Test for Delivery part order 1-20
		@Test
		public void test21() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order11.txt";
			String expectedresult="delivery:[11:01 [FriedMeatWithChili] , 11:00 [FriedMeatWithChili, RoastedDuck] , 11:02 [RoastedDuck, FriedMeatWithChili] ] deliverer: Alice scheduled deliver time: 11:16;delivery:[11:15 [RoastedDuck] ] deliverer: Bob scheduled deliver time: 11:29";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test22() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order12.txt";
			String expectedresult="delivery:[08:00 [RoastedDuck] , 08:10 [FriedMeatWithChili] ] deliverer: Alice scheduled deliver time: 08:18;delivery:[08:30 [PorkwithCornDumpling] ] deliverer: Bob scheduled deliver time: 08:38";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test23() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order13.txt";
			String expectedresult="delivery:[21:29 [PorkwithCornDumpling, PorkwithCornDumpling, FriedMeatWithChili, RoastedDuck] ] deliverer: Alice scheduled deliver time: 21:43;delivery:[21:34 [FriedMeatWithChili, RoastedDuck, PorkwithCornDumpling, RoastedDuck] , 21:35 [RoastedDuck, FriedMeatWithChili, PorkwithCornDumpling, RoastedDuck] ] deliverer: Bob scheduled deliver time: 21:53";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test24() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order14.txt";
			String expectedresult="delivery:[13:09 [PorkwithCornDumpling] , 13:12 [FriedMeatWithChili] , 13:10 [RoastedDuck] ] deliverer: Alice scheduled deliver time: 13:24";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test25() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order15.txt";
			String expectedresult="delivery:[17:05 [PorkwithCornDumpling, PorkwithCornDumpling, PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 17:15;delivery:[17:07 [RoastedDuck, FriedMeatWithChili] ] deliverer: Bob scheduled deliver time: 17:21;delivery:[17:21 [PorkwithCornDumpling] ] deliverer: Kitty scheduled deliver time: 17:31;delivery:[17:19 [RoastedDuck] ] deliverer: Alice scheduled deliver time: 17:35;delivery:[17:19 [RoastedDuck, PorkwithCornDumpling] ] deliverer: Bob scheduled deliver time: 17:35";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test26() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order16.txt";
			String expectedresult="delivery:[19:01 [PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 19:09;delivery:[19:30 [FriedMeatWithChili] , 19:31 [RoastedDuck, PorkwithCornDumpling] ] deliverer: Bob scheduled deliver time: 19:46;delivery:[20:01 [FriedMeatWithChili] ] deliverer: Kitty scheduled deliver time: 20:09;delivery:[20:07 [PorkwithCornDumpling, RoastedDuck] ] deliverer: Alice scheduled deliver time: 21:52";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test27() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order17.txt";
			String expectedresult="delivery:[14:00 [FriedMeatWithChili] , 14:02 [PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 14:10;delivery:[16:00 [RoastedDuck] ] deliverer: Bob scheduled deliver time: 16:14";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test28() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order18.txt";
			String expectedresult="delivery:[16:28 [PorkwithCornDumpling, RoastedDuck, FriedMeatWithChili] , 16:34 [FriedMeatWithChili, RoastedDuck, RoastedDuck] ] deliverer: Alice scheduled deliver time: 16:49;delivery:[16:38 [PorkwithCornDumpling, FriedMeatWithChili] ] deliverer: Bob scheduled deliver time: 18:45";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test29() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order19.txt";
			String expectedresult="delivery:[15:55 [PorkwithCornDumpling] , 15:49 [RoastedDuck, RoastedDuck] , 15:51 [FriedMeatWithChili, RoastedDuck] ] deliverer: Alice scheduled deliver time: 16:05;delivery:[15:59 [RoastedDuck] ] deliverer: Bob scheduled deliver time: 16:13";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test30() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order20.txt";
			String expectedresult="delivery:[13:12 [FriedMeatWithChili, FriedMeatWithChili, PorkwithCornDumpling] , 13:10 [RoastedDuck] , 13:15 [FriedMeatWithChili, PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 13:27;delivery:[13:15 [RoastedDuck, PorkwithCornDumpling, RoastedDuck] , 13:15 [RoastedDuck, PorkwithCornDumpling] , 13:15 [FriedMeatWithChili, RoastedDuck] ] deliverer: Bob scheduled deliver time: 13:32";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
}
