package Vue.Handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class that handles the hover effect on buttons
 *
 * @author Hugo Demule
 */
public final class ButtonHoverHandler extends MouseAdapter {

	/**
	 * Method that changes the cursor to hand cursor when the mouse enters the button
	 *
	 * @param e the event to be processed
	 * @author Hugo Demule
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// change the cursor to hand cursor
		e.getComponent().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

	/**
	 * Method that changes the cursor to default cursor when the mouse exits the button
	 *
	 * @param e the event to be processed
	 * @author Hugo Demule
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// change the cursor to default cursor
		e.getComponent().setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
	}
}
