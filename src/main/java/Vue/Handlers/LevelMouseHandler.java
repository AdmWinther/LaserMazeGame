package Vue.Handlers;

import Controller.LevelController;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Vue.Level.LevelPanel;
import Vue.Level.UITokens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class LevelMouseHandler implements MouseListener {

    private LevelController levelController;
    private LevelPanel levelPanel;
    private UITokens uiTokens;

    private boolean isPressed = false;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Token selectedToken = null;
    private boolean isSelectedPlaced = false;


    public LevelMouseHandler(LevelPanel levelPanel, LevelController levelController, UITokens uiTokens) {
        this.levelPanel = levelPanel;
        this.levelController = levelController;
        this.uiTokens = uiTokens;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if we have clicked on the reset button
        Rectangle2D reset = levelPanel.UIObjects.getPlacedObjects().get("reset");
        if (reset.contains(e.getX(), e.getY())) {
            levelController.resetLevel();
            System.out.println("Reset tokens");
        }

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
            }
        } else {
            Token token = uiTokens.getUnplacedTokenAt(e.getX(), e.getY());
            if (token != null) {
                selectedToken = token;
                isSelectedPlaced = false;
                System.out.println("Selected Token: " + selectedToken);
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
                System.out.println("Clicked on: " + x_coordinate + ", " + y_coordinate);
                Coordinate coordinate = new Coordinate(x_coordinate, y_coordinate);
                Token token = levelController.getTokenAtCoordinate(coordinate);
                System.out.println("Token: " + token);
                if (token != null && token.isMovable()) {
                    selectedToken = token;
                    isSelectedPlaced = true;
                    System.out.println("Selected Token: " + selectedToken);
                }
            } else {
                Token token = uiTokens.getUnplacedTokenAt(startX, startY);
                if (token != null) {
                    selectedToken = token;
                    isSelectedPlaced = false;
                    System.out.println("Selected Token: " + selectedToken);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isPressed) {
            System.out.println("Mouse released");

            isPressed = false;

            endX = e.getX();
            endY = e.getY();

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
                System.out.println("Released on: " + x_coordinate + ", " + y_coordinate);
                Coordinate coordinate = new Coordinate((int) x_coordinate, (int) y_coordinate);
                if (isSelectedPlaced) {
                    Coordinate from = new Coordinate((startX - widthOffset) / tileWidth, (startY - heightOffset) / tileHeight);
                    levelController.moveToken(from, coordinate);
                    System.out.println("Moved Token: " + selectedToken + " from " + from + " to " + coordinate);
                } else {
                    levelController.placeToken(selectedToken, coordinate);
                    System.out.println("Placed Token: " + selectedToken + " at " + coordinate);
                }
            } else {
                Rectangle2D bin = levelPanel.UIObjects.getPlacedObjects().get("bin");
                if (bin.contains(endX, endY)) {
                    levelController.removeToken(selectedToken);
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
}
