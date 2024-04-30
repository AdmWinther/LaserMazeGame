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

/**
 * This class has the responsibility to control the editable level panel and extends the functionalities of the level panel.
 *
 * @author Hugo Demule
 * @author Nathan Gromb
 * @see LevelPanel
 */
public final class EditableLevelPanel extends LevelPanel<EditableLevelController> {

    private final InventoryUI inventoryUI;
    private final AnimationsUI animationsUI;
    private final SandboxExtrasUI extrasUI;

    /**
     * Constructor of the EditableLevelPanel
     *
     * @param frame           The frame of the game
     * @param gameController  The game controller of the game
     * @param levelController The level controller of the game
     * @param loginController The login controller of the game
     * @author Nathan Gromb
     */
    public EditableLevelPanel(JFrame frame, GameController gameController, EditableLevelController levelController, LoginController loginController) {
        super(frame, gameController, levelController, loginController);

        inventoryUI = new InventoryUI(this, levelController);
        InventoryMouseHandler inventoryMouseHandler = new InventoryMouseHandler(levelController, inventoryUI);
        addMouseListener(inventoryMouseHandler);

        extrasUI = new SandboxExtrasUI(this);
        animationsUI = new AnimationsUI(this);
    }

    /**
     * paints the components
     *
     * @param g The graphics object
     * @author Hugo Demule
     * @author Nathan Gromb
     */
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

    /**
     * @return The extra UI
     * @author Nathan Gromb
     */
    public ExtrasUI getExtrasUI() {
        return extrasUI;
    }

    /**
     * @return the bin position
     * @author Nathan Gromb
     */
    public Position getBinPosition() {
        return inventoryUI.getBinPosition();
    }

    /**
     * Saves the current level
     *
     * @return True if the level was saved successfully, false otherwise
     * @author Hugo Demule
     */
    public boolean saveLevel() {
        return levelController.saveLevel();
    }
}
