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

    private CookStatus status;


    public Cook(String[] cuisines,String n, int rank){
        this.name = n;
        this.rank = rank;
        for(String c : cuisines) {
            expertise.add(c);
        }
        this.status = CookStatus.READY;
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
    public int compareTo(Cook o) {
        return this.status.compareTo(o.status);
    }

    public void cookFood() {
        assert this.status == CookStatus.READY;
        this.status = CookStatus.BUSY;
    }

    public static void resortCooks(ArrayList<Cook> cooks) {
        Collections.sort(cooks);
    }
}
