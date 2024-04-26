package Vue.Handlers;

import Controller.EditableLevelController;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Model.constants.MouseConstants;
import Vue.Level.EditableLevelPanel;
import Vue.Level.UILayers.InventoryUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class InventoryMouseHandler extends AdaptedMouseHandler implements MouseListener {
    private final EditableLevelController levelController;
    private final InventoryUI tokenDisplay;

    boolean isPressed = false;
    int startX;
    int startY;

    public InventoryMouseHandler(EditableLevelController levelController, InventoryUI tokenDisplay) {
        this.levelController = levelController;
        this.tokenDisplay = tokenDisplay;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Token token = tokenDisplay.getTokenAt(e.getX(), e.getY());
        if (token != null) {
            levelController.addToUnplacedTokens(token.copy());
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

            if (registerDragAsClick(e, startX, startY)) {
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
