package Classes;

import Classes.Laser.Laser;
import Classes.Token.Token;
import Classes.Utils.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class Level {
    private final Board board;
    private final Set<Token> tokens;
    private final Laser laser;

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

    public boolean placeToken(Token token, Coordinate position) {
        return board.placeToken(token, position);
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public Laser generateLaser() {
        laser = board.generateLaser();
        return laser;
    }
}
