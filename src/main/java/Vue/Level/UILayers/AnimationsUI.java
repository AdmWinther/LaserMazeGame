package Vue.Level.UILayers;

import Vue.Animations.CircleAnimation;
import Vue.Animations.ConfettiAnimation;
import Vue.Animations.LevelCompletedAnimation;
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

    private LevelPanel levelPanel;
    private ArrayList<Animation> activeAnimations;
    private ArrayList<Animation> queue;

    private int delay = 0;

    public AnimationsUI(LevelPanel levelPanel) {
        this.activeAnimations = new ArrayList<>();
        this.queue = new ArrayList<>();
        this.levelPanel = levelPanel;
    }

    @Override
    public void draw(Graphics2D g2d) {

        for (Animation animation : queue) {
            activeAnimations.add(animation);
        }
        queue.clear();

        for (Animation animation : activeAnimations) {
            animation.draw(g2d);
        }

        ArrayList<Animation> toRemove = new ArrayList<>();
        for (Animation animation : activeAnimations) {
            if (!animation.isRunning()) {
                toRemove.add(animation);
            }
        }
        activeAnimations.removeAll(toRemove);

        delay += 1000 / levelPanel.getFPS();
    }

    /**
     * Stop all animations
     *
     * @Author Nathan Gromb
     */
    public void stop() {
        for (Animation animation : activeAnimations) {
            animation.stop();
        }
    }

    /**
     * Start the confetti animation
     *
     * @Author Nathan Gromb
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

    /**
     * Start the level completed animation
     *
     * @author Nathan Gromb
     */
    public void levelCompleted() {
        if (delay < LEVEL_COMPLETED_DELAY) {
            return;
        }

        delay = 0;

        Animation levelCompleted = new LevelCompletedAnimation(this);
        queue.add(levelCompleted);

        levelCompleted.start();
    }
}
