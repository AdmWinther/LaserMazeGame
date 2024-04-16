package Vue.Game;

import Controller.Handlers.MouseHandler;
import Model.Classes.Level;
import Vue.Token.TokenManagerUI;
import Vue.UIObjects.ObjectsManager;
import Vue.UITile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen configuration
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48
    // Performance settings
    final int fps = 60;
    final int frameTime = 1000 / fps; // 16
    public int maxCol;
    public int screenWidth;
    public int maxRow;
    public int screenHeight;
    public Level level;
    // Object manager
    public ObjectsManager objectsManager;
    // Token manager
    public TokenManagerUI tokenManager;
    int widthOffset; // 240
    int heightOffset; // 120
    // Game thread
    Thread gameThread;
    // Tile manager
    TileManager tileManager;
    // Mouse handler
    MouseHandler mouseHandler;


    public GamePanel(Level level) {
        this.level = level;

        this.maxCol = level.tokenManager().getWidthX() + 6;
        this.maxRow = level.tokenManager().getHeightY() + 4;

        this.screenWidth = maxCol * tileSize;
        this.screenHeight = maxRow * tileSize;

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        System.out.println("Dimension: " + level.tokenManager().getWidthX() + "x" + level.tokenManager().getHeightY());

        widthOffset = (this.screenWidth - level.tokenManager().getWidthX() * tileSize) / 2;
        heightOffset = (this.screenHeight - level.tokenManager().getHeightY() * tileSize) / 2;

        System.out.println("Screen Width: " + screenWidth);
        System.out.println("Screen Height: " + screenHeight);
        System.out.println("Tile Size: " + tileSize);
        System.out.println("Width Offset: " + widthOffset);
        System.out.println("Height Offset: " + heightOffset);

        tileManager = new TileManager(this);
        tokenManager = new TokenManagerUI(level, this);
        mouseHandler = new MouseHandler(this, level, widthOffset, heightOffset);
        objectsManager = new ObjectsManager(this);

        addMouseListener(mouseHandler);

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
                update();
                repaint();
                delta--;
                count++;
            }
        }
    }

    public void update() {
        // Update game logic
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);
        tokenManager.draw(g2d, widthOffset, heightOffset);
        objectsManager.draw(g2d);

        g2d.dispose();
    }

}













