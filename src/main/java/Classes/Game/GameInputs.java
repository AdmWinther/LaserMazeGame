package Classes.Game;

import Classes.Level.LevelID;

import java.util.ArrayList;
import java.util.Scanner;

public class GameInputs {
    public static String selectLevel(ArrayList<LevelID> levelIDS){

        System.out.println("select a level:");
        for(int i = 0; i < levelIDS.size(); i++){
            System.out.println((i+1)+"-"+ levelIDS.get(i).getValue());
        }

        Scanner s = new Scanner(System.in);
        int selectedLevel = s.nextInt();
        return ("Level "+selectedLevel+"th with ID ="+levelIDS.get(selectedLevel-1).getValue()+" is selected.");
    }
    
}
