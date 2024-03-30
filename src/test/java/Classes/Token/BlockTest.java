package Classes.Token;

import java.util.Collections;

import org.junit.Test;
import static org.junit.Assert.*;


public class BlockTest  {
    @Test
    public void testBlockInitialState() {
        Token block = new Block();

        assertFalse(block.isMovable());
    }

    @Test
    public void testBlockAfterSetMovable() {
        Token block = new Block();
        block.setMovable(true);

        assertTrue(block.isMovable());
    }

    @Test
    public void testBlockPropagateLaser() {
        Block block = new Block();

        assertEquals(Collections.emptySet(), block.propagateLazer(Orientation.UP));
        assertEquals(Collections.emptySet(), block.propagateLazer(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), block.propagateLazer(Orientation.DOWN));
        assertEquals(Collections.emptySet(), block.propagateLazer(Orientation.LEFT));
    }

    @Test
    public void testBlockPropagateLaserWithNullOrientation() {
        Block block = new Block();

        assertThrows(IllegalArgumentException.class, () -> block.propagateLazer(null));
    }
}
