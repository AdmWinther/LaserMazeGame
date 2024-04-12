package Classes.Handlers;

import Classes.Game.GamePanel;
import Classes.Level;
import Classes.Tokens.Token;
import Classes.Utils.Coordinate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class MouseHandler implements MouseListener {

    private final Level level;
    private final int widthOffset;
    private final int heightOffset;
    private final GamePanel gamePanel;
    private boolean isPressed = false;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Token selectedToken = null;
    private boolean isSelectedPlaced = false;


    public MouseHandler(GamePanel gamePanel, Level level, int widthOffset, int heightOffset) {
        this.level = level;
        this.widthOffset = widthOffset;
        this.heightOffset = heightOffset;
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if we have clicked on the reset button
        Rectangle2D reset = gamePanel.objectsManager.getPlacedObjects().get("reset");
        if (reset.contains(e.getX(), e.getY())) {
            level.tokenManager().resetLevel();
            System.out.println("Reset tokens");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!isPressed) {
            isPressed = true;
            startX = e.getX();
            startY = e.getY();
            int tileSize = gamePanel.tileSize;
            int x_coordinate = (startX - widthOffset) / tileSize;
            int y_coordinate = (startY - heightOffset) / tileSize;

            int maxWidth = level.tokenManager().getWidthX();
            int maxHeight = level.tokenManager().getHeightY();

            if (x_coordinate >= 0 && x_coordinate < maxWidth && y_coordinate >= 0 && y_coordinate < maxHeight) {
                Coordinate coordinate = new Coordinate(x_coordinate, y_coordinate);
                Token token = level.tokenManager().getTokenAt(coordinate);
                if (token != null && token.isMovable()) {
                    selectedToken = level.tokenManager().getTokenAt(coordinate);
                    isSelectedPlaced = true;
                    System.out.println("Selected Token: " + selectedToken);
                }
            } else {
                Token token = gamePanel.tokenManager.getUnplacedTokenAt(startX, startY);
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
            isPressed = false;
            endX = e.getX();
            endY = e.getY();

            int tileSize = gamePanel.tileSize;
            double x_coordinate = (endX - widthOffset);
            double y_coordinate = (endY - heightOffset);

            if (x_coordinate < 0 || y_coordinate < 0) {
                return;
            }

            x_coordinate /= tileSize;
            y_coordinate /= tileSize;

            int maxWidth = level.tokenManager().getWidthX();
            int maxHeight = level.tokenManager().getHeightY();

            if (x_coordinate < maxWidth && y_coordinate < maxHeight) {
                Coordinate coordinate = new Coordinate((int) x_coordinate, (int) y_coordinate);
                if (selectedToken != null) {
                    Coordinate from = new Coordinate((startX - widthOffset) / tileSize, (startY - heightOffset) / tileSize);
                    if (isSelectedPlaced) {
                        level.tokenManager().moveToken(from, coordinate);
                        System.out.println("Moved Token: " + selectedToken + " from " + from + " to " + coordinate);

                    } else {
                        level.tokenManager().transferTokenToPlacedTokens(selectedToken, coordinate);
                        System.out.println("Placed Token: " + selectedToken + " at " + coordinate);
                    }
                }
            } else {
                Rectangle2D bin = gamePanel.objectsManager.getPlacedObjects().get("bin");
                if (bin.contains(endX, endY)) {
                    level.tokenManager().transferTokenToUnplacedTokens(selectedToken);
                    System.out.println("Token moved to unplaced tokens");
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
