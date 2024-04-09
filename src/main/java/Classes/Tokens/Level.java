package Classes.Tokens;

import Classes.Utils.Coordinate;

import java.util.ArrayList;

public class Level {

    /**
     * Board object
     */
    private final Board board;

    /**
     * ArrayList of Token objects
     */
    private final ArrayList<Token> tokens;

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

    public boolean addAndPlaceToken(Token token, Coordinate coordinate) {
        if (this.board.isCoordinateInBoard(coordinate)) {
            if (board.isPositionEmpty(coordinate)) {
                tokens.add(token);
                board.placeToken(this.tokens.size() - 1, coordinate);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Places a token on the board by calling "placeToken" of Board class.
     *
     * @param index,     the index of the token to be placed
     * @param coordinate the coordinate at which the token will be placed
     * @return true if the token has been placed successfully, otherwise false
     */
    public boolean placeToken(int index, Coordinate coordinate) {
        if (board.IndexOfTokenAt(coordinate) == -1) return board.placeToken(index, coordinate);
        else return false;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public Token getTokenByIndex(int tokenIndex) {
        return tokens.get(tokenIndex);
    }

    public boolean moveTokenFromTo(Coordinate fromCoordinate, Coordinate toCoordinate1) {
        if (board.isCoordinateInBoard(fromCoordinate) && board.isCoordinateInBoard(toCoordinate1)) {
            if (!board.isPositionEmpty(fromCoordinate)) {
                if (board.isPositionEmpty(toCoordinate1)) {
                    int tokenIndex = board.IndexOfTokenAt(fromCoordinate);
                    if (tokens.get(tokenIndex).isMovable()) {
                        board.placeToken(tokenIndex, toCoordinate1);
                        board.removeTokenFromBoard(fromCoordinate);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
