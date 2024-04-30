package Controller;

import Model.Classes.Level.Level;
import Model.Classes.Level.LevelBuilder;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;
import Vue.Game.Game;
import Vue.Level.LevelType;

import java.awt.*;
import java.util.List;

public class GameController {

    /**
     * The game
     */
    private final Game game;

    /**
     * The level builder
     */
    private LevelBuilder levelBuilder;

    /**
     * The current level ID
     */
    private LevelID currentLevelID;

    /**
     * The current level
     */
    private Level currentLevel;

    /**
     * The level type
     */
    private LevelType levelType;

    /**
     * Constructor of the GameController
     *
     * @param game Game - The game
     * @author Hugo Demule (s231675)
     */
    public GameController(Game game) {
        this.game = game;
    }

    /**
     * Set the current level ID
     *
     * @param levelID  The level ID
     * @param editable Is the level editable
     * @author Hugo Demule (s231675)
     * @author Léonard Amsler (s231715)
     */
    public void setCurrentLevelID(LevelID levelID, boolean editable) {
        this.currentLevelID = levelID;
        if (this.levelBuilder == null) {
            this.levelBuilder = new LevelBuilder(this.currentLevelID);
        } else {
            this.levelBuilder.setID(this.currentLevelID);
        }
        this.currentLevel = this.levelBuilder.build(editable);
    }

    /**
     * Get the current level
     *
     * @return Level - The current level
     * @author Léonard Amsler (s231715)
     */
    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * Get the current level ID
     *
     * @return LevelID - The current level ID
     * @author Léonard Amsler (s231715)
     */
    public LevelID getCurrentLevelID() {
        return this.currentLevelID;
    }

    /**
     * Turn on the campaign game mode
     *
     * @author Léonard Amsler (s231715)
     */
    public void turnOnCampaignGameMode() {
        this.levelType = LevelType.CAMPAIGN;
    }

    /**
     * Turn on the sandbox game mode
     *
     * @author Léonard Amsler (s231715)
     */
    public void turnOnSandboxGameMode() {
        this.levelType = LevelType.SANDBOX;
    }

    /**
     * Turn on the random game mode
     *
     * @Author Léonard Amsler (s231715)
     */
    public void turnOnRandomGameMode() {
        this.levelType = LevelType.RANDOM;
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
     * Get the current tile dimension
     *
     * @return Dimension - The current tile dimension
     * @author Hugo Demule
     */
    public Dimension getCurrentTileDimension() {
        return game.getCurrentTileDimension();
    }

    public LevelType getLevelType() {
        return levelType;
    }
}
