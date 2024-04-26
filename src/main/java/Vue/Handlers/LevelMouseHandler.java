package Vue.Handlers;

import Controller.LevelController;
import Model.constants.MouseConstants;
import Vue.Level.LevelPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse handler for the level panel
 * This class handles the mouse events on the level panel
 *
 * @author Léonard Amsler - s231715
 */
public class LevelMouseHandler extends AdaptedMouseHandler implements MouseListener {
    private static final int CLICK_MOVEMENT_THRESHOLD = MouseConstants.CLICK_MOVEMENT_THRESHOLD;

    private final LevelController levelController;
    private final LevelPanel levelPanel;

    private boolean isPressed = false;
    private int startX;
    private int startY;


    /**
     * Constructor of the level mouse handler
     * This class handles the mouse events on the level panel
     *
     * @param levelPanel      - The level panel
     * @param levelController - The level controller
     * @author Léonard Amsler - s231715
     */
    public LevelMouseHandler(LevelPanel levelPanel, LevelController levelController) {
        this.levelPanel = levelPanel;
        this.levelController = levelController;
    }

    /**
     * Handle the mouse click event
     * This method is called when the mouse is clicked
     *
     * @param e - The mouse event
     * @author Léonard Amsler - s231715
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if we have clicked on the reset button
        levelPanel.getExtrasUI().handleClick(e.getX(), e.getY());
    }

    /**
     * Handle the mouse press event
     * This method is called when the mouse is pressed
     *
     * @param e the event to be processed
     * @author Léonard Amsler - s231715
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

            if (registerDragAsClick(e, startX, startY)) {
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
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Handle the mouse exit event
     * This method is called when the mouse exits the level panel
     * This method is not used
     *
     * @param e the event to be processed
     * @author Léonard Amsler - s231715
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
