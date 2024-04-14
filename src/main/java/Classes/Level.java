package Classes;

import Classes.Laser.LaserManager;
import Classes.Token.Token;
import Classes.Token.TokenManager;

import java.util.*;

public class Level {
    private final TokenManager tokenManager;
    private final LaserManager laserManager;


    /**
     * Constructor for Level class
     * @param placedTokens the tokens that are already placed on the board
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
