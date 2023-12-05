package com.cs3343.demo.core;

import java.time.LocalTime;
import java.util.*;


public class Delivery {

    private Deliverer deliverer;
    private ArrayList<Order> orders;

    private LocalTime deliverTime;

    public LocalTime getFinishTime() {
        return finishTime;
    }

    private LocalTime finishTime;


    public Delivery(Deliverer deliverer, ArrayList<Order> orders) {
        this.deliverer = deliverer;
        this.orders = orders;
        // TODO: calculate deliverTime and finishTime

        var cookedTime= orders.stream()
                    .sorted(Comparator.comparing(Order::getCookedTime).reversed())
                    .toList()
                    .get(0).getCookedTime();
        var availableTime=deliverer.getAvailableTime();
        int comparison = cookedTime.compareTo(availableTime);
        if(comparison<0){
            this.deliverTime=availableTime;
        }else{
            this.deliverTime=cookedTime;
        }
        this.UpdateStatus2InDelivering();

    }
    public Deliverer getDeliverer() {
        return deliverer;
    }
    public LocalTime getDeliverTime() {
        return deliverTime;
    }
    public void SetFinishTime(double distance) {
        double orderOperationTime = distance * 2;
        this.finishTime = this.deliverTime.plusMinutes((int)orderOperationTime);
    }
    public void UpdateStatus2InDelivering(){
        for(Order order:this.orders){
            order.UpdateStatus2InDelivering();
        }
    }
    public void UpdateStatus3Delivered(){
        for(Order order:this.orders){
            order.UpdateStatus3Delivered();
        }
    }
    @Override
    public String toString() {
        return "delivery:"+this.orders.toString()+" deliverer: "+this.deliverer.toString()+" scheduled deliver time: "+this.deliverTime.toString();
    }

}
