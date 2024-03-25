package Classes.Utils;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class DataReader {
    public static String Read(String address) {
        File myObj = new File(address);
        try {
            String data = "";
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                data+=(myReader.nextLine());
            }
            return data;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
