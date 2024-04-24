package Vue.Level;

import Controller.LevelController;
import Controller.PlayableLevelController;
import Vue.Handlers.LevelMouseHandler;

import javax.swing.*;
import java.awt.*;

public class PlayableLevelPanel extends JPanel implements Runnable {

    // Borders
    public final int horizontalBorder = 2;
    public final int verticalBorder = 1;
    public final int wallThickness = 1;
    // Tile size settings
    final int originalTileSize = 16;
    // Performance settings
    final int fps = 60;
    final int frameTime = 1000 / fps;
    // Level configuration, screen size in tiles
    public int maxCol;
    public int maxRow;
    // Screen size in pixels
    public int screenWidth;
    public int screenHeight;
    // Controllers
    public PlayableLevelController levelController;

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


    public PlayableLevelPanel(PlayableLevelController levelController) {

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

        UIObjects = new UIObjects(this);
        UITiles = new UITiles(this, levelController);
        UITokens = new UITokens(this, levelController);
        UILaser = new UILaser(this, levelController);

        levelMouseHandler = new LevelMouseHandler(this, levelController, UITokens);
        addMouseListener(levelMouseHandler);

        setFocusable(true);
        requestFocus();

        start();
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }


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
}













