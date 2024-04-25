package Vue.Level.UILayers;

import Controller.EditableLevelController;
import Model.Classes.Token.*;
import Model.Classes.Utils.Pair;
import Vue.Interfaces.Drawable;
import Vue.Level.LevelPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class InventoryUI implements Drawable {

    private final EditableLevelController levelController;
    private final LevelPanel levelPanel;
    Map<Class<? extends Token>, Rectangle2D> inventoryRectangles = new HashMap<>();

    Map<String, BufferedImage> itemImages = new HashMap<>();

    private Pair<Class<? extends Token>, Pair<Integer, Integer>> draggedItem = null;

    public InventoryUI(LevelPanel levelPanel, EditableLevelController levelController) {
        this.levelController = levelController;
        this.levelPanel = levelPanel;
        setTokenImages();
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawInventory(g2d);
    }

    private void drawInventory(Graphics2D g2d) {

        // Display the unplaced tokens at the bottom of the screen, centered horizontally
        int tileWidth = levelPanel.tileWidth;
        int tileHeight = levelPanel.tileHeight;

        int nbTilesHorizontal = levelPanel.maxCol;
        int nbTilesVertical = levelPanel.maxRow;

        Set<Class<? extends Token>> inventory = levelController.getInventory();
        int size = inventory.size();

        int x = (nbTilesHorizontal - size) / 2 * tileWidth;
        int y = nbTilesVertical * tileHeight - tileHeight;

        inventoryRectangles.clear();

        for (Class<? extends Token> item : inventory) {
            Rectangle2D rect = new Rectangle2D.Double(x, y, tileWidth, tileHeight);
            inventoryRectangles.put(item, rect);

            Pair<Integer, Integer> position = getItemPosition(item, x, y);
            drawItem(g2d, item, position.first(), position.second(), tileWidth, tileHeight);

            x += tileWidth;
        }

    }

    private void drawItem(Graphics2D g2d, Class<? extends Token> item, int x, int y, int tileWidth, int tileHeight) {

        String tokenClassName = item.getSimpleName();
        BufferedImage tokenImage = itemImages.get(tokenClassName);
        g2d.drawImage(tokenImage, x, y, tileWidth, tileHeight, null);
    }

    private Pair<Integer, Integer> getItemPosition(Class<? extends Token> item, int realX, int realY) {
        if (isDraggedItem(item)) {
            int x = draggedItem.second().first();
            int y = draggedItem.second().second();

            return new Pair<>(x - levelPanel.tileWidth / 2, y - levelPanel.tileHeight / 2);
        }

        return new Pair<>(realX, realY);
    }

    private boolean isDraggedItem(Class<? extends Token> item) {
        return draggedItem != null && draggedItem.first().equals(item);
    }

    private BufferedImage readImage(String path) throws Exception {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
    }

    private void setTokenImages() {

        try {
            // 1. Load the images for the items
            BufferedImage blockerImage = readImage("/Tokens/block.png");
            BufferedImage laserGunImage = readImage("/Tokens/lasergun_RIGHT.png");
            BufferedImage mirrorImage = readImage("/Tokens/mirror_RIGHT.png");
            BufferedImage doubleMirrorImage = readImage("/Tokens/doubleMirror_RL.png");
            BufferedImage targetImage = readImage("/Tokens/target_RIGHT.png");
            BufferedImage splitterImage = readImage("/Tokens/splitter_RL.png");

            // 2. Store the images in the maps
            String blockerClassName = Block.class.getSimpleName();
            itemImages.put(blockerClassName, blockerImage);

            String laserGunClassName = LaserGun.class.getSimpleName();
            itemImages.put(laserGunClassName, laserGunImage);

            String mirrorClassName = OneSidedMirror.class.getSimpleName();
            itemImages.put(mirrorClassName, mirrorImage);

            String doubleMirrorClassName = DoubleSidedMirror.class.getSimpleName();
            itemImages.put(doubleMirrorClassName, doubleMirrorImage);

            String targetClassName = Target.class.getSimpleName();
            itemImages.put(targetClassName, targetImage);

            String splitterClassName = Splitter.class.getSimpleName();
            itemImages.put(splitterClassName, splitterImage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
