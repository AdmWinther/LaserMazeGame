package Vue.MainMenu;

import Controller.LevelController;
import Vue.Handlers.LevelMouseHandler;
import Vue.Level.UILaser;
import Vue.Level.UIObjects;
import Vue.Level.UITiles;
import Vue.Level.UITokens;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel{

    public int screenWidth;
    public int screenHeight;

    Thread mainMenuThread;

    public MainMenuPanel() {
        this.screenWidth = 720;
        this.screenHeight = 720;

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDoubleBuffered(true);

        setFocusable(true);
        requestFocus();


        this.setLayout(new BorderLayout());
        JLabel welcome = new JLabel("Welcome to the game", SwingConstants.CENTER);
        this.add(welcome, BorderLayout.NORTH);


        JPanel buttons = getjPanel();


        this.add(buttons, BorderLayout.CENTER);


        JLabel footer = new JLabel("Made by Group-3", SwingConstants.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    @NotNull
    private static JPanel getjPanel() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3,1));

        JLabel emptyLabel1 = new JLabel(" ");
        container.add(emptyLabel1);

        JButton loadGame = new JButton("Load Game");
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loadGame.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
//        loadGame.setMargin(new Insets(20, 20, 20, 20));

        JButton newGame = new JButton("New Game");
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton campaign = new JButton("Campaign");
        campaign.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(newGame);
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(loadGame);
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(campaign);
        buttons.add(Box.createHorizontalStrut(10));

//        buttons.setBackground(new Color(50,50, 50));

        container.add(buttons);
//        container.setBackground(new Color(100,100, 50));

        JLabel emptyLable2 = new JLabel(" ");
        container.add(emptyLable2);

        return container;
    }
}
