package com.cs3343.demo.core;

import com.cs3343.demo.impls.CookImpl;
import com.cs3343.demo.impls.DelivererImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class Deliverer implements Comparable<Deliverer> {
    private String name;

    private LocalTime availableTime;
    @Autowired
    private DelivererImpl delivererImpl;
    public Deliverer(){

    }
    public Deliverer(String name){
        this.name = name;
//        this.status = CookStatus.READY;
//        this.cookingDish = null;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getInfo(){
        return name +" ";
    }

    public static ArrayList<Deliverer> inputDelivererInfo(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<Deliverer> deliverers = new ArrayList<Deliverer>();
        while ((line = reader.readLine())!=null){
            String[] splitLine = line.split(" ");
            String name = splitLine[0];
            deliverers.add(new Deliverer(name));
        }
        return deliverers;
    }

    @Override
    //Used to sort cooks
    public int compareTo(Deliverer o) {
        return this.availableTime.compareTo(o.availableTime);
    }

    public void deliverFood(int distance) {
//        assert this.status == CookStatus.READY;
//        this.status = CookStatus.BUSY;
//        System.out.println("Before "+ this.availableTime.toString());
        int orderOperationTime = distance*2;
        this.availableTime = this.availableTime.plusMinutes(orderOperationTime);
//        System.out.println("After "+this.availableTime);
    }

    public void initializeAvailableTime(LocalTime time) {
        assert this.availableTime == null;
        this.availableTime = time;
    }

    public LocalTime getAvailableTime() {
        return this.availableTime;
    }

    public static String selectDeliverer(ArrayList<Deliverer> deliverers, int orderOperationTime) {
        Collections.sort(deliverers);
        Deliverer selectedDeliverer = deliverers.get(0);
        LocalTime startTime = selectedDeliverer.getAvailableTime();
        selectedDeliverer.deliverFood(orderOperationTime);
        return startTime+" "+selectedDeliverer;
    }

    public static Deliverer getDeliverer(ArrayList<Deliverer> deliverers){
        Collections.sort(deliverers);
        return deliverers.get(0);
    }

}

