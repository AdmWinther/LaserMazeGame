package Classes.Level;
import Classes.Lazer.Lazer;

import Classes.Token.Token;
import Interfaces.Runnable;
import java.util.HashSet;
import java.util.Set;

public class Level {
    private final Board board;
    private final Board solutionBoard;
    private final Set<Token> tokens;

//    private final LevelChecker levelChecker;

    private final String levelName;
    private Lazer lazer;
//    private LevelState levelState;

    public Level(Board board, Board solutionBoard, Set<Token> token, String levelName) {
        this.board = board;
        this.solutionBoard = solutionBoard;
        this.tokens = token;
        this.lazer = new Lazer();
//        this.levelChecker = new LevelChecker(this);
        this.levelName = levelName;
//        this.levelState = LevelState.STARTING;
    }
}
