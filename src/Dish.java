import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Collections;
public class Dish  implements Comparable<Dish>{
    private String dishName;
    private int dishCode;
    private int dishProductTime; //count by second
    private CookingMethod wayToCook;
    public Dish(){
    }
    public Dish(String dishName, int dishProductTime){
        this.dishName = dishName;
        this.wayToCook = getWayToCook(dishName);
        this.dishProductTime = dishProductTime;
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

//    private void setWayToCook() {
//        if(Objects.equals(this.dishName, "roastDuck")){
//            this.wayToCook="roast";
//        }else if(Objects.equals(this.dishName, "friedMeatWithChili")){
//            this.wayToCook="fry";
//        }
//    }

    private static CookingMethod getWayToCook(String dishName) {
        if(dishName.equals("roastedDuck")){
            return CookingMethod.ROAST;
        }else if(dishName.equals("friedMeatWithChili")){
            return CookingMethod.FRY;
        }
        return CookingMethod.FRY;
    }

    public CookingMethod getWayToCook() {
        return wayToCook;
    }
    @Override
    public String toString(){
        return dishName;
    }

    public static ArrayList<Dish> inputDishInfo(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        while ((line = reader.readLine())!=null){
            String[] splitLine = line.split(" ");
            dishes.add(new Dish(splitLine[0],Integer.parseInt(splitLine[1]) ));
        }
        return dishes;
    }

    @Override
    public int compareTo(Dish other) {
//        return 1;
//        return this.wayToCook.getPriority() - other.wayToCook.getPriority();
        return this.wayToCook.compareTo(other.wayToCook);
    }

    public static void testSort(){
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        dishes.add(new Dish("roastedDuck", 10));
        dishes.add(new Dish("friedMeatWithChili", 20));
        Collections.sort(dishes);
        System.out.println(dishes);
    }

}
