package Vue.MenuPanels;

import Controller.GameController;
import Controller.LevelPreparation;
import Controller.LoginController;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;
import Vue.Constants.Style;
import Vue.Constants.VueFilePaths;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.SoundPaths;
import Vue.SoundEffects.SoundPlayer;
import Vue.Utils.ButtonUtil;
import Vue.Utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class has the responsibility to display the campaign levels list.
 *
 * @author Léonard Amsler - s231715
 * @author Nathan Gromb - s231674
 */
public class CampaignPanel extends LevelMenuPanel {

    /**
     * Constructor of the campaign panel class
     *
     * @param frame          The frame
     * @param gameController The game controller
     * @author Léonard Amsler - s231715
     * @author Nathan Gromb - s231674
     */
    public CampaignPanel(JFrame frame, GameController gameController, LoginController loginController) {
        super(frame, gameController, loginController);
    }


    /**
     * Get the level buttons
     *
     * @return JPanel - The level buttons
     * @author Léonard Amsler - s231715
     * @author Nathan Gromb - s231674
     * @author Adam Winther
     */
    @Override
    protected JPanel getLevelButtonsList() {
        int campaignProgression = loginController.getCampaignProgress();
        //Campaign progression is the max level the user can play. It is initially set to 1 during the registration.

        JPanel levelButtonPanel = new JPanel();
        levelButtonPanel.setLayout(new GridLayout(Style.Grid.CampaignMenu.ROWS, Style.Grid.CampaignMenu.COLS, 0, 0));

        levelButtonPanel.setOpaque(false); // Make the panel transparent

        levelButtonPanel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.XXL, Style.Padding.XXL, Style.Padding.XXL, Style.Padding.XXL));

        BufferedImage enable_image = getImage(VueFilePaths.BOARD_TILE);
        BufferedImage disable_image = getImage(VueFilePaths.DISABLED_BOARD_TILE);

        // Resize images
        final double RESIZE_FACTOR = 1.5;
        enable_image = ImageUtil.resizeImage(enable_image, (int) (tileWidth * RESIZE_FACTOR), (int) (tileHeight * RESIZE_FACTOR));
        disable_image = ImageUtil.resizeImage(disable_image, (int) (tileWidth * RESIZE_FACTOR), (int) (tileHeight * RESIZE_FACTOR));

        // Load level IDs
        int nb_levels = DataReader.readCampaignLevelIDs().size();

        for (int i = 1; i <= nb_levels; i++) {
            ImageIcon scaledIcon = new ImageIcon(enable_image);

            JButton levelButton = levelButton(campaignProgression, disable_image, i, scaledIcon);

            levelButtonPanel.add(levelButton);
        }

        return levelButtonPanel;
    }

    /**
     * Create a level button
     *
     * @param campaignProgression The campaign progression
     * @param disable_image       The disabled image
     * @param buttonNumber        The button number
     * @param scaledIcon          The scaled icon
     * @return JButton The level button
     * @author Léonard Amsler - s231715
     * @author Nathan Gromb - s231674
     */
    private JButton levelButton(int campaignProgression, BufferedImage disable_image, int buttonNumber, ImageIcon scaledIcon) {
        JButton levelButton = new JButton(String.valueOf(buttonNumber), scaledIcon);

        levelButton.setHorizontalAlignment(SwingConstants.CENTER);

        levelButton.setHorizontalTextPosition(SwingConstants.CENTER);
        levelButton.setVerticalTextPosition(SwingConstants.CENTER);
        levelButton.setFont(new Font(Style.Font.MONOSPACED, Font.BOLD, Style.FontSize.H1));

        ButtonUtil.setButtonTransparent(levelButton);

        levelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SoundPlayer.play(SoundPaths.CAMPAIGN_BUTTON);

                if (!levelButton.isEnabled()) {
                    return;
                }
                LevelID levelID = gameController.getCampaignLevelIDs().get(buttonNumber - 1);
                frame.remove(CampaignPanel.this);
                LevelPreparation.preparePlayableLevel(levelID, frame, gameController, loginController);
            }
        });
        levelButton.addMouseListener(new ButtonHoverHandler());

        if (buttonNumber <= campaignProgression) {
            levelButton.setEnabled(true);
        } else {
            levelButton.setEnabled(false);
            // apply a gray filter to the button
            levelButton.setDisabledIcon(new ImageIcon(disable_image));
        }
        return levelButton;
    }
}
