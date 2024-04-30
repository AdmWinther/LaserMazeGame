package Vue.Level.UILayers;

import Controller.EditableLevelController;
import Controller.LevelController;
import Model.Classes.Token.Token;
import Vue.Level.EditableLevelPanel;
import Vue.Utils.Position;

import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * UI of the sandbox extras
 *
 * @author Nathan Gromb
 */
public class SandboxExtrasUI extends ExtrasUI {
	/**
	 * Constructor of the sandbox extras UI
	 *
	 * @param levelPanel The level panel
	 * @author Nathan Gromb
	 */
	public SandboxExtrasUI(EditableLevelPanel levelPanel) {
		super(levelPanel, false);

		loadObjectImages();
		setPlacedObjects();
	}

	/**
	 * Initialize the object set
	 *
	 * @author Léonard Amsler - s231715
	 * @author Hussein (Adam)
	 */
	public void loadObjectImages() {
		super.loadObjectImages();

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

		Position pos = ((EditableLevelPanel) getLevelPanel()).getBinPosition();
		placeObject("bin", pos.x(), pos.y());
	}

	/**
	 * Handle the mouse release while a token is being dragged
	 *
	 * @param token      The token being dragged
	 * @param pos        The position of the mouse
	 * @param controller The level controller
	 */
	@Override
	public void handleTokenDrop(Token token, Position pos, LevelController controller) {
		super.handleTokenDrop(token, pos, controller);

		EditableLevelController editableController = (EditableLevelController) controller;
		Rectangle2D bin = getPlacedObjects().get("bin");

		if (bin.contains(pos.x(), pos.y())) {
			editableController.removeToken(token);
		}
	}
}
