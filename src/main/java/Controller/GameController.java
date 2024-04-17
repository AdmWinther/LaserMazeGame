package Controller;

import Model.Classes.Level.CampaignLevel;
import Model.Classes.Level.LevelBuilder;
import Model.Classes.Level.LevelID;

public class GameController {

    private LevelBuilder levelBuilder;
    private LevelID currentLevelID;
    private CampaignLevel currentLevel;

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

    public CampaignLevel getCurrentLevel() {
        return this.currentLevel;
    }
}
