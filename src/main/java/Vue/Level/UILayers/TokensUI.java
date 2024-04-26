package Vue.Level.UILayers;

import Controller.LevelController;
import Model.Classes.Token.*;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;
import Vue.Level.LevelPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible for handling the tokens' display in the level.
 *
 * @see LevelPanel
 * @author Léonard Amsler, Nathan Gromb
 */
public class TokensUI extends TokenDisplay {

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

            Pair<Integer, Integer> position = getTokenPosition(token, x, y);
            drawToken(g2d, token, position.first(), position.second(), levelPanel.tileWidth, levelPanel.tileHeight);
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

        int nbTilesHorizontal = levelPanel.maxCol;
        int nbTilesVertical = levelPanel.maxRow;

        Set<Token> unplacedTokens = levelController.getUnplacedTokens();
        int size = unplacedTokens.size();

        int sidePadding = (nbTilesHorizontal - size) / 2 * tileWidth; // Center the unplaced tokens horizontally

        int x = sidePadding;
        int y = nbTilesVertical * tileHeight - tileHeight;

        rectangles.clear();

        for (Token token : unplacedTokens) {
            Rectangle2D rect = new Rectangle2D.Double(x, y, tileWidth, tileHeight);
            rectangles.put(token, rect);

            Pair<Integer, Integer> position = getTokenPosition(token, x, y);
            drawToken(g2d, token, position.first(), position.second(), tileWidth, tileHeight);

            x += tileWidth;
        }
    }
}
