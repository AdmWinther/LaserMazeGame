package Vue.Interfaces;

import java.awt.*;

/**
 * Interface for objects that can be drawn
 */
public interface Drawable {
    /**
     * Draw the object
     * @param g2d object on which to draw
     *
     * @author Nathan Gromb
     */
    void draw(Graphics2D g2d);
}
