package Vue.MenuPanels;

import Controller.GameController;
import Controller.LoginController;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;
import Model.Classes.Utils.DataWriter;
import Vue.Constants.JComponentsNames;
import Vue.Constants.Style;
import Vue.Constants.VueFilePaths;
import Vue.Handlers.ButtonHoverHandler;
import Vue.SoundEffects.Sound;
import Vue.Utils.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static Vue.MenuPanels.LevelPreparation.showPanel;


/**
 * The sandbox panel class has the responsibility to display the sandbox levels list and handle the interactions with the user
 *
 * @author Hugo Demule
 */
public class SandboxPanel extends JPanel {
    private final JFrame frame;
    private final JScrollPane buttons;
    private final JLabel backButton;
    GameController gameController;
    LoginController loginController;
    private int tileWidth = 16;
    private int tileHeight = 16;

    /**
     * Constructor of the campaign panel class
     *
     * @param frame          The frame
     * @param gameController The game controller
     * @author Hugo Demule
     */
    public SandboxPanel(JFrame frame, GameController gameController, LoginController loginController) {
        this.loginController = loginController;
        this.gameController = gameController;
        this.frame = frame;

        setDoubleBuffered(true);

        setLayout(new BorderLayout());

        // Background image
        ImageIcon backgroundImage = new ImageIcon("src/main/java/Vue/Resources/Tiles/background.png");
        ImagePanel backgroundPanel = new ImagePanel(backgroundImage.getImage());
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        // Create and add level buttons
        this.buttons = getSandboxLevelsList();
        backgroundPanel.add(buttons, BorderLayout.CENTER);

        this.backButton = getBackButton();
        backgroundPanel.add(backButton, BorderLayout.SOUTH);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });
    }

    /**
     * Get the level buttons
     *
     * @return JPanel The level buttons
     * @author Hugo Demule
     */
    private JScrollPane getSandboxLevelsList() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setOpaque(false);
        outerPanel.add(panel, new GridBagConstraints());

        JScrollPane sandboxLevelsList = initializeSandboxLevelsList(outerPanel);

        // Load images
        BufferedImage backgroundLevelList = getImage(VueFilePaths.SANDBOX_LIST_ITEM_BACKGROUND);
        BufferedImage playButtonImage = getImage(VueFilePaths.PLAY_BUTTON_ICON);
        BufferedImage editButtonImage = getImage(VueFilePaths.EDIT_BUTTON_ICON);
        BufferedImage deleteButtonImage = getImage(VueFilePaths.DELETE_BUTTON_ICON);
        BufferedImage newLevelButtonImage = getImage(VueFilePaths.SANDBOX_LIST_NEW_LEVEL_BACKGROUND);

        // Resize images
        final int SCALE = 5;
        final double ICON_RESIZE_FACTOR = 0.5;
        final int LONG_BUTTON_SCALE_FACTOR = 8;

        backgroundLevelList = ImageUtil.resizeImage(backgroundLevelList, tileWidth * SCALE * LONG_BUTTON_SCALE_FACTOR, tileHeight * SCALE);
        newLevelButtonImage = ImageUtil.resizeImage(newLevelButtonImage, tileWidth * SCALE * LONG_BUTTON_SCALE_FACTOR, tileHeight * SCALE);
        playButtonImage = ImageUtil.resizeImage(playButtonImage, (int) (tileWidth * SCALE * ICON_RESIZE_FACTOR), (int) (tileHeight * SCALE * ICON_RESIZE_FACTOR));
        editButtonImage = ImageUtil.resizeImage(editButtonImage, (int) (tileWidth * SCALE * ICON_RESIZE_FACTOR), (int) (tileHeight * SCALE * ICON_RESIZE_FACTOR));
        deleteButtonImage = ImageUtil.resizeImage(deleteButtonImage, (int) (tileWidth * SCALE * ICON_RESIZE_FACTOR), (int) (tileHeight * SCALE * ICON_RESIZE_FACTOR));

        // Load level IDs
        List<LevelID> levelIDs = DataReader.readSandboxLevelIDs();
        System.out.println(levelIDs);

        // Add new level button
        assert newLevelButtonImage != null;
        ImageIcon newLevelButtonIcon = new ImageIcon(newLevelButtonImage);
        JPanel newLevelButton = newLevelButton(newLevelButtonIcon);
        panel.add(newLevelButton);

        // Add level buttons
        for (LevelID levelID : levelIDs) {

            assert backgroundLevelList != null;
            ImageIcon scaledIcon = new ImageIcon(backgroundLevelList);

            JPanel levelPanel = levelPanel(scaledIcon);

            JPanel levelNameContainer = levelNameContainer(levelID);

            JPanel buttonsContainer = buttonsContainer(playButtonImage, editButtonImage, deleteButtonImage, levelID);

            // Create a wrapper panel with vertical BoxLayout to center buttonsContainer vertically
            JPanel verticalWrapper = verticalWrapper(buttonsContainer);

            levelPanel.add(verticalWrapper, BorderLayout.EAST);
            levelPanel.add(levelNameContainer, BorderLayout.WEST);
            panel.add(levelPanel);
        }

        return sandboxLevelsList;
    }

    /**
     * Get the back button
     *
     * @return JLabel - The back button
     * @author Hugo Demule
     */
    private JLabel getBackButton() {
        JLabel button = new JLabel();

        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        int padding = 50;
        button.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Sound.playButtonSound(null); // TODO null
                frame.getContentPane().remove(SandboxPanel.this);
                showPanel(frame, JComponentsNames.FrameID.MAIN_MENU);
            }
        });
        button.addMouseListener(new ButtonHoverHandler());

        return button;
    }

    /**
     * Resize the campaign panel
     *
     * @author Hugo Demule
     */
    public void resize() {

        tileWidth = 100;
        tileHeight = 100;

        // Set button sizes and positions
        for (Component component : buttons.getComponents()) {
            if (component instanceof JLabel button) {
                ImageIcon icon = new ImageIcon("src/main/java/Vue/Resources/Tiles/boardTile1.png");
                ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(tileWidth, tileHeight, Image.SCALE_DEFAULT));
                button.setIcon(scaledIcon);
                button.setFont(new Font("MonoSpaced", Font.BOLD, tileWidth / 2));
            }
        }

        // Update back button size and position
        int backButtonSize = tileWidth / 3 * 2;

        // Set the size and position of the back button
        backButton.setPreferredSize(new Dimension(backButtonSize, backButtonSize));
        JLabel button = backButton;
        ImageIcon icon = new ImageIcon("src/main/java/Vue/Resources/Objects/reset.png");
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(backButtonSize, backButtonSize, Image.SCALE_DEFAULT));
        button.setIcon(scaledIcon);
        button.setFont(new Font("MonoSpaced", Font.BOLD, tileWidth / 2));
    }

    /**
     * Initialize the sandbox levels list
     *
     * @param outerPanel The outer panel
     * @return JScrollPane The sandbox levels list
     * @author Hugo Demule
     */
    private static JScrollPane initializeSandboxLevelsList(JPanel outerPanel) {
        JScrollPane sandboxLevelsList = new JScrollPane(outerPanel);
        sandboxLevelsList.setOpaque(false); // Make the panel transparent
        sandboxLevelsList.getViewport().setOpaque(false);
        sandboxLevelsList.getVerticalScrollBar().setOpaque(false);
        sandboxLevelsList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sandboxLevelsList.setBorder(BorderFactory.createEmptyBorder(Style.Padding.XXL, Style.Padding.XXL, Style.Padding.XXL, Style.Padding.XXL));
        return sandboxLevelsList;
    }

    /**
     * Get an image
     *
     * @param playButtonIcon The icon of the play button
     * @return BufferedImage The image of the play button icon
     * @author Hugo Demule
     */
    private static BufferedImage getImage(String playButtonIcon) {
        BufferedImage playButtonImage = null;
        try {
            playButtonImage = ImageIO.read(new File(playButtonIcon));
        } catch (Exception e) {
            System.out.println("Error loading campaign button image");
        }
        return playButtonImage;
    }

    /**
     * Create a new level button
     *
     * @param newLevelButtonIcon The icon of the new level button
     * @return JPanel The new level button panel
     * @author Hugo Demule
     */
    private JPanel newLevelButton(ImageIcon newLevelButtonIcon) {
        JPanel newLevelButton = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(newLevelButtonIcon.getImage(), 0, 0, null);
            }
        };
        newLevelButton.setPreferredSize(new Dimension(newLevelButtonIcon.getIconWidth(), newLevelButtonIcon.getIconHeight()));


        newLevelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sound.playButtonSound(null); // TODO null
                LevelPreparation.prepareNewEditableLevel(frame, gameController);
            }
        });
        newLevelButton.addMouseListener(new ButtonHoverHandler());
        return newLevelButton;
    }

    /**
     * Create a level panel
     *
     * @param scaledIcon The scaled icon
     * @return JPanel The level panel
     * @author Hugo Demule
     */
    private JPanel levelPanel(ImageIcon scaledIcon) {
        JPanel levelPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledIcon.getImage(), 0, 0, null);
            }
        };
        levelPanel.setLayout(new BorderLayout());
        return levelPanel;
    }

    /**
     * Create a container for the level name
     *
     * @param levelID The level ID
     * @return JPanel The level name container panel
     * @author Hugo Demule
     */
    private static JPanel levelNameContainer(LevelID levelID) {
        JPanel levelNameContainer = new JPanel();
        levelNameContainer.setOpaque(false);
        levelNameContainer.setLayout(new BoxLayout(levelNameContainer, BoxLayout.X_AXIS));
        levelNameContainer.setBorder(BorderFactory.createEmptyBorder(0, Style.Padding.L, 0, 0));

        // add a label with the level name
        String levelNameString = "";
        try {
            levelNameString = DataReader.readLevelIDName(levelID);
            final int LENGTH_LIMIT = 25;
            if (levelNameString.length() > LENGTH_LIMIT)
                levelNameString = levelNameString.substring(0, LENGTH_LIMIT) + "...";
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel levelName = new JLabel(levelNameString);
        levelName.setFont(new Font(Style.Font.MONOSPACED, Font.BOLD, Style.FontSize.H2));
        levelNameContainer.add(levelName);
        return levelNameContainer;
    }

    /**
     * Create a container for the buttons
     *
     * @param playButtonImage   The image of the play button
     * @param editButtonImage   The image of the edit button
     * @param deleteButtonImage The image of the delete button
     * @param levelID           The level ID
     * @return JPanel The buttons container panel
     * @author Hugo Demule
     */
    private JPanel buttonsContainer(BufferedImage playButtonImage, BufferedImage editButtonImage, BufferedImage deleteButtonImage, LevelID levelID) {
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setOpaque(false);
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
        buttonsContainer.setBorder(BorderFactory.createEmptyBorder(Style.Padding.S, 0, 0, Style.Padding.M));

        // add three buttons stacked to the right of the levelPanel with three different images
        JPanel playButton = playButton(playButtonImage, levelID);

        JPanel editButton = editButton(editButtonImage, levelID);

        JPanel deleteButton = deleteButton(deleteButtonImage, levelID);

        deleteButton.addMouseListener(new ButtonHoverHandler());

        buttonsContainer.add(playButton);
        buttonsContainer.add(editButton);
        buttonsContainer.add(deleteButton);
        return buttonsContainer;
    }

    /**
     * Create a wrapper panel with vertical BoxLayout to center buttonsContainer vertically
     *
     * @param buttonsContainer The buttons container panel
     * @return JPanel The vertical wrapper panel
     * @author Hugo Demule
     */
    private static JPanel verticalWrapper(JPanel buttonsContainer) {
        JPanel verticalWrapper = new JPanel();
        verticalWrapper.setLayout(new BoxLayout(verticalWrapper, BoxLayout.Y_AXIS));
        verticalWrapper.setOpaque(false);
        verticalWrapper.add(Box.createVerticalGlue()); // Spacer to push the content to the center
        verticalWrapper.add(buttonsContainer);
        verticalWrapper.add(Box.createVerticalGlue()); // Spacer to push the content to the center
        return verticalWrapper;
    }

    /**
     * Create a play button
     *
     * @param playButtonImage The image of the play button
     * @param levelID         The level ID
     * @return JPanel The play button panel
     * @author Hugo Demule
     */
    private JPanel playButton(BufferedImage playButtonImage, LevelID levelID) {
        JPanel playButton = new JPanel();
        playButton.setOpaque(false);
        playButton.add(new JLabel(new ImageIcon(playButtonImage)));
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sound.playButtonSound(null); // TODO null
                LevelPreparation.preparePlayableLevel(levelID, frame, gameController, loginController);
            }
        });
        playButton.addMouseListener(new ButtonHoverHandler());
        return playButton;
    }

    /**
     * Create an edit button
     *
     * @param editButtonImage The image of the edit button
     * @param levelID         The level ID
     * @return JPanel The edit button panel
     * @author Hugo Demule
     */
    private JPanel editButton(BufferedImage editButtonImage, LevelID levelID) {
        JPanel editButton = new JPanel();
        editButton.setOpaque(false);
        editButton.add(new JLabel(new ImageIcon(editButtonImage)));
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sound.playButtonSound(null); // TODO null
                LevelPreparation.prepareEditableLevel(levelID, frame, gameController);
            }
        });
        editButton.addMouseListener(new ButtonHoverHandler());
        return editButton;
    }

    /**
     * Create a delete button
     *
     * @param deleteButtonImage The image of the delete button
     * @param levelID           The level ID
     * @return JPanel The delete button panel
     * @author Hugo Demule
     */
    private JPanel deleteButton(BufferedImage deleteButtonImage, LevelID levelID) {
        JPanel deleteButton = new JPanel();
        deleteButton.setOpaque(false);
        deleteButton.add(new JLabel(new ImageIcon(deleteButtonImage)));
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sound.playButtonSound(null); // TODO null
                try {
                    DataWriter.delete(levelID);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                refresh();
            }
        });
        return deleteButton;
    }

    /**
     * Refresh the sandbox panel
     *
     * @author Hugo Demule
     */
    private void refresh() {
        frame.add(new SandboxPanel(frame, gameController, loginController), JComponentsNames.FrameID.SANDBOX_LEVELS);
        frame.getContentPane().remove(this);
        frame.revalidate();
        showPanel(frame, JComponentsNames.FrameID.SANDBOX_LEVELS);
        frame.repaint();
    }
}
