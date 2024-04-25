package Vue.MainMenu;

import Controller.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static Vue.MainMenu.LevelPreparation.prepareLevel;
import static Vue.MainMenu.LevelPreparation.showPanel;
import static Vue.Utils.ButtonUtil.setButtonTransparent;
import static Vue.Utils.ImageUtil.resizeImage;

/**
 * This class is responsible for the main menu panel
 *
 * @Author Léonard Amsler - s231715
 * @Author Hussein (Adam)
 */
public class MainMenuPanel extends JPanel {

    Thread mainMenuThread;
    GameController gameController;

    /**
     * Constructor of the main menu panel class
     *
     * @param frame          - The frame
     * @param gameController - The game controller
     * @Author Léonard Amsler - s231715
     * @Author Nathan Gromb - s231674
     * @Author Hussein (Adam)
     */
    public MainMenuPanel(JFrame frame, GameController gameController) {

        this.gameController = gameController;

        int screenWidth = frame.getWidth();
        int screenHeight = frame.getHeight();
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setLayout(new BorderLayout());

        // Background image
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Vue/Resources/Tiles/background.png");
        ImagePanel backgroundPanel = new ImagePanel(backgroundImage.getImage());
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        // Title label
        JLabel titleLabel = new JLabel("Laser Maze", SwingConstants.CENTER);
        // Use a custom font
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel(frame);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);


        // Footer label
        JLabel footerLabel = new JLabel("Made by group 3", SwingConstants.RIGHT);
        footerLabel.setFont(new Font("Courier New", Font.PLAIN, 10));
        footerLabel.setForeground(Color.LIGHT_GRAY);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        backgroundPanel.add(footerLabel, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * Create the button panel
     *
     * @param frame - The frame
     * @return JPanel - The button panel
     * @Author Léonard Amsler - s231715
     * @Author Nathan Gromb - s231674
     */
    private JPanel createButtonPanel(JFrame frame) {
        // Button panel for the three buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 30));
        buttonPanel.setOpaque(false); // Make the panel transparent

        int width = frame.getWidth();
        int height = frame.getHeight();

        int sidePadding = 0;
        int topPadding = 0;

        if (width > 200) {
            sidePadding = (width - 200) / 2;
        }
        if (height > 300) {
            topPadding = (height - 300) / 2;
        }

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, sidePadding, topPadding, sidePadding));


        // Create buttons
        BufferedImage campaignButtonImage = null;
        BufferedImage sandboxButtonImage = null;
        BufferedImage randomButtonImage = null;
        try {
            campaignButtonImage = ImageIO.read(new File("src/main/java/Vue/Resources/MenuButtons/campaign.png"));
            sandboxButtonImage = ImageIO.read(new File("src/main/java/Vue/Resources/MenuButtons/sandbox.png"));
            randomButtonImage = ImageIO.read(new File("src/main/java/Vue/Resources/MenuButtons/random.png"));
        } catch (Exception e) {
            System.out.println("Error loading campaign button image");
        }

        // Resize button image such that they fit the button
        assert campaignButtonImage != null;
        assert sandboxButtonImage != null;
        assert randomButtonImage != null;
        int VScale = 4;
        double HScale = 2.5;
        int buttonWidth = campaignButtonImage.getWidth() * VScale;
        int buttonHeight = (int) (campaignButtonImage.getHeight() * HScale);
        campaignButtonImage = resizeImage(campaignButtonImage, buttonWidth, buttonHeight);
        sandboxButtonImage = resizeImage(sandboxButtonImage, buttonWidth, buttonHeight);
        randomButtonImage = resizeImage(randomButtonImage, buttonWidth, buttonHeight);

        // Create buttons
        JButton campaignButton = new JButton(new ImageIcon(campaignButtonImage));
        JButton sandboxButton = new JButton(new ImageIcon(sandboxButtonImage));
        JButton randomButton = new JButton(new ImageIcon(randomButtonImage));

        // Add action listeners to buttons
        campaignButton.addActionListener(e -> {
            System.out.println("Campaign button clicked");
            displayCampaignLevels(frame);
        });
        sandboxButton.addActionListener(e -> {
            System.out.println("Sandbox button clicked");
        });
        randomButton.addActionListener(e -> {
            System.out.println("Random button clicked");
            // TODO: Implement random level generation, for now just load level 1
            prepareLevel("level1", frame, gameController);
        });

        // Set button to transparent
        setButtonTransparent(campaignButton);
        setButtonTransparent(sandboxButton);
        setButtonTransparent(randomButton);

        // Remove the blue line around the button
        campaignButton.setFocusPainted(false);
        sandboxButton.setFocusPainted(false);
        randomButton.setFocusPainted(false);

        // Add buttons to panel
        buttonPanel.add(campaignButton);
        buttonPanel.add(sandboxButton);
        buttonPanel.add(randomButton);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized mainmenu");
                int width = frame.getWidth();
                int height = frame.getHeight();

                int sidePadding = 0;
                int topPadding = 0;

                if (width > 200) {
                    sidePadding = (width - 200) / 2;
                }
                if (height > 300) {
                    topPadding = (height - 300) / 2;
                }
                buttonPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, sidePadding, topPadding, sidePadding));
                buttonPanel.repaint();
            }
        });

        return buttonPanel;

    }

    /**
     * Display the campaign levels
     *
     * @param frame - The frame
     * @Author Nathan Gromb - s231674
     */
    private void displayCampaignLevels(JFrame frame) {
        CampaignPanel campaignPanel = new CampaignPanel(frame, gameController);
        frame.add(campaignPanel, "CampaignLevels");
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                campaignPanel.resize();
            }
        });

        showPanel(frame, "CampaignLevels");
        frame.pack();
    }


}
