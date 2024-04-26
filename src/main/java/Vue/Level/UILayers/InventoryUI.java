package Vue.Level.UILayers;

import Controller.EditableLevelController;
import Model.Classes.Token.*;
import Model.Classes.Utils.Pair;
import Model.Interfaces.Inventory;
import Vue.Level.LevelPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InventoryUI extends TokenDisplay {
    private int binX = 0;
    private int binY = 0;

    public InventoryUI(LevelPanel levelPanel, EditableLevelController levelController) {
        super(levelPanel, levelController);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Display the unplaced tokens at the left of the screen
        int tileWidth = levelPanel.tileWidth;
        int tileHeight = levelPanel.tileHeight;

        int nbTilesVertical = levelPanel.maxRow;

        Inventory inventory = ((EditableLevelController) levelController).getInventory();
        int size = inventory.getItems().size();

        int x = tileWidth/2;
        binX = x;

        int y = (nbTilesVertical - size) / 2 * tileHeight - tileHeight;

        rectangles.clear();

        for (Token token : inventory.getItems()) {
            Rectangle2D rect = new Rectangle2D.Double(x, y, tileWidth, tileHeight);
            rectangles.put(token, rect);

            Pair<Integer, Integer> position = getTokenPosition(token, x, y);
            drawToken(g2d, token, position.first(), position.second(), tileWidth, tileHeight);

            y += tileHeight;
        }

        binY = y + tileHeight;

    }

    public Pair<Integer, Integer> getBinPosition() {
        return new Pair<>(binX, binY);
    }
}
