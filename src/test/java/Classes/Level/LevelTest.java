package java.Classes.Level;

import org.junit.Test;

import java.Classes.Lazer.Lazer;
import java.Classes.Token.Block;
import java.Classes.Token.Token;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LevelTest {

    @Test
    public void testGetTokens() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Block());
        Level level = new Level(new Board(), new Board(), tokens, "Test Level");
        assertEquals(tokens, level.getTokens());
    }

    @Test
    public void testGetBoard() {
        Board board = new Board();
        Level level = new Level(board, new Board(), new ArrayList<>(), "Test Level");
        assertEquals(board, level.getBoard());
    }

    @Test
    public void testGetSolutionBoard() {
        Board solutionBoard = new Board();
        Level level = new Level(new Board(), solutionBoard, new ArrayList<>(), "Test Level");
        assertEquals(solutionBoard, level.getSolutionBoard());
    }

    @Test
    public void testSetLazer() {
        Lazer lazer = new Lazer();
        Level level = new Level(new Board(), new Board(), new ArrayList<>(), "Test Level");
        level.setLazer(lazer);
        assertEquals(lazer, level.getLazer());
    }

    @Test
    public void testGetLazer() {
        Lazer lazer = new Lazer();
        Level level = new Level(new Board(), new Board(), new ArrayList<>(), "Test Level");
        level.setLazer(lazer);
        assertEquals(lazer, level.getLazer());
    }
}
