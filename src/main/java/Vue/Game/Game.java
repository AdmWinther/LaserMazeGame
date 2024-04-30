package Vue.Game;

import Controller.GameController;
import Controller.LoginController;
import Vue.Constants.JComponentsNames;
import Vue.Constants.ResourcePaths;
import Vue.SoundEffects.SoundPaths;
import Vue.SoundEffects.SoundPlayer;
import Vue.Utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Main class of the UI
 * This is the entry point of the application.
 * It is responsible for initializing the game.
 *
 * @author Léonard Amsler - s231715
 * @author Hugo Demule
 */
public class Game {

	// find the OS max width and height for fullscreen
	public final double SCREEN_RATIO = 8.0 / 6;
	public final int INIT_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.9);
	public final int INIT_WIDTH = (int) (INIT_HEIGHT * SCREEN_RATIO);


	private final int TILE_WIDTH = INIT_WIDTH / 15;
	private final int TILE_HEIGHT = INIT_WIDTH / 15;
	final JFrame frame;
	SoundPlayer soundPlayer;


	public Game() {
		frame = new JFrame();
		frame.setLayout(new CardLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle(JComponentsNames.Label.LASER_MAZE);
		frame.setPreferredSize(new java.awt.Dimension(INIT_WIDTH, INIT_HEIGHT));

		double aspectRatio = INIT_WIDTH / (double) INIT_HEIGHT;
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				frame.setSize(frame.getWidth(), (int) (frame.getWidth() / aspectRatio));
			}
		});

		ImageIcon img = new ImageIcon(ResourcePaths.Icons.GAME_ICON);
		frame.setIconImage(img.getImage());

		GameController gameController = new GameController(this);
		LoginController loginController = new LoginController();

		FrameUtil.createLoginMenuIfNotExists(frame, loginController, gameController);
		FrameUtil.displayLoginMenu(frame);

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
		SoundPlayer.play(SoundPaths.MAIN_MENU_MUSIC_PATH, true);
	}

	/**
	 * Get the current tile dimension
	 *
	 * @return The current tile dimension
	 * @author Hugo Demule
	 */
	public Dimension getCurrentTileDimension() {
		return new Dimension(TILE_WIDTH, TILE_HEIGHT);
	}
}