package Vue.Level;

import Controller.LevelController;
import Model.Classes.Token.*;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;
import Vue.Interfaces.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class is responsible for handling the tokens' display in the level.
 *
 * @see LevelPanel
 * @author Léonard Amsler, Nathan Gromb
 */
public class UITokens implements Drawable {

    LevelController levelController;
    Map<Token, Rectangle2D> unPlacedTokenRectangles = new HashMap<>();
    Map<String, BufferedImage> tokenImages = new HashMap<>();
    Map<Pair<String, Orientation>, BufferedImage> orientedTokenImages = new HashMap<>();
    LevelPanel levelPanel;

    //TODO make this a 3-tuple
    private Pair<Token, Pair<Integer, Integer>> draggedToken = null;

    public UITokens(LevelPanel levelPanel, LevelController levelController) {
        this.levelController = levelController;
        this.levelPanel = levelPanel;
        setTokenImages();
    }

    /**
     * Loads the images for the tokens.
     *
     * @author Léonard Amsler
     */
    private void setTokenImages() {

        try {
            // 1. Load the images for the tokens
            BufferedImage beamerImageUP = readImage("/Tokens/lasergun_UP.png");
            BufferedImage beamerImageDOWN = readImage("/Tokens/lasergun_DOWN.png");
            BufferedImage beamerImageLEFT = readImage("/Tokens/lasergun_LEFT.png");
            BufferedImage beamerImageRIGHT = readImage("/Tokens/lasergun_RIGHT.png");

            BufferedImage blockerImage = readImage("/Tokens/block.png");

            BufferedImage mirrorImageUP = readImage("/Tokens/mirror_UP.png");
            BufferedImage mirrorImageDOWN = readImage("/Tokens/mirror_DOWN.png");
            BufferedImage mirrorImageLEFT = readImage("/Tokens/mirror_LEFT.png");
            BufferedImage mirrorImageRIGHT = readImage("/Tokens/mirror_RIGHT.png");

            BufferedImage doubleMirrorImageUPDOWN = readImage("/Tokens/doubleMirror_UD.png");
            BufferedImage doubleMirrorImageLEFTRIGHT = readImage("/Tokens/doubleMirror_RL.png");

            BufferedImage targetImageUP = readImage("/Tokens/target_UP.png");
            BufferedImage targetImageDOWN = readImage("/Tokens/target_DOWN.png");
            BufferedImage targetImageLEFT = readImage("/Tokens/target_LEFT.png");
            BufferedImage targetImageRIGHT = readImage("/Tokens/target_RIGHT.png");

            // 2. Store the images in the maps
            String beamerClassName = LaserGun.class.getSimpleName();
            putOrientedTokenImage(beamerImageUP, beamerImageDOWN, beamerImageLEFT, beamerImageRIGHT, beamerClassName);

            String blockerClassName = Block.class.getSimpleName();
            tokenImages.put(blockerClassName, blockerImage);

            String mirrorClassName = OneSidedMirror.class.getSimpleName();
            putOrientedTokenImage(mirrorImageUP, mirrorImageDOWN, mirrorImageLEFT, mirrorImageRIGHT, mirrorClassName);

            String doubleMirrorClassName = DoubleSidedMirror.class.getSimpleName();
            putOrientedTokenImage(doubleMirrorImageUPDOWN, doubleMirrorImageUPDOWN, doubleMirrorImageLEFTRIGHT, doubleMirrorImageLEFTRIGHT, doubleMirrorClassName);

            String targetClassName = Target.class.getSimpleName();
            putOrientedTokenImage(targetImageUP, targetImageDOWN, targetImageLEFT, targetImageRIGHT, targetClassName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method to read an image from the resources.
     *
     * @author Nathan Gromb
     */
    private BufferedImage readImage(String path) throws Exception {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
    }

    /**
     * Utility method to store the oriented token images in the map.
     *
     * @author Nathan Gromb
     */
    private void putOrientedTokenImage(BufferedImage beamerImageUP, BufferedImage beamerImageDOWN, BufferedImage beamerImageLEFT, BufferedImage beamerImageRIGHT, String beamerClassName) {
        orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.UP), beamerImageUP);
        orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.DOWN), beamerImageDOWN);
        orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.LEFT), beamerImageLEFT);
        orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.RIGHT), beamerImageRIGHT);
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

        unPlacedTokenRectangles.clear();

        for (Token token : unplacedTokens) {
            Rectangle2D rect = new Rectangle2D.Double(x, y, tileWidth, tileHeight);
            unPlacedTokenRectangles.put(token, rect);

            Pair<Integer, Integer> position = getTokenPosition(token, x, y);
            drawToken(g2d, token, position.first(), position.second(), tileWidth, tileHeight);

            x += tileWidth;
        }
    }

    /**
     * Draws a token at the given position.
     *
     * @author Léonard Amsler
     */
    private void drawToken(Graphics2D g2d, Token token, int x, int y, int tileWidth, int tileHeight) {

        String tokenClassName = token.getClass().getSimpleName();
        boolean isOrientable = token instanceof OrientedToken;

        BufferedImage tokenImage;
        if (isOrientable) {
            Orientation orientation = ((OrientedToken) token).getOrientation();
            tokenImage = orientedTokenImages.get(new Pair<>(tokenClassName, orientation));
        } else {
            tokenImage = tokenImages.get(tokenClassName);
        }

        g2d.drawImage(tokenImage, x, y, tileWidth, tileHeight, null);
    }

    /**
     * Returns the unplaced token at the given position.
     *
     * @author Léonard Amsler
     */
    public Token getUnplacedTokenAt(int x, int y) {
        for (Map.Entry<Token, Rectangle2D> entry : unPlacedTokenRectangles.entrySet()) {
            Rectangle2D rectangle = entry.getValue();
            if (rectangle.contains(x, y)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Sets the token being dragged and its position.
     *
     * @param token The token being dragged
     * @param x The x position of the mouse
     * @param y The y position of the mouse
     * @author Nathan Gromb
     */
    public void setDraggedToken(Token token, int x, int y) {
        draggedToken = new Pair<>(token, new Pair<>(x, y));
    }

    /**
     * Resets the dragged token to null.
     *
     * @author Nathan Gromb
     */
    public void resetDraggedToken() {
        draggedToken = null;
    }

    /**
     * Returns the position of the token, overriding the real position if the token is being dragged.
     *
     * @author Nathan Gromb
     */
    private Pair<Integer, Integer> getTokenPosition(Token token, int realX, int realY) {
        if (isDraggedToken(token)) {
            int x = draggedToken.second().first();
            int y = draggedToken.second().second();

            return new Pair<>(x - levelPanel.tileWidth / 2, y - levelPanel.tileHeight / 2);
        }

        return new Pair<>(realX, realY);
    }

    /**
     * Returns whether the token is the one being dragged.
     *
     * @author Nathan Gromb
     */
    private boolean isDraggedToken(Token token) {
        return draggedToken != null && draggedToken.first().equals(token);
    }
}
