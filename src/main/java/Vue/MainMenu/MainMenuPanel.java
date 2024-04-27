package Vue.MainMenu;

import Controller.GameController;
import Controller.LoginController;
import Model.Classes.Level.LevelID;
import Vue.Constants.JComponentsNames;
import Vue.Constants.Style;
import Vue.Constants.VueFilePaths;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.Sound;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static Vue.MainMenu.LevelPreparation.preparePlayableLevel;
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


    private final int MAX_WIDTH = 200;
    private final int MAX_HEIGHT = 300;
    Thread mainMenuThread;
    GameController gameController;
    LoginController loginController;
    private Clip mainMenuClip;
    private Clip campaignButtonClickSound;
    private boolean isButtonClickSoundStarted;

    /**
     * Constructor of the main menu panel class
     *
     * @param frame          - The frame
     * @param gameController - The game controller
     * @Author Léonard Amsler - s231715
     * @Author Nathan Gromb - s231674
     * @Author Hussein (Adam)
     */
    public MainMenuPanel(JFrame frame, GameController gameController, LoginController loginController) {

        this.gameController = gameController;
        this.loginController = loginController;

        int screenWidth = frame.getWidth();
        int screenHeight = frame.getHeight();
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setLayout(new BorderLayout());

        // Background image
        ImageIcon backgroundImage = new ImageIcon(VueFilePaths.BACKGROUND_IMAGE);
        ImagePanel backgroundPanel = new ImagePanel(backgroundImage.getImage());
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());


        topPanel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.L, Style.Padding.L, Style.Padding.L, Style.Padding.L));
        backgroundPanel.add(topPanel, BorderLayout.NORTH);

        // Title label
        JLabel titleLabel = new JLabel(JComponentsNames.Label.LASER_MAZE, SwingConstants.CENTER);
        titleLabel.setFont(new Font(Style.Font.COURIER_NEW, Font.BOLD, Style.FontSize.H1));
        titleLabel.setForeground(Color.LIGHT_GRAY);
        topPanel.add(titleLabel, BorderLayout.SOUTH);


        // Logout button
        JButton logoutButton = new JButton(JComponentsNames.Label.LOGOUT);
        logoutButton.addActionListener(e -> {
            Sound.playButtonSound(null); // TODO null
            loginController.logout();
            showPanel(frame, JComponentsNames.FrameID.LOGIN);
        });
        logoutButton.addMouseListener(new ButtonHoverHandler());
        logoutButton.setFont(new Font(Style.Font.COURIER_NEW, Font.PLAIN, Style.FontSize.H2));
        logoutButton.setForeground(Color.LIGHT_GRAY);
        setButtonTransparent(logoutButton);
        logoutButton.setHorizontalAlignment(SwingConstants.RIGHT);


        topPanel.add(logoutButton, BorderLayout.NORTH);


        // Button panel
        JPanel buttonPanel = createButtonPanel(frame);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);


        // Footer label
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setLayout(new BorderLayout());

        JLabel footerLabel = new JLabel(JComponentsNames.Label.FOOTER, SwingConstants.RIGHT);
        footerLabel.setFont(new Font(Style.Font.COURIER_NEW, Font.PLAIN, Style.FontSize.H3));
        footerLabel.setForeground(Color.LIGHT_GRAY);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.S, Style.Padding.S, Style.Padding.S, Style.Padding.S));
        footerPanel.add(footerLabel, BorderLayout.EAST);

        JLabel loginLabel = new JLabel(JComponentsNames.Label.LOGGED_AS + loginController.getCurrentUsername().username, SwingConstants.LEFT);
        loginLabel.setFont(new Font(Style.Font.COURIER_NEW, Font.PLAIN, Style.FontSize.H3));
        loginLabel.setForeground(Color.LIGHT_GRAY);
        loginLabel.setBorder(BorderFactory.createEmptyBorder(Style.Padding.S, Style.Padding.S, Style.Padding.S, Style.Padding.S));
        footerPanel.add(loginLabel, BorderLayout.WEST);

        backgroundPanel.add(footerPanel, BorderLayout.SOUTH);

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
        buttonPanel.setLayout(new GridLayout(Style.Grid.MainMenu.ROWS, Style.Grid.MainMenu.COLS, 0, Style.Padding.XL));
        buttonPanel.setOpaque(false); // Make the panel transparent

        int width = frame.getWidth();
        int height = frame.getHeight();

        int sidePadding = 0;
        int topPadding = 0;

        if (width > MAX_WIDTH) {
            sidePadding = (width - MAX_WIDTH) / 2;
        }
        if (height > MAX_HEIGHT) {
            topPadding = (height - MAX_HEIGHT) / 2;
        }

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, sidePadding, topPadding, sidePadding));


        // Create buttons
        BufferedImage campaignButtonImage = null;
        BufferedImage sandboxButtonImage = null;
        BufferedImage randomButtonImage = null;
        try {
            campaignButtonImage = ImageIO.read(new File(VueFilePaths.CAMPAIGN_LEVELS_BUTTON));
            sandboxButtonImage = ImageIO.read(new File(VueFilePaths.SANDBOX_LEVELS_BUTTON));
            randomButtonImage = ImageIO.read(new File(VueFilePaths.RANDOM_LEVELS_BUTTON));
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
            Sound.playButtonSound(campaignButtonClickSound);
            displayCampaignLevels(frame);
        });
        campaignButton.addMouseListener(new ButtonHoverHandler());
        sandboxButton.addActionListener(e -> {
            Sound.playButtonSound(campaignButtonClickSound);
            displaySandboxLevels(frame);
        });
        sandboxButton.addMouseListener(new ButtonHoverHandler());
        randomButton.addActionListener(e -> {
            Sound.playButtonSound(campaignButtonClickSound);

            gameController.turnOffCampaignGameMode();
            LevelID randomLevelID = gameController.getSandboxLevelIDs().get((int) (Math.random() * gameController.getSandboxLevelIDs().size()));
            preparePlayableLevel(randomLevelID, frame, gameController, loginController);
        });
        randomButton.addMouseListener(new ButtonHoverHandler());

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
                int width = frame.getWidth();
                int height = frame.getHeight();

                int sidePadding = 0;
                int topPadding = 0;

                if (width > MAX_WIDTH) {
                    sidePadding = (width - MAX_WIDTH) / 2;
                }
                if (height > MAX_HEIGHT) {
                    topPadding = (height - MAX_HEIGHT) / 2;
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
    public void displayCampaignLevels(JFrame frame) {
        gameController.turnOnCampaignGameMode();
        CampaignPanel campaignPanel = new CampaignPanel(frame, gameController, loginController);
        frame.add(campaignPanel, JComponentsNames.FrameID.CAMPAIGN_LEVELS);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                campaignPanel.resize();
            }
        });

        showPanel(frame, JComponentsNames.FrameID.CAMPAIGN_LEVELS);
        frame.pack();
    }

    private void displaySandboxLevels(JFrame frame) {
        SandboxPanel sandboxPanel = new SandboxPanel(frame, gameController, loginController);
        frame.add(sandboxPanel, JComponentsNames.FrameID.SANDBOX_LEVELS);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                sandboxPanel.resize();
            }
        });

        showPanel(frame, JComponentsNames.FrameID.SANDBOX_LEVELS);
        frame.pack();
    }


}
