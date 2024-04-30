package Vue.Level.UILayers;

import Controller.LevelController;
import Model.Classes.Token.*;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;
import Vue.Constants.VueFilePaths;
import Vue.Interfaces.Drawable;
import Vue.Level.LevelPanel;
import Vue.Utils.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a display of tokens in the level.
 *
 * @author Nathan Gromb
 * @see TokensUI
 * @see InventoryUI
 */
public abstract class TokenDisplay implements Drawable {
    LevelController levelController;
    Map<String, BufferedImage> tokenImages = new HashMap<>();
    Map<Pair<String, Orientation>, BufferedImage> orientedTokenImages;
    Map<Token, Rectangle2D> rectangles;
    LevelPanel levelPanel;

    private Pair<Token, Position> draggedToken;

    public TokenDisplay(LevelPanel levelPanel, LevelController levelController) {
        this.levelController = levelController;
        this.levelPanel = levelPanel;

        this.orientedTokenImages = new HashMap<>();
        this.rectangles = new HashMap<>();

        this.draggedToken = null;
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
            BufferedImage beamerImageUP = readImage(VueFilePaths.Tokens.LaserGun.UP);
            BufferedImage beamerImageDOWN = readImage(VueFilePaths.Tokens.LaserGun.DOWN);
            BufferedImage beamerImageLEFT = readImage(VueFilePaths.Tokens.LaserGun.LEFT);
            BufferedImage beamerImageRIGHT = readImage(VueFilePaths.Tokens.LaserGun.RIGHT);

            BufferedImage blockerImage = readImage(VueFilePaths.Tokens.BLOCK);

            BufferedImage mirrorImageUP = readImage(VueFilePaths.Tokens.Mirror.UP);
            BufferedImage mirrorImageDOWN = readImage(VueFilePaths.Tokens.Mirror.DOWN);
            BufferedImage mirrorImageLEFT = readImage(VueFilePaths.Tokens.Mirror.LEFT);
            BufferedImage mirrorImageRIGHT = readImage(VueFilePaths.Tokens.Mirror.RIGHT);

            BufferedImage doubleMirrorImageUP_DOWN = readImage(VueFilePaths.Tokens.DoubleMirror.UP_DOWN);
            BufferedImage doubleMirrorImageRIGHT_LEFT = readImage(VueFilePaths.Tokens.DoubleMirror.RIGHT_LEFT);

            BufferedImage targetImageUP = readImage(VueFilePaths.Tokens.Target.UP);
            BufferedImage targetImageDOWN = readImage(VueFilePaths.Tokens.Target.DOWN);
            BufferedImage targetImageLEFT = readImage(VueFilePaths.Tokens.Target.LEFT);
            BufferedImage targetImageRIGHT = readImage(VueFilePaths.Tokens.Target.RIGHT);

            BufferedImage splitterImageUP_DOWN = readImage(VueFilePaths.Tokens.Splitter.UP_DOWN);
            BufferedImage splitterImageRIGHT_LEFT = readImage(VueFilePaths.Tokens.Splitter.RIGHT_LEFT);

            BufferedImage checkpointRIGHT_LEFT = readImage(VueFilePaths.Tokens.Checkpoint.RIGHT_LEFT);
            BufferedImage checkpointUP_DOWN = readImage(VueFilePaths.Tokens.Checkpoint.UP_DOWN);

            // 2. Store the images in the maps
            String beamerClassName = LaserGun.class.getSimpleName();
            putOrientedTokenImage(beamerImageUP, beamerImageDOWN, beamerImageLEFT, beamerImageRIGHT, beamerClassName);

            String blockerClassName = Block.class.getSimpleName();
            tokenImages.put(blockerClassName, blockerImage);

            String mirrorClassName = OneSidedMirror.class.getSimpleName();
            putOrientedTokenImage(mirrorImageUP, mirrorImageDOWN, mirrorImageLEFT, mirrorImageRIGHT, mirrorClassName);

            String doubleMirrorClassName = DoubleSidedMirror.class.getSimpleName();
            putOrientedTokenImage(doubleMirrorImageUP_DOWN, doubleMirrorImageUP_DOWN, doubleMirrorImageRIGHT_LEFT, doubleMirrorImageRIGHT_LEFT, doubleMirrorClassName);

            String targetClassName = Target.class.getSimpleName();
            putOrientedTokenImage(targetImageUP, targetImageDOWN, targetImageLEFT, targetImageRIGHT, targetClassName);

            String splitterClassName = Splitter.class.getSimpleName();
            putOrientedTokenImage(splitterImageUP_DOWN, splitterImageUP_DOWN, splitterImageRIGHT_LEFT, splitterImageRIGHT_LEFT, splitterClassName);

            String checkpointClassName = Checkpoint.class.getSimpleName();
            putOrientedTokenImage(checkpointUP_DOWN, checkpointUP_DOWN, checkpointRIGHT_LEFT, checkpointRIGHT_LEFT, checkpointClassName);
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
    private void putOrientedTokenImage(BufferedImage imageUP, BufferedImage imageDOWN, BufferedImage imageLEFT, BufferedImage imageRIGHT, String className) {
        orientedTokenImages.put(new Pair<>(className, Orientation.UP), imageUP);
        orientedTokenImages.put(new Pair<>(className, Orientation.DOWN), imageDOWN);
        orientedTokenImages.put(new Pair<>(className, Orientation.LEFT), imageLEFT);
        orientedTokenImages.put(new Pair<>(className, Orientation.RIGHT), imageRIGHT);
    }

    /**
     * Sets the token being dragged and its position.
     *
     * @param token The token being dragged
     * @param pos   The position of the mouse
     * @author Nathan Gromb
     */
    public void setDraggedToken(Token token, Position pos) {
        draggedToken = new Pair<>(token, pos);
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
    public Position getTokenPosition(Token token, Position realPos) {
        if (isDraggedToken(token)) {
            int x = draggedToken.second().x();
            int y = draggedToken.second().y();

            return Position.of(x - levelPanel.tileWidth / 2, y - levelPanel.tileHeight / 2);
        }

        return Position.of(realPos.x(), realPos.y());
    }

    /**
     * Returns whether the token is the one being dragged.
     *
     * @author Nathan Gromb
     */
    private boolean isDraggedToken(Token token) {
        return draggedToken != null && draggedToken.first().id() == token.id();
    }

    /**
     * Returns the token at the given position.
     *
     * @author Léonard Amsler
     */
    public Token getTokenAtMousePos(Position pos) {
        for (Map.Entry<Token, Rectangle2D> entry : rectangles.entrySet()) {
            Rectangle2D rectangle = entry.getValue();
            if (rectangle.contains(pos.x(), pos.y())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Draws a token at the given position.
     *
     * @author Léonard Amsler
     */
    public void drawToken(Graphics2D g2d, Token token, Position pos, int tileWidth, int tileHeight) {

        String tokenClassName = token.type();
        boolean isOrientable = token instanceof OrientedToken;

        BufferedImage tokenImage;
        if (isOrientable) {
            Orientation orientation = ((OrientedToken) token).getOrientation();
            tokenImage = orientedTokenImages.get(new Pair<>(tokenClassName, orientation));
        } else {
            tokenImage = tokenImages.get(tokenClassName);
        }

        g2d.drawImage(tokenImage, pos.x(), pos.y(), tileWidth, tileHeight, null);
    }
}
