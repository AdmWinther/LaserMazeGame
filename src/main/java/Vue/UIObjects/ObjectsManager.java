package Vue.UIObjects;

import Vue.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectsManager {

    private final GamePanel gamePanel;
    // bin rectangle, in px (x, y, width, height)
    private final Object[] objects;
    private final Map<String, Rectangle2D> placedObjects = new HashMap<>();

    public ObjectsManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        objects = new Object[10];
        initializeObjectSet();
        setPlacedObjects();
    }

    public void initializeObjectSet() {
        try {
            objects[0] = new Object();
            objects[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/bin.png")));

            objects[1] = new Object();
            objects[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/reset.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void placeObject(String objectName, int x, int y) {
        placedObjects.put(objectName, new Rectangle(x, y, gamePanel.tileSize, gamePanel.tileSize));
    }

    public void setPlacedObjects() {
        int width = gamePanel.screenWidth;
        int height = gamePanel.screenHeight;
        double rightPadding = gamePanel.tileSize * 1.5;
        int tileSize = gamePanel.tileSize;

        // Place bin on the right side of the screen
        placeObject("bin", (int) (width - rightPadding), height / 2 - tileSize / 2);

        rightPadding = gamePanel.tileSize;
        // Place reset button on the top right corner of the screen
        placeObject("reset", (int) (width - rightPadding), 0);
    }

    public void draw(Graphics2D g2d) {
        for (Map.Entry<String, Rectangle2D> entry : placedObjects.entrySet()) {
            Rectangle2D rectangle = entry.getValue();
            String objectName = entry.getKey();
            switch (objectName) {
                case "bin" ->
                        g2d.drawImage(objects[0].image, rectangle.getBounds().x, rectangle.getBounds().y, (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
                case "reset" ->
                        g2d.drawImage(objects[1].image, rectangle.getBounds().x, rectangle.getBounds().y, (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
            }
        }
    }

    public Map<String, Rectangle2D> getPlacedObjects() {
        return placedObjects;
    }

}
