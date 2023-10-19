import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Order {
    private int orderCode;
    private List<Dish> dishes;
    private double distance;
    private int status;

    //0: 已下单但未送出
    //1: 已下单且已经送出
    //2：已经下单且客人已收到
    //-1: 订单已取消
    public Order() {
    }

    public Order(int orderCode, ArrayList<Dish> dishes, double distance) {
        this.orderCode = orderCode;
        this.dishes = dishes;
        this.distance = distance;
        this.status = 0;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDishes(Dish dish) {
        this.dishes.add(dish);
    }

    public static ArrayList<Order> inputOrderInfo(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int orderCode=0;
        ArrayList<Order> orders = new ArrayList<Order>();
        while ((line = reader.readLine()) != null) {
            String[] splitLine = line.split(" ");
            ArrayList<Dish> dishes = new ArrayList<Dish>();
            dishes.add(new Dish(splitLine[0]));
            int distance = Integer.parseInt(splitLine[1]);
            Order order=new Order(++orderCode,dishes,distance);
            orders.add(order);
        }
        return orders;
    }
public String toString(){
        return Integer.toString(this.orderCode)+this.dishes+Double.toString(this.distance);
}
//    public void deleteDishes(Dish dish){
//        this.dishes.remove(dish);
//    }
//    public void cancelOrder(){
//        this.dishes.clear();
//    }

}
