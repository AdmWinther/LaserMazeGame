package Vue.Animations;

import Vue.Interfaces.Animation;
import Vue.Level.UILayers.AnimationsUI;

import java.awt.*;

/**
 * Animation that displays when the level is completed
 *
 * @author Nathan Gromb
 */
public class LevelCompletedAnimation implements Animation {
	private final int OFFSET = 1;
	private final AnimationsUI AnimationsUI;
	private boolean running = false;

	public LevelCompletedAnimation(AnimationsUI AnimationsUI) {
		this.AnimationsUI = AnimationsUI;
	}

	@Override
	public void start() {
		if (running) {
			return;
		}

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
		if (!running) {
			return;
		}

		AnimationsUI.circle(Color.BLACK);
		AnimationsUI.confetti();

		running = false;
	}
}
