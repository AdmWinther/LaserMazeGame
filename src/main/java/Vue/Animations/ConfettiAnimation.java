package Vue.Animations;

import Vue.Interfaces.Animation;
import Vue.Level.LevelPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is responsible for the animation that makes the screen fill with confetti, starting from a given point
 *
 * @author Nathan Gromb
 */
public class ConfettiAnimation implements Animation {

	private static final int BURST_SIZE = 30;
	// TODO turn this into seconds or milliseconds
	private static final int BURST_DELAY = 5;
	private static final int BURSTS = 3;

	//Maximum speed of the confetti The minimum speed is half of this value
	private static final int CONFETTI_MAX_SPEED = 10;

	private final LevelPanel levelPanel;
	private final ArrayList<Confetti> confettiList;
	private final Random random;
	private boolean running = false;
	private int duration = 0;

	public ConfettiAnimation(LevelPanel levelPanel) {
		this(levelPanel, levelPanel.getWidth() / 2, levelPanel.getHeight() / 2);
	}

	public ConfettiAnimation(LevelPanel levelPanel, int x, int y) {
		this.levelPanel = levelPanel;
		this.confettiList = new ArrayList<>();
		this.random = new Random();
	}

	@Override
	public void draw(Graphics2D g2d) {
		if (!running) {
			return;
		}

		for (ConfettiAnimation.Confetti confetti : confettiList) {
			confetti.draw(g2d);
		}

		if (duration % BURST_DELAY == 0 && duration / BURST_DELAY < BURSTS) {
			for (int i = 0; i < BURST_SIZE; i++) {
				confettiList.add(this.randomConfetti(levelPanel.getWidth() / 2, levelPanel.getHeight() / 2, random));
			}
		}

		duration += 60 / levelPanel.getFPS();

		if (duration / BURST_DELAY < BURSTS) {
			return;
		}

		for (ConfettiAnimation.Confetti confetti : confettiList) {
			if (!confetti.done) {
				return;
			}
		}

		stop();
	}

	private Confetti randomConfetti(int x, int y, Random random) {

		int size = random.nextInt(10) + 5;
		Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

		int speed = random.nextInt(CONFETTI_MAX_SPEED - CONFETTI_MAX_SPEED / 2) + CONFETTI_MAX_SPEED / 3;
		int direction = random.nextInt(360);

		int horizontalSpeed = (int) (speed * Math.cos(Math.toRadians(direction)));
		int verticalSpeed = (int) (speed * Math.sin(Math.toRadians(direction)));

		return new Confetti(x, y, size, color, horizontalSpeed, verticalSpeed);
	}

	public void start() {
		if (running) {
			return;
		}

		duration = 0;

		confettiList.clear();
		running = true;
	}

	public void stop() {
		running = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	/**
	 * Class representing a single piece of confetti
	 *
	 * @author Nathan Gromb
	 */
	private class Confetti {
		private static final double SLOW_DOWN_FACTOR = 0.988;
		private static final double MIN_SPEED = 2;
		private final int size;
		private final Color color;
		public boolean done = false;
		private int x;
		private int y;
		// doubles to allow for slower deceleration
		private double verticalSpeed;
		private double horizontalSpeed;

		public Confetti(int x, int y, int size, Color color, int verticalSpeed, int horizontalSpeed) {
			this.x = x;
			this.y = y;

			this.size = size;
			this.color = color;

			this.verticalSpeed = verticalSpeed;
			this.horizontalSpeed = horizontalSpeed;
		}

		public void draw(Graphics2D g2d) {
			if (done) {
				return;
			}

			g2d.setColor(color);
			g2d.fillRect(x, y, size, size);

			// Move confetti
			y += (int) verticalSpeed * 60 / levelPanel.getFPS();
			x += (int) horizontalSpeed * 60 / levelPanel.getFPS();

			// Slow down confetti
			verticalSpeed *= SLOW_DOWN_FACTOR * 60 / levelPanel.getFPS();
			horizontalSpeed *= SLOW_DOWN_FACTOR * 60 / levelPanel.getFPS();

			if (reachedEnd() || isTooSlow()) {
				done = true;
			}
		}

		private boolean reachedEnd() {
			return outOfBounds();
		}

		private boolean isTooSlow() {
			return Math.sqrt(Math.pow(verticalSpeed, 2) + Math.pow(horizontalSpeed, 2)) < MIN_SPEED;
		}

		private boolean outOfBounds() {
			return y < 0 || y > levelPanel.getHeight() || x < 0 || x > levelPanel.getWidth();
		}
	}
}
