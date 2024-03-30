package Classes.Level;

import Classes.Lazer.Lazer;
import Classes.Token.Orientation;
import Classes.Token.OrientedToken;
import Classes.Token.Token;
import Classes.Utils.Coordinate;
import Interfaces.Runnable;

import java.util.HashSet;
import java.util.List;

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
    private final List<Token> tokens;
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
    private Lazer lazer;
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
    public Level(Board board, Board solutionBoard, List<Token> token, String levelName) {
        this.board = board;
        this.solutionBoard = solutionBoard;
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
     * @author Léonard Amsler - s231715
     */
    public List<Token> getTokens() {
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
        System.out.println("Running level" + levelName);
        while (levelState != LevelState.FINISHED) {
            switch (levelState) {
                case STARTING -> start();
                case NEED_USER_INPUT -> {
                    setLazer(generateLazer());
                    LevelPrinter.printLevel(this);
                    LevelPrinter.printBoardAndLazer(board, lazer);
                    LevelPrinter.printTokens(new HashSet<>(tokens));
                    needUserInput();
                }
                case CHECKING_SOLUTION -> checkSolution();
            }
        }
        System.out.println("Here is the solution");
        LevelPrinter.printLevel(this);
        LevelPrinter.printBoardAndLazer(board, lazer);
        LevelPrinter.printTokens(new HashSet<>(tokens));
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
    public void start() {
        System.out.println("Starting level " + levelName);
        levelState = LevelState.NEED_USER_INPUT;
    }

    /**
     * Need user input method
     *
     * @author Léonard Amsler - s231715
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
        token.setIsPlaced(true);

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
    public Lazer generateLazer() {
        return board.generateLazer();
    }

    /**
     * Getter for lazer
     *
     * @return Lazer - Lazer object
     * @author Léonard Amsler - s231715
     */
    public Lazer getLazer() {
        return lazer;
    }

    /**
     * Setter for lazer
     *
     * @param lazer Lazer - Lazer object
     * @author Léonard Amsler - s231715
     */
    public void setLazer(Lazer lazer) {
        this.lazer = lazer;
    }

}
