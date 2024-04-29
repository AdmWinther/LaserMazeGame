package Controller;

import Model.Classes.Level.Level;
import Model.Classes.Level.LevelBuilder;
import Model.Classes.Level.LevelID;

import java.io.File;

public class GameController {

    private LevelBuilder levelBuilder;
    private LevelID currentLevelID;
    private Level currentLevel;
    private boolean isInCampaignGameMode = false;

    public GameController() {
    }

    public void setCurrentLevelID(LevelID levelID, boolean editable) {
        this.currentLevelID = levelID;
        if (this.levelBuilder == null) {
            this.levelBuilder = new LevelBuilder(this.currentLevelID);
        } else {
            this.levelBuilder.setID(this.currentLevelID);
        }
        this.currentLevel = this.levelBuilder.build(editable);
    }


    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    public LevelID getCurrentLevelID() {
        return this.currentLevelID;
    }

    public void turnOnCampaignGameMode() {
        this.isInCampaignGameMode = true;
    }

    public void turnOffCampaignGameMode() {
        this.isInCampaignGameMode = false;
    }

    public boolean isInCampaignGameMode() {
        return this.isInCampaignGameMode;
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
