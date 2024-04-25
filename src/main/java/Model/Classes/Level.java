package Model.Classes;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.Token;
import Model.Classes.Token.TokenManager;
import Model.Classes.Utils.Pair;

import java.util.Set;

public class Level {

    private final String name;
    private final LevelID id;
    private final LaserManager laserManager;
    private final TokenManager tokenManager;
    public final int width;
    public final int height;
    private final int serialNr;

    private boolean levelCompleted;

    /**
     * Constructor for Level class
     *
     * @param placedTokens   the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     */
    public Level(String name, int serialNr,Token[][] placedTokens, Set<Token> unplacedTokens) {
        this.name = name;
        this.id = new LevelID(name + "_" + hashCode());
        this.serialNr = serialNr;
        tokenManager = new TokenManager(placedTokens, unplacedTokens);
        width = placedTokens.length;
        height = placedTokens[0].length;
        laserManager = new LaserManager(tokenManager, width, height);
        levelCompleted = false;
    }

    public TokenManager tokenManager() {
        return tokenManager;
    }

    public LaserManager laserManager() {
        return laserManager;
    }

    /**
     * Resets the level
     */
    public void reset() {
        tokenManager.reset();

    }

    public LevelID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Laser generateLaser() {
        Pair<Laser, Boolean> result;
        result = laserManager.checkSolution();

        this.levelCompleted = (boolean) result.second();

        return result.first();
    }

    public boolean isLevelCompleted() {
        return this.levelCompleted;
    }

    public int getSerialNr() {
        return serialNr;
    }
}
