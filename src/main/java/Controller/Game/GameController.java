package Controller.Game;

import Model.Classes.Level;
import Model.Classes.LevelBuilder;
import Model.Classes.LevelID;

public class GameController {

    private LevelBuilder levelBuilder;
    private LevelID currentLevelID;
    private Level currentLevel;

    public GameController() {
    }

    public void setCurrentLevelID(String levelID) {
        this.currentLevelID = new LevelID(levelID);
        if (this.levelBuilder == null) {
            this.levelBuilder = new LevelBuilder(this.currentLevelID);
        } else {
            this.levelBuilder.setID(this.currentLevelID);
        }
        this.currentLevel = this.levelBuilder.build();
    }

    public Level getCurrentLevel() {
        return this.currentLevel;
    }
}
