package Vue.Handlers;

import Model.constants.MouseConstants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class AdaptedMouseHandler implements MouseListener {

    /**
     * Check if a drag is very short, and returns whether to consider it as a click
     *
     * @param e MouseEvent to check
     * @param startX Start X coordinate of the drag
     * @param startY Start Y coordinate of the drag
     * @return Whether to consider the drag as a click
     * @author Nathan Gromb
     */
    public boolean registerDragAsClick(MouseEvent e, int startX, int startY){
        int endX = e.getX();
        int endY = e.getY();

        int threshold = MouseConstants.CLICK_MOVEMENT_THRESHOLD;

        if ((Math.abs(endX - startX) < threshold && Math.abs(endY - startY) < threshold
                && e.getClickCount() == 0)) {
            return true;
        }

        return false;
    }
}
