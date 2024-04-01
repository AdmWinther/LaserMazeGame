package Classes.Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class GameTest extends TestCase {

    public void testRun(){
        Game game = new Game();
        game.run();
        assertNotNull(game.getLevelData());
    }
}


