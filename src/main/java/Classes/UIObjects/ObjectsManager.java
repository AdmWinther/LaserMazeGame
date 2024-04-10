package Classes.UIObjects;

import Classes.Game.GamePanel;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void placeObject(String objectName, int x, int y) {
        placedObjects.put(objectName, new Rectangle(x, y, gamePanel.tileSize, gamePanel.tileSize));
    }

    public void setPlacedObjects() {
        // Place a token on right side, in the middle of the screen
        int width = gamePanel.screenWidth;
        int height = gamePanel.screenHeight;
        int rightPadding = gamePanel.tileSize;
        int tileSize = gamePanel.tileSize;
        placeObject("bin", width - rightPadding, height / 2 - tileSize / 2);
    }

    public void draw(Graphics2D g2d) {
        for (Map.Entry<String, Rectangle2D> entry : placedObjects.entrySet()) {
            Rectangle2D rectangle = entry.getValue();
            g2d.drawImage(objects[0].image, rectangle.getBounds().x, rectangle.getBounds().y, (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
        }
    }

    public Map<String, Rectangle2D> getPlacedObjects() {
        return placedObjects;
    }

}
