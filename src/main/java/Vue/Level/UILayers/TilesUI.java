package Vue.Level.UILayers;

import Vue.Interfaces.Drawable;
import Vue.Level.LevelPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * UI of the tiles
 *
 * @author Léonard Amsler - s231715
 */
public class TilesUI implements Drawable {

	private final LevelPanel levelPanel;
	private final Map<String, BufferedImage> tileImages = new HashMap<>();
	private String[][] levelTiles;

	public TilesUI(LevelPanel levelPanel) {
		this.levelPanel = levelPanel;
		loadTileImages();
		setLevelTiles();
	}

	/**
	 * Get the tile images
	 *
	 * @author Léonard Amsler - s231715
	 */
	public void loadTileImages() {
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
	 * Set the level tiles
	 *
	 * @author Léonard Amsler - s231715
	 */
	private void setLevelTiles() {

		levelTiles = new String[levelPanel.getLevelPanelConfig().getMaxRow() + 1][levelPanel.getLevelPanelConfig().getMaxCol() + 1];

		int maxRow = levelPanel.getLevelPanelConfig().getMaxRow();
		int maxCol = levelPanel.getLevelPanelConfig().getMaxCol();
		int horizontalBorder = LevelPanel.HORIZONTAL_BORDER;
		int verticalBorder = LevelPanel.VERTICAL_BORDER;
		int wallThickness = LevelPanel.WALL_THICKNESS;

		// Load map, set border and randomize the background tile
		for (int i = 0; i < maxRow + 1; i++) {
			for (int j = 0; j < maxCol + 1; j++) {
				if (i < verticalBorder || i >= maxRow - verticalBorder || j < horizontalBorder || j >= maxCol - horizontalBorder) {
					levelTiles[i][j] = "background";
				} else if (i < verticalBorder + wallThickness || i >= maxRow - verticalBorder - wallThickness || j < horizontalBorder + wallThickness || j >= maxCol - horizontalBorder - wallThickness) {
					levelTiles[i][j] = "brick";
				} else {
					levelTiles[i][j] = "boardTile" + (int) (Math.random() * 4 + 1);
				}
			}
		}
	}

	/**
	 * Draw the tiles
	 *
	 * @param g2d - The 2d graphics object
	 */
	public void draw(Graphics2D g2d) {
		for (int row = 0; row < levelPanel.getLevelPanelConfig().getMaxRow() + 1; row++) {
			for (int col = 0; col < levelPanel.getLevelPanelConfig().getMaxCol() + 1; col++) {
				String tileName = levelTiles[row][col];
				int x = col * levelPanel.getLevelPanelConfig().getTileWidth();
				int y = row * levelPanel.getLevelPanelConfig().getTileHeight();
				g2d.drawImage(tileImages.get(tileName), x, y, levelPanel.getLevelPanelConfig().getTileWidth(), levelPanel.getLevelPanelConfig().getTileHeight(), null);
			}
		}
	}
}
