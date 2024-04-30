package Vue.Level.UILayers;

import Vue.Animations.BingoAnimation;
import Vue.Animations.CircleAnimation;
import Vue.Animations.ConfettiAnimation;
import Vue.Interfaces.Animation;
import Vue.Interfaces.Drawable;
import Vue.Level.LevelPanel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class that handles the animations of the level
 *
 * @author Nathan Gromb
 */
public class AnimationsUI implements Drawable {

	private static final int LEVEL_COMPLETED_DELAY = 100;

	private final LevelPanel levelPanel;
	private final ArrayList<Animation> activeAnimations;
	private final ArrayList<Animation> queue;

	private int delay = 0;

	/**
	 * Constructor of the AnimationsUI
	 *
	 * @param levelPanel the panel of the level
	 * @author Nathan Gromb
	 */
	public AnimationsUI(LevelPanel levelPanel) {
		this.activeAnimations = new ArrayList<>();
		this.queue = new ArrayList<>();
		this.levelPanel = levelPanel;
	}

	/**
	 * Draws the animations.
	 *
	 * @param g2d object on which to draw
	 * @author Nathan Gromb
	 */
	@Override
	public void draw(Graphics2D g2d) {

		// Add all animations from the queue to the active animations
		activeAnimations.addAll(queue);
		queue.clear();

		for (Animation animation : activeAnimations) {
			animation.draw(g2d);
		}

		// Remove all animations that are not running anymore
		ArrayList<Animation> toRemove = new ArrayList<>();
		for (Animation animation : activeAnimations) {
			if (!animation.isRunning()) {
				toRemove.add(animation);
			}
		}
		activeAnimations.removeAll(toRemove);

		// Increment the delay
		delay += 1000 / levelPanel.getFPS();
	}

	/**
	 * Start the confetti animation
	 *
	 * @author Nathan Gromb
	 */
	public void confetti() {
		Animation confetti = new ConfettiAnimation(levelPanel);
		queue.add(confetti);

		confetti.start();
	}

	/**
	 * Start the circle animation
	 *
	 * @param color the color that fills the screen
	 * @author Nathan Gromb
	 */
	public void circle(Color color) {
		Animation circle = new CircleAnimation(levelPanel, color);
		queue.add(circle);

		circle.start();
	}

	public void bingo() {
		Animation bingo = new BingoAnimation(levelPanel);
		queue.add(bingo);

		bingo.start();
	}
}
