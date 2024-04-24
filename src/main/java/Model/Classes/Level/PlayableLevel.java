package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.StrictTokenManager;
import Model.Classes.Token.Token;
import Model.Classes.Token.TokenManager;

import java.util.Set;

public final class PlayableLevel extends Level{



    private final TokenManager tokenManager;
    private final LaserManager laserManager;
    /**
     * Constructor for Level class
     *
     * @param placedTokens   the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     */

    public PlayableLevel(String name, Token[][] placedTokens, Set<Token> unplacedTokens) {
        super(name,placedTokens.length,placedTokens[0].length);
        this.tokenManager = new StrictTokenManager(placedTokens, unplacedTokens);
        this.laserManager = new LaserManager(tokenManager, width, height);
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
        return laserManager.generateLaser();
    }
}
