package demo.core;

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
//    private int priority;

    CookingMethod(String name, int operationTime) {
        this.name = name;
        this.operationTime = operationTime;
//        this.priority = priority;
        //烤的食物只需要放入烤箱，不需要人工操作，所以operationTime为1
        //炒的食物需要人工全程操作，所以operationTime为-1，即需要一个人全程操作
    }

    public static CookingMethod getWayToCook(String wayToCook) {
        Map<String, CookingMethod> cookingMethodMap = new HashMap<>();
        cookingMethodMap.put("boil", CookingMethod.BOIL);
        cookingMethodMap.put("roast", CookingMethod.ROAST);
        cookingMethodMap.put("steam", CookingMethod.STEAM);
        cookingMethodMap.put("fry", CookingMethod.FRY);
        cookingMethodMap.put("stew", CookingMethod.STEW);

        return cookingMethodMap.getOrDefault(wayToCook, null);
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

    // public static void testCompareTo(){
    //     System.out.println(Integer.MAX_VALUE - Integer.MAX_VALUE);
    //     System.out.println(Integer.MAX_VALUE - 1);
    //     System.out.println(1-Integer.MAX_VALUE);
    //     System.out.println(FRY.customCompareTo(ROAST)); //1
    //     System.out.println(FRY.customCompareTo(BOIL)); //1
    //     System.out.println(ROAST.customCompareTo(STEAM)); //0
    //     System.out.println(ROAST.customCompareTo(BOIL)); //-1
    //     System.out.println(BOIL.customCompareTo(STEAM)); //-1
    // }

}