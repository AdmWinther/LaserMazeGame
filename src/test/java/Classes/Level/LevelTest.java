<<<<<<< HEAD
<<<<<<< HEAD
package Classes.Level;

import Classes.Token.Block;
import Classes.Token.Token;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LevelTest {

    @Test
    public void testGetTokens() {
        Set<Token> tokens = new HashSet<>();
        tokens.add(new Block());
        Level level = new Level(new Board(), new Board(), tokens, "Test Level");
        assertEquals(tokens, level.getTokens());
    }

    @Test
    public void testGetBoard() {
        Board board = new Board();
        Level level = new Level(board, new Board(), new HashSet<>(), "Test Level");
        assertEquals(board, level.getBoard());
    }

    @Test
    public void testGetSolutionBoard() {
        Board solutionBoard = new Board();
        Level level = new Level(new Board(), solutionBoard, new HashSet<>(), "Test Level");
        assertEquals(solutionBoard, level.getSolutionBoard());
    }
=======
package java.Classes.Level;

public class LevelTest {
    
>>>>>>> origin/LevelBuilder
=======
package Classes.Level;

public class LevelTest {
    
>>>>>>> origin/Tokens
}
