package Vue.Level;

import Controller.LevelController;
import Model.Classes.Token.*;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UITokens {

    LevelController levelController;
    Map<Token, Rectangle2D> unPlacedTokenRectangles = new HashMap<>();
    Map<String, BufferedImage> tokenImages = new HashMap<>();
    Map<Pair<String, Orientation>, BufferedImage> orientedTokenImages = new HashMap<>();
    LevelPanel levelPanel;

    /**
     * Constructor of the UI tokens class
     *
     * @param levelPanel      - The level panel
     * @param levelController - The level controller
     * @Author Léonard Amsler - s231715
     */
    public UITokens(LevelPanel levelPanel, LevelController levelController) {
        this.levelController = levelController;
        this.levelPanel = levelPanel;
        setTokenImages();
    }

    /**
     * Set the token images
     *
     * @Author Léonard Amsler - s231715
     */
    public void setTokenImages() {

        try {
            // 1. Load the images for the tokens
            BufferedImage beamerImageUP = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/lasergun_UP.png")));
            BufferedImage beamerImageDOWN = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/lasergun_DOWN.png")));
            BufferedImage beamerImageLEFT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/lasergun_LEFT.png")));
            BufferedImage beamerImageRIGHT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/lasergun_RIGHT.png")));

            BufferedImage blockerImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/blocker.png")));

            BufferedImage mirrorImageUP = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/mirror_U.png")));
            BufferedImage mirrorImageDOWN = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/mirror_D.png")));
            BufferedImage mirrorImageLEFT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/mirror_L.png")));
            BufferedImage mirrorImageRIGHT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/mirror_R.png")));

            BufferedImage doubleMirrorImageUPDOWN = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/doubleMirror_UD.png")));
            BufferedImage doubleMirrorImageLEFTRIGHT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/doubleMirror_RL.png")));

            BufferedImage splitterImageUPDOWN = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/splitter_UD.png")));
            BufferedImage splitterImageLEFTRIGHT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/splitter_RL.png")));

            BufferedImage targetImageRIGHT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/target_RIGHT.png")));
            BufferedImage targetImageLEFT = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/target_LEFT.png")));
            BufferedImage targetImageUP = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/target_UP.png")));
            BufferedImage targetImageDOWN = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/target_DOWN.png")));

            // 2. Store the images in the maps
            String beamerClassName = LaserGun.class.getSimpleName();
            orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.UP), beamerImageUP);
            orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.DOWN), beamerImageDOWN);
            orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.LEFT), beamerImageLEFT);
            orientedTokenImages.put(new Pair<>(beamerClassName, Orientation.RIGHT), beamerImageRIGHT);

            String blockerClassName = Block.class.getSimpleName();
            tokenImages.put(blockerClassName, blockerImage);

            String mirrorClassName = OneSidedMirror.class.getSimpleName();
            orientedTokenImages.put(new Pair<>(mirrorClassName, Orientation.UP), mirrorImageUP);
            orientedTokenImages.put(new Pair<>(mirrorClassName, Orientation.DOWN), mirrorImageDOWN);
            orientedTokenImages.put(new Pair<>(mirrorClassName, Orientation.LEFT), mirrorImageLEFT);
            orientedTokenImages.put(new Pair<>(mirrorClassName, Orientation.RIGHT), mirrorImageRIGHT);

            String doubleMirrorClassName = DoubleSidedMirror.class.getSimpleName();
            orientedTokenImages.put(new Pair<>(doubleMirrorClassName, Orientation.UP), doubleMirrorImageUPDOWN);
            orientedTokenImages.put(new Pair<>(doubleMirrorClassName, Orientation.DOWN), doubleMirrorImageUPDOWN);
            orientedTokenImages.put(new Pair<>(doubleMirrorClassName, Orientation.LEFT), doubleMirrorImageLEFTRIGHT);
            orientedTokenImages.put(new Pair<>(doubleMirrorClassName, Orientation.RIGHT), doubleMirrorImageLEFTRIGHT);

            String splitterClassName = Splitter.class.getSimpleName();
            orientedTokenImages.put(new Pair<>(splitterClassName, Orientation.UP), splitterImageUPDOWN);
            orientedTokenImages.put(new Pair<>(splitterClassName, Orientation.DOWN), splitterImageUPDOWN);
            orientedTokenImages.put(new Pair<>(splitterClassName, Orientation.LEFT), splitterImageLEFTRIGHT);
            orientedTokenImages.put(new Pair<>(splitterClassName, Orientation.RIGHT), splitterImageLEFTRIGHT);

            String targetClassName = Target.class.getSimpleName();
            orientedTokenImages.put(new Pair<>(targetClassName, Orientation.UP), targetImageUP);
            orientedTokenImages.put(new Pair<>(targetClassName, Orientation.DOWN), targetImageDOWN);
            orientedTokenImages.put(new Pair<>(targetClassName, Orientation.LEFT), targetImageLEFT);
            orientedTokenImages.put(new Pair<>(targetClassName, Orientation.RIGHT), targetImageRIGHT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draw the tokens on the screen
     *
     * @param g2d - The 2d graphics object
     * @Author Léonard Amsler - s231715
     */
    public void draw(Graphics2D g2d) {
        drawPlacedTokens(g2d);
        drawUnplacedTokens(g2d);
    }

    /**
     * Draw the placed tokens
     *
     * @param g2d - The 2d graphics object
     * @Author Léonard Amsler - s231715
     */
    public void drawPlacedTokens(Graphics2D g2d) {

        Map<Coordinate, Token> placedTokens = levelController.getPlacedTokens();

        int widthOffset = levelPanel.widthOffset;
        int heightOffset = levelPanel.heightOffset;

        for (Map.Entry<Coordinate, Token> entry : placedTokens.entrySet()) {
            Coordinate coordinate = entry.getKey();
            Token token = entry.getValue();
            int x = widthOffset + coordinate.x() * levelPanel.tileWidth;
            int y = heightOffset + coordinate.y() * levelPanel.tileHeight;
            drawToken(g2d, token, x, y, levelPanel.tileWidth, levelPanel.tileHeight);
        }

    }

    /**
     * Draw the unplaced tokens
     *
     * @param g2d - The 2d graphics object
     * @Author Léonard Amsler - s231715
     */
    public void drawUnplacedTokens(Graphics2D g2d) {
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
            drawToken(g2d, token, x, y, tileWidth, tileHeight);
            x += tileWidth;
        }
    }

    /**
     * Draw a token
     *
     * @param g2d        - The 2d graphics object
     * @param token      - The token to draw
     * @param x          - The x coordinate
     * @param y          - The y coordinate
     * @param tileWidth  - The width of the tile
     * @param tileHeight - The height of the tile
     * @Author Léonard Amsler - s231715
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
     * Get the unplaced token at the given coordinates
     *
     * @param x - The x coordinate
     * @param y - The y coordinate
     * @return The unplaced token at the given coordinates
     * @Author Léonard Amsler - s231715
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
}
