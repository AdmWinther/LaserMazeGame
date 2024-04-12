package Classes;

import Classes.Tokens.Token;

import java.util.Set;

public class Level {

    private final TokenManager tokenManager;

    /**
     * Constructor for Level class
     *
     * @param placedTokens   the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     */
    public Level(Token[][] placedTokens, Set<Token> unplacedTokens) {
        tokenManager = new TokenManager(placedTokens, unplacedTokens);
    }

    public TokenManager tokenManager() {
        return tokenManager;
    }
}
