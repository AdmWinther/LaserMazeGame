package Vue.MainMenu;

import Controller.GameController;
import Controller.LevelController;
import Controller.LoginController;
import Vue.Level.LevelPanel;

import javax.swing.*;
import java.awt.*;

public class LevelPreparation {

    /**
     * Prepare the level
     *
     * @param levelID        - The level ID
     * @param frame          - The frame
     * @param gameController - The game controller
     * @Author Léonard Amsler - s231715
     */
    public static void prepareLevel(String levelID, JFrame frame, GameController gameController, LoginController loginController) {
        gameController.setCurrentLevelID(levelID);
        LevelController levelController = new LevelController(gameController.getCurrentLevel());

        LevelPanel levelPanel = new LevelPanel(frame, gameController, levelController, loginController);

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
