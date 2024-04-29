package Vue.Animations;

import Vue.Interfaces.Animation;
import Vue.Level.LevelPanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * Animation that makes the screen fill with a color in a circle shape
 *
 * @Author Nathan Gromb
 */
public class CircleAnimation implements Animation {
	private static final double SHRINKING_SPEED = 500;
	private static final int DEFAULT_TIME_STILL = 2;

	private final LevelPanel levelPanel;
	private final int timeStill;
	private final Color color;
	private int circleSize; // Size of the circle
	private boolean running = false;
	private int duration = 0;
	private int stillDuration = 0;

	public CircleAnimation(LevelPanel levelPanel, Color color) {
		this(levelPanel, color, DEFAULT_TIME_STILL);
	}

	public CircleAnimation(LevelPanel levelPanel, Color color, int timeStill) {
		this.levelPanel = levelPanel;
		this.timeStill = timeStill * 1000;
		this.color = color;
	}

	@Override
	public void start() {
		if (running) {
			return;
		}

		running = true;
		duration = 0;
		stillDuration = 0;

		circleSize = Math.max(levelPanel.getWidth(), levelPanel.getHeight()); // Start with maximum size
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
		if (!running) {
			return;
		}

		int x = (levelPanel.getWidth() - circleSize) / 2;
		int y = (levelPanel.getHeight() - circleSize) / 2;
		Shape clipShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

		Shape wholePanel = new Rectangle(0, 0, levelPanel.getWidth(), levelPanel.getHeight());

		Area area = new Area(wholePanel);
		area.subtract(new Area(clipShape));

		g2d.setColor(color);
		g2d.fill(area);

		if (circleSize > 0) {
			circleSize = Math.max(0, (int) (circleSize - SHRINKING_SPEED / levelPanel.getFPS()));
		} else {
			stillDuration += 1000 / levelPanel.getFPS();
		}

		duration += 1000 / levelPanel.getFPS();

		if (stillDuration > timeStill) {
			stop();
		}
	}
}
