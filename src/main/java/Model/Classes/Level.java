package Model.Classes;

import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.Token;
import Model.Classes.Token.TokenManager;

import java.util.Set;

public class Level {
    private final LaserManager laserManager;
    private final TokenManager tokenManager;
    private final int width;
    private final int height;


    /**
     * Constructor for Level class
     *
     * @param placedTokens   the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     */
    public Level(Token[][] placedTokens, Set<Token> unplacedTokens) {
        tokenManager = new TokenManager(placedTokens, unplacedTokens);
        laserManager = new LaserManager(tokenManager);
        width = placedTokens.length;
        height = placedTokens[0].length;
    }

    public TokenManager tokenManager() {
        return tokenManager;
    }

    public LaserManager laserManager() {
        return laserManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void resetLevel() {
        tokenManager.resetLevel();
    }
}
