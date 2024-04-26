package Vue.Level;

import Controller.PlayableLevelController;
import Vue.Level.UILayers.AnimationsUI;
import Vue.Level.UILayers.ExtrasUI;

import java.awt.*;

public final class PlayableLevelPanel extends LevelPanel {

    private final AnimationsUI animationsUI;
    private final ExtrasUI extrasUI;

    public PlayableLevelPanel(PlayableLevelController levelController) {
        super(levelController);
        extrasUI = new ExtrasUI(this);
        animationsUI = new AnimationsUI(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);
        drawTiles(g2d);
        drawLasers(g2d);
        extrasUI.draw(g2d);
        drawTokens(g2d);
        animationsUI.draw(g2d);
    }

    public ExtrasUI getExtrasUI() {
        return extrasUI;
    }
}
