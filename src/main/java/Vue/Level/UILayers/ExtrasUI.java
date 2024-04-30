package Vue.Level.UILayers;

import Controller.LevelController;
import Model.Classes.Token.Token;
import Vue.Interfaces.Drawable;
import Vue.Level.EditableLevelPanel;
import Vue.Level.LevelPanel;
import Vue.SoundEffects.Sound;
import Vue.Utils.FrameUtil;
import Vue.Utils.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * UI of the extra objects
 *
 * @author Léonard Amsler
 * @author Hussein (Adam)
 * @author Nathan Gromb
 */
public class ExtrasUI implements Drawable {

    public final LevelPanel levelPanel;
    private final Map<String, Rectangle2D> placedObjects;
    private final Map<String, BufferedImage> objectImages;

    /**
     * Constructor of the extras UI
     *
     * @param levelPanel The level panel
     */
    public ExtrasUI(LevelPanel levelPanel) {
        this(levelPanel, true);
    }

    /**
     * Constructor of the extras UI
     *
     * @param levelPanel The level panel
     * @param init       If the object set should be initialized
     * @author Léonard Amsler
     * @author Nathan Gromb
     */
    public ExtrasUI(LevelPanel levelPanel, boolean init) {
        this.levelPanel = levelPanel;

        placedObjects = new HashMap<>();
        objectImages = new HashMap<>();

        if (init) {
            loadObjectImages();
            setPlacedObjects();
        }
    }

    /**
     * Initialize the object set
     *
     * @author Léonard Amsler - s231715
     * @author Hussein (Adam)
     */
    public void loadObjectImages() {
        try {
            BufferedImage chestImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/chest.png")));
            BufferedImage resetImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/reset.png")));
            BufferedImage backImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/back.png")));
            BufferedImage bingoImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/bingo.png")));

            objectImages.put("chest", chestImage);
            objectImages.put("reset", resetImage);
            objectImages.put("back", backImage);
            objectImages.put("bingo", bingoImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the placed objects
     *
     * @author Léonard Amsler - s231715
     */
    public void setPlacedObjects() {
        int width = levelPanel.screenWidth;
        int height = levelPanel.screenHeight;
        double rightPadding = levelPanel.tileWidth * 1.5;

        // Place chest on the right side of the screen
        placeObject("chest", (int) (width - rightPadding), height / 2 - levelPanel.tileHeight / 2);

        rightPadding = levelPanel.tileWidth;
        // Place reset button in the top right corner of the screen
        placeObject("reset", (int) (width - rightPadding), 0);
        // Place back button On the left side of the reset button
        placeObject("back", (int) (width - 2 * rightPadding), 0);
    }

    /**
     * Place an object on the screen
     *
     * @param objectName The name of the object
     * @param x          The x coordinate
     * @param y          The y coordinate
     * @author Léonard Amsler - s231715
     */
    public void placeObject(String objectName, int x, int y) {
        int width = levelPanel.tileWidth;
        int height = levelPanel.tileHeight;

        placedObjects.put(objectName, new Rectangle(x, y, width, height));
    }

    /**
     * Draw the objects on the screen
     *
     * @param g2d - The 2d graphics object
     * @author Léonard Amsler - s231715
     */
    public void draw(Graphics2D g2d) {
        setPlacedObjects();
        for (Map.Entry<String, Rectangle2D> entry : placedObjects.entrySet()) {
            Rectangle2D rectangle = entry.getValue();
            String objectName = entry.getKey();
            BufferedImage objectImage = objectImages.get(objectName);
            g2d.drawImage(objectImage, (int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
        }
    }

    /**
     * Get the object images
     *
     * @return The object images
     * @author Nathan Gromb
     */
    public Map<String, BufferedImage> getObjectImages() {
        return objectImages;
    }

    /**
     * Handle the mouse release while a token is being dragged
     *
     * @param token      The token being dragged
     * @param pos        The mouse position
     * @param controller The level controller
     */
    public void handleTokenDrop(Token token, Position pos, LevelController controller) {
        Rectangle2D chest = placedObjects.get("chest");
        if (chest.contains(pos.x(), pos.y())) {
            controller.transferTokenToUnplacedTokens(token);
        }
    }

    /**
     * Get the placed objects
     *
     * @return The placed objects
     * @author Nathan Gromb
     */
    public Map<String, Rectangle2D> getPlacedObjects() {
        return placedObjects;
    }

    /**
     * Handle the click on an extra object
     *
     * @param pos The position of the click
     * @author Nathan Gromb
     */
    public void handleClick(Position pos) {
        Rectangle2D reset = placedObjects.get("reset");
        if (reset.contains(pos.x(), pos.y())) {
            Sound.playButtonSound();
            levelPanel.levelController.resetLevel();
        }

        Rectangle2D back = placedObjects.get("back");
        if (back.contains(pos.x(), pos.y())) {
            Sound.playButtonSound();
            if (levelPanel instanceof EditableLevelPanel) {
                ((EditableLevelPanel) levelPanel).saveLevel();
                FrameUtil.createSandboxMenuIfNotExists(levelPanel.frame, levelPanel.gameController, levelPanel.loginController);
            }
            levelPanel.exitLevel();
        }
    }

    public LevelPanel getLevelPanel() {
        return levelPanel;
    }
}
