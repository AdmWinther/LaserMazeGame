package Classes.Game;
import Classes.Utils.DataReader;

import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Runnable {
    private Player player;
    final String LevelDataPath = "./src/test/java/Game/gameData.txt";
    //todo: move the path to a configuration file rather than hard code the path.

    private ArrayList<String> levelData;
    public void main(String[] args) {
        System.out.println("This is the main menu.");
        levelData = DataReader.extractLevelIDs(LevelDataPath);
//        System.out.println(levelData.toString());

        System.out.println("select a level.");
        System.out.println("1- Level 1");
        System.out.println("2- Level 2");
        System.out.println("3- Level 3");

        Scanner s = new Scanner(System.in);
        int selectedLevel = s.nextInt();
        switch (selectedLevel){
            default:
                System.out.println("level 1 ia selected.");
            case 1:
                System.out.println("level 1 ia selected.");
            case 2:
                System.out.println("level 2 ia selected.");
            case 3:
                System.out.println("level 3 ia selected.");
        }
    }

    @Override
    public void run() {
        //todo: add the actions required to run!
    }

    public void stop() {
        //Todo: add the actions required on exit.
    }
}
