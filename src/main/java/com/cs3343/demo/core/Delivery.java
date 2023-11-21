package com.cs3343.demo.core;

import java.time.LocalTime;
import java.util.*;
import java.time.Duration;


public class DeliveryAssignment {

    private Deliverer deliverer;
    private ArrayList<Order> orders;

    private LocalTime deliverTime;
    private LocalTime finishTime;

    public DeliveryAssignment(Deliverer deliverer, ArrayList<Order> orders) {
        this.deliverer = deliverer;
        this.orders = orders;
        // TODO: calculate deliverTime and finishTime

        var cookedTime= orders.stream()
                    .sorted(Comparator.comparing(Order::getCookedTime))
                    .toList()
                    .get(0).getCookedTime();
        var availableTime=deliverer.getAvailableTime();
        int comparison = cookedTime.compareTo(availableTime);
        if(comparison<0){
            this.deliverTime=availableTime;
        }else{
            this.deliverTime=cookedTime;
        }

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
    @Override
    public String toString() {
        return "delivery:"+this.orders.toString()+this.deliverer.toString()+this.deliverTime.toString();
    }

}
