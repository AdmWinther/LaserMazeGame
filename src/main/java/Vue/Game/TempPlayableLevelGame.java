package Vue.Game;

import Controller.EditableLevelController;
import Controller.GameController;
import Controller.PlayableLevelController;
import Model.Classes.Level.EditableLevel;
import Model.Classes.Level.LevelID;
import Model.Classes.Level.PlayableLevel;
import Vue.Level.EditableLevelPanel;
import Vue.Level.LevelPanel;
import Vue.Level.PlayableLevelPanel;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


/**
 * Main class of the UI
 * This is the entry point of the application
 *
 * @author Léonard Amsler - s231715
 */
public class TempPlayableLevelGame {

    JFrame frame;
    LevelPanel levelPanel;
    GameController gameController;

    public TempPlayableLevelGame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");

        gameController = new GameController();
        gameController.setCurrentLevelID(new LevelID("level6"), false);

        PlayableLevelController levelController = new PlayableLevelController((PlayableLevel) gameController.getCurrentLevel());
        levelPanel = new PlayableLevelPanel(levelController);
        frame.add(levelPanel);
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                levelPanel.resize();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
        frame.pack();
    }

    public static void main(String[] args) {
        TempPlayableLevelGame game = new TempPlayableLevelGame();
        game.start();
    }

    public void start() {
        frame.setVisible(true);
    }

}