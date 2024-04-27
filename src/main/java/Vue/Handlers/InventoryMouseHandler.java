package Vue.Handlers;

import Controller.EditableLevelController;
import Model.Classes.Token.Token;
import Vue.Level.UILayers.InventoryUI;

import java.awt.event.MouseEvent;

/**
 * Class that handles the mouse events on the inventory's UI
 *
 * @author Nathan Gromb
 */
public final class InventoryMouseHandler extends AdaptedMouseHandler {
	private final EditableLevelController levelController;
	private final InventoryUI tokenDisplay;

	private boolean isPressed = false;
	private int startX;
	private int startY;

	/**
	 * Constructor of the InventoryMouseHandler
	 *
	 * @param levelController the controller of the level
	 * @param tokenDisplay    the inventory's UI
	 * @author Nathan Gromb
	 */
	public InventoryMouseHandler(EditableLevelController levelController, InventoryUI tokenDisplay) {
		this.levelController = levelController;
		this.tokenDisplay = tokenDisplay;
	}

	/**
	 * Method that handles the mouse click event
	 * Adds the token to the unplaced tokens if the click is on a token
	 *
	 * @param e the event to be processed
	 * @author Nathan Gromb
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Token token = tokenDisplay.getTokenAtMousePos(e.getX(), e.getY());
		if (token != null) {
			levelController.addToUnplacedTokens(token.copy());
		}
	}

	/**
	 * Method that handles the mouse press event
	 *
	 * @param e the event to be processed
	 * @author Nathan Gromb
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!isPressed) {
			isPressed = true;
			startX = e.getX();
			startY = e.getY();
		}
	}

	/**
	 * Method that handles the mouse release event
	 *
	 * @param e the event to be processed
	 * @author Nathan Gromb
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (isPressed) {
			isPressed = false;

			if (registerDragAsClick(e, startX, startY)) {
				mouseClicked(e);
			}
		}
	}

	/**
	 * Method that handles the mouse enter event
	 *
	 * @param e the event to be processed
	 * @author Nathan Gromb
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Method that handles the mouse exit event
	 *
	 * @param e the event to be processed
	 * @author Nathan Gromb
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
}
