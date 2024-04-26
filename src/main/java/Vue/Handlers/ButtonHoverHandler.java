package Vue.Handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonHoverHandler extends MouseAdapter {

    @Override
    public void mouseEntered(MouseEvent e) {
        // change the cursor to hand cursor
        e.getComponent().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // change the cursor to default cursor
        e.getComponent().setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
}
