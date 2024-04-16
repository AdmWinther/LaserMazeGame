package Vue.Game;

import Controller.Game.GameController;
import Model.Classes.Level;

import javax.swing.*;

public class Game {

    JFrame frame;
    GamePanel gamePanel;
    GameController gameController;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");

        GameController gameController = new GameController();
        gameController.setCurrentLevelID("level_10x10_2TokensOnBoard_1TokenToPlace");
        Level level = gameController.getCurrentLevel();

        gamePanel = new GamePanel(level);

        frame.add(gamePanel);
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
