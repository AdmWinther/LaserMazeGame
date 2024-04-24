package Vue.Game;

import Controller.GameController;
import Controller.LevelController;
import Vue.Level.LevelPanel;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Game {

    JFrame frame;
    LevelPanel levelPanel;
    GameController gameController;

    private final double aspectRatio;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");

        // TODO: This is a hard-coded level. We should have a gamePanel and the user should be able to select the level they want to play.
        gameController = new GameController();
        gameController.setCurrentLevelID("10x10_AlmostCompleted");

        LevelController levelController = new LevelController(gameController.getCurrentLevel());
        levelPanel = new LevelPanel(levelController);

        aspectRatio = (double) levelPanel.maxCol / levelPanel.maxRow;

        frame.add(levelPanel);
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.setSize(frame.getWidth(), (int) (frame.getWidth() / aspectRatio));
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
        Game game = new Game();
        game.start();
    }

    public void start() {
        frame.setVisible(true);
    }

}