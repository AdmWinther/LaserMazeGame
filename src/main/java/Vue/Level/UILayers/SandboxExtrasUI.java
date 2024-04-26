package Vue.Level.UILayers;

import Controller.EditableLevelController;
import Controller.LevelController;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Pair;
import Vue.Level.EditableLevelPanel;

import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.time.Year;
import java.util.Objects;

public class SandboxExtrasUI extends ExtrasUI {
    public SandboxExtrasUI(EditableLevelPanel levelPanel) {
        super(levelPanel, false);
        initializeObjectSet();
        setPlacedObjects();
    }

    @Override
    public void handleTokenDrop(Token token, int x, int y, LevelController controller) {
        super.handleTokenDrop(token, x, y, controller);

        EditableLevelController editableController = (EditableLevelController) controller;
        Rectangle2D bin = getPlacedObjects().get("bin");

        if (bin.contains(x, y)) {
            editableController.removeToken(token);
            System.out.println("Deleted Token: " + token);
        }
    }

    /**
     * Initialize the object set
     *
     * @author Léonard Amsler - s231715
     * @author Hussein (Adam)
     */
    public void initializeObjectSet() {
        super.initializeObjectSet();

        try {
            BufferedImage binImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Objects/bin.png")));

            getObjectImages().put("bin", binImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the placed objects
     *
     * @author Léonard Amsler - s231715
     */
    public void setPlacedObjects() {
        super.setPlacedObjects();

        Pair<Integer, Integer> pos = ((EditableLevelPanel) getLevelPanel()).getBinPosition();
        placeObject("bin", pos.first(), pos.second());
    }
}
