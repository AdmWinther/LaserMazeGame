package Vue.Level.UILayers;

import Controller.LevelController;
import Vue.Interfaces.Drawable;
import Vue.Level.LevelPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TilesUI implements Drawable {

    LevelPanel levelPanel;
    LevelController levelController;
    String[][] levelTiles;
    Map<String, BufferedImage> tileImages = new HashMap<>();

    public TilesUI(LevelPanel levelPanel, LevelController levelController) {
        this.levelPanel = levelPanel;
        this.levelController = levelController;
        getTileImage();
        setLevelTiles();
    }

    /**
     * Set the level tiles
     *
     * @Author Léonard Amsler - s231715
     */
    private void setLevelTiles() {

        levelTiles = new String[levelPanel.maxRow + 1][levelPanel.maxCol + 1];

        int maxRow = levelPanel.maxRow;
        int maxCol = levelPanel.maxCol;
        int horizontalBorder = levelPanel.horizontalBorder;
        int verticalBorder = levelPanel.verticalBorder;
        int wallThickness = levelPanel.wallThickness;

        // Load map, set border and randomize the background tile
        for (int i = 0; i < maxRow + 1; i++) {
            for (int j = 0; j < maxCol + 1; j++) {
                if (i < verticalBorder || i >= maxRow - verticalBorder || j < horizontalBorder || j >= maxCol - horizontalBorder) {
                    levelTiles[i][j] = "background";
                } else if (i < verticalBorder + wallThickness ||
                        i >= maxRow - verticalBorder - wallThickness ||
                        j < horizontalBorder + wallThickness ||
                        j >= maxCol - horizontalBorder - wallThickness) {
                    levelTiles[i][j] = "brick";
                } else {
                    levelTiles[i][j] = "boardTile" + (int) (Math.random() * 4 + 1);
                }
            }
        }
    }

    /**
     * Get the tile image
     *
     * @Author Léonard Amsler - s231715
     */
    public void getTileImage() {
        try {
            BufferedImage background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/background.png")));
            BufferedImage brick = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/brick.png")));
            BufferedImage boardTile1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/boardTile1.png")));
            BufferedImage boardTile2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/boardTile2.png")));
            BufferedImage boardTile3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/boardTile3.png")));
            BufferedImage boardTile4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/boardTile4.png")));

            tileImages.put("background", background);
            tileImages.put("brick", brick);
            tileImages.put("boardTile1", boardTile1);
            tileImages.put("boardTile2", boardTile2);
            tileImages.put("boardTile3", boardTile3);
            tileImages.put("boardTile4", boardTile4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draw the tiles
     *
     * @param g2d - The 2d graphics object
     */
    public void draw(Graphics2D g2d) {
        for (int row = 0; row < levelPanel.maxRow + 1; row++) {
            for (int col = 0; col < levelPanel.maxCol + 1; col++) {
                String tileName = levelTiles[row][col];
                int x = col * levelPanel.tileWidth;
                int y = row * levelPanel.tileHeight;
                g2d.drawImage(tileImages.get(tileName), x, y, levelPanel.tileWidth, levelPanel.tileHeight, null);
            }
        }
    }
}
