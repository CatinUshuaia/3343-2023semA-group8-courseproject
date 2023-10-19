import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderCode;
    private List<Dish> dishes;
    private double distance;
    private int status;
    //0: 已下单但未送出
    //1: 已下单且已经送出
    //2：已经下单且客人已收到
    //-1: 订单已取消
    public Order(){
    }

    public Order(int orderCode, List<Dish> dishes, double distance){
        this.orderCode = orderCode;
        this.dishes = dishes;
        this.distance = distance;
        this.status = 0;
    }

    public int getOrderCode() { return orderCode; }
    public void setOrderCode(int orderCode){
        this.orderCode = orderCode;
    }
    public List<Dish> getDishes() { return dishes; }
    public void setDishes(List<Dish> dishes){
        this.dishes = dishes;
    }
    public void addDishes(Dish dish){
        this.dishes.add(dish);
    }
//    public void deleteDishes(Dish dish){
//        this.dishes.remove(dish);
//    }
//    public void cancelOrder(){
//        this.dishes.clear();
//    }
}
