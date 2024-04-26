package Vue.MainMenu;

import Controller.GameController;
import Controller.PlayableLevelController;
import Model.Classes.Level.PlayableLevel;
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
    public static void preparePlayableLevel(String levelID, JFrame frame, GameController gameController) {
        prepareLevel(levelID, frame, gameController, false);
    }

    public static void prepareEditableLevel(String levelID, JFrame frame, GameController gameController) {
        prepareLevel(levelID, frame, gameController, true);
    }

    private static void prepareLevel(String levelID, JFrame frame, GameController gameController, boolean editable) {
        gameController.setCurrentLevelID(levelID, editable);
        PlayableLevelController levelController = new PlayableLevelController((PlayableLevel) gameController.getCurrentLevel());

        LevelPanel levelPanel = new PlayableLevelPanel(levelController);

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
