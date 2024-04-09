package Classes.Game;

import Classes.UITile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public final int maxCol = 15;
    public final int maxRow = 10;
    // Screen configuration
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    final int screenWidth = maxCol * tileSize;
    final int screenHeight = maxRow * tileSize;
    // Performance settings
    final int fps = 60;
    final int frameTime = 1000 / fps;
    // Game thread
    Thread gameThread;
    // Tile manager
    TileManager tileManager;
    // Token manager


    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        tileManager = new TileManager(this);
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

        while (gameThread != null) {
            currentTime = System.currentTimeMillis();
            delta += (currentTime - lastTime) / (double) frameTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
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

        g2d.dispose();
    }

}













