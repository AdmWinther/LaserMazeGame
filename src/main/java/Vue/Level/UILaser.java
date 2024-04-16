package Vue.Level;

import Controller.LevelController;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UILaser {

    private final LevelPanel levelPanel;
    private final LevelController levelController;
    private final Map<String, BufferedImage> laserImages = new HashMap<>();
    private int laserTimer = 0;

    public UILaser(LevelPanel levelPanel, LevelController levelController) {
        this.levelPanel = levelPanel;
        this.levelController = levelController;
        loadLaserImages();
    }

    public void loadLaserImages() {
        try {
            BufferedImage laserImage_horizontal = ImageIO.read(Objects.requireNonNull(getClass().getResource("/laser/laser_horizontal.png")));
            BufferedImage laserImage_vertical = ImageIO.read(Objects.requireNonNull(getClass().getResource("/laser/laser_vertical.png")));

            laserImages.put("laser_horizontal", laserImage_horizontal);
            laserImages.put("laser_vertical", laserImage_vertical);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        Set<Pair<Coordinate, Coordinate>> laserFragments = levelController.getLaserFragments();

        boolean shouldGenerateLaser = levelController.shouldGenerateLaser();
        if (!shouldGenerateLaser) {
            return;
        } else {
            levelController.setShouldGenerateLaser(false);
        }

        for (Pair<Coordinate, Coordinate> laserFragment : laserFragments) {
            Coordinate start = laserFragment.first();
            Coordinate end = laserFragment.second();

            int widthOffset = levelPanel.widthOffset;
            int heightOffset = levelPanel.heightOffset;

            int startX = start.x() * levelPanel.tileWidth + widthOffset;
            int startY = start.y() * levelPanel.tileHeight + heightOffset;
            int endX = end.x() * levelPanel.tileWidth + widthOffset;
            int endY = end.y() * levelPanel.tileHeight + heightOffset;

            int minX = Math.min(startX, endX);
            int minY = Math.min(startY, endY);

            int width = Math.abs(startX - endX);
            int height = Math.abs(startY - endY);

            // Place at the center of the tile


            if (startX == endX) { // Vertical laser
                minY += levelPanel.tileHeight / 2;
                g2d.drawImage(laserImages.get("laser_vertical"), minX, minY, levelPanel.tileWidth, height, null);
            } else { // Horizontal laser
                minX += levelPanel.tileWidth / 2;
                g2d.drawImage(laserImages.get("laser_horizontal"), minX, minY, width, levelPanel.tileHeight, null);
            }
        }

        if (laserTimer == 0) {
            laserTimer += levelPanel.fps;
        } else {
            laserTimer--;
            if (laserTimer == 0) {
                levelController.shouldGenerateLaser();
            }
        }


    }
}
