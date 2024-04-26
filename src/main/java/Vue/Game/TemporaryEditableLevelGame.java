package Vue.Game;

import Controller.EditableLevelController;
import Controller.GameController;
import Controller.PlayableLevelController;
import Model.Classes.Level.EditableLevel;
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
public class TemporaryEditableLevelGame {

    JFrame frame;
    LevelPanel levelPanel;
    GameController gameController;

    public TemporaryEditableLevelGame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");

        gameController = new GameController();
        gameController.setCurrentLevelID("sandbox-level1", "editable");

        EditableLevelController levelController = new EditableLevelController((EditableLevel) gameController.getCurrentLevel());
        levelPanel = new EditableLevelPanel(levelController);
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
        TemporaryEditableLevelGame game = new TemporaryEditableLevelGame();
        game.start();
    }

    public void start() {
        frame.setVisible(true);
    }

}