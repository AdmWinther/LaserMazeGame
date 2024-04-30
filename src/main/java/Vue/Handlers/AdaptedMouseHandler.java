package Vue.Handlers;

import Model.Constants.MouseConstants;
import Vue.Utils.Position;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Abstract class that adapts the MouseHandler to the needs of the application
 * It is meant to be extended by classes that need to handle mouse events
 * Allowing to register a drag as a click if it is very short
 *
 * @author Nathan Gromb
 */
public abstract class AdaptedMouseHandler implements MouseListener {

	/**
	 * Check if a drag is very short, and returns whether to consider it as a click
	 *
	 * @param e        MouseEvent to check
	 * @param startPos Position of the start of the drag
	 * @return Whether to consider the drag as a click
	 * @author Nathan Gromb
	 */
	public boolean registerDragAsClick(MouseEvent e, Position startPos) {
		Position endPos = Position.ofEvent(e);

		int threshold = MouseConstants.CLICK_MOVEMENT_THRESHOLD;

		return Math.abs(endPos.x() - startPos.x()) < threshold && Math.abs(endPos.y() - startPos.y()) < threshold && e.getClickCount() == 0;
	}
}
