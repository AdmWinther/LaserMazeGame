package Vue.Level;

import Controller.EditableLevelController;
import Vue.Level.UILayers.AnimationsUI;
import Vue.Level.UILayers.InventoryUI;

import java.awt.*;

public final class EditableLevelPanel extends LevelPanel {

    private final InventoryUI inventoryUI;
    private final AnimationsUI animationsUI;

    public EditableLevelPanel(EditableLevelController levelController) {
        super(levelController);
        inventoryUI = new InventoryUI(this, levelController);
        animationsUI = new AnimationsUI(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);
        inventoryUI.draw(g2d);
        animationsUI.draw(g2d);
        
    }
}
