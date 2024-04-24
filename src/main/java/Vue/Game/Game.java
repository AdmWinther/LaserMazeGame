package Vue.Game;

import Controller.GameController;
import Controller.PlayableLevelController;
import Vue.Level.PlayableLevelPanel;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Game {

    JFrame frame;
    PlayableLevelPanel playableLevelPanel;
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

        PlayableLevelController levelController = new PlayableLevelController(gameController.getCurrentLevel());
        playableLevelPanel = new PlayableLevelPanel(levelController);
        frame.add(playableLevelPanel);

        aspectRatio = (double) playableLevelPanel.maxCol / playableLevelPanel.maxRow;

        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.setSize(frame.getWidth(), (int) (frame.getWidth() / aspectRatio));
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