package Controller;

import Model.Classes.Level.EditableLevel;
import Model.Classes.Level.LevelID;
import Model.Classes.Level.PlayableLevel;
import Vue.Constants.JComponentsNames;
import Vue.Game.Game;
import Vue.Level.EditableLevelPanel;
import Vue.Level.LevelPanel;
import Vue.Level.PlayableLevelPanel;

import javax.swing.*;

/**
 * This class has the responsibility to prepare the level to be displayed
 *
 * @author Léonard Amsler - s231715
 * @author Hugo Demule
 */
public class LevelPreparation {

    /**
     * Prepare the level
     *
     * @param levelID        The level ID
     * @param frame          The frame
     * @param gameController The game controller
     * @author Léonard Amsler - s231715
     */
    public static void preparePlayableLevel(LevelID levelID, JFrame frame, GameController gameController, LoginController loginController) {
        prepareLevel(levelID, frame, gameController, false, loginController, JComponentsNames.FrameID.CAMPAIGN_LEVELS);
    }

    /**
     * Prepare the level
     *
     * @param levelID         The level ID
     * @param frame           The frame
     * @param gameController  The game controller
     * @param editable        Is the level editable
     * @param loginController The login controller
     * @param frameID         The frame ID
     * @author Léonard Amsler - s231715
     */
    private static void prepareLevel(LevelID levelID, JFrame frame, GameController gameController, boolean editable, LoginController loginController, String frameID) {
        gameController.setCurrentLevelID(levelID, editable);

        LevelController levelController;
        LevelPanel levelPanel;

        if (editable) {
            levelController = new EditableLevelController(frame, null, (EditableLevel) gameController.getCurrentLevel());
            levelPanel = new EditableLevelPanel(frame, gameController, (EditableLevelController) levelController, loginController);
            levelController.setLevelPanel(levelPanel);
        } else {
            levelController = new PlayableLevelController(frame, null, (PlayableLevel) gameController.getCurrentLevel());
            levelPanel = new PlayableLevelPanel(frame, gameController, (PlayableLevelController) levelController, loginController);
            levelController.setLevelPanel(levelPanel);
        }

        frame.add(levelPanel, frameID);
        Game.showPanel(frame, frameID);
        frame.pack();
    }

    /**
     * Prepare a new empty editable level
     *
     * @param frame          The frame
     * @param gameController The game controller
     * @author Hugo Demule
     */
    public static void prepareNewEditableLevel(JFrame frame, GameController gameController) {
        prepareEditableLevel(LevelID.NEW_LEVEL, frame, gameController);
    }

    /**
     * Prepare the level to be editable
     *
     * @param levelID        The level ID
     * @param frame          The frame
     * @param gameController The game controller
     * @author Hugo Demule
     */
    public static void prepareEditableLevel(LevelID levelID, JFrame frame, GameController gameController) {
        prepareLevel(levelID, frame, gameController, true, null, JComponentsNames.FrameID.SANDBOX_LEVELS);
    }

}
