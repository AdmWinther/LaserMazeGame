package Vue.Utils;

import javax.swing.*;

/**
 * This class is responsible for providing utility functions for buttons
 *
 * @author Léonard Amsler
 * @see JButton
 */
public class ButtonUtil {

    /**
     * Set the button transparent
     *
     * @param button JButton - The button
     * @author Léonard Amsler - s231715
     */
    public static void setButtonTransparent(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
}
