package Vue.Level.UILayers;

import Controller.LevelController;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Pair;
import Vue.Interfaces.Drawable;
import Vue.Level.LevelPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * UI of the laser
 *
 * @author Léonard Amsler - s231715
 * @author Hussein (Adam)
 */
public class LaserUI implements Drawable {
	private final static int LASER_DURATION = 1;

	private final LevelPanel levelPanel;
	private final LevelController levelController;
	private final Map<String, BufferedImage> laserImages = new HashMap<>();
	private int laserTimer = 0;

	/**
	 * Constructor of the laser UI
	 *
	 * @param levelPanel      The level panel
	 * @param levelController The level controller
	 * @author Léonard Amsler - s231715
	 * @author Hussein (Adam)
	 */
	public LaserUI(LevelPanel levelPanel, LevelController levelController) {
		this.levelPanel = levelPanel;
		this.levelController = levelController;

		loadLaserImages();
	}

	/**
	 * Load the laser images
	 *
	 * @Author Léonard Amsler - s231715
	 */
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

	/**
	 * Draw the laser
	 *
	 * @param g2d The 2d graphics object
	 * @author Léonard Amsler - s231715
	 * @author Hussein (Adam)
	 */
	public void draw(Graphics2D g2d) {
		boolean shouldDisplayLaser = levelController.shouldDisplayLaser();

		// if the laser should be displayed and the timer is 0, set the timer to the duration of the laser
		if (shouldDisplayLaser && laserTimer == 0) {
			laserTimer += LASER_DURATION * levelPanel.getFPS();
			levelController.setShouldDisplayLaser(false);
		} else if (laserTimer > 0) {
			laserTimer--;
		} else {
			return;
		}

		Set<Pair<Coordinate, Coordinate>> laserFragments = levelController.getLaserFragments();

		for (Pair<Coordinate, Coordinate> laserFragment : laserFragments) {
			drawLaserFragment(g2d, laserFragment);
		}
	}

	/**
	 * Draw a laser fragment
	 *
	 * @param g2d           The 2d graphics object
	 * @param laserFragment The laser fragment to draw
	 * @author Léonard Amsler - s231715
	 * @author Hussein (Adam)
	 */
	private void drawLaserFragment(Graphics2D g2d, Pair<Coordinate, Coordinate> laserFragment) {
		Coordinate start = laserFragment.first();
		Coordinate end = laserFragment.second();

		int startX = start.x() * levelPanel.tileWidth + levelPanel.widthOffset;
		int startY = start.y() * levelPanel.tileHeight + levelPanel.heightOffset;
		int endX = end.x() * levelPanel.tileWidth + levelPanel.widthOffset;
		int endY = end.y() * levelPanel.tileHeight + levelPanel.heightOffset;

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
}
