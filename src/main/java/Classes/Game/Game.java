package Classes.Game;

import Classes.Board;
import Classes.Level;
import Classes.Orientation;
import Classes.Tokens.LaserGun;
import Classes.Tokens.OneSidedMirror;
import Classes.Tokens.Receiver;
import Classes.Tokens.Token;
import Classes.Utils.Coordinate;

import javax.swing.*;

public class Game {

    JFrame frame;
    GamePanel gamePanel;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Laser Maze");

        Level level = new Level(new Board(4, 9));

        Token beamer = new LaserGun(false, Orientation.UP);
        OneSidedMirror mirror = new OneSidedMirror(true, Orientation.UP);
        Receiver receiver = new Receiver(false, Orientation.UP);

        level.addToken(beamer);
        level.addToken(mirror);
        level.addToken(receiver);

        level.placeToken(beamer, new Coordinate(0, 2));
        level.placeToken(mirror, new Coordinate(1, 1));
        level.placeToken(receiver, new Coordinate(2, 0));

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
