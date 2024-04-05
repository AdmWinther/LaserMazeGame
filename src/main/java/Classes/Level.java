package Classes;

import Classes.Utils.Coordinate;

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

    public void addToken(Token token) {
        tokens.add(token);
    }

    /**
     * Places a token on the board by calling "placeToken" of Board class.
     * @param token the token to place
     * @param coordinate the coordinate at which the token will be placed
     * @return true if the token has been placed successfully, otherwise false
     */
    public boolean placeToken(Token token, Coordinate coordinate) {
        if (tokens.contains(token)) return board.placeToken(token, coordinate);
        else return false;
    }

    public Set<Token> getTokens() {
        return tokens;
    }
}
