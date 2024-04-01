package Classes.Level;

import Classes.Lazer.Laser;
import Classes.Token.Orientation;
import Classes.Token.OrientedToken;
import Classes.Token.Token;
import Classes.Token.TokenID;
import Classes.Utils.Coordinate;
import Interfaces.Runnable;

import java.util.HashSet;
import java.util.Set;

public class Level implements Runnable {

    /**
     * Board object
     */
    private final Board board;

    /**
     * Solution board
     */
    private final Board solutionBoard;

    /**
     * ArrayList of Token objects
     */
    private final Set<Token> tokens;
    /**
     * LevelChecker object
     */
    private final LevelChecker levelChecker;
    /**
     * Name of the level
     */
    private final String levelName;
    /**
     * Lazer object
     */
    private Laser lazer;
    /**
     * State of the level
     */
    private LevelState levelState;

    /**
     * Parameterized constructor
     *
     * @param board         Board - Board object
     * @param solutionBoard Board - Solution board
     * @param token         ArrayList - ArrayList of Token objects
     * @param levelName     String - Name of the level
     * @author Léonard Amsler - s231715
     */
    public Level(Board board, Board solutionBoard, Set<Token> token, String levelName) {
        this.board = board;
        this.solutionBoard = solutionBoard;
        this.tokens = token;
        this.lazer = new Laser();
        this.levelChecker = new LevelChecker(this);
        this.levelName = levelName;
        this.levelState = LevelState.STARTING;
    }

    /**
     * Getter for levelName
     *
     * @return String - Name of the level
     * @author Léonard Amsler - s231715
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Getter for tokens
     *
     * @return ArrayList - ArrayList of Token objects
     * @author Léonard Amsler - s231715
     */
    public Set<Token> getTokens() {
        return tokens;
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

    /**
     * Getter for solutionBoard
     *
     * @return Board - Solution board
     * @author Léonard Amsler - s231715
     */
    public Board getSolutionBoard() {
        return solutionBoard;
    }

    /**
     * Run method
     *
     * @author Léonard Amsler - s231715
     */
    @Override
    public void run() {
        System.out.println("Running level " + levelName);
        while (levelState != LevelState.FINISHED) {
            switch (levelState) {
                case STARTING -> start();
                case NEED_USER_INPUT -> {
                    this.lazer = generateLazer();
                    LevelPrinter.print(this);
                    LevelPrinter.print(board, lazer);
                    LevelPrinter.print(new HashSet<>(tokens));
                    needUserInput();
                }
                case CHECKING_SOLUTION -> checkSolution();
            }
        }
        System.out.println("Here is the solution");
        LevelPrinter.print(this);
        LevelPrinter.print(board, lazer);
        LevelPrinter.print(new HashSet<>(tokens));
        stop();
    }

    /**
     * Stop method
     *
     * @author Léonard Amsler - s231715
     */
    @Override
    public void stop() {
        System.out.println("Stopping level " + levelName);
    }


    /**
     * Start method
     *
     * @author Léonard Amsler - s231715
     */
    private void start() {
        levelState = LevelState.NEED_USER_INPUT;
    }

    /**
     * Need user input method
     *
     * @author Léonard Amsler - s231715
     */
    private void needUserInput() {
        System.out.println("\nNeed user input");

        // Select a token
        boolean isTokenIndexValid = false;
        TokenID tokenID = null;
        while (!isTokenIndexValid) {
            tokenID = LevelInput.selectToken();
            isTokenIndexValid = levelChecker.checkTokenSelection(tokenID);
        }
        TokenID finalTokenID = tokenID;
        Token token = tokens.stream().filter(t -> t.id() == finalTokenID).findFirst().orElse(null);

        if (token == null) {
            System.out.println("Token not found");
            return;
        }


        // Select a coordinate
        boolean isPositionValid = false;
        Coordinate coordinate = new Coordinate(-1, -1);
        while (!isPositionValid) {
            coordinate = LevelInput.getNewPosition();
            isPositionValid = levelChecker.checkNewPosition(coordinate);
        }

        // Select the orientation, if the token is orientable
        Orientation orientation = null;
        if (token instanceof OrientedToken) {
            boolean isOrientationValid = false;
            int orientation_int = -1;
            while (!isOrientationValid) {
                orientation_int = LevelInput.selectOrientation();
                isOrientationValid = levelChecker.checkOrientation(orientation_int);
            }
            orientation = Orientation.values()[orientation_int];
        }

        // Affect the token to the new position
        board.setTokenCoordinate(token, coordinate);
        if (token instanceof OrientedToken) {
            ((OrientedToken) token).setOrientation(orientation);
        }

        levelState = LevelState.CHECKING_SOLUTION;
    }

    /**
     * Check solution method
     *
     * @author Léonard Amsler - s231715
     */
    private void checkSolution() {
        System.out.println("Checking solution");
        Boolean isSolutionCorrect = levelChecker.checkSolution();
        if (isSolutionCorrect) {
            System.out.println("Congratulations, you have finished the level");
            levelState = LevelState.FINISHED;
        } else {
            System.out.println("The solution is incorrect, please try again");
            levelState = LevelState.NEED_USER_INPUT;
        }
    }

    /**
     * Generate lazer method
     *
     * @return Lazer - Lazer object
     * @author Léonard Amsler - s231715
     */
    private Laser generateLazer() {
        return board.generateLazer();
    }
}
