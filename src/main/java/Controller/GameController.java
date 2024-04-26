package Controller;

import Model.Classes.Level;
import Model.Classes.LevelBuilder;
import Model.Classes.LevelID;

import java.io.File;

public class GameController {

    private LevelBuilder levelBuilder;
    private LevelID currentLevelID;
    private Level currentLevel;
    private boolean campaignGameMode = false;

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

    public void turnOnCampaignGameMode() {
        this.campaignGameMode = true;
    }

    public void turnOffCampaignGameMode() {
        this.campaignGameMode = false;
    }

    public boolean getCampaignGameMode() {
        return this.campaignGameMode;
    }

    public int getMaxCampaignLevel() {
        // Get the number of json file into the Model/Ressources/campaign folder
        String Path = "src/main/java/Model/Resources/campaign";
        File folder = new File(Path);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        System.out.println("Number of files in the campaign folder: " + listOfFiles.length);
        return listOfFiles.length;
    }
}
