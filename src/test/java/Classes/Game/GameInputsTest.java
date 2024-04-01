package Classes.Game;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;

public class GameInputsTest extends TestCase {
    public void testSelectLevel(){
        Game game = new Game();
        game.run();
        provideInput(String.valueOf(2));
        String response = GameInputs.selectLevel(game.getLevelData());
        assertEquals(response, "Level 2th with ID =Second Level is selected.");
    }

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
