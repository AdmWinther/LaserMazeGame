package Vue.Game;

import Controller.GameController;
import Vue.MainMenu.MainMenuPanel;

import javax.swing.*;

public class Game {

    JFrame frame;
    JPanel currentPanel;

    GameController gameController;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle("Laser Maze");
        frame.setPreferredSize(new java.awt.Dimension(800, 600));

        ImageIcon img = new ImageIcon("./src/main/java/Vue/Resources/Tokens/icon.png");
        frame.setIconImage(img.getImage());

        currentPanel = new MainMenuPanel(frame);
        frame.getContentPane().add(currentPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        frame.setVisible(true);
    }
}