package com.cs3343.demo.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;

public class DeliveryAssignmentManager {
    public Delivery GenerateDeliveryAssignment(ArrayList<Order> sortedOrders, ArrayList<Deliverer> deliverers) {
        // 1. Find a group of available orders
        var ordersToBeDeliveredTogether = new ArrayList<Order>();
        ordersToBeDeliveredTogether.add(sortedOrders.get(0));
        for (int i = 1; i < sortedOrders.size() && ordersToBeDeliveredTogether.size() < 3; ++i) {
            var nextItem = sortedOrders.get(i);
            for(int j=0;j<ordersToBeDeliveredTogether.size();j++){
                var currentItem =ordersToBeDeliveredTogether.get(j) ;
                var currentItemCookedTime = currentItem.getCookedTime();
                var timeDiff = Duration.between(currentItemCookedTime, nextItem.getCookedTime()).toMinutes();
                var distance=currentItem.getLocation().DistanceWithAnotherOrder(nextItem.getLocation());
                if (timeDiff < 15 && distance < 10) {
                    ordersToBeDeliveredTogether.add(nextItem);
                    nextItem.UpdateStatus2InDelivering();
                    break;
                }
            }
        }
        // 2. Find a deliverer
        var firstAvailableDeliverer = deliverers.stream()
                .sorted(Comparator.comparing(Deliverer::getAvailableTime))
                .toList()
                .get(0);

        var maxDistance = ordersToBeDeliveredTogether.stream().mapToDouble(Order::getDistance).max().getAsDouble();

        // 3. build a new assignment;
        var newDelivery=new Delivery(firstAvailableDeliverer, ordersToBeDeliveredTogether);
        firstAvailableDeliverer.AssignDelivery(newDelivery);
        //update time status for deliverers and deliveryAssignments

        firstAvailableDeliverer.deliverFood(maxDistance);
        newDelivery.SetFinishTime(maxDistance);


        return newDelivery;
    }
}
