package Vue.Level;

import Controller.LevelController;
import Vue.Handlers.LevelMouseHandler;
import Vue.Handlers.TokenMouseHandler;
import Vue.Level.UILayers.ExtrasUI;
import Vue.Level.UILayers.LaserUI;
import Vue.Level.UILayers.TilesUI;
import Vue.Level.UILayers.TokensUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class LevelPanel extends JPanel implements Runnable {

    // Borders
    public final int horizontalBorder = 2;
    public final int verticalBorder = 1;
    public final int wallThickness = 1;
    // Level configuration, screen size in tiles
    public final int maxCol;
    public final int maxRow;
    // Tile size settings
    final int originalTileSize = 16;
    // Performance settings
    final int fps = 60;
    final int frameTime = 1000 / fps;
    // Screen size in pixels
    public int screenWidth;
    public int screenHeight;
    // Controllers
    public LevelController levelController;

    // Objects to draw
    public ExtrasUI extrasUI;
    public TokensUI tokensUI;
    public TilesUI tilesUI;
    public LaserUI laserUI;

    public LevelMouseHandler levelMouseHandler;
    public TokenMouseHandler tokenMouseHandler;
    // Offsets, number of pixels to the top left corner of the level board
    public int widthOffset;
    public int heightOffset;
    int HScale = 3;
    public int tileWidth = originalTileSize * HScale;
    int VScale = 3;
    public int tileHeight = originalTileSize * VScale;
    // Thread
    Thread gameThread;


    /**
     * Constructor of the level panel class
     *
     * @param levelController - The level controller
     * @author Léonard Amsler - s231715
     */
    public LevelPanel(LevelController levelController) {

        this.levelController = levelController;

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

        tilesUI = new TilesUI(this, levelController);
        tokensUI = new TokensUI(this, levelController);
        laserUI = new LaserUI(this, levelController);

        levelMouseHandler = new LevelMouseHandler(this, levelController);
        addMouseListener(levelMouseHandler);
        tokenMouseHandler = new TokenMouseHandler(this, levelController, tokensUI);
        addMouseListener(tokenMouseHandler);
        addMouseMotionListener(tokenMouseHandler);

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
    }

    public void drawTiles(Graphics2D g2d) {
        tilesUI.draw(g2d);
    }

    public void drawLasers(Graphics2D g2d) {
        laserUI.draw(g2d);
    }

    public void drawTokens(Graphics2D g2d) {
        tokensUI.draw(g2d);
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

    public int getFPS() {
        return fps;
    }

    public ExtrasUI getExtrasUI() {
        return extrasUI;
    }
}













