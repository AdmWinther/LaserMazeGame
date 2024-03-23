package Classes.Level;

import Classes.Token.Token;
import Classes.Utils.Coordinate;

public class LevelChecker {

    /**
     * Level object
     */
    private final Level level;

    /**
     * Default constructor
     */
    public LevelChecker() {
        this.level = new Level();
    }

    /**
     * Parameterized constructor
     *
     * @param level Level - Level object
     */
    public LevelChecker(Level level) {
        this.level = level;
    }

    /**
     * Check if the token selected is valid
     *
     * @param token int - Token selected
     * @return boolean - True if the token is valid, false otherwise
     */
    public boolean checkTokenSelection(int token) {
        int nbTokens = level.getTokens().size();
        Token selectedToken = level.getTokens().get(token);
        boolean isMovable = selectedToken.isMovable();
        if (token < 0) {
            return false;
        } else if (token >= level.getTokens().size()) {
            return false;
        } else return isMovable;
    }

    /**
     * Check if the coordinate selected is valid
     *
     * @param x int - X coordinate
     * @param y int - Y coordinate
     * @return boolean - True if the coordinate is valid, false otherwise
     */
    public boolean checkNewPosition(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (x < 0 || y < 0) {
            return false;
        } else if (x >= level.getBoard().getWidth()) {
            return false;
        } else if (y >= level.getBoard().getHeight()) {
            return false;
        } else return true;
    }

    /**
     * Check if the provided solution is correct
     */
    public Boolean checkSolution() {
        Board solutionBoard = level.getSolutionBoard();
        Board currentBoard = level.getBoard();
        return solutionBoard.equals(currentBoard);
    }
}
