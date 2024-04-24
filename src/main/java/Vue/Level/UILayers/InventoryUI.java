package Vue.Level.UILayers;

import Controller.EditableLevelController;
import Vue.Interfaces.Drawable;

import java.awt.*;

public class InventoryUI implements Drawable {

    private final EditableLevelController levelController;

    public InventoryUI(EditableLevelController levelController) {
        this.levelController = levelController;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO
    }
}
