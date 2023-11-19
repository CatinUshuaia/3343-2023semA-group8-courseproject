package com.cs3343.demo.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;

public class DeliveryAssignmentManager {
    public DeliveryAssignment GenerateOrderAssignment(ArrayList<Order> sortedOrders, ArrayList<Deliverer> deliverers) {
        // 1. Find a group of available orders
        var currentItem = sortedOrders.get(0);
        var currentItemCookedTime = currentItem.getCookedTime();
        var currentItemDistance = currentItem.getDistance();
        var ordersToBeDeliveredTogether = new ArrayList<Order>();
        for (int i = 1; i < sortedOrders.size() && ordersToBeDeliveredTogether.size() <= 3; ++i) {
            var nextItem = sortedOrders.get(i);
            var timeDiff = Duration.between(currentItemCookedTime, nextItem.getCookedTime()).toMinutes();
            var distanceDiff = Math.abs(currentItemDistance - nextItem.getDistance());
            if (timeDiff < 30 && distanceDiff < 10) {
                ordersToBeDeliveredTogether.add(nextItem);
                nextItem.UpdateStatus2InDelivering();
            }
        }

        // 2. Find a deliverer
        var firstAvailableDeliverer = deliverers.stream()
                .sorted(Comparator.comparing(Deliverer::getAvailableTime))
                .toList()
                .get(0);

        var maxDistance = ordersToBeDeliveredTogether.stream().mapToDouble(Order::getDistance).max().getAsDouble();
        firstAvailableDeliverer.deliverFood(maxDistance);

        // 3. build a new assignment;
        return new DeliveryAssignment(firstAvailableDeliverer, ordersToBeDeliveredTogether);
    }
}
