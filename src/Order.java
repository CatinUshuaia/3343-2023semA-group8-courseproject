import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Order {
    private int orderCode;
    private ArrayList<Dish> dishes;
    private double distance;
    private int status;
    //0: 已下单但未送出
    //1: 所有菜品已做好，未送出
    //2：外卖员已经送出，客人未收到
    //3：客人已收到
    //-1: 订单已取消

    private LocalTime orderTime;
    public Order() {
    }

    public Order(int orderCode, ArrayList<Dish> dishes, double distance, String time) {
        this.orderCode = orderCode;
        this.dishes = dishes;
        this.distance = distance;
        this.status = 0;
        this.orderTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

//    public void setDishes(ArrayList<Dish> dishes) {
//        this.dishes = dishes;
//    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

//    public void addDishes(Dish dish) {
//        this.dishes.add(dish);
//    }


    public static ArrayList<Order> inputOrderInfo(String filePath, ArrayList<Dish> allDishes) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int orderCode=0;
        ArrayList<Order> orders = new ArrayList<Order>();
        while ((line = reader.readLine()) != null) {
            String[] splitLine = line.split(" ");
            String timeStr = splitLine[0];
            ArrayList<Dish> dishes = new ArrayList<Dish>();
            String dishesStr = splitLine[1];
            String[] splitDishes = dishesStr.split(",");
            for (String dishName : splitDishes) {
                for (Dish dish : allDishes) {
                    if (dishName.equals(dish.getDishName())) {
                        dishes.add(dish);
                    }
                }
            }
            int distance = Integer.parseInt(splitLine[2]);
            Order order=new Order(++orderCode,dishes,distance,timeStr);
            orders.add(order);
        }
        return orders;
    }
    @Override
    public String toString(){
             return this.orderTime+ " " + this.dishes + " ";
    }
//    public void deleteDishes(Dish dish){
//        this.dishes.remove(dish);
//    }
//    public void cancelOrder(){
//        this.dishes.clear();
//    }

    public void allDishedCooked(){
        this.status = 1;
    }

}
