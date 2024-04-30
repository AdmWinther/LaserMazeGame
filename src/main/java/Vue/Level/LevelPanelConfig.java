package Vue.Level;

import java.io.Serializable;

public class LevelPanelConfig implements Serializable {
	// Level configuration, screen size in tiles
	private int maxRow;
	private int maxCol;// Screen size in pixels
	private int screenWidth;
	private int screenHeight;// Offsets, number of pixels to the top left corner of the level board
	private int widthOffset;
	private int heightOffset;// Tile size
	private int tileWidth;
	private int tileHeight;

	public LevelPanelConfig() {
		this.setTileWidth(LevelPanel.ORIGINAL_TILE_SIZE * LevelPanel.SCALE);
		this.setTileHeight(LevelPanel.ORIGINAL_TILE_SIZE * LevelPanel.SCALE);
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getWidthOffset() {
		return widthOffset;
	}

	public void setWidthOffset(int widthOffset) {
		this.widthOffset = widthOffset;
	}

	public int getHeightOffset() {
		return heightOffset;
	}

	public void setHeightOffset(int heightOffset) {
		this.heightOffset = heightOffset;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
}