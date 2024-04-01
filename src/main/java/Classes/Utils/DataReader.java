package Classes.Utils;

import Classes.Level.LevelID;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
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

    public static ArrayList<LevelID> extractLevelIDs(String levelDataPath) {
        ArrayList<LevelID> levelIDS = new ArrayList<LevelID>();
        levelIDS.add(new LevelID("First Level"));
        levelIDS.add(new LevelID("Second Level"));
        levelIDS.add(new LevelID("Third Level"));
        levelIDS.add(new LevelID("Forth Level"));
        levelIDS.add(new LevelID("Fifth Level"));
        return levelIDS;
    }
}
