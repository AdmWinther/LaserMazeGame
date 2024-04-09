package Classes.UITile;

import Classes.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    int[][] tileMapNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        tileMapNum = new int[gamePanel.maxRow][gamePanel.maxCol];
        getTileImage();
        loadMap("/Levels/level0.txt");
    }

    private void loadMap(String path) {
        // Load map
        try {
            int row = 0;
            int col = 0;

            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (String token : tokens) {
                    int tileNum = Integer.parseInt(token);
                    tileMapNum[row][col] = tileNum;
                    col++;
                }
                row++;
                col = 0;
            }

            reader.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        // Get tile image
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/background.png")));
            tiles[0].isSolid = true;

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/brick.png")));
            tiles[1].isSolid = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/boardTile.png")));
            tiles[2].isSolid = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {

        for (int row = 0; row < gamePanel.maxRow; row++) {
            for (int col = 0; col < gamePanel.maxCol; col++) {
                int tileNum = tileMapNum[row][col];
                int x = col * gamePanel.tileSize;
                int y = row * gamePanel.tileSize;
                g2d.drawImage(tiles[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }

    }
}
