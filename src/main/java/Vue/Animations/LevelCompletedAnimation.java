package Vue.Animations;

import Vue.Interfaces.Animation;
import Vue.Level.UILayers.UIAnimations;

import java.awt.*;

/**
 * Animation that displays when the level is completed
 *
 * @Author Nathan Gromb
 */
public class LevelCompletedAnimation implements Animation {
    private final int OFFSET = 1;

    private boolean running = false;

    private final UIAnimations UIAnimations;

    public LevelCompletedAnimation(UIAnimations UIAnimations) {
        this.UIAnimations = UIAnimations;
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
    public void draw(Graphics2D g2d) {
        if (!running) {
            return;
        }

        UIAnimations.circle(Color.BLACK);
        UIAnimations.confetti();

        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
