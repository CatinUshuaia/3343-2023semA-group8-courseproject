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
    }
}
