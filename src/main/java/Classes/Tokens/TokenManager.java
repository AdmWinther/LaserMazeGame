package Classes.Tokens;

import Classes.Board;
import Classes.Game.GamePanel;
import Classes.Level;
import Classes.Orientation;
import Classes.Utils.Coordinate;
import Classes.Utils.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TokenManager {

    Level level;
    GamePanel gamePanel;
    Map<Pair<String, Orientation>, BufferedImage> orientedTokenImages;
    Map<String, BufferedImage> tokenImages;

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
            BufferedImage mirror1Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/mirror1.png")));
            BufferedImage mirror2Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/mirror2.png")));
            BufferedImage doubleMirror1Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/doubleMirror1.png")));
            BufferedImage doubleMirror2Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/doubleMirror2.png")));
            BufferedImage receiverImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tokens/receiver.png")));

            orientedTokenImages.put(new Pair<>("beamer", Orientation.UP), beamerImageUP);
            orientedTokenImages.put(new Pair<>("beamer", Orientation.DOWN), beamerImageDOWN);
            orientedTokenImages.put(new Pair<>("beamer", Orientation.LEFT), beamerImageLEFT);
            orientedTokenImages.put(new Pair<>("beamer", Orientation.RIGHT), beamerImageRIGHT);

            tokenImages.put("blocker", blockerImage);

            orientedTokenImages.put(new Pair<>("mirror", Orientation.UP), mirror1Image);
            orientedTokenImages.put(new Pair<>("mirror", Orientation.DOWN), mirror1Image);
            orientedTokenImages.put(new Pair<>("mirror", Orientation.LEFT), mirror2Image);
            orientedTokenImages.put(new Pair<>("mirror", Orientation.RIGHT), mirror2Image);

            orientedTokenImages.put(new Pair<>("doubleMirror", Orientation.UP), doubleMirror1Image);
            orientedTokenImages.put(new Pair<>("doubleMirror", Orientation.DOWN), doubleMirror1Image);
            orientedTokenImages.put(new Pair<>("doubleMirror", Orientation.LEFT), doubleMirror2Image);
            orientedTokenImages.put(new Pair<>("doubleMirror", Orientation.RIGHT), doubleMirror2Image);

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
        int tileSize = gamePanel.tileSize;
        Board board = level.getBoard();
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Token token = board.getTokenAt(coordinate);
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
}
