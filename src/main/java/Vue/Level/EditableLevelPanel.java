package Vue.Level;

import Controller.EditableLevelController;
import Controller.GameController;
import Controller.LoginController;
import Vue.Handlers.InventoryMouseHandler;
import Vue.Level.UILayers.AnimationsUI;
import Vue.Level.UILayers.ExtrasUI;
import Vue.Level.UILayers.InventoryUI;
import Vue.Level.UILayers.SandboxExtrasUI;
import Vue.Utils.Position;

import javax.swing.*;
import java.awt.*;

public final class EditableLevelPanel extends LevelPanel {

    private final InventoryUI inventoryUI;
    private final AnimationsUI animationsUI;
    private final SandboxExtrasUI extrasUI;

    public EditableLevelPanel(JFrame frame, GameController gameController, EditableLevelController levelController, LoginController loginController) {
        super(frame, gameController, levelController, loginController);

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

    public Position getBinPosition() {
        return inventoryUI.getBinPosition();
    }

    /**
     * Saves the current level
     *
     * @return True if the level was saved successfully
     */
    public boolean saveLevel() {
        return ((EditableLevelController) levelController).saveLevel();
    }
}
