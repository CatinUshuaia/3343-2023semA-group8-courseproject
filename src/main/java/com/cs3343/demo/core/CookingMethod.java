package com.cs3343.demo.core;

public enum CookingMethod implements Comparable<CookingMethod> {
    BOIL("boil", 1),
    ROAST("roast", 2),
    FRY("fry", -1);

    private String name;
    private int operationTime;
//    private int priority;

    private CookingMethod(String name, int operationTime) {
        this.name = name;
        this.operationTime = operationTime;
//        this.priority = priority;
        //烤的食物只需要放入烤箱，不需要人工操作，所以operationTime为1
        //炒的食物需要人工全程操作，所以operationTime为-1，即需要一个人全程操作
    }

    public String getName() {
        return name;
    }

    //    public int getPriority() {
//        return priority;
//    }
    public int getOperationTime() {
        return operationTime;
    }
    public String toString() {
        return name;
    }

    //personally I would like to make compareTo more flexible
    //public int customCompareTo(CookingMethod other) {
    //  return this.operationTime - other.operationTime;
    //}


}