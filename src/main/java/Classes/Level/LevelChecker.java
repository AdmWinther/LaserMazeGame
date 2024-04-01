package Classes.Level;

import Classes.Token.Orientation;
import Classes.Token.Token;
import Classes.Token.TokenID;
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
     * @param tokenID TokenID - Token selected
     * @return boolean - True if the token is valid, false otherwise
     * @author Léonard Amsler - s231715
     */
    public boolean checkTokenSelection(TokenID tokenID) {


        System.out.println("\nTokens in level: \n");
        for (Token token : level.getTokens()) {
            System.out.println("    Token: " + token.id());
        }

        Token selectedToken = level.getTokens().stream().filter(t -> t.id().equals(tokenID)).findFirst().orElse(null);

        System.out.println("\nSelected token: " + selectedToken);
        
        if (selectedToken == null) {
            return false;
        } else {
            return selectedToken.isMovable();
        }

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
        } else if (x >= level.getBoard().getWIDTH()) {
            return false;
        } else if (y >= level.getBoard().getHEIGHT()) {
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
     * @param orientationIndex int - Orientation selected
     * @return boolean - True if the orientation is valid, false otherwise
     * @author Léonard Amsler - s231715
     */
    public Boolean checkOrientation(int orientationIndex) {
        return orientationIndex >= 0 && orientationIndex < Orientation.values().length;
    }
}
