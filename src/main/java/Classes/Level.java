package Classes;

import Classes.Tokens.Token;
import Classes.Utils.Coordinate;

import java.util.*;

public class Level {

    /**
     * Board object
     */
    private final Board board;

    /**
     * ArrayList of Token objects
     */
    private final List<Token> tokens;

    /**
     * Parameterized constructor
     *
     * @param board Board - Board object
     * @author Léonard Amsler - s231715
     */
    public Level(Board board) {
        this.board = board;
        this.tokens = new ArrayList<>();
    }

    public Level(Board board, List<Token> tokens) {
        this.board = board;
        this.tokens = tokens;
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
     * @param token, the token to be placed
     * @param coordinate the coordinate at which the token will be placed
     * @return true if the token has been placed successfully, otherwise false
     */
    public boolean placeToken(Token token, Coordinate coordinate) {
        if (tokens.contains(token)) {
            tokens.remove(token);
            return board.placeToken(token, coordinate);
        }
        else return false;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Token getTokenOnBoardAt(Coordinate position) {
        return board.getTokenAt(position);
    }

    public boolean moveTokenFromTo(Coordinate fromCoordinate, Coordinate toCoordinate1) {

        if (!board.isCoordinateInBoard(fromCoordinate)
                || !board.isCoordinateInBoard(toCoordinate1)
                || board.isPositionEmpty(fromCoordinate)
                || !board.isPositionEmpty(toCoordinate1)) {
            return false;
        }

        Token token = board.getTokenAt(fromCoordinate);
        if (token.isMovable()) {
            return board.removeToken(fromCoordinate) && board.placeToken(token, toCoordinate1);
        }

        return false;
    }
}
