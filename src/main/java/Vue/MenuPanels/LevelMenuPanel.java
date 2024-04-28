package Vue.MenuPanels;

import Controller.GameController;
import Controller.LoginController;
import Vue.Constants.JComponentsNames;
import Vue.Constants.Style;
import Vue.Constants.VueFilePaths;
import Vue.Game.Game;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.Sound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class has the responsibility to display the common elements of the level menu panels.
 *
 * @author Hugo Demule
 * @author Léonard Amsler - s231715
 * @author Nathan Gromb - s231674
 */
public abstract class LevelMenuPanel extends JPanel {
    protected final JFrame frame;
    protected final JComponent buttons;
    protected final JLabel backButton;
    protected final GameController gameController;
    protected final LoginController loginController;
    protected final ImagePanel backgroundPanel;
    public int tileWidth = 16;
    public int tileHeight = 16;

    /**
     * Constructor of the campaign panel class
     *
     * @param frame           The frame
     * @param gameController  The game controller
     * @param loginController The login controller
     * @author Léonard Amsler - s231715
     * @author Nathan Gromb - s231674
     */
    public LevelMenuPanel(JFrame frame, GameController gameController, LoginController loginController) {
        this.frame = frame;
        this.gameController = gameController;
        this.loginController = loginController;

        setDoubleBuffered(true);
        setLayout(new BorderLayout());

        // Background image
        ImageIcon backgroundImage = new ImageIcon(VueFilePaths.BACKGROUND_IMAGE);
        this.backgroundPanel = new ImagePanel(backgroundImage.getImage());
        this.backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        this.backButton = getBackButton();
        this.backgroundPanel.add(backButton, BorderLayout.SOUTH);

        this.buttons = getLevelButtonsList();
        this.backgroundPanel.add(buttons, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });
    }

    /**
     * Get the back button
     *
     * @return JLabel - The back button
     * @author Nathan Gromb - s231674
     */
    private JLabel getBackButton() {
        JLabel button = new JLabel();

        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        int padding = 50;
        button.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        JPanel thisClass = this;

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Sound.playButtonSound();
                frame.getContentPane().remove(thisClass);
                Game.showPanel(frame, JComponentsNames.FrameID.MAIN_MENU);
            }
        });
        button.addMouseListener(new ButtonHoverHandler());

        return button;
    }

    protected abstract JComponent getLevelButtonsList();

    /**
     * Resizes the campaign panel
     *
     * @author Léonard Amsler - s231715
     * @author Nathan Gromb - s231674
     */
    public void resize() {

        tileWidth = 100;
        tileHeight = 100;

        // Set button sizes and positions
        for (Component component : buttons.getComponents()) {
            if (component instanceof JLabel button) {
                ImageIcon icon = new ImageIcon(VueFilePaths.BOARD_TILE);
                ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(tileWidth, tileHeight, Image.SCALE_DEFAULT));
                button.setIcon(scaledIcon);
                button.setFont(new Font(Style.Font.MONOSPACED, Font.BOLD, tileWidth / 2));
            }
        }

        // Update back button size and position
        int backButtonSize = tileWidth / 3 * 2;

        // Set the size and position of the back button
        backButton.setPreferredSize(new Dimension(backButtonSize, backButtonSize));
        JLabel button = backButton;
        ImageIcon icon = new ImageIcon(VueFilePaths.RESET_BUTTON_ICON);
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(backButtonSize, backButtonSize, Image.SCALE_DEFAULT));
        button.setIcon(scaledIcon);
        button.setFont(new Font(Style.Font.MONOSPACED, Font.BOLD, tileWidth / 2));
    }

    /**
     * Get the image
     *
     * @param imagePath The image path
     * @return BufferedImage The image
     * @author Léonard Amsler - s231715
     * @author Nathan Gromb - s231674
     */
    protected static BufferedImage getImage(String imagePath) {
        BufferedImage enable_image = null;
        try {
            enable_image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Error loading image : " + imagePath);
        }
        return enable_image;
    }

}
