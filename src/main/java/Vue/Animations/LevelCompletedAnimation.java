package Vue.Animations;

import Vue.Interfaces.Animation;
import Vue.Level.LevelAnimations;

import java.awt.*;

/**
 * Animation that displays when the level is completed
 *
 * @Author Nathan Gromb
 */
public class LevelCompletedAnimation implements Animation {
    private final int OFFSET = 1;

    private boolean running = false;

    private final LevelAnimations levelAnimations;

    public LevelCompletedAnimation(LevelAnimations levelAnimations) {
        this.levelAnimations = levelAnimations;
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

        levelAnimations.circle(Color.BLACK);
        levelAnimations.confetti();

        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
