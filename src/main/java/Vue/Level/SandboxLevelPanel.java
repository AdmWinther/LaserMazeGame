package Vue.Level;

import Controller.EditableLevelController;
import Vue.Level.UILayers.InventoryUI;

import java.awt.*;

public final class SandboxLevelPanel extends LevelPanel {

    private final InventoryUI InventoryUI;

    public SandboxLevelPanel(EditableLevelController levelController) {
        super(levelController);
        InventoryUI = new InventoryUI(levelController);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);

        InventoryUI.draw(g2d);
    }
}
