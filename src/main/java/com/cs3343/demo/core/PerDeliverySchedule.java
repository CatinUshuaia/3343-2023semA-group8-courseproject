package com.cs3343.demo.core;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.Duration;
import java.util.Collections;

public class PerDeliverySchedule{
    private Deliverer deliverer;
    private ArrayList<Order> orders;
    private LocalTime deliverTime;
    private LocalTime finishTime;

    public PerDeliverySchedule generateSchedule(ArrayList<Order> sortedOrders, ArrayList<Deliverer> deliverers){
        PerDeliverySchedule deliverSchedule=new PerDeliverySchedule();
        LocalTime lastOrderFinished;
        for (int i = 0; i < sortedOrders.size() - 2; i++) {
            Order order1 = sortedOrders.get(i);
            Order order2 = sortedOrders.get(i + 1);
            Order order3 = sortedOrders.get(i + 2);

            // 计算相邻三个元素的finishedTime之差的绝对值之和
            long timeDiffSum = Duration.between(order1.getCookedTime(),order2.getCookedTime()).toMinutes()+Duration.between(order3.getCookedTime(),order3.getCookedTime()).toMinutes();

            // 计算相邻三个元素的距离差的绝对值之和
            int distanceDiffSum = (int) (Math.abs(order1.getDistance() - order2.getDistance()) +
                                Math.abs(order2.getDistance() - order3.getDistance()));

            // 检查绝对值之和是否在50以内，如果是则将三个元素添加到提取列表中
            if ((timeDiffSum + distanceDiffSum* 2L) <= 50) {
                this.orders.add(order1);
                this.orders.add(order2);
                this.orders.add(order2);
                //找到availableTime和Order的finish time 最近的deliverer，把这三个order加给这个deliverer
                Order maxCookedTimeOrder = Collections.max(orders);
                LocalTime maxTime=maxCookedTimeOrder.getCookedTime();
                Deliverer closestDeliverer = null;
                 Duration minTimeDifference = Duration.ofSeconds(Long.MAX_VALUE);
                for (Deliverer deliverer : deliverers) {
                    Duration timeDifference = Duration.between(deliverer.getAvailableTime() , maxTime);
                    if (timeDifference.compareTo(minTimeDifference)<0) {
                        minTimeDifference = timeDifference;
                        closestDeliverer = deliverer;
                    }
                }

            }
        }
        return deliverSchedule;
    }
    public static void deliver(ArrayList<Deliverer> deliverers, int orderOperationTime) {
        Collections.sort(deliverers);
        Deliverer selectedDeliverer = deliverers.get(0);
        LocalTime startTime = selectedDeliverer.getAvailableTime();
        selectedDeliverer.deliverFood(orderOperationTime);
//            return startTime+" "+selectedDeliverer;
    }
}
