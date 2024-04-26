package Vue.Game;

import Controller.GameController;
import Controller.LoginMenuController;
import Vue.LoginMenu.LoginMenu;

import javax.swing.*;
import java.awt.*;

import static Vue.MainMenu.LevelPreparation.showPanel;

/**
 * Main class of the UI
 * This is the entry point of the application
 *
 * @author Léonard Amsler - s231715
 */
public class Game {

    JFrame frame;
    GameController gameController;

    public Game() {
        frame = new JFrame();
        frame.setLayout(new CardLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle("Laser Maze");
        frame.setPreferredSize(new java.awt.Dimension(800, 600));

        ImageIcon img = new ImageIcon("./src/main/java/Vue/Resources/Tokens/icon.png");
        frame.setIconImage(img.getImage());

        gameController = new GameController();
        //MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, gameController);
        //frame.add(mainMenuPanel, "MainMenu");
        //showPanel(frame, "MainMenu");

        LoginMenuController loginMenuController = new LoginMenuController();
        LoginMenu loginMenu = new LoginMenu(frame, loginMenuController, gameController);
        frame.add(loginMenu, "LoginMenu");
        showPanel(frame, "LoginMenu");

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Entry point of the application
     * Create a new instance of the game and start it
     *
     * @param args Command line arguments (unused)
     * @author Léonard Amsler - s231715
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    /**
     * Start the game
     * Display the main menu
     *
     * @author Léonard Amsler - s231715
     */
    public void start() {
        frame.setVisible(true);
    }
}