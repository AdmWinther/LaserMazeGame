package Vue.Handlers;

import Controller.LevelController;
import Model.Classes.Token.OrientedToken;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Vue.Level.LevelPanel;
import Vue.Level.UILayers.TokenDisplay;
import Vue.Utils.Position;

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
	private Position mouseStartPos;

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
			tokenDisplay.setDraggedToken(selectedToken, Position.ofEvent(e));
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

		int widthOffset = levelPanel.getLevelPanelConfig().getWidthOffset();
		int heightOffset = levelPanel.getLevelPanelConfig().getHeightOffset();

		int x_coordinate = (e.getX() - widthOffset) / levelPanel.getLevelPanelConfig().getTileWidth();
		int y_coordinate = (e.getY() - heightOffset) / levelPanel.getLevelPanelConfig().getTileHeight();

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
			mouseStartPos = Position.ofEvent(e);

			int boardX = (mouseStartPos.x() - levelPanel.getLevelPanelConfig().getWidthOffset()) / levelPanel.getLevelPanelConfig().getTileWidth();
			int boardY = (mouseStartPos.y() - levelPanel.getLevelPanelConfig().getHeightOffset()) / levelPanel.getLevelPanelConfig().getTileHeight();

			int boardWidth = levelController.getWidth();
			int boardHeight = levelController.getHeight();

			selectToken(mouseStartPos, boardX, boardY, boardWidth, boardHeight);
		}
	}

	/**
	 * Method that selects a token given the mouse coordinates (relative to the window and to the board)
	 *
	 * @param mousePos    the position of the mouse
	 * @param boardX      the x coordinate of the mouse in board coordinates
	 * @param boardY      the y coordinate of the mouse in board coordinates
	 * @param boardWidth  max board x coordinate
	 * @param boardHeight max board y coordinate
	 * @author Nathan Gromb
	 */
	private void selectToken(Position mousePos, int boardX, int boardY, int boardWidth, int boardHeight) {
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
			Token token = tokenDisplay.getTokenAtMousePos(mousePos);

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

			if (registerDragAsClick(e, mouseStartPos)) {
				mouseClicked(e);
				return;
			}

			Position mouseEndPos = Position.ofEvent(e);

			int boardX = (mouseEndPos.x() - levelPanel.getLevelPanelConfig().getWidthOffset()) / levelPanel.getLevelPanelConfig().getTileWidth();
			int boardY = (mouseEndPos.y() - levelPanel.getLevelPanelConfig().getHeightOffset()) / levelPanel.getLevelPanelConfig().getTileHeight();

			if (selectedToken == null) {
				return;
			}

			// if the mouse is released on the board
			if (boardX < levelController.getWidth() && boardY < levelController.getHeight() && boardX >= 0 && boardY >= 0) {
				Coordinate coordinate = new Coordinate(boardX, boardY);

				// if the selected token is already on the board, move it
				if (isSelectedPlaced) {
					int x = (mouseStartPos.x() - levelPanel.getLevelPanelConfig().getWidthOffset()) / levelPanel.getLevelPanelConfig().getTileWidth();
					int y = (mouseStartPos.y() - levelPanel.getLevelPanelConfig().getHeightOffset()) / levelPanel.getLevelPanelConfig().getTileHeight();
					Coordinate from = new Coordinate(x, y);
					levelController.moveToken(from, coordinate);
					// else, place it
				} else {
					levelController.transferTokenToPlacedTokens(selectedToken, coordinate);
				}
				// if the mouse is released outside the board, pass the responsibility to the extras UI
			} else {
				levelPanel.getExtrasUI().handleTokenDrop(selectedToken, mouseEndPos, levelController);
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
