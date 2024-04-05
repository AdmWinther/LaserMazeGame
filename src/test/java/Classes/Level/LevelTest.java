package Classes.Level;

import Classes.Token.Token.Block;
import Classes.Token.Token.Token;
import Classes.Token.Token.TokenID;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LevelTest {

    @Test
    public void testGetTokens() {
        Set<Token> tokens = new HashSet<>();
        tokens.add(new Block(new TokenID("Block"), false));
        Level level = new Level(new Board(0, 0), new Board(0, 0), tokens, "Test Level");
        assertEquals(tokens, level.getTokens());
    }

    @Test
    public void testGetBoard() {
        Board board = new Board(0, 0);
        Level level = new Level(board, new Board(0, 0), new HashSet<>(), "Test Level");
        assertEquals(board, level.getBoard());
    }

    @Test
    public void testGetSolutionBoard() {
        Board solutionBoard = new Board(0, 0);
        Level level = new Level(new Board(0, 0), solutionBoard, new HashSet<>(), "Test Level");
        assertEquals(solutionBoard, level.getSolutionBoard());
    }
}
