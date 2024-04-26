package Vue.MainMenu;

import Controller.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static Vue.MainMenu.LevelPreparation.preparePlayableLevel;
import static Vue.Utils.ButtonUtil.setButtonTransparent;
import static Vue.Utils.ImageUtil.resizeImage;

public class SandboxPanel extends JPanel {
    private final JFrame frame;
    private final JPanel buttons;
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
    private JPanel getLevelButtons() {
        JPanel levelButtonPanel = new JPanel();
        int rows = 4; // TODO depend on amount of campaign levels
        int cols = 4;
        levelButtonPanel.setLayout(new GridLayout(rows, cols, 0, 0));

        levelButtonPanel.setOpaque(false); // Make the panel transparent

        int padding = 50;
        levelButtonPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/main/java/Vue/Resources/Tiles/boardTile1.png"));
        } catch (Exception e) {
            System.out.println("Error loading campaign button image");
        }
        assert image != null;

        int scale = 5;
        image = resizeImage(image, tileWidth * scale, tileHeight * scale);

        for (int i = 0; i < rows * cols; i++) {
            ImageIcon scaledIcon = new ImageIcon(image);
            JButton levelButton = new JButton(String.valueOf(i + 1), scaledIcon);

            levelButton.setHorizontalAlignment(SwingConstants.CENTER);

            levelButton.setHorizontalTextPosition(SwingConstants.CENTER);
            levelButton.setVerticalTextPosition(SwingConstants.CENTER);
            levelButton.setFont(new Font("MonoSpaced", Font.BOLD, 30));

            setButtonTransparent(levelButton);

            levelButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    System.out.println("Level " + levelButton.getText() + " clicked");
                    preparePlayableLevel("level" + levelButton.getText(), frame, gameController);
                }
            });

            levelButtonPanel.add(levelButton);
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
