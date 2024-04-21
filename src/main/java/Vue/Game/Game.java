package Vue.Game;

import Controller.GameController;
import Controller.LevelController;
import Vue.Level.LevelPanel;
import Vue.MainMenu.MainMenuPanel;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Game {

    JFrame frame;
    LevelPanel levelPanel;
    GameController gameController;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");
        ImageIcon img = new ImageIcon("./src/main/java/Vue/Resources/Tokens/icon.png");
        frame.setIconImage(img.getImage());

//        this.prepareLevel("level6");
        MainMenuPanel mainMenuPanel = new MainMenuPanel(frame);
        frame.add(mainMenuPanel);
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