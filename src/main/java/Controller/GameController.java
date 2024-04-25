package Controller;

import Model.Classes.Level.Level;
import Model.Classes.Level.LevelBuilder;
import Model.Classes.Level.LevelID;

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
        // TODO: TO REMOVE!!! find a way to build the level of the right type
        this.currentLevel = this.levelBuilder.build("editable");
    }

    public Level getCurrentLevel() {
        return this.currentLevel;
    }
}
