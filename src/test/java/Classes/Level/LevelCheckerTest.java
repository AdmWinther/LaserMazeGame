<<<<<<< HEAD
package Classes.Level;

import Classes.Utils.Coordinate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LevelCheckerTest {

    @Test
    public void testCheckTokenSelection() {
        Level level1 = new Level(new Board(), new Board(), new ArrayList<>(), "Test Level");
        LevelChecker levelChecker1 = new LevelChecker(level1);
        assertFalse(levelChecker1.checkTokenSelection(-1));
        assertFalse(levelChecker1.checkTokenSelection(0));
        assertFalse(levelChecker1.checkTokenSelection(1));
    }

    @Test
    public void testCheckNewPosition() {
        Level level = new Level(new Board(), new Board(), new ArrayList<>(), "Test Level");
        LevelChecker levelChecker = new LevelChecker(level);
        assertFalse(levelChecker.checkNewPosition(new Coordinate(-1, -1)));
        assertFalse(levelChecker.checkNewPosition(new Coordinate(0, 0)));
        assertFalse(levelChecker.checkNewPosition(new Coordinate(1, 1)));
    }

    @Test
    public void testCheckSolution() {
        Level level = new Level(new Board(), new Board(), new ArrayList<>(), "Test Level");
        LevelChecker levelChecker = new LevelChecker(level);
        assertFalse(levelChecker.checkSolution());
    }

    @Test
    public void testCheckOrientation() {
        Level level = new Level(new Board(), new Board(), new ArrayList<>(), "Test Level");
        LevelChecker levelChecker = new LevelChecker(level);
        assertTrue(levelChecker.checkOrientation(0));
        assertTrue(levelChecker.checkOrientation(1));
        assertTrue(levelChecker.checkOrientation(2));
        assertTrue(levelChecker.checkOrientation(3));
        assertFalse(levelChecker.checkOrientation(-1));
        int randomInt = new Random().nextInt(100) + 4;
        assertFalse(levelChecker.checkOrientation(randomInt));
    }

=======
package java.Classes.Level;

public class LevelCheckerTest {
    
>>>>>>> origin/LevelBuilder
}
