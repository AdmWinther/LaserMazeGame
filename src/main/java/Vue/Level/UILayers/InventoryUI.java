package Vue.Level.UILayers;

import Controller.EditableLevelController;
import Model.Classes.Token.Token;
import Model.Interfaces.Inventory;
import Vue.Level.LevelPanel;
import Vue.Utils.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * UI of the inventory
 *
 * @author Nathan Gromb
 */
public class InventoryUI extends TokenDisplay {
	private final Inventory inventory;
	private final int size;

	private final int xCoord;
	private int binYCoord;

	/**
	 * Constructor of the inventory UI
	 *
	 * @param levelPanel      The level panel
	 * @param levelController The level controller
	 */
	public InventoryUI(LevelPanel levelPanel, EditableLevelController levelController) {
		super(levelPanel, levelController);

		inventory = levelController.getInventory();
		size = inventory.getItems().size();

		xCoord = levelPanel.getLevelPanelConfig().getTileWidth() / 2;
	}

	/**
	 * Draws the inventory on the screen.
	 *
	 * @param g2d object on which to draw
	 * @author Nathan Gromb
	 */
	@Override
	public void draw(Graphics2D g2d) {
		int tileWidth = levelPanel.getLevelPanelConfig().getTileWidth();
		int tileHeight = levelPanel.getLevelPanelConfig().getTileHeight();

		int y = (levelPanel.getLevelPanelConfig().getMaxRow() - size) / 2 * tileHeight - tileHeight;

		rectangles.clear();

		// draw a token an increment y to position the next token
		for (Token token : inventory.getItems()) {
			Rectangle2D rect = new Rectangle2D.Double(xCoord, y, levelPanel.getLevelPanelConfig().getTileWidth(), levelPanel.getLevelPanelConfig().getTileHeight());
			rectangles.put(token, rect);

			Position position = getTokenPosition(token, Position.of(xCoord, y));
			drawToken(g2d, token, position, tileWidth, tileHeight);

			y += tileHeight;
		}

		binYCoord = y + tileHeight;
	}

	/**
	 * Returns where the bin should be drawn
	 *
	 * @return where the bin should be drawn
	 * @author Nathan Gromb
	 */
	public Position getBinPosition() {
		return Position.of(xCoord, binYCoord);
	}
}
