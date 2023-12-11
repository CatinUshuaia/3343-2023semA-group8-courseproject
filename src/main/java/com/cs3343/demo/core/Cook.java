package com.cs3343.demo.core;


import java.io.*;
import java.time.LocalTime;

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

    public static ArrayList<Cook> inputInfo_old(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<Cook> cooks = new ArrayList<Cook>();
        int id =1;
        while ((line = reader.readLine())!=null) {
            String[] splitLine = line.split(" ");
            String name = splitLine[0];
            cooks.add(new Cook(name, id));
            id += 1;
        }
        reader.close();
        return cooks;
    }

    public static ArrayList<Cook> inputInfo(String filePath, boolean defaultInput) throws IOException{
        InputStream inputStream;
        if (defaultInput) {
            inputStream = Dish.class.getResourceAsStream("/" + filePath);

        } else {
            inputStream = new FileInputStream(filePath);
        }
        if (inputStream == null) {
            throw new IOException("File not found in resources: " + filePath);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        ArrayList<Cook> cooks = new ArrayList<Cook>();
        int id =1;
        while ((line = reader.readLine())!=null) {
            String[] splitLine = line.split(" ");
            String name = splitLine[0];
            cooks.add(new Cook(name, id));
            id += 1;
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