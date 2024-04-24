package Vue.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CampaignPanel extends JPanel {
    private final JFrame frame;
    private final JPanel buttons;
    private final JLabel backButton;

    private int tileWidth = 16;
    private int tileHeight = 16;

    public CampaignPanel(JFrame frame) {
        this.frame = frame;

        setDoubleBuffered(true);

        setLayout(new BorderLayout());

        // Create and add level buttons
        this.buttons = getLevelButtons();
        add(buttons, BorderLayout.CENTER);

        this.backButton = getBackButton();
        add(backButton, BorderLayout.SOUTH);

        int padding = 100;
        this.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
        });
    }

    private void calculateTileAndGridSize() {
        // Calculate the tile size based on the screen size, padding, and number of columns/rows
    }

    public void resize() {
        // Recalculate tile size
        calculateTileAndGridSize();

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

    private JPanel getLevelButtons() {
        JPanel container = new JPanel();
        int rows = 4; // TODO depend on amount of campaign levels
        int cols = 4;
        container.setLayout(new GridLayout(rows, cols, 0, 0));

        for (int i = 0; i < rows * cols; i++) {
            JLabel levelButton = new JLabel();
            levelButton.setHorizontalAlignment(SwingConstants.CENTER);
            levelButton.setVerticalAlignment(SwingConstants.CENTER);
            levelButton.setFont(new Font("MonoSpaced", Font.BOLD, tileWidth / 2));
            levelButton.setForeground(new Color(0x393535));
            levelButton.setText(String.valueOf(i + 1));

            ImageIcon icon = new ImageIcon("src/main/java/Vue/Resources/Tiles/boardTile1.png");
            ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(tileWidth, tileHeight, Image.SCALE_DEFAULT));
            levelButton.setIcon(scaledIcon);

            // Set layout to null to allow manual positioning of text
            levelButton.setLayout(null);
            // Position the text in the center of the button
            levelButton.setBounds(0, 0, tileWidth, tileHeight);
            // Position the text relative to the center of the button
            levelButton.setHorizontalTextPosition(SwingConstants.CENTER);
            levelButton.setVerticalTextPosition(SwingConstants.CENTER);

            levelButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    System.out.println("Level " + levelButton.getText() + " clicked");
                }
            });

            container.add(levelButton);
        }

        return container;
    }

    private JLabel getBackButton() {
        JLabel button = new JLabel();

        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                frame.getContentPane().remove(CampaignPanel.this);
            }
        });

        return button;
    }
}
