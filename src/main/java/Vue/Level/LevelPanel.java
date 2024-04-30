package Vue.Level;

import Controller.GameController;
import Controller.LevelController;
import Controller.LoginController;
import Vue.Handlers.LevelMouseHandler;
import Vue.Handlers.TokenKeyboardHandler;
import Vue.Handlers.TokenMouseHandler;
import Vue.Level.UILayers.ExtrasUI;
import Vue.Level.UILayers.LaserUI;
import Vue.Level.UILayers.TilesUI;
import Vue.Level.UILayers.TokensUI;
import Vue.Utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


/**
 * This class has the responsibility to control the level panel.
 *
 * @author Léonard Amsler
 * @author Nathan Gromb
 */
public abstract class LevelPanel<LevelControllerType extends LevelController> extends JPanel implements Runnable {
	// Performance settings
	public static final int FPS = 60;
	public static final int FRAME_TIME = 1000 / FPS;
	// Borders
	public static final int HORIZONTAL_BORDER = 2;
	public static final int VERTICAL_BORDER = 1;
	public static final int WALL_THICKNESS = 1;
	// Tile size settings
	public static final int ORIGINAL_TILE_SIZE = 16;
	// Scale
	public static final int SCALE = 3;

	// Config
	private final LevelPanelConfig levelPanelConfig = new LevelPanelConfig();
	private final TokensUI tokensUI;
	private final TilesUI tilesUI;
	private final LaserUI laserUI;
	// Mouse handlers
	private final LevelMouseHandler levelMouseHandler;
	private final TokenMouseHandler tokenMouseHandler;
	// Keyboard handler
	private final TokenKeyboardHandler tokenKeyboardHandler;
	// Controllers
	private LevelControllerType levelController;
	private LoginController loginController;
	// Objects to draw
	private ExtrasUI extrasUI;
	private JFrame frame;
	private GameController gameController;
	// Thread
	private Thread gameThread;

	/**
	 * Constructor of the level panel class
	 *
	 * @param levelController - The level controller
	 * @author Léonard Amsler - s231715
	 */
	public LevelPanel(JFrame frame, GameController gameController, LevelControllerType levelController, LoginController loginController) {

		this.levelController = levelController;

		int boardWidth = levelController.getWidth();
		int boardHeight = levelController.getHeight();

		this.levelPanelConfig.setMaxCol(boardWidth + 2 * (HORIZONTAL_BORDER + WALL_THICKNESS));
		this.levelPanelConfig.setMaxRow(boardHeight + 2 * (VERTICAL_BORDER + WALL_THICKNESS));

		this.levelPanelConfig.setScreenWidth(levelPanelConfig.getMaxCol() * levelPanelConfig.getTileWidth());
		this.levelPanelConfig.setScreenHeight(levelPanelConfig.getMaxRow() * levelPanelConfig.getTileHeight());

		setPreferredSize(new Dimension(levelPanelConfig.getScreenWidth(), levelPanelConfig.getScreenHeight()));
		setDoubleBuffered(true);

		levelPanelConfig.setWidthOffset((this.levelPanelConfig.getScreenWidth() - boardWidth * levelPanelConfig.getTileWidth()) / 2);
		levelPanelConfig.setHeightOffset((this.levelPanelConfig.getScreenHeight() - boardHeight * levelPanelConfig.getTileHeight()) / 2);

		tilesUI = new TilesUI(this);
		tokensUI = new TokensUI(this, levelController);
		laserUI = new LaserUI(this, levelController);

		levelMouseHandler = new LevelMouseHandler(this);
		addMouseListener(levelMouseHandler);
		tokenMouseHandler = new TokenMouseHandler(this, levelController, tokensUI);
		addMouseListener(tokenMouseHandler);
		addMouseMotionListener(tokenMouseHandler);
		tokenKeyboardHandler = new TokenKeyboardHandler(levelController);
		addKeyListener(tokenKeyboardHandler);

		this.loginController = loginController;
		this.frame = frame;
		this.gameController = gameController;

		setFocusable(true);
		requestFocus();

		start();

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resize();
			}
		});
	}

	/**
	 * Start the game thread
	 *
	 * @author Léonard Amsler - s231715
	 */
	public void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Resize the level panel
	 * This method is called when the window is resized
	 *
	 * @author Léonard Amsler - s231715
	 */
	public void resize() {
		// Get the size of the screen
		Dimension screenSize = getSize();
		levelPanelConfig.setScreenWidth(screenSize.width);
		levelPanelConfig.setScreenHeight(screenSize.height);

		// Calculate the new tile size
		int newTileWidth = levelPanelConfig.getScreenWidth() / levelPanelConfig.getMaxCol();
		int newTileHeight = levelPanelConfig.getScreenHeight() / levelPanelConfig.getMaxRow();

		// Set the new tile size
		levelPanelConfig.setTileWidth(newTileWidth);
		levelPanelConfig.setTileHeight(newTileHeight);

		// Calculate the new offsets
		int boardWidth = levelController.getWidth();
		int boardHeight = levelController.getHeight();

		levelPanelConfig.setWidthOffset((levelPanelConfig.getMaxCol() - boardWidth) / 2 * levelPanelConfig.getTileWidth());
		levelPanelConfig.setHeightOffset((levelPanelConfig.getMaxRow() - boardHeight) / 2 * levelPanelConfig.getTileHeight());
	}

	/**
	 * Run method of the game thread
	 * It is the game engine, it will update the game state and repaint the screen at a fixed frame rate
	 * It follows the delta time pattern: <a href="https://en.wikipedia.org/wiki/Delta_timing"> description of the pattern </a>
	 *
	 * @author Léonard Amsler - s231715
	 */
	@Override
	public void run() {

		double delta = 0;
		long lastTime = System.currentTimeMillis();
		long currentTime;

		int lastSecond = 0;

		while (gameThread != null) {
			currentTime = System.currentTimeMillis();
			delta += (currentTime - lastTime) / (double) FRAME_TIME;
			lastTime = currentTime;

			if (currentTime / 1000 != lastSecond) {
				lastSecond = (int) (currentTime / 1000);
			}

			if (delta >= 1) {
				repaint();
				delta--;
			}
		}
	}

	/**
	 * Paint the component
	 *
	 * @param g - The graphics object
	 * @author Léonard Amsler - s231715
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocus();
	}

	/**
	 * Draw the tiles on the screen
	 *
	 * @param g2d The 2d graphics object
	 * @author Nathan Gromb
	 */
	public void drawTiles(Graphics2D g2d) {
		tilesUI.draw(g2d);
	}

	/**
	 * Draw the lasers on the screen
	 *
	 * @param g2d The 2d graphics object
	 * @author Nathan Gromb
	 */
	public void drawLasers(Graphics2D g2d) {
		laserUI.draw(g2d);
	}

	/**
	 * Draw the tokens on the screen
	 *
	 * @param g2d The 2d graphics object
	 * @author Nathan Gromb
	 */
	public void drawTokens(Graphics2D g2d) {
		tokensUI.draw(g2d);
	}

	/**
	 * Get the frames per second
	 *
	 * @return The frames per second of the game engine
	 * @author Nathan Gromb
	 */
	public int getFPS() {
		return FPS;
	}

	/**
	 * Get the extras UIs
	 *
	 * @return The extras UIs of the level panel
	 * @author Nathan Gromb
	 */
	public ExtrasUI getExtrasUI() {
		return extrasUI;
	}

	/**
	 * Exits the level
	 *
	 * @author Léonard Amsler
	 */
	public void exitLevel() {
		FrameUtil.removeLevel(frame);

		switch (gameController.getLevelType()) {
			case CAMPAIGN -> {
				FrameUtil.createCampaignMenuIfNotExists(frame, gameController, loginController);
				FrameUtil.refreshCampaignMenu(frame, gameController, loginController);
				FrameUtil.displayCampaignMenu(frame);
			}
			case SANDBOX -> {
				FrameUtil.createSandboxMenuIfNotExists(frame, gameController, loginController);
				FrameUtil.refreshSandboxMenu(frame, gameController, loginController);
				FrameUtil.displaySandboxMenu(frame);
			}
			case RANDOM -> {
				FrameUtil.createMainMenuIfNotExists(frame, gameController, loginController);
				FrameUtil.displayMainMenu(frame);
			}
		}
	}

	public LevelPanelConfig getLevelPanelConfig() {
		return levelPanelConfig;
	}

	public LevelControllerType getLevelController() {
		return levelController;
	}

	public void setLevelController(LevelControllerType levelController) {
		this.levelController = levelController;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public LevelMouseHandler getLevelMouseHandler() {
		return levelMouseHandler;
	}

	public TokenMouseHandler getTokenMouseHandler() {
		return tokenMouseHandler;
	}

	public TokenKeyboardHandler getTokenKeyboardHandler() {
		return tokenKeyboardHandler;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public Thread getGameThread() {
		return gameThread;
	}

	public void setGameThread(Thread gameThread) {
		this.gameThread = gameThread;
	}
}













