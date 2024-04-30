package Vue.Level.UILayers;

import Controller.LevelController;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Vue.Level.LevelPanel;
import Vue.Utils.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible for handling the tokens' display in the level.
 *
 * @author Léonard Amsler
 * @author Nathan Gromb
 */
public class TokensUI extends TokenDisplay {

	/**
	 * Constructor for the TokensUI class.
	 *
	 * @param levelPanel      The level panel
	 * @param levelController The level controller
	 * @author Nathan Gromb
	 */
	public TokensUI(LevelPanel levelPanel, LevelController levelController) {
		super(levelPanel, levelController);
	}

	/**
	 * Draws the tokens on the board.
	 *
	 * @param g2d The graphics object to draw on
	 * @author Léonard Amsler
	 */
	public void draw(Graphics2D g2d) {
		drawPlacedTokens(g2d);
		drawUnplacedTokens(g2d);
	}

	/**
	 * Draws the tokens that are placed on the board.
	 *
	 * @param g2d The graphics object to draw on
	 * @author Léonard Amsler
	 */
	private void drawPlacedTokens(Graphics2D g2d) {

		Map<Coordinate, Token> placedTokens = levelController.getPlacedTokens();

		int widthOffset = levelPanel.widthOffset;
		int heightOffset = levelPanel.heightOffset;

		for (Map.Entry<Coordinate, Token> entry : placedTokens.entrySet()) {
			Coordinate coordinate = entry.getKey();
			Token token = entry.getValue();

			int x = widthOffset + coordinate.x() * levelPanel.tileWidth;
			int y = heightOffset + coordinate.y() * levelPanel.tileHeight;

			Position position = getTokenPosition(token, Position.of(x, y));

			drawToken(g2d, token, position, levelPanel.tileWidth, levelPanel.tileHeight);
		}
	}

	/**
	 * Draws the tokens that are not placed on the board.
	 *
	 * @param g2d The graphics object to draw on
	 * @author Léonard Amsler
	 */
	private void drawUnplacedTokens(Graphics2D g2d) {
		// Display the unplaced tokens at the bottom of the screen, centered horizontally
		int tileWidth = levelPanel.tileWidth;
		int tileHeight = levelPanel.tileHeight;

		Set<Token> unplacedTokens = levelController.getUnplacedTokens();

		int size = unplacedTokens.size();
		int x = (levelPanel.maxCol - size) / 2 * tileWidth;
		int y = levelPanel.maxRow * tileHeight - tileHeight;

		rectangles.clear();

		for (Token token : unplacedTokens) {
			Rectangle2D rect = new Rectangle2D.Double(x, y, tileWidth, tileHeight);
			rectangles.put(token, rect);

			Position position = getTokenPosition(token, Position.of(x, y));
			drawToken(g2d, token, position, tileWidth, tileHeight);

			x += tileWidth;
		}
	}
}
