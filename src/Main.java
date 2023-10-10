import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static String COOK_INPUT = "cook.txt";
    // instead of inputing file, randomly generate the cook info

    // can also be randomly generated
    private static String DISH_INPUT ="dish.txt";

    private static String ORDER_INPUT = "order.txt";

    public static void main(String[] args) throws IOException {

        ArrayList<Cook> cooks = Cook.inputCookInfo("src/cook.txt");
        for(Cook c: cooks){
            System.out.println(c.getInfo());
        }

    }
}