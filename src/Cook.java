import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cook {
    private Set<String> expertise = new HashSet<>();
    private String name;
    private int rank;

    public Cook(String[] cuisines,String n, int rank){
        this.name = n;
        this.rank = rank;
        for(String c : cuisines) {
            expertise.add(c);
        }
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

}
