package Vue.Game;

import Controller.GameController;
import Controller.LoginController;
import Vue.LoginMenu.LoginMenu;
import Vue.SoundEffects.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static Vue.MenuPanels.LevelPreparation.showPanel;

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
        int INIT_WIDTH = 800;
        int INIT_HEIGHT = 600;

        frame.setPreferredSize(new java.awt.Dimension(INIT_WIDTH, INIT_HEIGHT));

        ImageIcon img = new ImageIcon("./src/main/java/Vue/Resources/Tokens/icon.png");
        frame.setIconImage(img.getImage());

        gameController = new GameController();
        LoginController loginController = new LoginController();
        LoginMenu loginMenu = new LoginMenu(frame, loginController, gameController);
        frame.add(loginMenu, "LoginMenu");
        showPanel(frame, "LoginMenu");

        double aspectRatio = INIT_WIDTH / (double) INIT_HEIGHT;
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.setSize(frame.getWidth(), (int) (frame.getWidth() / aspectRatio));
            }
        });


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
        Sound.playMainMenuTheme();
    }
}