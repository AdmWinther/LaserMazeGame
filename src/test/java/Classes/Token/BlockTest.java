package Classes.Token;

import java.util.Collections;

import org.junit.Test;

import static org.junit.Assert.*;


public class BlockTest {
    @Test
    public void testBlockInitialState() {
        Token block = new Block(0, false);

        assertFalse(block.isMovable());
    }

    @Test
    public void testBlockAfterSetMovable() {
        Token block = new Block(0, false);
        block.setMovable(true);

        assertTrue(block.isMovable());
    }

    @Test
    public void testBlockPropagateLaser() {
        Block block = new Block(0, false);

        assertEquals(Collections.emptySet(), block.propagateLazer(Orientation.UP));
        assertEquals(Collections.emptySet(), block.propagateLazer(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), block.propagateLazer(Orientation.DOWN));
        assertEquals(Collections.emptySet(), block.propagateLazer(Orientation.LEFT));
    }

    @Test
    public void testBlockPropagateLaserWithNullOrientation() {
        Block block = new Block(0, false);

        assertThrows(IllegalArgumentException.class, () -> block.propagateLazer(null));
    }

    @Test
    public void testBlockId() {
        Token block = new Block(0, false);
        assertEquals(0, block.id());
    }

    @Test
    public void testBlockToString() {
        Token block = new Block(0, false);
        assertEquals("Block, id: 0, movable: false", block.toString());
    }

    @Test
    public void testBlockStrictlyEquals() {
        Token block = new Block(0, false);
        Token block2 = new Block(0, false);
        Token block3 = new Block(1, false);
        Token block4 = new Block(0, true);
        Token block5 = new Block(0, false);
        block5.setMovable(true);

        assertTrue(block.strictlyEquals(block2));
        assertFalse(block.strictlyEquals(block3));
        assertFalse(block.strictlyEquals(block4));
        assertFalse(block.strictlyEquals(block5));
    }

    @Test
    public void testBlockEquals() {
        Token block = new Block(0, false);
        Token block2 = new Block(0, false);
        Token block3 = new Block(1, false);
        Token block4 = new Block(0, true);
        Token block5 = new Block(0, false);
        block5.setMovable(true);

        assertTrue(block.equals(block2));
        assertTrue(block.equals(block3));
        assertTrue(block.equals(block4));
        assertTrue(block.equals(block5));
    }
}
