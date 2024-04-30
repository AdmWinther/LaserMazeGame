package Vue.Animations;

import Vue.Interfaces.Animation;
import Vue.Level.LevelPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class BingoAnimation implements Animation {

	private static final int MAX_DURATION = 4000;
	private final LevelPanel levelPanel;
	private final BufferedImage image;

	private boolean running = false;
	private int duration = 0;

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

	@Override
	public void start() {
		running = true;
	}

	@Override
	public void stop() {
		running = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void draw(Graphics2D g2d) {
		if (running) {
			int x = Math.floorDiv((levelPanel.maxCol - 3) * levelPanel.tileWidth, 2);
			int y = Math.floorDiv((levelPanel.maxRow - 1) * levelPanel.tileHeight, 2);
			g2d.drawImage(image, x, y, 3 * levelPanel.tileWidth, levelPanel.tileHeight, null);
		}

		duration += 1000 / levelPanel.getFPS();

		if (duration > MAX_DURATION) {
			stop();
		}
	}
}
