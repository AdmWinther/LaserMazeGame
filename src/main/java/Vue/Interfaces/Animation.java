package Vue.Interfaces;

/**
 * Interface for animations
 *
 * @see Drawable
 * @author Nathan Gromb
 */
public interface Animation extends Drawable {

    /**
     * Start the animation
     *
     * @author Nathan Gromb
     */
    void start();

    /**
     * Stop the animation
     *
     * @author Nathan Gromb
     */
    void stop();

    /**
     * Check if the animation is running
     *
     * @return true if the animation is running, false otherwise
     * @author Nathan Gromb
     */
    boolean isRunning();
}
