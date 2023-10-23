import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cook implements Comparable<Cook> {
    private Set<String> expertise = new HashSet<>();
    private String name;
    private int rank;

//    private CookStatus status;

    private LocalTime availableTime;


    public Cook(String[] cuisines,String n, int rank){
        this.name = n;
        this.rank = rank;
        for(String c : cuisines) {
            expertise.add(c);
        }
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
        while ((line = reader.readLine())!=null){
            String[] splitLine = line.split(" ");
            String name = splitLine[0];
            String[] cuisines = splitLine[1].split(",");
            int rank = Integer.parseInt(splitLine[2]);
            cooks.add(new Cook(cuisines,name,rank));
        }
        return cooks;
    }

    @Override
    //Used to sort cooks
    //以后的算法可能跟复杂，考虑的不仅仅是availableTime
    public int compareTo(Cook o) {

        return this.availableTime.compareTo(o.availableTime);
    }

    public void cookFood(int dishOperationTime) {
//        assert this.status == CookStatus.READY;
//        this.status = CookStatus.BUSY;
//        System.out.println("Before "+ this.availableTime.toString());
        this.availableTime = this.availableTime.plusMinutes(dishOperationTime);
//        System.out.println("After "+this.availableTime);
    }

    public void initializeAvailableTime(LocalTime time) {
        assert this.availableTime == null;
        this.availableTime = time;
    }

    public LocalTime getAvailableTime() {
        return this.availableTime;
    }

    public static String selectCook(ArrayList<Cook> cooks,int dishOperationTime) {
        Collections.sort(cooks);
        Cook selectedCook = cooks.get(0);
        LocalTime startTime = selectedCook.getAvailableTime();
        selectedCook.cookFood(dishOperationTime);
        return startTime+" "+selectedCook;
    }

    public static Cook selectCook(ArrayList<Cook> cooks){
        Collections.sort(cooks);
        return cooks.get(0);
    }

}
