package Vue.Game;

import Controller.GameController;
import Controller.LoginController;
import Vue.Constants.JComponentsNames;
import Vue.Constants.VueFilePaths;
import Vue.LoginMenu.LoginMenu;
import Vue.SoundEffects.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Main class of the UI
 * This is the entry point of the application
 *
 * @author Léonard Amsler - s231715
 */
public class Game {

    public final int INIT_WIDTH = 1200;
    public final int INIT_HEIGHT = 900;
    JFrame frame;
    GameController gameController;


    public Game() {
        frame = new JFrame();
        frame.setLayout(new CardLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(JComponentsNames.Label.LASER_MAZE);

        frame.setPreferredSize(new java.awt.Dimension(INIT_WIDTH, INIT_HEIGHT));

        ImageIcon img = new ImageIcon(VueFilePaths.GAME_ICON);
        frame.setIconImage(img.getImage());

        gameController = new GameController(this);
        LoginController loginController = new LoginController();
        LoginMenu loginMenu = new LoginMenu(frame, loginController, gameController);
        frame.add(loginMenu, JComponentsNames.FrameID.LOGIN);
        showPanel(frame, JComponentsNames.FrameID.LOGIN);

        double aspectRatio = INIT_WIDTH / (double) INIT_HEIGHT;
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // TODO change also if the height changes
                frame.setSize(frame.getWidth(), (int) (frame.getWidth() / aspectRatio));
            }
        });


        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Shows a panel
     *
     * @param frame     The frame
     * @param panelName The panel name
     * @author Léonard Amsler - s231715
     */
    public static void showPanel(JFrame frame, String panelName) {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), panelName);
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

    public Dimension getCurrentTileDimension() {
        int size = getCurrentFrameDimension().width / 10;
        return new Dimension(size, size);
    }

    /**
     * Get the current size of the frame
     *
     * @return The current size of the frame
     * @author Hugo Demule
     */
    public Dimension getCurrentFrameDimension() {
        return frame.getSize();
    }
}