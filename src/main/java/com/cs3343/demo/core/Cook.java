package com.cs3343.demo.core;

import com.cs3343.demo.entity.CookEntity;
import com.cs3343.demo.impls.CookImpl;
import com.cs3343.demo.services.CookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Component
@Scope(SCOPE_PROTOTYPE)
public class Cook implements Comparable<Cook> {
    private Set<String> expertise = new HashSet<>();
    private String name;
    private int rank;
    private int cookCode;

    @Autowired
    private CookImpl cookImpl;

//    private CookStatus status;

    private LocalTime availableTime;

    public Cook(){
    }
    public Cook(String[] cuisines,String n, int rank,int id){
        this.name = n;
        this.rank = rank;
        for(String c : cuisines) {
            expertise.add(c);
        }
        this.cookCode = id;
//        this.status = CookStatus.READY;
//        this.cookingDish = null;
    }


    @Override
    public String toString(){
        return name;
    }

    public String getInfo(){
        return name +" "+ expertise + " " + rank ;
    }

    public static ArrayList<Cook> inputCookInfo(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<Cook> cooks = new ArrayList<Cook>();
        int id =1;
        while ((line = reader.readLine())!=null){
            String[] splitLine = line.split(" ");
            String name = splitLine[0];
            String[] cuisines = splitLine[1].split(",");
            int rank = Integer.parseInt(splitLine[2]);
            cooks.add(new Cook(cuisines,name,rank,id));
            id+=1;
            //CookEntity cookEntity = new CookEntity(name, Arrays.toString(cuisines), rank);
            //cookImpl.save(cookEntity);
            //读取input并存入database

        }

        reader.close();
        return cooks;
    }
    @Override
    //Used to sort cooks
    //以后的算法可能跟复杂，考虑的不仅仅是availableTime
    public int compareTo(Cook o) {

//        return this.availableTime.compareTo(o.availableTime);
        int comparisionTime = this.availableTime.compareTo(o.availableTime);
        if (comparisionTime!=0){
            return comparisionTime;
        }else{
            return Integer.compare(this.cookCode, o.cookCode);
        }
    }

    public void cookFood(LocalTime expectedFinish) {
        this.availableTime = expectedFinish;

    }

    public void initializeAvailableTime(LocalTime time) {
        assert this.availableTime == null;
        this.availableTime = time;
    }

    public LocalTime getAvailableTime() {
        return this.availableTime;
    }

    //public static String selectCook(ArrayList<Cook> cooks,int dishOperationTime) {
    //Collections.sort(cooks);
    //Cook selectedCook = cooks.get(0);
    //LocalTime startTime = selectedCook.getAvailableTime();
    //selectedCook.cookFood(dishOperationTime);
    //return startTime+" "+selectedCook;
    //}

    public static Cook selectCook(ArrayList<Cook> cooks){
        Collections.sort(cooks);
        return cooks.get(0);
    }

}