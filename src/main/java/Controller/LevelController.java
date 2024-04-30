package Controller;

import Model.Classes.Laser.LaserFragment;
import Model.Classes.Level.Level;
import Model.Classes.Token.OrientedToken;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;
import Vue.Level.LevelPanel;

import javax.swing.*;
import java.util.*;

/**
 * This class has the responsibility to control the level
 * This is an abstract class that will be extended by PlayableLevelController and EditableLevelController
 */
public abstract class LevelController<LevelPanelType extends LevelPanel, LevelType extends Level> {

    /**
     * The frame
     */
    protected final JFrame frame;

    /**
     * The level
     */
    protected LevelType level;

    /**
     * The level panel
     */
    protected LevelPanelType levelPanel;

    /**
     * The game controller
     */
    protected GameController gameController;

    /**
     * Should display laser
     */
    boolean shouldDisplayLaser = false;

    /**
     * Constructor of the LevelController
     *
     * @param gameController GameController - The game controller
     * @param frame          JFrame - The frame
     * @param levelPanel     LevelPanel - The level panel
     * @param level          Level - The level
     * @author Hugo Demule (s231675)
     * @author Léonard Amsler (s231715)
     */
    public LevelController(GameController gameController, JFrame frame, LevelPanelType levelPanel, LevelType level) {
        this.gameController = gameController;
        this.frame = frame;
        this.level = level;
        this.levelPanel = levelPanel;
    }

    /**
     * Get the width of the level
     *
     * @return int - The width of the level
     * @author Hugo Demule (s231675)
     * @author Léonard Amsler (s231715)
     */
    public int getWidth() {
        return level.width;
    }


    /**
     * Set the level panel
     *
     * @param levelPanel LevelPanel - The level panel
     * @author Hugo Demule (s231675)
     */
    public void setLevelPanel(LevelPanelType levelPanel) {
        this.levelPanel = levelPanel;
    }

    /**
     * Get the height of the level
     *
     * @return int - The height of the level
     * @author Hugo Demule (s231675)
     * @author Léonard Amsler (s231715)
     */
    public int getHeight() {
        return level.height;
    }

    /**
     * Get the unplaced tokens
     *
     * @return Set of Token - The unplaced tokens
     * @author Léonard Amsler (s231715)
     */
    public Set<Token> getUnplacedTokens() {
        return level.tokenManager().getUnplacedTokens();
    }

    /**
     * Get the placed tokens
     *
     * @return Map of Coordinate and Token - The placed tokens
     * @author Léonard Amsler (s231715)
     */
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

    /**
     * Get the token at a specific coordinate
     *
     * @param coordinate Coordinate - The coordinate
     * @return Token - The token at the coordinate, null if there is no token at the coordinate
     * @author Hugo Demule (s231675)
     * @author Léonard Amsler (s231715)
     */
    public Token getTokenAt(Coordinate coordinate) {
        return level.tokenManager().getTokenAt(coordinate);
    }

    /**
     * Reset the level
     *
     * @author Hugo Demule (s231675)
     * @author Léonard Amsler (s231715)
     */
    public void resetLevel() {
        level.reset();
    }

    /**
     * Move a token from a coordinate to another
     *
     * @param from Coordinate - The coordinate from
     * @param to   Coordinate - The coordinate to
     * @author Léonard Amsler (s231715)
     */
    public void moveToken(Coordinate from, Coordinate to) {
        level.tokenManager().moveToken(from, to);
    }

    /**
     * Transfer a token to the placed tokens
     *
     * @param token      Token - The token
     * @param coordinate Coordinate - The coordinate of the token in the placed tokens
     * @author Hugo Demule (s231675)
     * @author Léonard Amsler (s231715)
     */
    public void transferTokenToPlacedTokens(Token token, Coordinate coordinate) {
        level.tokenManager().transferTokenToPlacedTokens(token, coordinate);
    }

    /**
     * Transfer a token to the unplaced tokens
     *
     * @param token Token - The token to transfer to the unplaced tokens
     * @author Hugo Demule (s231675)
     * @author Léonard Amsler (s231715)
     */
    public void transferTokenToUnplacedTokens(Token token) {
        level.tokenManager().transferTokenToUnplacedTokens(token);
    }

    /**
     * Get the laser fragments
     *
     * @return Set of pair of Coordinate - The laser fragments
     * @author Léonard Amsler (s231715)
     */
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

    /**
     * Get the laser gun coordinate
     *
     * @return Coordinate - The laser gun coordinate, null if there is no laser gun in the level
     * @author Léonard Amsler (s231715)
     */
    public Coordinate getLaserGunCoordinate() {
        return level.tokenManager().findLaserGunPosition();
    }

    /**
     * Get if we should display the laser
     *
     * @return boolean - True if we should display the laser, false otherwise
     * @author Léonard Amsler (s231715)
     */
    public boolean shouldDisplayLaser() {
        return shouldDisplayLaser;
    }

    /**
     * Set if we should display the laser
     *
     * @param b boolean - True if we should display the laser, false otherwise
     * @author Léonard Amsler (s231715)
     */
    public void setShouldDisplayLaser(boolean b) {
        shouldDisplayLaser = b;
    }

    /**
     * Rotate a token
     *
     * @param token Token - The token to rotate
     * @author Hugo Demule (s231675)
     */
    public void rotateToken(Token token) {
        if (token instanceof OrientedToken orientedToken && token.isMovable()) {
            Orientation nextOrientation = Orientation.values()[(orientedToken.getOrientation().ordinal() + 1) % Orientation.values().length];
            orientedToken.setOrientation(nextOrientation);
        }
    }

    /**
     * Check if the level is in sandbox mode
     *
     * @return boolean - True if the level is in sandbox mode, false otherwise
     * @author Hugo Demule (s231675)
     */
    public boolean isSandbox() {
        return false;
    }
}
