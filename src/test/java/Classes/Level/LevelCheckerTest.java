package Classes.Level;

import Classes.Token.Token.TokenID;
import Classes.Utils.Coordinate;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LevelCheckerTest {

    @Test
    public void testCheckTokenSelection() {
        Level level1 = new Level(new Board(0, 0), new Board(0, 0), new HashSet<>(), "Test Level");
        LevelChecker levelChecker1 = new LevelChecker(level1);
        assertFalse(levelChecker1.checkTokenSelection(new TokenID("0")));
        assertFalse(levelChecker1.checkTokenSelection(new TokenID("1")));
        assertFalse(levelChecker1.checkTokenSelection(new TokenID("2")));
    }

    @Test
    public void testCheckNewPosition() {
        Level level = new Level(new Board(0, 0), new Board(0, 0), new HashSet<>(), "Test Level");
        LevelChecker levelChecker = new LevelChecker(level);
        assertFalse(levelChecker.checkNewPosition(new Coordinate(-1, -1)));
        assertFalse(levelChecker.checkNewPosition(new Coordinate(0, 0)));
        assertFalse(levelChecker.checkNewPosition(new Coordinate(1, 1)));
    }

    @Test
    public void testCheckSolution() {
        Level level = new Level(new Board(0, 0), new Board(0, 0), new HashSet<>(), "Test Level");
        LevelChecker levelChecker = new LevelChecker(level);
        assertFalse(levelChecker.checkSolution());
    }

    @Test
    public void testCheckOrientation() {
        Level level = new Level(new Board(0, 0), new Board(0, 0), new HashSet<>(), "Test Level");
        LevelChecker levelChecker = new LevelChecker(level);
        assertTrue(levelChecker.checkOrientation(0));
        assertTrue(levelChecker.checkOrientation(1));
        assertTrue(levelChecker.checkOrientation(2));
        assertTrue(levelChecker.checkOrientation(3));
        assertFalse(levelChecker.checkOrientation(-1));
        int randomInt = new Random().nextInt(100) + 4;
        assertFalse(levelChecker.checkOrientation(randomInt));
    }
}
