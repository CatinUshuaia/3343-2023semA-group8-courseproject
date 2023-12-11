package com.cs3343.demo.core;

import java.util.HashMap;
import java.util.Map;

public enum CookingMethod {
    BOIL("boil", 1),
    ROAST("roast", 2),
    STEAM("steam", 2),
    STEW("stew", Integer.MAX_VALUE),
    FRY("fry", Integer.MAX_VALUE);

    private String name;
    private int operationTime;

    CookingMethod(String name, int operationTime) {
        this.name = name;
        this.operationTime = operationTime;
    }

    private static final Map<String, CookingMethod> cookingMethodMap = new HashMap<>();

    static {
        cookingMethodMap.put("boil", CookingMethod.BOIL);
        cookingMethodMap.put("roast", CookingMethod.ROAST);
        cookingMethodMap.put("steam", CookingMethod.STEAM);
        cookingMethodMap.put("fry", CookingMethod.FRY);
        cookingMethodMap.put("stew", CookingMethod.STEW);
    }

    public static CookingMethod getWayToCook(String wayToCook) {
        return cookingMethodMap.getOrDefault(wayToCook, null);
    }

    public int getOperationTime() {
        return operationTime;
    }
    public String toString() {
        return name;
    }

    //personally I would like to make compareTo more flexible
    public int customCompareTo(CookingMethod other) {
        int comparison = this.operationTime - other.operationTime;
        if(comparison>0){
            return 1;
        }else if(comparison==0){
            return 0;
        }else{
            return -1;
        }
    }

    public boolean noSavedTimeOperation(){
        return this.operationTime == Integer.MAX_VALUE;
    }

}