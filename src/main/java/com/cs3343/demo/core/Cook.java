package com.cs3343.demo.core;


import java.time.LocalTime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;


public class Cook implements Comparable<Cook>  {
    private String name;
    private int cookCode;

    private LocalTime availableTime;


    public Cook(String n, int id){
        this.name=n;
        this.cookCode = id;
    }

    @Override
    public String toString(){
        return name;
    }

    public static ArrayList<Cook> inputInfo(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<Cook> cooks = new ArrayList<Cook>();
        int id =1;
        while ((line = reader.readLine())!=null) {
            String[] splitLine = line.split(" ");
            String name = splitLine[0];
            cooks.add(new Cook(name, id));
            id += 1;
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


    public static Cook selectCook(ArrayList<Cook> cooks){
        Collections.sort(cooks);
        return cooks.get(0);
    }


}