package Vue.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class is responsible for placing and drawing objects on the screen
 *
 * @Author Léonard Amsler - s231715
 */
public class UIObjects {

    private final LevelPanel levelPanel;
    private final Map<String, Rectangle2D> placedObjects = new HashMap<>();
    private final Map<String, BufferedImage> objectImages = new HashMap<>();

    /**
     * Constructor of the UI objects class
     *
     * @param levelPanel - The level panel
     * @Author Léonard Amsler - s231715
     */
    public UIObjects(LevelPanel levelPanel) {
        this.levelPanel = levelPanel;
        initializeObjectSet();
        setPlacedObjects();
    }

    /**
     * Initialize the object set
     *
     * @Author Léonard Amsler - s231715
     * @Author Hussein (Adam)
     */
    public void initializeObjectSet() {
        try {
            BufferedImage binImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/bin.png")));
            BufferedImage resetImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/reset.png")));
            BufferedImage backImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/back.png")));

            objectImages.put("bin", binImage);
            objectImages.put("reset", resetImage);
            objectImages.put("back", backImage);
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
        placedObjects.put(objectName, new Rectangle(x, y, levelPanel.tileWidth, levelPanel.tileHeight));
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

        // Place bin on the right side of the screen
        placeObject("bin", (int) (width - rightPadding), height / 2 - levelPanel.tileHeight / 2);

        rightPadding = levelPanel.tileWidth;
        // Place reset button on the top right corner of the screen
        placeObject("reset", (int) (width - rightPadding), 0);
        // Place back button On the left side of the reset button
        placeObject("back", (int) (width - 2 * rightPadding), 0);
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

    /**
     * Get the placed objects
     *
     * @return The placed objects
     * @Author Léonard Amsler - s231715
     */
    public Map<String, Rectangle2D> getPlacedObjects() {
        return placedObjects;
    }

}
