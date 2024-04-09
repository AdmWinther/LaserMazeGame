package Classes.Handlers;

import Classes.Game.GamePanel;
import Classes.Level;
import Classes.Tokens.Token;
import Classes.Utils.Coordinate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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


    public MouseHandler(GamePanel gamePanel, Level level, int widthOffset, int heightOffset) {
        this.level = level;
        this.widthOffset = widthOffset;
        this.heightOffset = heightOffset;
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

            if (x_coordinate >= 0 && x_coordinate < level.getBoard().getWidth() && y_coordinate >= 0 && y_coordinate < level.getBoard().getHeight()) {
                Coordinate coordinate = new Coordinate(x_coordinate, y_coordinate);
                selectedToken = level.getBoard().getTokenAt(coordinate);
            } else {
                selectedToken = null;
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

            if (x_coordinate < level.getBoard().getWidth() && y_coordinate < level.getBoard().getHeight()) {
                Coordinate coordinate = new Coordinate((int) x_coordinate, (int) y_coordinate);
                if (selectedToken != null) {
                    Coordinate from = new Coordinate((startX - widthOffset) / tileSize, (startY - heightOffset) / tileSize);
                    level.moveTokenFromTo(from, coordinate);
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
