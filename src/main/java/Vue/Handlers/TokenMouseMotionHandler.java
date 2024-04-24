package Vue.Handlers;

import Controller.LevelController;
import Vue.Level.UITokens;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * This class is responsible for handling the mouse drag events for the tokens.
 * The effect is only visual, and the token is not actually moved.
 *
 * @see TokenMouseHandler
 * @author Nathan Gromb
 */
public class TokenMouseMotionHandler implements MouseMotionListener {
    private final UITokens uiTokens;
    private final TokenMouseHandler tokenMouseHandler;

    public TokenMouseMotionHandler(UITokens uiTokens, TokenMouseHandler tokenMouseHandler) {
        this.uiTokens = uiTokens;
        this.tokenMouseHandler = tokenMouseHandler;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (tokenMouseHandler.getSelectedToken() != null) {
            uiTokens.setDraggedToken(tokenMouseHandler.getSelectedToken(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
