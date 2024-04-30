package Controller;

import Model.Classes.Level.PlayableLevel;
import Vue.Level.PlayableLevelPanel;

import javax.swing.*;

/**
 * This class has the responsibility to control the playable level
 *
 * @see LevelController
 */
public class PlayableLevelController extends LevelController {

    /**
     * Constructor of the PlayableLevelController
     *
     * @param gameController GameController - The game controller
     * @param frame          JFrame - The frame
     * @param levelPanel     PlayableLevelPanel - The level panel
     * @param level          PlayableLevel - The level
     * @author Hugo Demule (s231675)
     */
    public PlayableLevelController(GameController gameController, JFrame frame, PlayableLevelPanel levelPanel, PlayableLevel level) {
        super(gameController, frame, levelPanel, level);
    }

    /**
     * Check if the level is completed
     *
     * @return boolean - True if the level is completed, false otherwise
     * @author Hugo Demule (s231675)
     */
    @Override
    public boolean isSandbox() {
        return false;
    }

    /**
     * Check if the level is completed
     *
     * @return boolean - True if the level is completed, false otherwise
     * @author Adam (Hossein) (s240844)
     */
    public boolean isLevelCompleted() {
        return ((PlayableLevel) level).isLevelCompleted();
    }
}