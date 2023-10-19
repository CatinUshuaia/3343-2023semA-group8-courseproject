import java.util.Objects;

public class Dish {
    private String dishName;
    private int dishCode;
    private int dishProductTime; //count by second
    private String wayToCook;
    public Dish(){
    }
    public Dish(String dishName){
        this.dishName = dishName;
//        this.dishCode = dishCode;
//        this.dishProductTime=dishProductTime;
    }

    public String getDishName() { return dishName; }
    public void setDishName(String dishName){
        this.dishName = dishName;
    }
    public int getDishCode(){ return dishCode; }
    public void setDishCode(int dishCode){
        this.dishCode = dishCode;
    }
    public int getDishProductTime() { return dishProductTime; }
    public void setDishProductTime(int dishProductTime){
        this.dishProductTime = dishProductTime;
    }

    public void setWayToCook() {
        if(Objects.equals(this.dishName, "roastDuck")){
            this.wayToCook="roast";
        }else if(Objects.equals(this.dishName, "friedMeatWithChili")){
            this.wayToCook="fry";
        }
    }
    @Override
    public String toString(){
        return dishName + Integer.toString(dishCode);
    }

}
