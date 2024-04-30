package Vue.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is responsible for providing utility functions for images
 *
 * @author Léonard Amsler
 * @see BufferedImage
 */
public class ImageUtil {

    /**
     * Resize the image
     *
     * @param image  BufferedImage - The image
     * @param width  int - The width
     * @param height int - The height
     * @return BufferedImage - The resized image
     * @author Léonard Amsler - s231715
     */
    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        if (image == null) {
            return null;
        }
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
