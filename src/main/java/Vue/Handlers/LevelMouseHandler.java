package Vue.Handlers;

import Controller.LevelController;
import Model.constants.MouseConstants;
import Vue.Level.LevelPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class LevelMouseHandler implements MouseListener {
    private static final int CLICK_MOVEMENT_THRESHOLD = MouseConstants.CLICK_MOVEMENT_THRESHOLD;

    private final LevelController levelController;
    private final LevelPanel levelPanel;

    private boolean isPressed = false;
    private int startX;
    private int startY;

    public LevelMouseHandler(LevelPanel levelPanel, LevelController levelController) {
        this.levelPanel = levelPanel;
        this.levelController = levelController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if we have clicked on the reset button
        Rectangle2D reset = levelPanel.UIObjects.getPlacedObjects().get("reset");
        if (reset.contains(e.getX(), e.getY())) {
            levelController.resetLevel();
            System.out.println("Reset tokens");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!isPressed) {
            isPressed = true;
            startX = e.getX();
            startY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isPressed) {
            isPressed = false;

            if (Math.abs(e.getX() - startX) < CLICK_MOVEMENT_THRESHOLD && Math.abs(e.getY() - startY) < CLICK_MOVEMENT_THRESHOLD) {
                mouseClicked(e);
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
