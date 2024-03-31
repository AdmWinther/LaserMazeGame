package Classes.Token;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;


public class BlockTest {
    @Test
    public void testBlockInitialState() {
        Token block = new Block(new TokenID("0"), false);

        assertFalse(block.isMovable());
    }

    @Test
    public void testBlockAfterSetMovable() {
        Token block = new Block(new TokenID("0"), false);
        block.setMovable(true);

        assertTrue(block.isMovable());
    }

    @Test
    public void testBlockPropagateLaser() {
        Block block = new Block(new TokenID("0"), false);

        assertEquals(Collections.emptySet(), block.propagateLaser(Orientation.UP));
        assertEquals(Collections.emptySet(), block.propagateLaser(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), block.propagateLaser(Orientation.DOWN));
        assertEquals(Collections.emptySet(), block.propagateLaser(Orientation.LEFT));
    }

    @Test
    public void testBlockPropagateLaserWithNullOrientation() {
        Block block = new Block(new TokenID("0"), false);

        assertThrows(IllegalArgumentException.class, () -> block.propagateLaser(null));
    }

    @Test
    public void testBlockId() {
        Token block = new Block(new TokenID("0"), false);
        assertEquals(0, block.id());
    }

    @Test
    public void testBlockToString() {
        Token block = new Block(new TokenID("0"), false);
        assertEquals("Block, id: 0, movable: false", block.toString());
    }

    @Test
    public void testBlockStrictlyEquals() {
        Token block = new Block(new TokenID("0"), false);
        Token block2 = new Block(new TokenID("0"), false);
        Token block3 = new Block(new TokenID("1"), false);
        Token block4 = new Block(new TokenID("0"), true);
        Token block5 = new Block(new TokenID("0"), false);
        block5.setMovable(true);

        assertTrue(block.strictlyEquals(block2));
        assertFalse(block.strictlyEquals(block3));
        assertFalse(block.strictlyEquals(block4));
        assertFalse(block.strictlyEquals(block5));
    }

    @Test
    public void testBlockEquals() {
        Token block = new Block(new TokenID("0"), false);
        Token block2 = new Block(new TokenID("0"), false);
        Token block3 = new Block(new TokenID("1"), false);
        Token block4 = new Block(new TokenID("0"), true);
        Token block5 = new Block(new TokenID("0"), false);
        block5.setMovable(true);

        assertEquals(block, block2);
        assertEquals(block, block3);
        assertEquals(block, block4);
        assertEquals(block, block5);
    }
}
