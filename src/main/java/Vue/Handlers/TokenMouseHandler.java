package Vue.Handlers;

import Controller.LevelController;
import Model.Classes.Token.OrientedToken;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Vue.Level.LevelPanel;
import Vue.Level.UILayers.TokenDisplay;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class is responsible for handling the mouse events for the tokens.
 * It handles the token selection, placement and removal.
 *
 * @author Nathan Gromb
 * @author Leonard Amsler
 */
public class TokenMouseHandler extends AdaptedMouseHandler implements MouseListener, MouseMotionListener {
    final LevelController levelController;
    final LevelPanel levelPanel;
    final TokenDisplay tokenDisplay;

    boolean isPressed = false;
    int startX;
    int startY;

    Token selectedToken = null;
    boolean isSelectedPlaced = false;

    public TokenMouseHandler(LevelPanel levelPanel, LevelController levelController, TokenDisplay tokenDisplay) {
        this.levelPanel = levelPanel;
        this.levelController = levelController;
        this.tokenDisplay = tokenDisplay;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selectedToken = null;
        tokenDisplay.resetDraggedToken();

        int tileWidth = levelPanel.tileWidth;
        int tileHeight = levelPanel.tileHeight;

        int widthOffset = levelPanel.widthOffset;
        int heightOffset = levelPanel.heightOffset;

        int x_coordinate = (e.getX() - widthOffset) / tileWidth;
        int y_coordinate = (e.getY() - heightOffset) / tileHeight;

        int maxWidth = levelController.getWidth();
        int maxHeight = levelController.getHeight();

        if (x_coordinate >= 0 && x_coordinate < maxWidth && y_coordinate >= 0 && y_coordinate < maxHeight) {
            System.out.println("Clicked on: " + x_coordinate + ", " + y_coordinate);
            Coordinate coordinate = new Coordinate(x_coordinate, y_coordinate);

            Token other = levelController.getTokenAt(coordinate);

            if (other != null && other.isMovable() && other instanceof OrientedToken) {
                levelController.rotateToken(other);
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!isPressed) {
            isPressed = true;
            startX = e.getX();
            startY = e.getY();
            int tileWidth = levelPanel.tileWidth;
            int tileHeight = levelPanel.tileHeight;

            int widthOffset = levelPanel.widthOffset;
            int heightOffset = levelPanel.heightOffset;

            int x_coordinate = (startX - widthOffset) / tileWidth;
            int y_coordinate = (startY - heightOffset) / tileHeight;

            int maxWidth = levelController.getWidth();
            int maxHeight = levelController.getHeight();

            if (x_coordinate >= 0 && x_coordinate < maxWidth && y_coordinate >= 0 && y_coordinate < maxHeight) {
                Coordinate coordinate = new Coordinate(x_coordinate, y_coordinate);
                Token token = levelController.getTokenAt(coordinate);
                if (token != null && token.isMovable()) {
                    selectedToken = token;
                    isSelectedPlaced = true;
                }
            } else {
                Token token = tokenDisplay.getTokenAt(startX, startY);
                System.out.println("Token chosen: " + token);
                if (token != null) {
                    selectedToken = token;
                    isSelectedPlaced = false;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tokenDisplay.resetDraggedToken();

        if (isPressed) {
            isPressed = false;

            int endX = e.getX();
            int endY = e.getY();

            if (registerDragAsClick(e, startX, startY)) {
                mouseClicked(e);
                return;
            }

            int widthOffset = levelPanel.widthOffset;
            int heightOffset = levelPanel.heightOffset;

            int x_coordinate = (endX - widthOffset);
            int y_coordinate = (endY - heightOffset);

            int tileWidth = levelPanel.tileWidth;
            int tileHeight = levelPanel.tileHeight;

            x_coordinate /= tileWidth;
            y_coordinate /= tileHeight;

            int maxWidth = levelController.getWidth();
            int maxHeight = levelController.getHeight();

            if (selectedToken == null) {
                return;
            }

            if (x_coordinate < maxWidth && y_coordinate < maxHeight && x_coordinate >= 0 && y_coordinate >= 0) {
                if (x_coordinate < 0 || y_coordinate < 0) {
                    return;
                }

                Coordinate coordinate = new Coordinate((int) x_coordinate, (int) y_coordinate);
                if (isSelectedPlaced) {
                    Coordinate from = new Coordinate((startX - widthOffset) / tileWidth, (startY - heightOffset) / tileHeight);
                    levelController.moveToken(from, coordinate);
                    System.out.println("Moved Token: " + selectedToken + " from " + from + " to " + coordinate);
                } else {
                    levelController.transferTokenToPlacedTokens(selectedToken, coordinate);
                    System.out.println("Placed Token: " + selectedToken + " at " + coordinate);
                }
            } else {
                levelPanel.getExtrasUI().handleTokenDrop(selectedToken, endX, endY, levelController);
            }

            selectedToken = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedToken != null) {
            tokenDisplay.setDraggedToken(selectedToken, e.getX(), e.getY());

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
