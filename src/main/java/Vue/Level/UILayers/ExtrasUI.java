package Vue.Level.UILayers;

import Controller.LevelController;
import Model.Classes.Level.Level;
import Model.Classes.Token.Token;
import Vue.Interfaces.Drawable;
import Vue.Level.LevelPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExtrasUI implements Drawable {

    private final LevelPanel levelPanel;
    private final Map<String, Rectangle2D> placedObjects = new HashMap<>();
    private final Map<String, BufferedImage> objectImages = new HashMap<>();

    public ExtrasUI(LevelPanel levelPanel, boolean init) {
        this.levelPanel = levelPanel;

        if (init) {
            initializeObjectSet();
            setPlacedObjects();
        }
    }

    public ExtrasUI(LevelPanel levelPanel) {
        this(levelPanel, true);
    }

    /**
     * Initialize the object set
     *
     * @Author Léonard Amsler - s231715
     * @Author Hussein (Adam)
     */
    public void initializeObjectSet() {
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
     * Place an object on the screen
     *
     * @param objectName - The name of the object
     * @param x          - The x coordinate
     * @param y          - The y coordinate
     * @Author Léonard Amsler - s231715
     */
    public void placeObject(String objectName, int x, int y) {
        int width = levelPanel.tileWidth;
        int height = levelPanel.tileHeight;
        if (objectName.equals("bingo")) {
            width *= 3;
        }
        placedObjects.put(objectName, new Rectangle(x, y, width, height));
    }

    /**
     * Set the placed objects
     *
     * @Author Léonard Amsler - s231715
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

//        placeObject("bingo", (int) (width - 6 * rightPadding), 3);
    }

    /**
     * Draw the objects on the screen
     *
     * @param g2d - The 2d graphics object
     * @Author Léonard Amsler - s231715
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

    public void drawBingo() {
        int x = Math.floorDiv((levelPanel.maxCol-3) * levelPanel.tileWidth, 2);
        int y = Math.floorDiv((levelPanel.maxRow-1) * levelPanel.tileHeight, 2);
        placeObject("bingo", x, y);
    }

    /**
     * Get the placed objects
     *
     * @return The placed objects
     * @Author Léonard Amsler - s231715
     */
    public Map<String, Rectangle2D> getPlacedObjects() {
        return placedObjects;
    }

    public Map<String, BufferedImage> getObjectImages() {
        return objectImages;
    }

    public void handleTokenDrop(Token token, int x, int y, LevelController controller) {
        Rectangle2D chest = getPlacedObjects().get("chest");
        if (chest.contains(x, y)) {
            controller.transferTokenToUnplacedTokens(token);
            System.out.println("Removed Token: " + token);
        }
    }

    public void handleClick(int x, int y) {
        Rectangle2D reset = levelPanel.getExtrasUI().getPlacedObjects().get("reset");
        if (reset.contains(x, y)) {
            levelPanel.levelController.resetLevel();
            System.out.println("Reset tokens");
        }
    }

    public LevelPanel getLevelPanel() {
        return levelPanel;
    }
}
