package Vue.Utils;

import java.awt.event.MouseEvent;

/**
 * Represents a position on the screen.
 *
 * @param x The x coordinate
 * @param y The y coordinate
 * @author Nathan Gromb
 */
public record Position(int x, int y) {

	/**
	 * Returns the position of the mouse event.
	 *
	 * @param e The mouse event
	 * @return The position of the mouse event
	 * @author Nathan Gromb
	 */
	public static Position ofEvent(MouseEvent e) {
		return Position.of(e.getX(), e.getY());
	}

	/**
	 * Returns a new position with the given coordinates.
	 *
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return The new position
	 * @author Nathan Gromb
	 */
	public static Position of(int x, int y) {
		return new Position(x, y);
	}
}
