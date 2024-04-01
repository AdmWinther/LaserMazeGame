package Classes.Game;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class GameTest extends TestCase {
    public void testRun(){
        Game game = new Game();
        game.run();
        int levelCount = game.getLevelData().size();
        System.out.println(levelCount);
        assertEquals(levelCount, 5);
    }
}


