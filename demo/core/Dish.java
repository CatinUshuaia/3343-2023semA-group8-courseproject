package demo.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Collections;
public class Dish  implements Comparable<Dish>,Cloneable{
    private String dishName;
    private int dishCode;
    private int dishProductTime;
    private CookingMethod wayToCook;
    private Order order;
    private Boolean cooked;
    private LocalTime cookedTime;
    public Dish(){
    }
    public Dish(int code, String dishName, int dishProductTime,String wayToCook){
        this.dishCode = code;
        this.dishName = dishName;
//        this.wayToCook = getWayToCook(dishName);
        this.setWayToCook(wayToCook);
        this.dishProductTime = dishProductTime;
//        this.dishCode = dishCode;
//        this.dishProductTime=dishProductTime;
        this.order = null;
    }


    public void setOrder(Order order){
        assert this.order == null;
        this.order = order;
        assert this.cooked == null;
        this.cooked = false;
    }
    public Order getOrder(){
        return this.order;
    }
    public String getDishName() { return dishName; }
    public boolean isSameDish(Dish other){
        return this.dishName == other.dishName;
    }
    public int getDishCode(){ return dishCode; }

    public boolean sameDish(Dish other){
        return this.dishCode==other.dishCode;
    }
    public void setDishCode(int dishCode){
        this.dishCode = dishCode;
    }
//    public int getDishProductTime() { return dishProductTime; }
//    public void setDishProductTime(int dishProductTime){
//        this.dishProductTime = dishProductTime;
//    }

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

    //    public CookingMethod getWayToCook() {
//        return wayToCook;
//    }
    private void setWayToCook(String wayToCook){
        if(wayToCook.equals("roast")){
            this.wayToCook = CookingMethod.ROAST;
        }else if(wayToCook.equals("fry")){
            this.wayToCook = CookingMethod.FRY;
        }
        else if(wayToCook.equals("boil")){
            this.wayToCook = CookingMethod.BOIL;
        }
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
            dishes.add(new Dish(Integer.parseInt(splitLine[0]),splitLine[1],Integer.parseInt(splitLine[2]),splitLine[3] ));
        }
        reader.close();
        return dishes;
    }


    @Override
    public int compareTo(Dish other) {

        if(this.cooked){
            return 1;
        }else if(other.cooked){
            return -1;
        }

        //above logic is ued in generateSchedule1_2
        //which can be removed later

        int comparison_wayToCook = this.wayToCook.customCompareTo(other.wayToCook);
        // int comparison_wayToCook = this.wayToCook.compareTo(other.wayToCook);
        if(comparison_wayToCook!=0){
            return comparison_wayToCook;
        }

        return this.order.getOrderTime()
                .compareTo(other.order.getOrderTime());
    }

    // Sort based on the second criteria using a lambda expression
    public static Comparator<Dish> getTimeComparator() {
        return Comparator.comparing(Dish::getOrderedTime);
    }

    public int getDishProductTime(){
        return this.dishProductTime;
    }

    public int getOccupiedTime(){
        int operationTime = this.wayToCook.getOperationTime();
        if(operationTime==-1) {
            return this.dishProductTime;
        }else{
            return operationTime;
        }
    }

    //    public static void testSort(){
//        ArrayList<Dish> dishes = new ArrayList<Dish>();
//        dishes.add(new Dish("roastedDuck", 10));
//        dishes.add(new Dish("friedMeatWithChili", 20));
//        Collections.sort(dishes);
//        System.out.println(dishes);
//    }
    @Override
    public Dish clone() throws CloneNotSupportedException{
        assert this.order == null;
        assert this.cooked == null;
        //we only need to clone the object when it is not in any order.
        //so shallow clone is adopted
        return (Dish)super.clone();
    }
    public boolean getIsCooked(){
        return this.cooked;
    }
    public void initializeIsCooked(){
        this.cooked = false;
    }

    public void cooked(LocalTime expectedFinishedTime){
//        this.order.updateStatusIfAllDishCooked();
        this.cooked = true;
        this.cookedTime = expectedFinishedTime;
    }

    public LocalTime getCookedTime(){
        return this.cookedTime;
    }

    public LocalTime getOrderedTime(){
        return this.order.getOrderTime();
    }
    public boolean isNOTEarlierThan(LocalTime time){
        return this.getOrderedTime().compareTo(time)<1;
    }

    public boolean isOrderedSameTimeWith(Dish other){
        return this.getOrderedTime().compareTo(other.getOrderedTime())==0;
    }
}
