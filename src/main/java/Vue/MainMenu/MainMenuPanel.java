package Vue.MainMenu;

import Controller.GameController;
import Controller.LevelController;
import Vue.Level.LevelPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(JFrame frame) {


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

    private static void prepareLevel(String levelID, JFrame frame) {

        GameController gameController = new GameController();
        gameController.setCurrentLevelID(levelID);
        LevelController levelController = new LevelController(gameController.getCurrentLevel());

        LevelPanel levelPanel = new LevelPanel(levelController);

        frame.getContentPane().removeAll();
        frame.add(levelPanel);
        frame.revalidate();
        frame.repaint();
        
    }

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

        System.out.println("width: " + width);
        System.out.println("height: " + height);
        System.out.println("sidePadding: " + sidePadding);
        System.out.println("topPadding: " + topPadding);

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
        int buttonWidth = 200;
        int buttonHeight = 50;
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
        });
        sandboxButton.addActionListener(e -> {
            System.out.println("Sandbox button clicked");
        });
        randomButton.addActionListener(e -> {
            System.out.println("Random button clicked");
            prepareLevel("level1", frame);
        });

        // Set button to transparent
        campaignButton.setOpaque(false);
        campaignButton.setContentAreaFilled(false);
        campaignButton.setBorderPainted(false);

        sandboxButton.setOpaque(false);
        sandboxButton.setContentAreaFilled(false);
        sandboxButton.setBorderPainted(false);

        randomButton.setOpaque(false);
        randomButton.setContentAreaFilled(false);
        randomButton.setBorderPainted(false);

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

                System.out.println("width: " + width);
                System.out.println("height: " + height);
                System.out.println("sidePadding: " + sidePadding);
                System.out.println("topPadding: " + topPadding);

                buttonPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, sidePadding, topPadding, sidePadding));
                buttonPanel.repaint();
            }
        });

        return buttonPanel;
    }

    private BufferedImage resizeImage(BufferedImage campaignButtonImage, int i, int i1) {
        Image tmp = campaignButtonImage.getScaledInstance(i, i1, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(i, i1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    private static class ImagePanel extends JPanel {
        private final Image backgroundImage;

        public ImagePanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            if (backgroundImage != null) {
                // Calculate the number of tiles needed to fill the panel
                int width = getWidth();
                int height = getHeight();
                int scale = 3;
                int tileWidth = backgroundImage.getWidth(null) * scale;
                int tileHeight = backgroundImage.getHeight(null) * scale;

                // Draw the background image tiles to fill the panel
                for (int x = 0; x < width; x += tileWidth) {
                    for (int y = 0; y < height; y += tileHeight) {
                        g2d.drawImage(backgroundImage, x, y, tileWidth, tileHeight, this);
                    }
                }
            }
        }
    }
}
