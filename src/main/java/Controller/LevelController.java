package Controller;

import Model.Classes.Laser.LaserFragment;
import Model.Classes.Level.Level;
import Model.Classes.Token.OrientedToken;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;
import Vue.Level.LevelPanel;
import Vue.MainMenu.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class LevelController {

    protected Level level;
    boolean shouldDisplayLaser = false;
    protected final JFrame frame;
    protected LevelPanel levelPanel;

    public LevelController(JFrame frame, LevelPanel levelPanel, Level level) {
        this.frame = frame;
        this.level = level;
        this.levelPanel = levelPanel;
    }

    public int getWidth() {
        return level.width;
    }

    public void backToMenu(){
        System.out.println(Arrays.toString(frame.getContentPane().getComponents()));
        frame.getContentPane().remove(levelPanel);
        // show the main menu
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        cardLayout.show(frame.getContentPane(), "MainMenu");
    }

    public void setLevelPanel(LevelPanel levelPanel){
        this.levelPanel = levelPanel;
    }

    public int getHeight() {
        return level.height;
    }

    public Set<Token> getUnplacedTokens() {
        return level.tokenManager().getUnplacedTokens();
    }

    public Map<Coordinate, Token> getPlacedTokens() {
        Token[][] tokens = level.tokenManager().getPlacedTokens();

        Map<Coordinate, Token> placedTokens = new HashMap<>();
        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0; j < tokens[0].length; j++) {
                if (tokens[i][j] != null) {
                    placedTokens.put(new Coordinate(i, j), tokens[i][j]);
                }
            }
        }

        return placedTokens;
    }

    public Token getTokenAt(Coordinate coordinate) {
        return level.tokenManager().getTokenAt(coordinate);
    }

    public void resetLevel() {
        level.reset();
    }

    public void moveToken(Coordinate from, Coordinate to) {
        level.tokenManager().moveToken(from, to);
    }

    public void transferTokenToPlacedTokens(Token token, Coordinate coordinate) {
        level.tokenManager().transferTokenToPlacedTokens(token, coordinate);
    }

    public void transferTokenToUnplacedTokens(Token token) {
        level.tokenManager().transferTokenToUnplacedTokens(token);
    }

    public Set<Pair<Coordinate, Coordinate>> getLaserFragments() {
        Set<Pair<Coordinate, Coordinate>> set = new HashSet<>();
        List<LaserFragment> fragments = level.generateLaser().getFragments();

        for (LaserFragment fragment : fragments) {
            if (fragment == null) {
                continue;
            }
            set.add(new Pair<>(fragment.from(), fragment.to()));
        }

        return set;
    }

    public Coordinate getLaserGunCoordinate() {
        return level.tokenManager().findLaserGunPosition();
    }

    public boolean shouldDisplayLaser() {
        return shouldDisplayLaser;
    }

    public void setShouldDisplayLaser(boolean b) {
        shouldDisplayLaser = b;
    }

    public void rotateToken(Token token) {
        if (token instanceof OrientedToken orientedToken && token.isMovable()) {
            Orientation nextOrientation = Orientation.values()[(orientedToken.getOrientation().ordinal() + 1) % Orientation.values().length];
            orientedToken.setOrientation(nextOrientation);
        }
    }

    public boolean isSandbox() {
        return false;
    }
}
