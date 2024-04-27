package Vue.Handlers;

import Vue.Level.LevelPanel;
import Vue.Utils.Position;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse handler for the level panel
 * This class handles the mouse events on the level panel
 *
 * @author Léonard Amsler - s231715
 */
public class LevelMouseHandler extends AdaptedMouseHandler implements MouseListener {

	private final LevelPanel levelPanel;

	private boolean isPressed = false;
	private Position mouseStartPos;


	/**
	 * Constructor of the level mouse handler
	 *
	 * @param levelPanel - The level panel
	 * @author Léonard Amsler - s231715
	 */
	public LevelMouseHandler(LevelPanel levelPanel) {
		this.levelPanel = levelPanel;
	}

	/**
	 * Handle the mouse click event
	 * Passes the responsibility to the extras UI
	 *
	 * @param e - The mouse event
	 * @author Léonard Amsler - s231715
	 * @author Nathan Gromb
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Check if we have clicked on the reset button
		levelPanel.getExtrasUI().handleClick(Position.ofEvent(e));
	}

	/**
	 * Handle the mouse press event
	 *
	 * @param e the event to be processed
	 * @author Léonard Amsler - s231715
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!isPressed) {
			isPressed = true;
			mouseStartPos = Position.ofEvent(e);
		}
	}

	/**
	 * Handle the mouse release event
	 * This method is called when the mouse is released
	 *
	 * @param e the event to be processed
	 * @author Léonard Amsler - s231715
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (isPressed) {
			isPressed = false;

			if (registerDragAsClick(e, mouseStartPos)) {
				mouseClicked(e);
			}
		}
	}

	/**
	 * Handle the mouse enter event
	 * This method is called when the mouse enters the level panel
	 * This method is not used
	 *
	 * @param e the event to be processed
	 * @author Léonard Amsler - s231715
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Handle the mouse exit event
	 * This method is called when the mouse exits the level panel
	 * This method is not used
	 *
	 * @param e the event to be processed
	 * @author Léonard Amsler - s231715
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
}
