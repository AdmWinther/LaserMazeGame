package Controller;

import Model.Classes.Level.Level;
import Model.Classes.Level.LevelBuilder;
import Model.Classes.Level.LevelID;

import java.io.File;

public class GameController {

    private LevelBuilder levelBuilder;
    private LevelID currentLevelID;
    private Level currentLevel;
    private boolean campaignGameMode = false;

    public GameController() {
    }

    public void setCurrentLevelID(LevelID levelID, boolean editable) {
        this.currentLevelID = levelID;
        if (this.levelBuilder == null) {
            this.levelBuilder = new LevelBuilder(this.currentLevelID);
        } else {
            this.levelBuilder.setID(this.currentLevelID);
        }
        // TODO: TO REMOVE!!! find a way to build the level of the right type
        this.currentLevel = this.levelBuilder.build(editable);
    }


    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    public LevelID getCurrentLevelID() {
        return this.currentLevelID;
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
