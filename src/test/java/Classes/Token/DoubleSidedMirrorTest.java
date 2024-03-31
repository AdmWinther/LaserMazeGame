package Classes.Token;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;


public class DoubleSidedMirrorTest {
    @Test
    public void testDoubleSidedMirrorInitialState() {
        OrientedToken doubleSidedMirror = new DoubleSidedMirror(new TokenID("0"), true, Orientation.UP);

        Assert.assertEquals(Orientation.UP, doubleSidedMirror.getOrientation());
        assertTrue(doubleSidedMirror.isMovable());
    }

    @Test
    public void testDoubleSidedMirrorInitialStateWithParameters() {
        OrientedToken doubleSidedMirror1 = new DoubleSidedMirror(new TokenID("0"), true, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, doubleSidedMirror1.getOrientation());
        assertTrue(doubleSidedMirror1.isMovable());

        OrientedToken doubleSidedMirror2 = new DoubleSidedMirror(new TokenID("1"), true, Orientation.DOWN);
        assertEquals(Orientation.DOWN, doubleSidedMirror2.getOrientation());
        assertTrue(doubleSidedMirror2.isMovable());

        OrientedToken doubleSidedMirror3 = new DoubleSidedMirror(new TokenID("2"), true, Orientation.LEFT);
        assertEquals(Orientation.LEFT, doubleSidedMirror3.getOrientation());
        assertTrue(doubleSidedMirror3.isMovable());

        OrientedToken doubleSidedMirror4 = new DoubleSidedMirror(new TokenID("3"), true, Orientation.UP);
        assertEquals(Orientation.UP, doubleSidedMirror4.getOrientation());
        assertTrue(doubleSidedMirror4.isMovable());

        OrientedToken doubleSidedMirror5 = new DoubleSidedMirror(new TokenID("4"), false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, doubleSidedMirror5.getOrientation());
        assertFalse(doubleSidedMirror5.isMovable());

        OrientedToken doubleSidedMirror6 = new DoubleSidedMirror(new TokenID("5"), false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, doubleSidedMirror6.getOrientation());
        assertFalse(doubleSidedMirror6.isMovable());

        OrientedToken doubleSidedMirror7 = new DoubleSidedMirror(new TokenID("6"), false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, doubleSidedMirror7.getOrientation());
        assertFalse(doubleSidedMirror7.isMovable());

        OrientedToken doubleSidedMirror8 = new DoubleSidedMirror(new TokenID("7"), false, Orientation.UP);
        assertEquals(Orientation.UP, doubleSidedMirror8.getOrientation());
        assertFalse(doubleSidedMirror8.isMovable());
    }

    @Test
    public void testDoubleSidedMirrorPropagateLaser() {
        DoubleSidedMirror doubleSidedMirror1 = new DoubleSidedMirror(new TokenID("0"), false, Orientation.UP);
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror1.propagateLaser(Orientation.DOWN));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror1.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror1.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror1.propagateLaser(Orientation.LEFT));

        DoubleSidedMirror doubleSidedMirror2 = new DoubleSidedMirror(new TokenID("1"), false, Orientation.RIGHT);
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror2.propagateLaser(Orientation.DOWN));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror2.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror2.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror2.propagateLaser(Orientation.UP));

        DoubleSidedMirror doubleSidedMirror3 = new DoubleSidedMirror(new TokenID("2"), false, Orientation.DOWN);
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror3.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror3.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror3.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror3.propagateLaser(Orientation.DOWN));

        DoubleSidedMirror doubleSidedMirror4 = new DoubleSidedMirror(new TokenID("3"), false, Orientation.LEFT);
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror4.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror4.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror4.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror4.propagateLaser(Orientation.DOWN));
    }

    @Test
    public void testDoubleSidedMirrorPropagateLaserWithNullOrientation() {
        DoubleSidedMirror doubleSidedMirror = new DoubleSidedMirror(new TokenID("0"), false, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> doubleSidedMirror.propagateLaser(null));
    }

    @Test
    public void testDoubleSidedMirrorInitialStateWithNullOrientation() {
        assertThrows(IllegalArgumentException.class, () -> new DoubleSidedMirror(new TokenID("0"), true, null));
    }

    @Test
    public void testDoubleSidedMirrorSetOrientationWithNullOrientation() {
        DoubleSidedMirror doubleSidedMirror = new DoubleSidedMirror(new TokenID("0"), true, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> doubleSidedMirror.setOrientation(null));
    }

    @Test
    public void testDoubleSidedMirrorId() {
        Token doubleSidedMirror = new DoubleSidedMirror(new TokenID("0"), false, Orientation.UP);
        assertEquals(0, doubleSidedMirror.id());
    }

    @Test
    public void testDoubleSidedMirrorToString() {
        Token doubleSidedMirror = new DoubleSidedMirror(new TokenID("0"), false, Orientation.UP);
        assertEquals("DoubleSidedMirror, id: 0, movable: false, orientation: UP", doubleSidedMirror.toString());
    }

    @Test
    public void testDoubleSidedMirrorStrictlyEquals() {
        Token doubleSidedMirror = new DoubleSidedMirror(new TokenID("0"), false, Orientation.UP);
        Token doubleSidedMirror2 = new DoubleSidedMirror(new TokenID("0"), false, Orientation.UP);
        Token doubleSidedMirror3 = new DoubleSidedMirror(new TokenID("1"), false, Orientation.UP);
        Token doubleSidedMirror4 = new DoubleSidedMirror(new TokenID("0"), true, Orientation.UP);
        Token doubleSidedMirror5 = new DoubleSidedMirror(new TokenID("0"), false, Orientation.DOWN);

        assertTrue(doubleSidedMirror.strictlyEquals(doubleSidedMirror2));
        assertFalse(doubleSidedMirror.strictlyEquals(doubleSidedMirror3));
        assertFalse(doubleSidedMirror.strictlyEquals(doubleSidedMirror4));
        assertFalse(doubleSidedMirror.strictlyEquals(doubleSidedMirror5));
    }

    @Test
    public void testDoubleSidedMirrorEquals() {
        Token doubleSidedMirror = new DoubleSidedMirror(new TokenID("0"), false, Orientation.UP);
        Token doubleSidedMirror2 = new DoubleSidedMirror(new TokenID("0"), false, Orientation.UP);
        Token doubleSidedMirror3 = new DoubleSidedMirror(new TokenID("1"), false, Orientation.UP);
        Token doubleSidedMirror4 = new DoubleSidedMirror(new TokenID("0"), true, Orientation.UP);
        Token doubleSidedMirror5 = new DoubleSidedMirror(new TokenID("0"), false, Orientation.DOWN);
        Token doubleSidedMirror6 = new DoubleSidedMirror(new TokenID("0"), false, Orientation.LEFT);

        assertEquals(doubleSidedMirror, doubleSidedMirror2);
        assertEquals(doubleSidedMirror, doubleSidedMirror3);
        assertEquals(doubleSidedMirror, doubleSidedMirror4);
        assertEquals(doubleSidedMirror, doubleSidedMirror5);
        assertNotEquals(doubleSidedMirror, doubleSidedMirror6);
    }
}
