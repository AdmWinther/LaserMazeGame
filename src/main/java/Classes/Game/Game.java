package Classes.Game;

import Classes.Level;
import Classes.Orientation;
import Classes.Tokens.*;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class Game {

    JFrame frame;
    GamePanel gamePanel;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");

        int width = 9;
        int height = 4;

        // Placed tokens
        Token beamer = new LaserGun(false, Orientation.UP);
        Receiver receiver = new Receiver(false, Orientation.UP);
        Block block = new Block(false);

        Token[][] placedTokens;
        placedTokens = new Token[width][height];
        placedTokens[0][2] = beamer;
        placedTokens[8][0] = receiver;
        placedTokens[7][0] = block;

        // Unplaced tokens
        OneSidedMirror mirror = new OneSidedMirror(true, Orientation.RIGHT);
        DoubleSidedMirror doubleMirror = new DoubleSidedMirror(true, Orientation.LEFT);

        Set<Token> unplacedTokens;
        unplacedTokens = new HashSet<>();
        unplacedTokens.add(doubleMirror);
        unplacedTokens.add(mirror);


        Level level = new Level(placedTokens, unplacedTokens);

        gamePanel = new GamePanel(level);

        frame.add(gamePanel);
        frame.pack();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        frame.setVisible(true);
    }

}
