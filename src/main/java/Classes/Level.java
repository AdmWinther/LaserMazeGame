package Classes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Level {

    /**
     * Board object
     */
    private final Board board;

    /**
     * ArrayList of Token objects
     */
    private final Set<Token> tokens;

    /**
     * Parameterized constructor
     *
     * @param board Board - Board object
     * @author Léonard Amsler - s231715
     */
    public Level(Board board) {
        this.board = board;
        this.tokens = new HashSet<>();
    }

    /**
     * Getter for board
     *
     * @return Board - Board object
     * @author Léonard Amsler - s231715
     */
    public Board getBoard() {
        return board;
    }

    public boolean addToken(Token token) {
        return tokens.add(token);
    }

    public Set<Token> getTokens() {
        return tokens;
    }
}
