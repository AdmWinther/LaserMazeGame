package Vue.MainMenu;

import Controller.GameController;
import Model.Classes.Level.LevelID;
import Model.Classes.Utils.DataReader;
import Model.Classes.Utils.DataWriter;
import Vue.Utils.ButtonUtil;
import Vue.Utils.ImageUtil;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class SandboxPanel extends JPanel {
    private final JFrame frame;
    private final JScrollPane buttons;
    private final JLabel backButton;
    GameController gameController;
    private int tileWidth = 16;
    private int tileHeight = 16;

    /**
     * Constructor of the campaign panel class
     *
     * @param frame          - The frame
     * @param gameController - The game controller
     * @author Hugo Demule
     */
    public SandboxPanel(JFrame frame, GameController gameController) {
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
        this.buttons = getLevelButtons();
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
     * Resize the campaign panel
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
     * Get the level buttons
     *
     * @return JPanel - The level buttons
     * @author Hugo Demule
     */
    private JScrollPane getLevelButtons() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setOpaque(false);
        outerPanel.add(panel, new GridBagConstraints());

        JScrollPane levelButtonPanel = new JScrollPane(outerPanel);
        levelButtonPanel.setOpaque(false); // Make the panel transparent
        levelButtonPanel.getViewport().setOpaque(false);
        levelButtonPanel.getVerticalScrollBar().setOpaque(false);
        levelButtonPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        int padding = 50;
        levelButtonPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        BufferedImage image = null;
        BufferedImage playButtonImage = null;
        BufferedImage editButtonImage = null;
        BufferedImage deleteButtonImage = null;
        try {
            image = ImageIO.read(new File("src/main/java/Vue/Resources/Tiles/longBoardTile1.png"));
            playButtonImage = ImageIO.read(new File("src/main/java/Vue/Resources/Objects/bin.png"));
            editButtonImage = ImageIO.read(new File("src/main/java/Vue/Resources/Objects/bin.png"));
            deleteButtonImage = ImageIO.read(new File("src/main/java/Vue/Resources/Objects/bin.png"));
        } catch (Exception e) {
            System.out.println("Error loading campaign button image");
        }
        assert image != null;
        assert playButtonImage != null;
        assert editButtonImage != null;
        assert deleteButtonImage != null;

        int scale = 5;
        image = ImageUtil.resizeImage(image, tileWidth * scale * 6, tileHeight * scale);
        double resizeFactor = 0.5;
        playButtonImage = ImageUtil.resizeImage(playButtonImage, (int)(tileWidth * scale * resizeFactor), (int)(tileHeight * scale * resizeFactor));
        editButtonImage = ImageUtil.resizeImage(editButtonImage, (int)(tileWidth * scale * resizeFactor), (int)(tileHeight * scale * resizeFactor));
        deleteButtonImage = ImageUtil.resizeImage(deleteButtonImage, (int)(tileWidth * scale * resizeFactor), (int)(tileHeight * scale * resizeFactor));

        List<LevelID> levelIDs = DataReader.readSandboxLevelIDs();
        System.out.println(levelIDs);

        for (LevelID levelID: levelIDs) {
            ImageIcon scaledIcon = new ImageIcon(image);
            JPanel levelPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(scaledIcon.getImage(), 0, 0, null);
                }
            };
            levelPanel.setLayout(new BorderLayout());
            levelPanel.setPreferredSize(new Dimension(scaledIcon.getIconWidth(), scaledIcon.getIconHeight()));

            JPanel levelNameContainer = new JPanel();
            levelNameContainer.setOpaque(false);
            levelNameContainer.setLayout(new BoxLayout(levelNameContainer, BoxLayout.X_AXIS));
            levelNameContainer.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

            // add a label with the level name
            JLabel levelName = new JLabel(levelID.value());
            levelName.setFont(new Font("MonoSpaced", Font.BOLD, 20));
            levelNameContainer.add(levelName);


            JPanel buttonsContainer = new JPanel();
            buttonsContainer.setOpaque(false);
            buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
            buttonsContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

            // add three buttons stacked to the right of the levelPanel with three different images
            JPanel playButton = new JPanel();
            playButton.setOpaque(false);
            playButton.add(new JLabel(new ImageIcon(playButtonImage)));

            JPanel editButton = new JPanel();
            editButton.setOpaque(false);
            editButton.add(new JLabel(new ImageIcon(editButtonImage)));

            JPanel deleteButton = new JPanel();
            deleteButton.setOpaque(false);
            deleteButton.add(new JLabel(new ImageIcon(deleteButtonImage)));

            buttonsContainer.add(playButton);
            buttonsContainer.add(editButton);
            buttonsContainer.add(deleteButton);

            // Create a wrapper panel with vertical BoxLayout to center buttonsContainer vertically
            JPanel verticalWrapper = new JPanel();
            verticalWrapper.setLayout(new BoxLayout(verticalWrapper, BoxLayout.Y_AXIS));
            verticalWrapper.setOpaque(false);
            verticalWrapper.add(Box.createVerticalGlue()); // Spacer to push the content to the center
            verticalWrapper.add(buttonsContainer);
            verticalWrapper.add(Box.createVerticalGlue()); // Spacer to push the content to the center


            levelPanel.add(verticalWrapper, BorderLayout.EAST);
            levelPanel.add(levelNameContainer, BorderLayout.WEST);

            playButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    System.out.println(levelID.value() + " PLAY");
                    LevelPreparation.preparePlayableLevel(levelID, frame, gameController);
                }
            });

            editButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    System.out.println(levelID.value() + " EDIT");
                    LevelPreparation.prepareEditableLevel(levelID, frame, gameController);
                }
            });

            deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    System.out.println(levelID.value() + " DELETE");
                    /*
                    try {
                        DataWriter.delete(new LevelID("test"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                     */
                }
            });

            panel.add(levelPanel);
        }

        return levelButtonPanel;
    }

    /**
     * Get the back button
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
                frame.getContentPane().remove(SandboxPanel.this);
            }
        });

        return button;
    }
}
