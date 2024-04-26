package Vue.MainMenu;

import Controller.EditableLevelController;
import Controller.GameController;
import Controller.LevelController;
import Controller.PlayableLevelController;
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
     * @param levelID The level ID
     * @param frame The frame
     * @param gameController The game controller
     * @Author Léonard Amsler - s231715
     */
    public static void preparePlayableLevel(LevelID levelID, JFrame frame, GameController gameController) {
        prepareLevel(levelID, frame, gameController, false);
    }

    public static void prepareEditableLevel(LevelID levelID, JFrame frame, GameController gameController) {
        prepareLevel(levelID, frame, gameController, true);
    }

    public static void prepareNewEditableLevel(JFrame frame, GameController gameController) {
        prepareLevel(LevelID.NEW_LEVEL, frame, gameController, true);
    }

    private static void prepareLevel(LevelID levelID, JFrame frame, GameController gameController, boolean editable) {
        gameController.setCurrentLevelID(levelID, editable);

        LevelController levelController;
        LevelPanel levelPanel;

        if (editable) {
            levelController = new EditableLevelController((EditableLevel) gameController.getCurrentLevel());
            levelPanel = new EditableLevelPanel((EditableLevelController) levelController);
        } else {
            levelController = new PlayableLevelController((PlayableLevel) gameController.getCurrentLevel());
            levelPanel = new PlayableLevelPanel((PlayableLevelController) levelController);
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
