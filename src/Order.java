import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderCode;
    private List<Dish> cuisines;

    public Order(){
    }

    public Order(int orderCode, List<Dish> cuisines){
        this.orderCode = orderCode;
        this.cuisines = cuisines;
    }

    public int getOrderCode() { return orderCode; }
    public void setOrderCode(int orderCode){
        this.orderCode = orderCode;
    }
    public List<Dish> getCuisines() { return cuisines; }
    public void setCuisines(List<Dish> cuisines){
        this.cuisines = cuisines;
    }
    public void addCuisine(Dish cuisine){
        this.cuisines.add(cuisine);
    }
    public void deleteCuisine(Dish cuisine){
        this.cuisines.remove(cuisine);
    }
}
