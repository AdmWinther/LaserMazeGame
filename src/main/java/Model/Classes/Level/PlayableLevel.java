package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.Token;
import Model.Classes.TokenManager.StrictTokenManager;
import Model.Classes.Utils.Pair;
import Model.Interfaces.TokenManager;

import java.util.Set;

public final class PlayableLevel extends Level {

    private final TokenManager tokenManager;
    private final LaserManager laserManager;
    private boolean levelCompleted;

    /**
     * Constructor for Level class
     *
     * @param placedTokens   the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     */
    public PlayableLevel(String name, Token[][] placedTokens, Set<Token> unplacedTokens) {
        super(name, placedTokens.length, placedTokens[0].length);
        tokenManager = new StrictTokenManager(placedTokens, unplacedTokens);
        laserManager = new LaserManager(tokenManager, width, height);
        levelCompleted = false;
    }

    @Override
    public TokenManager tokenManager() {
        return tokenManager;
    }

    @Override
    public LaserManager laserManager() {
        return laserManager;
    }

    @Override
    public void reset() {
        tokenManager.reset();
    }

    @Override
    public Laser generateLaser() {
        Pair<Laser, Boolean> result;
        result = laserManager.checkSolution();

        this.levelCompleted = (boolean) result.second();

        return result.first();
    }

    public boolean isLevelCompleted() {
        return this.levelCompleted;
    }
}
