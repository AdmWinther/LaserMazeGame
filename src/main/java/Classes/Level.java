package Classes;

import Classes.Laser.Laser;
import Classes.Laser.LaserFragment;
import Classes.Laser.LaserGenerator;
import Classes.Tokens.Token;

import java.util.*;

public class Level {
    private final TokenManager tokenManager;
    private final LaserGenerator laserGenerator;


    /**
     * Constructor for Level class
     * @param placedTokens the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     */
    public Level(Token[][] placedTokens, Set<Token> unplacedTokens) {
        tokenManager = new TokenManager(placedTokens, unplacedTokens);
        laserGenerator = new LaserGenerator(tokenManager);
    }
    public TokenManager tokenManager() {
        return tokenManager;
    }

    public Laser generateLaser() {
        return laserGenerator.generateLaser();
    }
}
