package com.cs3343.demo.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Order implements Comparable<Order> {
    private int orderCode;
    private ArrayList<Dish> dishes;
    private double distance;

    private Location location;

    private int status;
    //0: 已下单
    //1: 所有菜品已做好，未送出
    //2：外卖员已经送出，客人未收到
    //3：客人已收到
    //-1: 订单已取消
    private LocalTime orderTime;
    private LocalTime cookedTime;
    public Order() {
    }

    public Order(int orderCode, ArrayList<Dish> dishes, Location location, String time) {
        this.orderCode = orderCode;
        this.dishes = dishes;
        this.location = location;
        this.status = 0;
        this.orderTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        this.dishes.forEach(d -> {
            d.setOrder(this);
            d.initializeIsCooked();
        });
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

    public static Order newOrder(int orderCode, String line, ArrayList<Dish> allDishes) throws IOException, CloneNotSupportedException {
        String[] splitLine = line.split(" ");
            String timeStr = splitLine[0];
            ArrayList<Dish> dishes = new ArrayList<Dish>();
            String dishesStr = splitLine[1];
            String[] splitDishes = dishesStr.split(",");
            for (String dishStr : splitDishes) {
                for (Dish dish : allDishes) {
                    if(dishStr.equals(dish.getDishCode()+"")
                            || dishStr.equals(dish.getDishName())){
                        dishes.add(dish.clone());
                    }
                }
            }
            int xCoordinate = Integer.parseInt(splitLine[2]);
            int yCoordinate = Integer.parseInt(splitLine[3]);
            return new Order(orderCode,dishes,new Location(xCoordinate,yCoordinate),timeStr);
    }


    public static ArrayList<Order> inputOrderInfo(String filePath, ArrayList<Dish> allDishes) throws IOException, CloneNotSupportedException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int orderCode=0;
        ArrayList<Order> orders = new ArrayList<Order>();
        while ((line = reader.readLine()) != null) {
            orderCode += 1;
            orders.add(newOrder(orderCode,line,allDishes));
        }
        reader.close();
        return orders;
    }

    @Override
    public String toString(){
        return this.dishes + " ";
    }

    public void updateStatusIfAllDishCooked(){
        boolean allCooked = true;
        LocalTime latestTime = null;
        for (Dish dish : this.dishes) {
            if(!dish.getIsCooked()){
                allCooked = false;
            }else{
                if(latestTime == null){
                    latestTime = dish.getCookedTime();

                }else if(dish.getCookedTime().compareTo(latestTime)==1){
                    latestTime = dish.getCookedTime();
                }
            }
        }
        if(allCooked){
            this.status = 1;
            this.cookedTime = latestTime;
        }
    }

    public LocalTime getCookedTime() {
        return this.cookedTime;
    }

    public int getStatus(){
        return this.status;
    }

    public double getDistance() {
        return this.distance;
    }

    public void UpdateStatus2InDelivering(){
        this.status=2;
    }
public void UpdateStatus3Delivered(){
        this.status=3;
}
    public int compareTo(Order other) {
        return this.getCookedTime().compareTo(other.getCookedTime());
    }

    public Location getLocation() {
        return location;
    }
}


