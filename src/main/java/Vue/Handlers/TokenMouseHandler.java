package Vue.Handlers;

import Controller.LevelController;
import Model.Classes.Token.OrientedToken;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Vue.Level.LevelPanel;
import Vue.Level.UILayers.TokenDisplay;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * This class is responsible for handling the mouse events for the tokens.
 * It handles the token selection, placement and removal.
 *
 * @author Nathan Gromb
 * @author Leonard Amsler
 */
public final class TokenMouseHandler extends AdaptedMouseHandler implements MouseMotionListener {
	private final LevelController levelController;
	private final LevelPanel levelPanel;
	private final TokenDisplay tokenDisplay;

	private boolean isPressed;
	private int mouseStartX;
	private int mouseStartY;

	private Token selectedToken;
	private boolean isSelectedPlaced;

	/**
	 * Constructor of the TokenMouseHandler
	 *
	 * @param levelPanel      the panel of the level
	 * @param levelController the controller of the level
	 * @param tokenDisplay    the token display of the level
	 * @author Nathan Gromb
	 * @author Leonard Amsler
	 */
	public TokenMouseHandler(LevelPanel levelPanel, LevelController levelController, TokenDisplay tokenDisplay) {
		this.levelPanel = levelPanel;
		this.levelController = levelController;
		this.tokenDisplay = tokenDisplay;

		this.isPressed = false;
		this.selectedToken = null;
		this.isSelectedPlaced = false;
	}

	/**
	 * Method that handles the mouse drag event
	 * Sets the UI's dragged token to the selected token
	 *
	 * @param e the event to be processed
	 * @author Nathan Gromb
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (selectedToken != null) {
			tokenDisplay.setDraggedToken(selectedToken, e.getX(), e.getY());
		}
	}

	/**
	 * Method that handles the mouse move event
	 *
	 * @param e the event to be processed
	 * @author Léonard Amsler
	 */
	@Override
	public void mouseMoved(MouseEvent e) {}

	/**
	 * Method that handles the mouse click event
	 * If the click is on a token, it rotates it
	 *
	 * @param e the event to be processed
	 * @author Léonard Amsler
	 * @author Nathan Gromb
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		selectedToken = null;
		tokenDisplay.resetDraggedToken();

		int widthOffset = levelPanel.widthOffset;
		int heightOffset = levelPanel.heightOffset;

		int x_coordinate = (e.getX() - widthOffset) / levelPanel.tileWidth;
		int y_coordinate = (e.getY() - heightOffset) / levelPanel.tileHeight;

		int maxWidth = levelController.getWidth();
		int maxHeight = levelController.getHeight();

		if (x_coordinate >= 0 && x_coordinate < maxWidth && y_coordinate >= 0 && y_coordinate < maxHeight) {
			Coordinate coordinate = new Coordinate(x_coordinate, y_coordinate);

			Token other = levelController.getTokenAt(coordinate);

			if (other != null && other.isMovable() && other instanceof OrientedToken) {
				levelController.rotateToken(other);
			}
		}
	}

	/**
	 * Method that handles the mouse press event
	 * If the press is on a token, it selects it
	 *
	 * @param e the event to be processed
	 * @author Léonard Amsler
	 * @author Nathan Gromb
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!isPressed) {
			isPressed = true;
			mouseStartX = e.getX();
			mouseStartY = e.getY();

			int boardX = (mouseStartX - levelPanel.widthOffset) / levelPanel.tileWidth;
			int boardY = (mouseStartY - levelPanel.heightOffset) / levelPanel.tileHeight;

			int boardWidth = levelController.getWidth();
			int boardHeight = levelController.getHeight();

			selectToken(mouseStartX, mouseStartY, boardX, boardY, boardWidth, boardHeight);
		}
	}

	/**
	 * Method that selects a token given the mouse coordinates (relative to the window and to the board)
	 *
	 * @param windowX     the x coordinate of the mouse relative to the window
	 * @param windowY     the y coordinate of the mouse relative to the window
	 * @param boardX      the x coordinate of the mouse in board coordinates
	 * @param boardY      the y coordinate of the mouse in board coordinates
	 * @param boardWidth  max board x coordinate
	 * @param boardHeight max board y coordinate
	 * @author Nathan Gromb
	 */
	private void selectToken(int windowX, int windowY, int boardX, int boardY, int boardWidth, int boardHeight) {
		// if the press in inside the board
		if (boardX >= 0 && boardX < boardWidth && boardY >= 0 && boardY < boardHeight) {
			Coordinate boardCoordinate = new Coordinate(boardX, boardY);
			Token token = levelController.getTokenAt(boardCoordinate);

			if (token != null && token.isMovable()) {
				selectedToken = token;
				isSelectedPlaced = true;
			}
			// if the press is outside the board (unplaced tokens)
		} else {
			Token token = tokenDisplay.getTokenAtMousePos(windowX, windowY);

			if (token != null) {
				selectedToken = token;
				isSelectedPlaced = false;
			}
		}
	}

	/**
	 * Method that handles the mouse release event
	 * If the release is on a valid position, it moves the token
	 * If the release is outside the board, it passes the responsibility to the extras UI
	 *
	 * @param e the event to be processed
	 * @author Léonard Amsler
	 * @author Nathan Gromb
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		tokenDisplay.resetDraggedToken();

		if (isPressed) {
			isPressed = false;

			if (registerDragAsClick(e, mouseStartX, mouseStartY)) {
				mouseClicked(e);
				return;
			}

			int mouseEndX = e.getX();
			int mouseEndY = e.getY();

			int boardX = (mouseEndX - levelPanel.widthOffset) / levelPanel.tileWidth;
			int boardY = (mouseEndY - levelPanel.heightOffset) / levelPanel.tileHeight;

			if (selectedToken == null) {
				return;
			}

			// if the mouse is released on the board
			if (boardX < levelController.getWidth() && boardY < levelController.getHeight() && boardX >= 0 && boardY >= 0) {
				Coordinate coordinate = new Coordinate(boardX, boardY);

				// if the selected token is already on the board, move it
				if (isSelectedPlaced) {
					Coordinate from = new Coordinate((mouseStartX - levelPanel.widthOffset) / levelPanel.tileWidth, (mouseStartY - levelPanel.heightOffset) / levelPanel.tileHeight);
					levelController.moveToken(from, coordinate);
					// else, place it
				} else {
					levelController.transferTokenToPlacedTokens(selectedToken, coordinate);
				}
				// if the mouse is released outside the board, pass the responsibility to the extras UI
			} else {
				levelPanel.getExtrasUI().handleTokenDrop(selectedToken, mouseEndX, mouseEndY, levelController);
			}

			// the mouse is released, the token is not selected anymore
			selectedToken = null;
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
