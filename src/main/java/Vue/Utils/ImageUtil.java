package Vue.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {

    /**
     * Resize the image
     *
     * @param image BufferedImage - The image
     * @param i     int - The width
     * @param i1    int - The height
     * @return BufferedImage - The resized image
     * @author Léonard Amsler - s231715
     */
    public static BufferedImage resizeImage(BufferedImage image, int i, int i1) {
        if (image == null) {
            return null;
        }
        Image tmp = image.getScaledInstance(i, i1, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(i, i1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
