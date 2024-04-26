package Vue.Handlers;

import Vue.Level.UILayers.TokensUI;

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
    private final TokensUI tokensUI;
    private final TokenMouseHandler tokenMouseHandler;

    public TokenMouseMotionHandler(TokensUI tokensUI, TokenMouseHandler tokenMouseHandler) {
        this.tokensUI = tokensUI;
        this.tokenMouseHandler = tokenMouseHandler;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (tokenMouseHandler.getSelectedToken() != null) {
            tokensUI.setDraggedToken(tokenMouseHandler.getSelectedToken(), e.getX(), e.getY());

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
