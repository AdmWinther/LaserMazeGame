package Vue.Level;

import Controller.GameController;
import Controller.LevelController;
import Controller.LoginController;
import Vue.Handlers.LevelMouseHandler;
import Vue.MainMenu.MainMenuPanel;
import Vue.SoundEffects.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Timer;
import java.util.TimerTask;

import static Vue.MainMenu.LevelPreparation.prepareLevel;
import static Vue.MainMenu.LevelPreparation.showPanel;

/**
 * This class is the panel for the level
 *
 * @Author Léonard Amsler - s231715
 */
public class LevelPanel extends JPanel implements Runnable {

    // Borders
    public final int horizontalBorder = 2;
    public final int verticalBorder = 1;
    public final int wallThickness = 1;
    // Tile size settings
    final int originalTileSize = 16;
    // Performance settings
    final int fps = 60;
    final int frameTime = 1000 / fps;
    private final Clip tada;
    // Level configuration, screen size in tiles
    public int maxCol;
    public int maxRow;
    // Screen size in pixels
    public int screenWidth;
    public int screenHeight;
    // Controllers
    public LevelController levelController;
    public LoginController loginController;
    // UI Tiles, Objects and Tokens
    public UIObjects UIObjects;
    public UITokens UITokens;
    public UITiles UITiles;
    public UILaser UILaser;
    public LevelMouseHandler levelMouseHandler;
    // Offsets, number of pixels to the top left corner of the level board
    public int widthOffset;
    public int heightOffset;
    int HScale = 3;
    public int tileWidth = originalTileSize * HScale;
    int VScale = 3;
    public int tileHeight = originalTileSize * VScale;
    // Thread
    Thread gameThread;
    JFrame frame;
    GameController gameController;
    private boolean tadaPlayed = false;
    private Timer timer;


    /**
     * Constructor of the level panel class
     *
     * @param levelController - The level controller
     * @author Léonard Amsler - s231715
     */
    public LevelPanel(JFrame frame, GameController gameController, LevelController levelController, LoginController loginController) {

        this.levelController = levelController;
        this.loginController = loginController;


        int boardWidth = levelController.getWidth();
        int boardHeight = levelController.getHeight();

        this.maxCol = boardWidth + 2 * (horizontalBorder + wallThickness);
        this.maxRow = boardHeight + 2 * (verticalBorder + wallThickness);

        this.screenWidth = maxCol * tileWidth;
        this.screenHeight = maxRow * tileHeight;

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDoubleBuffered(true);

        widthOffset = (this.screenWidth - boardWidth * tileWidth) / 2;
        heightOffset = (this.screenHeight - boardHeight * tileHeight) / 2;

        UIObjects = new UIObjects(this);
        UITiles = new UITiles(this, levelController);
        UITokens = new UITokens(this, levelController);
        UILaser = new UILaser(this, levelController);

        levelMouseHandler = new LevelMouseHandler(this, levelController, UITokens);
        addMouseListener(levelMouseHandler);

        this.tada = Sound.levelCompleted();

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
     * @Author Léonard Amsler - s231715
     */
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    /**
     * Run method of the game thread
     * It is the game engine, it will update the game state and repaint the screen at a fixed frame rate
     * It follows the delta time pattern: <a href="https://en.wikipedia.org/wiki/Delta_timing"> description of the pattern </a>
     *
     * @Author Léonard Amsler - s231715
     */
    @Override
    public void run() {

        double delta = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;

        int count = 0;
        int lastSecond = 0;

        while (gameThread != null) {
            currentTime = System.currentTimeMillis();
            delta += (currentTime - lastTime) / (double) frameTime;
            lastTime = currentTime;

            if (currentTime / 1000 != lastSecond) {
                lastSecond = (int) (currentTime / 1000);
                System.out.println("FPS: " + count);
                count = 0;
            }

            if (this.levelController.levelComplete()) {
                if (!tadaPlayed) {
                    this.tada.start();
                    this.tadaPlayed = true;
                    this.timer = new Timer("delayAfterLevelCompleted");
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            gameThread = null;
                            int currentLevel = levelController.getLevelSerialNr();
                            int campaignProgression = loginController.getCampaignProgress();
                            if (gameController.getCampaignGameMode()) {
                                //if in the campaign mode, it should go to the next level.
                                if (currentLevel == campaignProgression + 2) { // +2 because the first progression is -1 and that the first level is level1
                                    loginController.incrementProgression();
                                }
                                prepareLevel("level" + (levelController.getLevelSerialNr() + 1), frame, gameController, loginController);
                            } else {
                                //if we are in Random level mode
                                MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, gameController, loginController);
                                frame.add(mainMenuPanel, "MainMenu");
                                showPanel(frame, "MainMenu");
                                frame.pack();
                            }
                        }

                        ;
                    }, 2000);
                }

                this.UIObjects.drawBingo();
                removeMouseListener(this.levelMouseHandler);
            }

            if (delta >= 1) {
                repaint();
                delta--;
                count++;
            }
        }
    }

    /**
     * Paint the component
     *
     * @param g - The graphics object
     * @Author Léonard Amsler - s231715
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        UITiles.draw(g2d);
        UILaser.draw(g2d);
        UITokens.draw(g2d);
        UIObjects.draw(g2d);


        g2d.dispose();
    }

    /**
     * Resize the level panel
     * This method is called when the window is resized
     *
     * @Author Léonard Amsler - s231715
     */
    public void resize() {
        // Get the size of the screen
        Dimension screenSize = getSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        // Calculate the new tile size
        int newTileWidth = screenWidth / maxCol;
        int newTileHeight = screenHeight / maxRow;

        // Set the new tile size
        tileWidth = newTileWidth;
        tileHeight = newTileHeight;

        // Calculate the new offsets
        int boardWidth = levelController.getWidth();
        int boardHeight = levelController.getHeight();

        widthOffset = (maxCol - boardWidth) / 2 * tileWidth;
        heightOffset = (maxRow - boardHeight) / 2 * tileHeight;

    }

    public void exitLevel() {
        gameThread = null;
        MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, gameController, loginController);
        if (gameController.getCampaignGameMode()) {
            //if in the campaign mode, it should go to the next level.
            mainMenuPanel.displayCampaignLevels(frame);
        } else {
            //if we are in Random level mode
            frame.add(mainMenuPanel, "MainMenu");
            showPanel(frame, "MainMenu");
            frame.pack();
        }
    }
}













