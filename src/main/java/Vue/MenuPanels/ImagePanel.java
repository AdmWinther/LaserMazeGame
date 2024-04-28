package Vue.MenuPanels;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for the image panel
 *
 * @Author Léonard Amsler - s231715
 */
public class ImagePanel extends JPanel {
    private final Image backgroundImage;
    private final int tileWidth;
    private final int tileHeight;

    /**
     * Constructor of the image panel class
     *
     * @param backgroundImage - The background image
     * @Author Léonard Amsler - s231715
     */
    public ImagePanel(Image backgroundImage, Dimension tileDimension) {
        this.backgroundImage = backgroundImage;
        this.tileWidth = tileDimension.width;
        this.tileHeight = tileDimension.height;
        // access to the graphics object
    }

    /**
     * Paint the component
     *
     * @param g the <code>Graphics</code> object to protect
     * @Author Léonard Amsler - s231715
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (backgroundImage != null) {
            // Calculate the number of tiles needed to fill the panel
            int width = getWidth();
            int height = getHeight();
            int scale = 3;
            //int tileWidth = backgroundImage.getWidth(null) * scale;
            //int tileHeight = backgroundImage.getHeight(null) * scale;
            System.out.println("Image panel would draw a " + this.tileWidth + "x" + this.tileHeight + " image");

            // Draw the background image tiles to fill the panel
            for (int x = 0; x < width; x += tileWidth) {
                for (int y = 0; y < height; y += tileHeight) {
                    g2d.drawImage(backgroundImage, x, y, tileWidth, tileHeight, this);
                }
            }
        }
    }
}
