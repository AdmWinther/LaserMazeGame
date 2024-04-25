package Vue.Handlers;

import Controller.LevelController;
import Model.Classes.Token.OrientedToken;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Model.constants.MouseConstants;
import Vue.Level.LevelPanel;
import Vue.Level.UILayers.TokensUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.function.DoubleToIntFunction;

/**
 * This class is responsible for handling the mouse events for the tokens.
 * It handles the token selection, placement and removal.
 *
 * @see TokenMouseMotionHandler
 * @author Nathan Gromb
 * @author Leonard Amsler
 */
public class TokenMouseHandler implements MouseListener {
    private static final int CLICK_MOVEMENT_THRESHOLD = MouseConstants.CLICK_MOVEMENT_THRESHOLD;

    private final LevelController levelController;
    private final LevelPanel levelPanel;
    private final TokensUI tokensUI;

    private boolean isPressed = false;
    private int startX;
    private int startY;

    private Token selectedToken = null;
    private boolean isSelectedPlaced = false;

    public TokenMouseHandler(LevelPanel levelPanel, LevelController levelController, TokensUI tokensUI) {
        this.levelPanel = levelPanel;
        this.levelController = levelController;
        this.tokensUI = tokensUI;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selectedToken = null;
        tokensUI.resetDraggedToken();

        int tileWidth = levelPanel.tileWidth;
        int tileHeight = levelPanel.tileHeight;

        int widthOffset = levelPanel.widthOffset;
        int heightOffset = levelPanel.heightOffset;

        int x_coordinate = (e.getX() - widthOffset) / tileWidth;
        int y_coordinate = (e.getY() - heightOffset) / tileHeight;

        int maxWidth = levelController.getWidth();
        int maxHeight = levelController.getHeight();

        if (x_coordinate >= 0 && x_coordinate < maxWidth && y_coordinate >= 0 && y_coordinate < maxHeight) {
            Coordinate laserGunCoordinate = levelController.getLaserGunCoordinate();
            System.out.println("Clicked on: " + x_coordinate + ", " + y_coordinate);
            Coordinate coordinate = new Coordinate(x_coordinate, y_coordinate);

            if (laserGunCoordinate.equals(coordinate)) {
                levelController.setShouldDisplayLaser(true);
            } else {
                Token other = levelController.getTokenAt(coordinate);

                if (other != null && other.isMovable() && other instanceof OrientedToken) {
                    levelController.rotateToken(other);
                }
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
                Token token = tokensUI.getUnplacedTokenAt(startX, startY);
                System.out.println("Token chosen!" + token);
                if (token != null) {
                    selectedToken = token;
                    isSelectedPlaced = false;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tokensUI.resetDraggedToken();

        if (isPressed) {
            isPressed = false;

            int endX = e.getX();
            int endY = e.getY();

            // If the drag is very short, consider it as a click
            // make sure that mouseClicked has not been called already using e.getClickCount() == 0
            if (Math.abs(endX - startX) < CLICK_MOVEMENT_THRESHOLD && Math.abs(endY - startY) < CLICK_MOVEMENT_THRESHOLD
                    && e.getClickCount() == 0) {
                mouseClicked(e);
                return;
            } else if (e.getClickCount() > 1) {
                return;
            }

            int widthOffset = levelPanel.widthOffset;
            int heightOffset = levelPanel.heightOffset;

            double x_coordinate = (endX - widthOffset);
            double y_coordinate = (endY - heightOffset);

            if (x_coordinate < 0 || y_coordinate < 0) {
                return;
            }

            int tileWidth = levelPanel.tileWidth;
            int tileHeight = levelPanel.tileHeight;

            x_coordinate /= tileWidth;
            y_coordinate /= tileHeight;

            int maxWidth = levelController.getWidth();
            int maxHeight = levelController.getHeight();

            if (selectedToken == null) {
                return;
            }

            if (x_coordinate < maxWidth && y_coordinate < maxHeight) {
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
                Rectangle2D bin = levelPanel.ExtrasUI.getPlacedObjects().get("bin");
                if (bin.contains(endX, endY)) {
                    levelController.transferTokenToUnplacedTokens(selectedToken);
                    System.out.println("Removed Token: " + selectedToken);
                }
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

    public Token getSelectedToken() {
        return selectedToken;
    }
}
