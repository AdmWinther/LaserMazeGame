package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.Token;
import Model.Classes.TokenManager.StrictTokenManager;
import Model.Classes.Utils.Pair;
import Model.Interfaces.TokenManager;

import java.util.Set;

/**
 * PlayableLevel class.
 * PlayableLevel is a subclass of the Level class. PlayableLevel is used in Campaign, Random Level, and running a level
 * that is made in the sandbox.
 * Contains a TokenManager and a LaserManager.
 * Contains a method to check if the level is completed.
 *
 * @Author Hugo Demule, Lina Mounan
 */
public final class PlayableLevel extends Level {

    private final TokenManager tokenManager;
    private final LaserManager laserManager;
    private boolean levelCompleted;

    /**
     * Constructor for Level class
     *
     * @param placedTokens   the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     * @author Hugo Demule, Lina Mounan
     */
    public PlayableLevel(String name, Token[][] placedTokens, Set<Token> unplacedTokens) {
        super(name, placedTokens.length, placedTokens[0].length);
        tokenManager = new StrictTokenManager(placedTokens, unplacedTokens);
        laserManager = new LaserManager(tokenManager, width, height);
        levelCompleted = false;
    }

    /**
     * Get the TokenManager of the level
     *
     * @return TokenManager of the level
     * @author Hugo Demule
     */
    @Override
    public TokenManager tokenManager() {
        return tokenManager;
    }

    /**
     * Get the LaserManager of the level
     *
     * @author Hugo Demule
     */
    @Override
    public LaserManager laserManager() {
        return laserManager;
    }

    /**
     * Reset the level
     * Resetting the level will reset the TokenManager of the level.
     *
     * @author Hugo Demule
     */
    @Override
    public void reset() {
        tokenManager.reset();
    }

    /**
     * Generate the laser
     * Generate the laser by checking the solution of the level.
     *
     * @return the laser that is generated
     * @author Adam Winther
     */
    @Override
    public Laser generateLaser() {
        Pair<Laser, Boolean> result;
        result = laserManager.checkSolution();

        this.levelCompleted = result.second();

        return result.first();
    }

    /**
     * Check if the level is completed
     *
     * @return true if the level is completed, false otherwise
     * @author Adam Winther
     */
    public boolean isLevelCompleted() {
        return this.levelCompleted;
    }
}
