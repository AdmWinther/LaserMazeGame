package Classes.Game;

import Classes.Handlers.MouseHandler;
import Classes.Level;
import Classes.Tokens.TokenManager;
import Classes.UIObjects.ObjectsManager;
import Classes.UITile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public final int maxCol = 15;
    public final int maxRow = 8;
    // Screen configuration
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48
    public final int screenWidth = maxCol * tileSize; // 720
    public final int screenHeight = maxRow * tileSize; // 480
    // Performance settings
    final int fps = 60;
    final int frameTime = 1000 / fps; // 16
    public Level level;
    // Object manager
    public ObjectsManager objectsManager;
    int widthOffset; // 240
    int heightOffset; // 120
    // Game thread
    Thread gameThread;
    // Tile manager
    TileManager tileManager;
    // Token manager
    TokenManager tokenManager;
    // Mouse handler
    MouseHandler mouseHandler;


    public GamePanel(Level level) {
        this.level = level;

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        widthOffset = (this.screenWidth - level.getBoard().getWidth() * tileSize) / 2;
        heightOffset = (this.screenHeight - level.getBoard().getHeight() * tileSize) / 2;

        tileManager = new TileManager(this);
        tokenManager = new TokenManager(level, this);
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













