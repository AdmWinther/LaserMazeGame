package Classes.Game;
import Classes.Level.LevelID;
import Classes.Utils.DataReader;
import Interfaces.Runnable;

import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Runnable {
    private Player player;

    private ArrayList<LevelID> levelData;

    @Override
    public void run() {
        System.out.println("This is the main menu.");
        levelData = DataReader.extractLevelIDs(GameData.getLEVELIDSPATH());
    }

    public void stop() {
        //Todo: add the actions required on exit.
    }

    public ArrayList<LevelID> getLevelData() {
        return levelData;
    }

    public void setLevelData(ArrayList<LevelID> levelData) {
        this.levelData = levelData;
    }
}
