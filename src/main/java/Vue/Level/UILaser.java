package Vue.Level;

import Controller.LevelController;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Pair;
import Vue.Interfaces.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UILaser implements Drawable {

    private final PlayableLevelPanel playableLevelPanel;
    private final LevelController levelController;
    private final Map<String, BufferedImage> laserImages = new HashMap<>();
    private final int laserDuration = 1; // 1 second
    private int laserTimer = 0;

    public UILaser(PlayableLevelPanel playableLevelPanel, LevelController levelController) {
        this.playableLevelPanel = playableLevelPanel;
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
        boolean shouldDisplayLaser = levelController.shouldDisplayLaser();
        if (shouldDisplayLaser && laserTimer == 0) {
            laserTimer += laserDuration * playableLevelPanel.fps;
            levelController.setShouldDisplayLaser(false);
        } else if (laserTimer > 0) {
            laserTimer--;
        } else {
            return;
        }

        Set<Pair<Coordinate, Coordinate>> laserFragments = levelController.getLaserFragments();

        for (Pair<Coordinate, Coordinate> laserFragment : laserFragments) {
            Coordinate start = laserFragment.first();
            Coordinate end = laserFragment.second();

            int widthOffset = playableLevelPanel.widthOffset;
            int heightOffset = playableLevelPanel.heightOffset;

            int startX = start.x() * playableLevelPanel.tileWidth + widthOffset;
            int startY = start.y() * playableLevelPanel.tileHeight + heightOffset;
            int endX = end.x() * playableLevelPanel.tileWidth + widthOffset;
            int endY = end.y() * playableLevelPanel.tileHeight + heightOffset;

            int minX = Math.min(startX, endX);
            int minY = Math.min(startY, endY);

            int width = Math.abs(startX - endX);
            int height = Math.abs(startY - endY);

            // Place at the center of the tile


            if (startX == endX) { // Vertical laser
                minY += playableLevelPanel.tileHeight / 2;
                g2d.drawImage(laserImages.get("laser_vertical"), minX, minY, playableLevelPanel.tileWidth, height, null);
            } else { // Horizontal laser
                minX += playableLevelPanel.tileWidth / 2;
                g2d.drawImage(laserImages.get("laser_horizontal"), minX, minY, width, playableLevelPanel.tileHeight, null);
            }
        }


    }
}
