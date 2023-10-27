import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Logger {
    public static void writeTxt(ArrayList<String> list, String fileName) {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("Line 1");
//        list.add("Line 2");
//        list.add("Line 3");

//        String fileName = "output.txt";  // Specify the file name or path

        try {
            FileWriter writer = new FileWriter(fileName);

            for (String line : list) {
                writer.write(line + System.lineSeparator());  // Write each line with a new line separator
            }

            writer.close();  // Close the writer to release resources
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}