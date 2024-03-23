package Classes.Level;

import Classes.Lazer.Lazer;
import Classes.Token.Orientation;
import Classes.Token.OrientedToken;
import Classes.Token.Token;
import Classes.Utils.Coordinate;
import Interfaces.Runnable;

import java.util.ArrayList;

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
    private final ArrayList<Token> tokens;

    /**
     * Lazer object
     */
    private final Lazer lazer;

    /**
     * LevelChecker object
     */
    private final LevelChecker levelChecker;

    /**
     * Name of the level
     */
    private final String levelName;

    /**
     * State of the level
     */
    private LevelState levelState;

    /**
     * Parameterized constructor
     *
     * @param board         Board - Board object
     * @param SolutionBoard Board - Solution board
     * @param token         ArrayList - ArrayList of Token objects
     * @param levelName     String - Name of the level
     */
    public Level(Board board, Board SolutionBoard, ArrayList<Token> token, String levelName) {
        this.board = board;
        this.solutionBoard = SolutionBoard;
        this.tokens = token;
        this.lazer = new Lazer();
        this.levelChecker = new LevelChecker(this);
        this.levelName = levelName;
        this.levelState = LevelState.STARTING;
    }

    /**
     * Getter for tokens
     *
     * @return ArrayList - ArrayList of Token objects
     */
    public ArrayList<Token> getTokens() {
        return tokens;
    }

    /**
     * Getter for board
     *
     * @return Board - Board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for solutionBoard
     *
     * @return Board - Solution board
     */
    public Board getSolutionBoard() {
        return solutionBoard;
    }

    /**
     * Run method
     */
    @Override
    public void run() {
        System.out.println("Running level" + levelName);
        while (levelState != LevelState.FINISHED) {
            switch (levelState) {
                case STARTING:
                    start();
                    break;
                case NEED_USER_INPUT:
                    LevelPrinter.printLevel(this);
                    LevelPrinter.printBoardAndLazer(board, lazer);
                    LevelPrinter.printTokens(tokens);
                    needUserInput();
                    break;
                case CHECKING_SOLUTION:
                    checkSolution();
                    break;
            }
        }
        System.out.println("Here is the solution");
        LevelPrinter.printLevel(this);
        LevelPrinter.printBoardAndLazer(board, lazer);
        LevelPrinter.printTokens(tokens);
        stop();
    }

    /**
     * Stop method
     */
    @Override
    public void stop() {
        System.out.println("Stopping level " + levelName);
    }


    /**
     * Start method
     */
    public void start() {
        System.out.println("Starting level " + levelName);
        levelState = LevelState.NEED_USER_INPUT;
    }

    /**
     * Need user input method
     */
    private void needUserInput() {
        System.out.println("Need user input");

        // Select a token
        boolean isTokenIndexValid = false;
        int tokenIndex = -1;
        while (!isTokenIndexValid) {
            tokenIndex = LevelInput.selectToken();
            isTokenIndexValid = levelChecker.checkTokenSelection(tokenIndex);
        }
        Token token = tokens.get(tokenIndex);

        // Select a coordinate
        boolean isPositionValid = false;
        Coordinate coordinate = new Coordinate(-1, -1);
        while (!isPositionValid) {
            coordinate = LevelInput.getNewPosition();
            isPositionValid = levelChecker.checkNewPosition(coordinate);
        }

        // Select the orientation
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
        token.setCoordinate(coordinate); // Or board.setTokenCoordinate(token, coordinate);
        if (token instanceof OrientedToken) {
            token.setOrientation(orientation);
        }
        token.setIsPlaced(true);

        levelState = LevelState.CHECKING_SOLUTION;
    }

    /**
     * Check solution method
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


}
