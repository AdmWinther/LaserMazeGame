package Vue.Animations;

import Vue.Interfaces.Animation;
import Vue.Level.LevelPanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * Animation that makes the screen fill with a color in a circle shape or,
 * if inverted, makes the screen appear from a circle shape.
 *
 * @author Nathan Gromb
 */
public class CircleAnimation implements Animation {
	private static final double SHRINKING_SPEED = 1000;
	private static final int DEFAULT_TIME_STILL = 2;

	private final LevelPanel levelPanel;
	private final int timeStill;
	private final Color color;
	private final boolean inverted;
	private final int upperBound;
	private int circleRadius; // Size of the circle
	private boolean running = false;
	private int stillDuration = 0;


	/**
	 * Constructor of the CircleAnimation
	 *
	 * @param levelPanel the panel of the level
	 * @param color      the color of the circle
	 * @param inverted   true if the animation should be inverted, false otherwise
	 * @author Nathan Gromb
	 */
	public CircleAnimation(LevelPanel levelPanel, Color color, boolean inverted) {
		this.levelPanel = levelPanel;
		this.timeStill = DEFAULT_TIME_STILL * 1000;
		this.color = color;
		this.inverted = inverted;

		// diagonal of the panel
		this.upperBound = (int) Math.sqrt(Math.pow(levelPanel.getWidth(), 2) + Math.pow(levelPanel.getHeight(), 2));
	}

	/**
	 * Start the animation
	 *
	 * @author Nathan Gromb
	 */
	@Override
	public void start() {
		if (running) {
			return;
		}

		running = true;
		stillDuration = 0;

		circleRadius = inverted ? 0 : upperBound; // Start with maximum size
	}

	/**
	 * Stop the animation
	 *
	 * @author Nathan Gromb
	 */
	@Override
	public void stop() {
		running = false;
	}

	/**
	 * Check if the animation is running
	 *
	 * @return true if the animation is running, false otherwise
	 * @author Nathan Gromb
	 */
	@Override
	public boolean isRunning() {
		return running;
	}

	/**
	 * Draws the circle animation
	 *
	 * @param g2d object on which to draw
	 * @author Nathan Gromb
	 */
	@Override
	public void draw(Graphics2D g2d) {
		if (!running) {
			return;
		}

		int x = (levelPanel.getWidth() - circleRadius) / 2;
		int y = (levelPanel.getHeight() - circleRadius) / 2;
		Shape clipShape = new Ellipse2D.Double(x, y, circleRadius, circleRadius);
		Area clip = new Area(clipShape);

		Shape wholePanel = new Rectangle(0, 0, levelPanel.getWidth(), levelPanel.getHeight());
		Area area = new Area(wholePanel);

		area.subtract(clip);

		g2d.setColor(color);
		g2d.fill(area);

		if ((inverted && circleRadius < upperBound) || (!inverted && circleRadius > 0)) {
			circleRadius = getNextCircleSize(inverted);
		} else {
			stillDuration += 1000 / levelPanel.getFPS();
		}

		if (stillDuration > timeStill) {
			stop();
		}
	}

	private int getNextCircleSize(boolean inverted) {
		return inverted ? Math.min(upperBound, (int) (circleRadius + SHRINKING_SPEED / levelPanel.getFPS())) : Math.max(0, (int) (circleRadius - SHRINKING_SPEED / levelPanel.getFPS()));
	}
}
