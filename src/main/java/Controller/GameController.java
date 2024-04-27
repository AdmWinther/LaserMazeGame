package Controller;

import Model.Classes.Level.Level;
import Model.Classes.Level.LevelBuilder;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;

import java.util.List;

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

    /**
     * Get the list of level IDs for the campaign mode
     *
     * @return List of level IDs
     * @author Hugo Demule
     */
    public List<LevelID> getCampaignLevelIDs() {
        return DataReader.readCampaignLevelIDs();
    }

    /**
     * Get the list of level IDs for the sandbox mode
     *
     * @return List of level IDs
     * @author Hugo Demule
     */
    public List<LevelID> getSandboxLevelIDs() {
        return DataReader.readSandboxLevelIDs();
    }

}
