package Vue.MainMenu;

import Controller.GameController;
import Controller.LevelController;
import Vue.Level.LevelPanel;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MainMenuPanel extends JPanel {

    private JFrame frame;
    final int originalTileSize = 16;
    public int screenWidth;
    public int screenHeight;
    Thread mainMenuThread;
    int HScale = 3;
    int VScale = 3;
    int tileWidth = originalTileSize * HScale;
    int tileHeight = originalTileSize * VScale;

    public MainMenuPanel(JFrame frame) {
        this.screenWidth = 720;
        this.screenHeight = 720;
        this.frame = frame;


        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();

        JPanel backgroundPanel = getBackgroundJPanel();
        this.add(backgroundPanel);

        BorderLayout borderPanel = new BorderLayout();


        this.setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome to the game", SwingConstants.CENTER);
        this.add(welcome, BorderLayout.NORTH);


        JPanel buttons = getButtonsJPanel();
        this.add(buttons, BorderLayout.CENTER);

        JLabel footer = new JLabel("Made by Group-3", SwingConstants.CENTER);
        this.add(footer, BorderLayout.SOUTH);

        this.setVisible(true);

    }

    private void prepareLevel(String levelID, JFrame frame) {

        GameController gameController = new GameController();
        gameController.setCurrentLevelID(levelID);

        LevelController levelController = new LevelController(gameController.getCurrentLevel());

        LevelPanel levelPanel = new LevelPanel(levelController);
        frame.add(levelPanel, "Level");
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                levelPanel.resize();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
        showPanel("Level");
        frame.pack();
    }

    private void displayCampaignLevels(JFrame frame) {
        CampaignPanel campaignPanel = new CampaignPanel(frame);
        frame.add(campaignPanel, "CampaignLevels");
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                campaignPanel.resize();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });

        showPanel("CampaignLevels");
        frame.pack();
    }

    @NotNull
    private JPanel getButtonsJPanel() {

        JPanel backgroundPanel = new JPanel();

        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3, 1));

        JLabel emptyLabel1 = new JLabel(" ");
        container.add(emptyLabel1);

        JButton loadGame = new JButton("Load Game");
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loadGame.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
//        loadGame.setMargin(new Insets(20, 20, 20, 20));
        loadGame.addActionListener(e -> {
//                System.out.println("You clicked the button");
            prepareLevel("level6", frame);
        });

        JButton newGame = new JButton("New Game");
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton campaign = new JButton("Campaign");
        campaign.setAlignmentX(Component.CENTER_ALIGNMENT);
        campaign.addActionListener(e -> displayCampaignLevels(frame));

        backgroundPanel.add(Box.createHorizontalStrut(10));
        backgroundPanel.add(newGame);
        backgroundPanel.add(Box.createHorizontalStrut(10));
        backgroundPanel.add(loadGame);
        backgroundPanel.add(Box.createHorizontalStrut(10));
        backgroundPanel.add(campaign);
        backgroundPanel.add(Box.createHorizontalStrut(10));

        container.add(backgroundPanel);

        return container;
    }

    private JPanel getBackgroundJPanel() {
        // Add the background
        int nbTilesX = screenWidth / tileWidth;
        int nbTilesY = screenHeight / tileHeight;

        BufferedImage background = null;
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/background.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        final BufferedImage finalBackground = background;

        if (background == null) {
            return new JPanel();
        }


        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                for (int i = 0; i < nbTilesX; i++) {
                    for (int j = 0; j < nbTilesY; j++) {
                        g2d.drawImage(finalBackground, i * tileWidth, j * tileHeight, tileWidth, tileHeight, null);
                    }
                }
            }
        };

        return backgroundPanel;
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), panelName);
    }
}
