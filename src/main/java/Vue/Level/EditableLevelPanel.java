package Vue.Level;

import Controller.EditableLevelController;
import Model.Classes.Utils.Pair;
import Vue.Handlers.InventoryMouseHandler;
import Vue.Level.UILayers.AnimationsUI;
import Vue.Level.UILayers.ExtrasUI;
import Vue.Level.UILayers.InventoryUI;
import Vue.Level.UILayers.SandboxExtrasUI;

import java.awt.*;

public final class EditableLevelPanel extends LevelPanel {

    private final InventoryUI inventoryUI;
    private final AnimationsUI animationsUI;
    private final SandboxExtrasUI extrasUI;

    public EditableLevelPanel(EditableLevelController levelController) {
        super(levelController);
        inventoryUI = new InventoryUI(this, levelController);
        InventoryMouseHandler inventoryMouseHandler = new InventoryMouseHandler(levelController, inventoryUI);
        addMouseListener(inventoryMouseHandler);

        extrasUI = new SandboxExtrasUI(this);
        animationsUI = new AnimationsUI(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);
        drawTiles(g2d);
        drawLasers(g2d);
        extrasUI.draw(g2d);
        inventoryUI.draw(g2d);
        drawTokens(g2d);
        animationsUI.draw(g2d);
        
    }

    public ExtrasUI getExtrasUI() {
        return extrasUI;
    }

    public Pair<Integer, Integer> getBinPosition() {
        return inventoryUI.getBinPosition();
    }
}
