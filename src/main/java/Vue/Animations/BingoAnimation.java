package Vue.Animations;

import Vue.Interfaces.Animation;
import Vue.Level.LevelPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * This class is responsible for the animation of the "Bingo" message that appears when the player wins the game.
 *
 * @author Nathan Gromb
 */
public class BingoAnimation implements Animation {

    private static final int MAX_DURATION = 4000;
    private final LevelPanel levelPanel;
    private final BufferedImage image;

    private boolean running = false;
    private int duration = 0;

    /**
     * Constructor of the BingoAnimation class.
     *
     * @param levelPanel The level panel where the animation will be displayed.
     * @author Nathan Gromb
     */
    public BingoAnimation(LevelPanel levelPanel) {
        this.levelPanel = levelPanel;

        BufferedImage loadedImage;
        try {
            loadedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/bingo.png")));
        } catch (Exception e) {
            loadedImage = null;
            e.printStackTrace();
        }

        this.image = loadedImage;
    }

    /**
     * This method starts the animation.
     *
     * @author Nathan Gromb
     */
    @Override
    public void start() {
        running = true;
    }

    /**
     * This method stops the animation.
     *
     * @author Nathan Gromb
     */
    @Override
    public void stop() {
        running = false;
    }

    /**
     * This method checks if the animation is running.
     *
     * @return boolean True if the animation is running, false otherwise.
     * @author Nathan Gromb
     */
    @Override
    public boolean isRunning() {
        return running;
    }

    /**
     * This method draws the animation on the screen.
     *
     * @param g2d The graphics object used to draw the animation.
     * @author Nathan Gromb
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (running) {
            int x = Math.floorDiv((levelPanel.getLevelPanelConfig().getMaxCol() - 3) * levelPanel.getLevelPanelConfig().getTileWidth(), 2);
            int y = Math.floorDiv((levelPanel.getLevelPanelConfig().getMaxRow() - 1) * levelPanel.getLevelPanelConfig().getTileHeight(), 2);
            g2d.drawImage(image, x, y, 3 * levelPanel.getLevelPanelConfig().getTileWidth(), levelPanel.getLevelPanelConfig().getTileHeight(), null);
        }

        duration += 1000 / LevelPanel.FPS;

        if (duration > MAX_DURATION) {
            stop();
        }
    }
}
