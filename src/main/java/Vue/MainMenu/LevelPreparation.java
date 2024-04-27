package Vue.MainMenu;

import Controller.*;
import Model.Classes.Level.EditableLevel;
import Model.Classes.Level.LevelID;
import Model.Classes.Level.PlayableLevel;
import Vue.Level.EditableLevelPanel;
import Vue.Level.LevelPanel;
import Vue.Level.PlayableLevelPanel;

import javax.swing.*;
import java.awt.*;

public class LevelPreparation {

    /**
     * Prepare the level
     *
     * @param levelID        The level ID
     * @param frame          The frame
     * @param gameController The game controller
     * @Author Léonard Amsler - s231715
     */
    public static void preparePlayableLevel(LevelID levelID, JFrame frame, GameController gameController, LoginController loginController) {
        prepareLevel(levelID, frame, gameController, false, loginController);
    }

    public static void prepareEditableLevel(LevelID levelID, JFrame frame, GameController gameController) {
        prepareLevel(levelID, frame, gameController, true, null);
    }

    public static void prepareNewEditableLevel(JFrame frame, GameController gameController) {
        prepareLevel(LevelID.NEW_LEVEL, frame, gameController, true, null);
    }

    private static void prepareLevel(LevelID levelID, JFrame frame, GameController gameController, boolean editable, LoginController loginController) {
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

        frame.add(levelPanel, "Level");
        showPanel(frame, "Level");
        frame.pack();
    }

    /**
     * Show a panel
     *
     * @param frame     - The frame
     * @param panelName - The panel name
     * @Author Léonard Amsler - s231715
     */
    public static void showPanel(JFrame frame, String panelName) {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), panelName);
    }
}
