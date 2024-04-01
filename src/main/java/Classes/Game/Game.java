package Classes.Game;
import Classes.Level.LevelID;
import Classes.Utils.DataReader;
import Interfaces.Runnable;

import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Runnable {
    private Player player;
    final String LevelDataPath = "./src/test/java/Game/gameData.txt";
    //todo: move the path to a configuration file rather than hard code the path.

    private ArrayList<LevelID> levelData;
    public void main(String[] args) {

    }

    @Override
    public void run() {
        System.out.println("This is the main menu.");
        levelData = DataReader.extractLevelIDs(LevelDataPath);
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
