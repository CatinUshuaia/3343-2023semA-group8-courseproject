package com.cs3343.demo.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

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
        this.setWayToCook(wayToCook);
        this.dishProductTime = dishProductTime;
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
    public int getDishCode(){ return dishCode; }

    public boolean sameDish(Dish other){
        return this.dishCode==other.dishCode;
    }

    private void setWayToCook(String wayToCook){
        this.wayToCook = CookingMethod.getWayToCook(wayToCook);
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

    public int getOccupiedTime_bug(){
        int operationTime = this.wayToCook.getOperationTime();
        if(operationTime==-1) {
            return this.dishProductTime;
        }else{
            return operationTime;
        }
    }

    public int getOccupiedTime(){
        if(this.wayToCook.noSavedTimeOperation()) {
            return this.dishProductTime;
        }else{
            return this.wayToCook.getOperationTime();
        }
    }
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