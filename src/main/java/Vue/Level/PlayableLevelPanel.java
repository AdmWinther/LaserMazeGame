package Vue.Level;

import Controller.PlayableLevelController;
import Vue.Level.UILayers.AnimationsUI;

import java.awt.*;

public final class PlayableLevelPanel extends LevelPanel {

    private final AnimationsUI AnimationsUI;

    public PlayableLevelPanel(PlayableLevelController levelController) {
        super(levelController);
        AnimationsUI = new AnimationsUI(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);

        AnimationsUI.draw(g2d);
    }
}
