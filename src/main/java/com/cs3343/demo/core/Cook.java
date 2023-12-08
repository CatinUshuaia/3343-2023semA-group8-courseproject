package com.cs3343.demo.core;

import java.time.LocalTime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Cook implements Comparable<Cook> {
    private Set<String> expertise = new HashSet<>();
    private String name;
    private int rank;
    private int cookCode;

    private LocalTime availableTime;


    public Cook(String[] cuisines,String n, int rank,int id){
        this.name = n;
        this.rank = rank;
        for(String c : cuisines) {
            expertise.add(c);
        }
        this.cookCode = id;
    }


    @Override
    public String toString(){
        return name;
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