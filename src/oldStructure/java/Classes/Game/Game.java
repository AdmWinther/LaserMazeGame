package Classes.Game;

import Classes.Level.Level;
import Classes.Level.LevelBuilder;
import Classes.Level.LevelID;
import Classes.Utils.DataReader;
import Interfaces.Runnable;

import java.util.ArrayList;

public class Game implements Runnable {

    private final LevelBuilder levelBuilder = new LevelBuilder(new LevelID("0"));
    private Player player;

    public Game() {
        this.player = new Player();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

    @Override
    public void run() {
        System.out.println("Starting the game ...\n");
        ArrayList<LevelID> levelIds = new ArrayList<>(DataReader.extractLevelIDs(GameData.getLevelIdsPath()));

        LevelID levelId = GameInputs.selectLevel(levelIds);

        levelBuilder.setID(levelId);

        Level level = levelBuilder.build();

        if (level != null) {
            System.out.println("Level " + level.getLevelName() + " loaded.");
            System.out.println("Starting level ...");
            level.run();
            System.out.println("Level finished.");
        } else {
            System.out.println("Error loading level.");
        }

        if (GameInputs.selectAnotherLevel()) {
            run();
        } else {
            System.out.println("Exiting game.");
            stop();
        }
    }

    public void stop() {
        System.out.println("Game finished.");
        System.exit(0);
    }

}
