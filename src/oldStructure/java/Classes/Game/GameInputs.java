package Classes.Game;

import Classes.Level.LevelID;

import java.util.ArrayList;
import java.util.Scanner;

public class GameInputs {
    public static LevelID selectLevel(ArrayList<LevelID> levelIDS) {

        System.out.println("Here is the list of levels:");
        System.out.println("----------------------");
        System.out.println("-- Index | Level ID --");
        System.out.println("----------------------");
        for (int i = 0; i < levelIDS.size(); i++) {
            System.out.println("--   " + i + "   |     " + levelIDS.get(i).value() + "    --");
        }
        System.out.println("----------------------");

        int size = levelIDS.size();
        System.out.println("\nPlease select a level (0-" + (size - 1) + "):");

        boolean isLevelSelected = false;
        LevelID selectedLevel = null;

        while (!isLevelSelected) {
            Scanner s = new Scanner(System.in);
            int level = s.nextInt();
            if (level >= 0 && level < size) {
                selectedLevel = levelIDS.get(level);
                isLevelSelected = true;
            } else {
                System.out.println("Please select a valid level (0-" + (size - 1) + ").");
            }
        }

        return selectedLevel;
    }

    public static boolean selectAnotherLevel() {
        System.out.println("Do you want to select another level? (y/n)");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        return input.equals("y");
    }

}
