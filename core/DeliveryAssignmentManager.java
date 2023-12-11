package demo.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;

public class DeliveryAssignmentManager {
    private ArrayList<Order> sortedOrders;
    private ArrayList<Deliverer> deliverers;
    private Deliverer firstAvailableDeliverer;

    private ArrayList<Order> ordersToBeDeliveredTogether;

    private double maxDistance;
    public DeliveryAssignmentManager(ArrayList<Order> sortedOrders, ArrayList<Deliverer> deliverers){
        this.sortedOrders=sortedOrders;
        this.deliverers=deliverers;
    }
    
    public DeliveryAssignmentManager(){

    }
    public Delivery GenerateDeliveryAssignment(ArrayList<Order> sortedOrders, ArrayList<Deliverer> deliverers) {
        ordersToBeDeliveredTogether = new ArrayList<Order>();
        ordersToBeDeliveredTogether.add(sortedOrders.get(0));
        for (int i = 1; i < sortedOrders.size() && ordersToBeDeliveredTogether.size() < 3; ++i) {
            var nextItem = sortedOrders.get(i);
            for(int j=0;j<ordersToBeDeliveredTogether.size();j++){
                var currentItem =ordersToBeDeliveredTogether.get(j) ;
                var currentItemCookedTime = currentItem.getCookedTime();
                var timeDiff = Duration.between(currentItemCookedTime, nextItem.getCookedTime()).toMinutes();
                var distance=currentItem.getLocation().DistanceWithAnotherOrder(nextItem.getLocation());
                if (timeDiff < 10 && distance < 10) {
                    ordersToBeDeliveredTogether.add(nextItem);
                    break;
                }
            }
        }
        firstAvailableDeliverer = deliverers.stream()
                .sorted(Comparator.comparing(Deliverer::getAvailableTime))
                .toList()
                .get(0);
        var newDelivery = new Delivery(firstAvailableDeliverer, ordersToBeDeliveredTogether);
        firstAvailableDeliverer.AssignDelivery(newDelivery);
        maxDistance = ordersToBeDeliveredTogether.stream().mapToDouble(Order::getDistance).max().getAsDouble();
        firstAvailableDeliverer.deliverFood(maxDistance);

        return newDelivery;
    }


    public void deliverInDeed(Delivery newDelivery) {
        newDelivery.SetFinishTime(maxDistance);
        newDelivery.UpdateStatus3Delivered();
    }
}
