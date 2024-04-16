package Model.Classes;

import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.Token;
import Model.Classes.Token.TokenManager;

import java.util.Set;

public class Level {
    private final LaserManager laserManager;
    private final TokenManager tokenManager;


    /**
     * Constructor for Level class
     *
     * @param placedTokens   the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     */
    public Level(Token[][] placedTokens, Set<Token> unplacedTokens) {
        tokenManager = new TokenManager(placedTokens, unplacedTokens);
        laserManager = new LaserManager(tokenManager);
    }

    public TokenManager tokenManager() {
        return tokenManager;
    }

    public LaserManager laserManager() {
        return laserManager;
    }
}
