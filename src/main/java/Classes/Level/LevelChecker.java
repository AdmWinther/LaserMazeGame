package Classes.Level;

import Classes.Token.Orientation;
import Classes.Token.Token;
import Classes.Utils.Coordinate;

public class LevelChecker {

    /**
     * Level object
     */
    private final Level level;

    /**
     * Parameterized constructor
     *
     * @param level Level - Level object
     * @author Léonard Amsler - s231715
     */
    public LevelChecker(Level level) {
        this.level = level;
    }

    /**
     * Check if the token selected is valid
     *
     * @param token int - Token selected
     * @return boolean - True if the token is valid, false otherwise
     * @author Léonard Amsler - s231715
     */
    public boolean checkTokenSelection(int token) {
        int nbTokens = level.getTokens().size();
        Token selectedToken = level.getTokens().get(token);
        boolean isMovable = selectedToken.isMovable();
        if (token < 0) {
            return false;
        } else if (token >= nbTokens) {
            return false;
        } else return isMovable;
    }

    /**
     * Check if the coordinate selected is valid
     *
     * @param coordinate Coordinate - Coordinate selected
     * @return boolean - True if the coordinate is valid, false otherwise
     * @author Léonard Amsler - s231715
     */
    public boolean checkNewPosition(Coordinate coordinate) {
        int x = coordinate.x();
        int y = coordinate.y();
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
     *
     * @author Léonard Amsler - s231715
     */
    public Boolean checkSolution() {
        Board solutionBoard = level.getSolutionBoard();
        Board currentBoard = level.getBoard();
        return solutionBoard.equals(currentBoard);
    }

    /**
     * Check if the orientation selected is valid
     *
     * @param orientation int - Orientation selected
     * @return boolean - True if the orientation is valid, false otherwise
     * @author Léonard Amsler - s231715
     */
    public Boolean checkOrientation(int orientation) {
        return orientation >= 0 && orientation < Orientation.values().length;
    }
}
