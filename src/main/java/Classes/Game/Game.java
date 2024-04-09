package Classes.Game;

import javax.swing.*;

public class Game {

    JFrame frame;
    GamePanel gamePanel;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");
        gamePanel = new GamePanel();
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
