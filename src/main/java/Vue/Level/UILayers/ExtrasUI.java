package Vue.Level.UILayers;

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

    public ExtrasUI(LevelPanel levelPanel) {
        this.levelPanel = levelPanel;
        initializeObjectSet();
        setPlacedObjects();
    }

    public void initializeObjectSet() {
        try {
            BufferedImage binImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/bin.png")));
            BufferedImage resetImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/reset.png")));

            objectImages.put("bin", binImage);
            objectImages.put("reset", resetImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void placeObject(String objectName, int x, int y) {
        placedObjects.put(objectName, new Rectangle(x, y, levelPanel.tileWidth, levelPanel.tileHeight));
    }

    public void setPlacedObjects() {
        int width = levelPanel.screenWidth;
        int height = levelPanel.screenHeight;
        double rightPadding = levelPanel.tileWidth * 1.5;

        // Place bin on the right side of the screen
        placeObject("bin", (int) (width - rightPadding), height / 2 - levelPanel.tileHeight / 2);

        rightPadding = levelPanel.tileWidth;
        // Place reset button on the top right corner of the screen
        placeObject("reset", (int) (width - rightPadding), 0);
    }

    public void draw(Graphics2D g2d) {
        setPlacedObjects();
        for (Map.Entry<String, Rectangle2D> entry : placedObjects.entrySet()) {
            Rectangle2D rectangle = entry.getValue();
            String objectName = entry.getKey();
            BufferedImage objectImage = objectImages.get(objectName);
            g2d.drawImage(objectImage, (int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
        }
    }

    public Map<String, Rectangle2D> getPlacedObjects() {
        return placedObjects;
    }

}
