package Classes.Tokens;

import Classes.Game.GamePanel;
import Classes.Level;
import Classes.Orientation;
import Classes.Utils.Coordinate;
import Classes.Utils.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TokenManager {

    Level level;
    GamePanel gamePanel;
    Map<Pair<String, Orientation>, BufferedImage> orientedTokenImages;
    Map<String, BufferedImage> tokenImages;
    Map<Token, Rectangle2D> unPlacedTokenRectangles = new HashMap<>();

    public TokenManager(Level level, GamePanel gamePanel) {
        this.level = level;
        this.gamePanel = gamePanel;
        setTokenImages();
    }

    public void setTokenImages() {

        tokenImages = new HashMap<>();
        orientedTokenImages = new HashMap<>();

        try {
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

            BufferedImage receiverImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/receiver.png")));

            orientedTokenImages.put(new Pair<>("beamer", Orientation.UP), beamerImageUP);
            orientedTokenImages.put(new Pair<>("beamer", Orientation.DOWN), beamerImageDOWN);
            orientedTokenImages.put(new Pair<>("beamer", Orientation.LEFT), beamerImageLEFT);
            orientedTokenImages.put(new Pair<>("beamer", Orientation.RIGHT), beamerImageRIGHT);

            tokenImages.put("blocker", blockerImage);

            orientedTokenImages.put(new Pair<>("mirror", Orientation.UP), mirrorImageUP);
            orientedTokenImages.put(new Pair<>("mirror", Orientation.DOWN), mirrorImageDOWN);
            orientedTokenImages.put(new Pair<>("mirror", Orientation.LEFT), mirrorImageLEFT);
            orientedTokenImages.put(new Pair<>("mirror", Orientation.RIGHT), mirrorImageRIGHT);

            orientedTokenImages.put(new Pair<>("doubleMirror", Orientation.UP), doubleMirrorImageUPDOWN);
            orientedTokenImages.put(new Pair<>("doubleMirror", Orientation.DOWN), doubleMirrorImageUPDOWN);
            orientedTokenImages.put(new Pair<>("doubleMirror", Orientation.LEFT), doubleMirrorImageLEFTRIGHT);
            orientedTokenImages.put(new Pair<>("doubleMirror", Orientation.RIGHT), doubleMirrorImageLEFTRIGHT);

            orientedTokenImages.put(new Pair<>("receiver", Orientation.UP), receiverImage);
            orientedTokenImages.put(new Pair<>("receiver", Orientation.DOWN), receiverImage);
            orientedTokenImages.put(new Pair<>("receiver", Orientation.LEFT), receiverImage);
            orientedTokenImages.put(new Pair<>("receiver", Orientation.RIGHT), receiverImage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTokenImage(String tokenType, Orientation orientation) {
        if (tokenType.equals("mirror") || tokenType.equals("doubleMirror") || tokenType.equals("beamer") || tokenType.equals("receiver")) {
            return orientedTokenImages.get(new Pair<>(tokenType, orientation));
        } else {
            return tokenImages.get(tokenType);
        }
    }

    public void draw(Graphics2D g2d, int widthOffset, int heightOffset) {
        drawPlacedTokens(g2d, widthOffset, heightOffset);
        drawUnplacedTokens(g2d);
    }

    public void drawPlacedTokens(Graphics2D g2d, int widthOffset, int heightOffset) {

        int tileSize = gamePanel.tileSize;
        int width = level.tokenManager().getWidthX();
        int height = level.tokenManager().getHeightY();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Token token = level.tokenManager().getTokenAt(coordinate);
                int x = widthOffset + i * tileSize;
                int y = heightOffset + j * tileSize;
                if (token != null) {
                    if (token instanceof OneSidedMirror) {
                        OneSidedMirror mirror = (OneSidedMirror) token;
                        g2d.drawImage(getTokenImage("mirror", mirror.getOrientation()), x, y, tileSize, tileSize, null);
                    } else if (token instanceof DoubleSidedMirror) {
                        DoubleSidedMirror doubleMirror = (DoubleSidedMirror) token;
                        g2d.drawImage(getTokenImage("doubleMirror", doubleMirror.getOrientation()), x, y, tileSize, tileSize, null);
                    } else if (token instanceof LaserGun) {
                        LaserGun beamer = (LaserGun) token;
                        g2d.drawImage(getTokenImage("beamer", beamer.getOrientation()), x, y, tileSize, tileSize, null);
                    } else if (token instanceof Block) {
                        g2d.drawImage(getTokenImage("blocker", null), x, y, tileSize, tileSize, null);
                    } else if (token instanceof Receiver) {
                        Receiver receiver = (Receiver) token;
                        g2d.drawImage(getTokenImage("receiver", receiver.getOrientation()), x, y, tileSize, tileSize, null);
                    }
                }
            }
        }
    }

    public void drawUnplacedTokens(Graphics2D g2d) {
        // Display the unplaced tokens at the bottom of the screen, centered horizontally
        int tileSize = gamePanel.tileSize;

        int nbTilesVertical = gamePanel.maxRow;

        int nbTilesHorizontal = gamePanel.maxCol;

        Set<Token> unplacedTokens = level.tokenManager().getUnplacedTokens();
        int size = unplacedTokens.size();

        int sidePadding = (nbTilesHorizontal - size) / 2 * tileSize;

        int x = sidePadding;
        int y = nbTilesVertical * tileSize - tileSize;

        unPlacedTokenRectangles.clear();

        for (Token token : unplacedTokens) {
            Rectangle2D rect = new Rectangle2D.Double(x, y, tileSize, tileSize);
            unPlacedTokenRectangles.put(token, rect);
            if (token instanceof OneSidedMirror) {
                OneSidedMirror mirror = (OneSidedMirror) token;
                g2d.drawImage(getTokenImage("mirror", mirror.getOrientation()), x, y, tileSize, tileSize, null);
            } else if (token instanceof DoubleSidedMirror) {
                DoubleSidedMirror doubleMirror = (DoubleSidedMirror) token;
                g2d.drawImage(getTokenImage("doubleMirror", doubleMirror.getOrientation()), x, y, tileSize, tileSize, null);
            } else if (token instanceof LaserGun) {
                LaserGun beamer = (LaserGun) token;
                g2d.drawImage(getTokenImage("beamer", beamer.getOrientation()), x, y, tileSize, tileSize, null);
            } else if (token instanceof Block) {
                g2d.drawImage(getTokenImage("blocker", null), x, y, tileSize, tileSize, null);
            } else if (token instanceof Receiver) {
                Receiver receiver = (Receiver) token;
                g2d.drawImage(getTokenImage("receiver", receiver.getOrientation()), x, y, tileSize, tileSize, null);
            }
            x += tileSize;
        }
    }

    public Token getUnplacedTokenAt(int x, int y) {
        for (Map.Entry<Token, Rectangle2D> entry : unPlacedTokenRectangles.entrySet()) {
            if (entry.getValue().contains(x, y)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
