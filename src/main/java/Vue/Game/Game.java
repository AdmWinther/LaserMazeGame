package Vue.Game;

import Controller.GameController;
import Controller.LevelController;
import Controller.PlayableLevelController;
import Vue.Level.PlayableLevelPanel;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Game {

    JFrame frame;
    PlayableLevelPanel playableLevelPanel;
    GameController gameController;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");

        // TODO: This is a hard-coded level. We should have a gamePanel and the user should be able to select the level they want to play.
        gameController = new GameController();
        gameController.setCurrentLevelID("level3");

        PlayableLevelController levelController = new PlayableLevelController(gameController.getCurrentLevel());
        playableLevelPanel = new PlayableLevelPanel(levelController);
        frame.add(playableLevelPanel);
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                playableLevelPanel.resize();
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