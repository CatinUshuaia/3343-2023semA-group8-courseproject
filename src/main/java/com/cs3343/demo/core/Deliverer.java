package com.cs3343.demo.core;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;


public class Deliverer implements Comparable<Deliverer> {
    private String name;
    private ArrayList<Order> deliverOrder;
    private LocalTime availableTime;
    public Deliverer(){

    }
    public Deliverer(String name){
        this.name = name;
        this.availableTime=LocalTime.now();
    }

    @Override
    public String toString(){
        return name;
    }

    public String getInfo(){
        return name +" ";
    }

    public static ArrayList<Deliverer> inputDelivererInfo(String xmlFilePath) throws IOException{
        ArrayList<Deliverer> deliverers = new ArrayList<>();

        try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(xmlFilePath);
                Element root = document.getDocumentElement();
                NodeList delivererList = root.getElementsByTagName("deliverer");

                if (delivererList.getLength() > 0) {
                    for (int i = 0; i < delivererList.getLength(); i++) {
                        Element delivererElement = (Element) delivererList.item(i);
String delivererName=delivererElement.getElementsByTagName("name").item(0).getTextContent();
                        System.out.println(delivererName);
                        deliverers.add(new Deliverer(delivererName));
                }
            }
        }catch (Exception e) {
                e.printStackTrace();
            }
        return deliverers;
    }

    @Override
    //Used to sort cooks
    public int compareTo(Deliverer o) {
        return this.availableTime.compareTo(o.availableTime);
    }

    public void deliverFood(double distance) {
        double orderOperationTime = distance * 2;
        this.availableTime = this.availableTime.plusMinutes((int)orderOperationTime);
    }

    public LocalTime getAvailableTime() {
        return this.availableTime;
    }

}