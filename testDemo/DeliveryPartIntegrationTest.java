package demo.testDemo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import demo.core.Runner;

public class DeliveryPartIntegrationTest {
	 private final static String COOK_INPUT = "src/demo/core/testcase/cook.txt";

	    private final static String DISH_INPUT ="src/demo/core/testcase/dish.txt";
	    
	    private final static String DELIVERER_INPUT ="src/demo/core/testcase/deliverers.xml";
	
	    //Test for Delivery part order 1-20
		@Test
		public void test21() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order11.txt";
			String expectedresult="delivery:[[FriedMeatWithChili] , [FriedMeatWithChili, RoastedDuck] , [RoastedDuck, FriedMeatWithChili] ] deliverer: Alice scheduled deliver time: 11:16delivery:[[RoastedDuck] ] deliverer: Bob scheduled deliver time: 11:29";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test22() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order12.txt";
			String expectedresult="delivery:[[RoastedDuck] , [FriedMeatWithChili] ] deliverer: Alice scheduled deliver time: 08:18delivery:[[PorkwithCornDumpling] ] deliverer: Bob scheduled deliver time: 08:38";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test23() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order13.txt";
			String expectedresult="delivery:[[PorkwithCornDumpling, PorkwithCornDumpling, FriedMeatWithChili, RoastedDuck] , [FriedMeatWithChili, RoastedDuck, PorkwithCornDumpling, RoastedDuck] , [RoastedDuck, FriedMeatWithChili, PorkwithCornDumpling, RoastedDuck] ] deliverer: Alice scheduled deliver time: 21:51";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test24() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order14.txt";
			String expectedresult="delivery:[[PorkwithCornDumpling] , [FriedMeatWithChili] , [RoastedDuck] ] deliverer: Alice scheduled deliver time: 13:24";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test25() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order15.txt";
			String expectedresult="delivery:[[PorkwithCornDumpling, PorkwithCornDumpling, PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 17:15delivery:[[RoastedDuck, FriedMeatWithChili] ] deliverer: Bob scheduled deliver time: 17:21delivery:[[PorkwithCornDumpling] ] deliverer: Kitty scheduled deliver time: 17:29delivery:[[RoastedDuck] ] deliverer: Alice scheduled deliver time: 17:34delivery:[[RoastedDuck, PorkwithCornDumpling] ] deliverer: Bob scheduled deliver time: 17:34";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test26() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order16.txt";
			String expectedresult="delivery:[[PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 19:09delivery:[[FriedMeatWithChili] , [RoastedDuck, PorkwithCornDumpling] ] deliverer: Bob scheduled deliver time: 19:45delivery:[[FriedMeatWithChili] ] deliverer: Kitty scheduled deliver time: 20:09delivery:[[PorkwithCornDumpling, RoastedDuck] ] deliverer: Alice scheduled deliver time: 20:21";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test27() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order17.txt";
			String expectedresult="delivery:[[FriedMeatWithChili] , [PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 14:10delivery:[[RoastedDuck] ] deliverer: Bob scheduled deliver time: 16:14";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test28() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order18.txt";
			String expectedresult="delivery:[[PorkwithCornDumpling, RoastedDuck, FriedMeatWithChili] , [PorkwithCornDumpling, FriedMeatWithChili] , [FriedMeatWithChili, RoastedDuck, RoastedDuck] ] deliverer: Alice scheduled deliver time: 16:49";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test29() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order19.txt";
			String expectedresult="delivery:[[PorkwithCornDumpling] , [RoastedDuck, RoastedDuck] , [FriedMeatWithChili, RoastedDuck] ] deliverer: Alice scheduled deliver time: 16:05delivery:[[RoastedDuck] ] deliverer: Bob scheduled deliver time: 16:13";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		
		@Test
		public void test30() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order20.txt";
			String expectedresult="delivery:[[FriedMeatWithChili, FriedMeatWithChili, PorkwithCornDumpling] , [RoastedDuck] , [FriedMeatWithChili, PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 13:27delivery:[[RoastedDuck, PorkwithCornDumpling, RoastedDuck] , [RoastedDuck, PorkwithCornDumpling] , [FriedMeatWithChili, RoastedDuck] ] deliverer: Bob scheduled deliver time: 13:32";
			assertEquals(expectedresult, Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		@Test
		public void test31() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order1.txt";
			String expectedresult="delivery:[[FriedMeatWithChili] , [PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 14:10delivery:[[RoastedDuck] ] deliverer: Bob scheduled deliver time: 16:14";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		//same table with different time
		@Test
		public void test32() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order2.txt";
			String expectedresult="delivery:[[RoastedDuck] ] deliverer: Alice scheduled deliver time: 13:14delivery:[[FriedMeatWithChili] ] deliverer: Bob scheduled deliver time: 13:18delivery:[[RoastedDuck] ] deliverer: Kitty scheduled deliver time: 13:44";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		//ONE EXTREM VALUE
		@Test
		public void test33() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order3.txt";
			String expectedresult="delivery:[[FriedMeatWithChili, FriedMeatWithChili] , [FriedMeatWithChili, RoastedDuck] , [RoastedDuck, FriedMeatWithChili] ] deliverer: Alice scheduled deliver time: 09:18";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		//ALL WITH RANDOM VALUE
		@Test
		public void test34() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order4.txt";
			String expectedresult="delivery:[[FriedMeatWithChili, FriedMeatWithChili, RoastedDuck] , [RoastedDuck] ] deliverer: Alice scheduled deliver time: 00:17delivery:[[FriedMeatWithChili] , [RoastedDuck, FriedMeatWithChili] ] deliverer: Bob scheduled deliver time: 00:44";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		// a small square
		@Test
		public void test35() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order5.txt";
			String expectedresult="delivery:[[FriedMeatWithChili] , [RoastedDuck] , [RoastedDuck] ] deliverer: Alice scheduled deliver time: 11:16delivery:[[FriedMeatWithChili] , [FriedMeatWithChili] , [RoastedDuck] ] deliverer: Bob scheduled deliver time: 11:18";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		// 3-3 cluster
		@Test
		public void test36() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order6.txt";
			String expectedresult="delivery:[[FriedMeatWithChili] ] deliverer: Alice scheduled deliver time: 11:09delivery:[[FriedMeatWithChili, RoastedDuck] ] deliverer: Bob scheduled deliver time: 11:14delivery:[[RoastedDuck, FriedMeatWithChili] ] deliverer: Kitty scheduled deliver time: 11:16delivery:[[RoastedDuck] ] deliverer: Alice scheduled deliver time: 11:29";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		//a big square
		@Test
		public void test37() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order7.txt";
			String expectedresult="delivery:[[FriedMeatWithChili] , [PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 14:10delivery:[[RoastedDuck] ] deliverer: Bob scheduled deliver time: 16:14";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		//in one line
		@Test
		public void test38() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order8.txt";
			String expectedresult="delivery:[[PorkwithCornDumpling, RoastedDuck, FriedMeatWithChili] , [PorkwithCornDumpling, FriedMeatWithChili] , [FriedMeatWithChili, RoastedDuck, RoastedDuck] ] deliverer: Alice scheduled deliver time: 16:49";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		//in one line
		@Test
		public void test39() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order9.txt";
			String expectedresult="delivery:[[FriedMeatWithChili] , [RoastedDuck] , [PorkwithCornDumpling] ] deliverer: Alice scheduled deliver time: 13:24delivery:[[PorkwithCornDumpling] , [RoastedDuck] , [RoastedDuck] ] deliverer: Bob scheduled deliver time: 13:30";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}
		// one center and one outer
		@Test
		public void test40() throws Exception {
			String ORDER_INPUT = "src/demo/core/testcase/order10.txt";
			String expectedresult="delivery:[[FriedMeatWithChili, FriedMeatWithChili, PorkwithCornDumpling] , [RoastedDuck] , [FriedMeatWithChili, RoastedDuck] ] deliverer: Alice scheduled deliver time: 13:32delivery:[[FriedMeatWithChili, PorkwithCornDumpling] , [RoastedDuck, PorkwithCornDumpling] ] deliverer: Bob scheduled deliver time: 13:32delivery:[[RoastedDuck, PorkwithCornDumpling, RoastedDuck] ] deliverer: Kitty scheduled deliver time: 13:32";
			assertEquals(expectedresult,  Runner.getDelivery(COOK_INPUT,DISH_INPUT, DELIVERER_INPUT, ORDER_INPUT));
		}    
		//all huge numbers
}
