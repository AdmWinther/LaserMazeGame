package Vue.MenuPanels;

import Controller.GameController;
import Controller.LoginController;
import Vue.Constants.Style;
import Vue.Constants.VueFilePaths;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.SoundPaths;
import Vue.SoundEffects.SoundPlayer;
import Vue.Utils.FrameUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
    protected int tileWidth;
    protected int tileHeight;

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

        // TODO change the tile size depending on the screen size | call gameController.getCurrentGameFrameDimension()

        this.tileHeight = gameController.getCurrentTileDimension().height;
        this.tileWidth = gameController.getCurrentTileDimension().width;

        setDoubleBuffered(true);
        setLayout(new BorderLayout());

        // Background image
        ImageIcon backgroundImage = new ImageIcon(VueFilePaths.BACKGROUND_TILE);
        this.backgroundPanel = new ImagePanel(backgroundImage.getImage(), gameController.getCurrentTileDimension());
        this.backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        this.backButton = getBackButton();
        this.backgroundPanel.add(backButton, BorderLayout.SOUTH);

        this.buttons = getLevelButtonsList();
        this.backgroundPanel.add(buttons, BorderLayout.CENTER);
    }

    /**
     * Get the back button
     *
     * @return JLabel - The back button
     * @author Nathan Gromb - s231674
     */
    private JLabel getBackButton() {
        JLabel button = new JLabel();

        button.setPreferredSize(gameController.getCurrentTileDimension());
        ImageIcon icon = new ImageIcon(VueFilePaths.RESET_BUTTON_ICON);
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(gameController.getCurrentTileDimension().width, gameController.getCurrentTileDimension().height, Image.SCALE_DEFAULT));
        button.setIcon(scaledIcon);
        button.setFont(new Font(Style.Font.MONOSPACED, Font.BOLD, tileWidth / 2));

        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        button.setBorder(BorderFactory.createEmptyBorder(Style.Padding.XXL, Style.Padding.XXL, Style.Padding.XXL, Style.Padding.XXL));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                SoundPlayer.play(SoundPaths.BUTTON_CLICK);
                FrameUtil.createMainMenuIfNotExists(frame, gameController, loginController);
                FrameUtil.displayMainMenu(frame);
            }
        });
        button.addMouseListener(new ButtonHoverHandler());

        return button;
    }

    protected abstract JComponent getLevelButtonsList();

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
            e.printStackTrace();
        }
        return enable_image;
    }
}
