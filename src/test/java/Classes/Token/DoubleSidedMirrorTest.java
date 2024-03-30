package Classes.Token;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Set;


public class DoubleSidedMirrorTest {
    @Test
    public void testDoubleSidedMirrorInitialState() {
        OrientedToken doubleSidedMirror = new DoubleSidedMirror(0, true, Orientation.UP);

        assertEquals(Orientation.UP, doubleSidedMirror.getOrientation());
        assertTrue(doubleSidedMirror.isMovable());
    }

    @Test
    public void testDoubleSidedMirrorInitialStateWithParameters() {
        OrientedToken doubleSidedMirror1 = new DoubleSidedMirror(0, true, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, doubleSidedMirror1.getOrientation());
        assertTrue(doubleSidedMirror1.isMovable());

        OrientedToken doubleSidedMirror2 = new DoubleSidedMirror(1, true, Orientation.DOWN);
        assertEquals(Orientation.DOWN, doubleSidedMirror2.getOrientation());
        assertTrue(doubleSidedMirror2.isMovable());

        OrientedToken doubleSidedMirror3 = new DoubleSidedMirror(2, true, Orientation.LEFT);
        assertEquals(Orientation.LEFT, doubleSidedMirror3.getOrientation());
        assertTrue(doubleSidedMirror3.isMovable());

        OrientedToken doubleSidedMirror4 = new DoubleSidedMirror(3, true, Orientation.UP);
        assertEquals(Orientation.UP, doubleSidedMirror4.getOrientation());
        assertTrue(doubleSidedMirror4.isMovable());

        OrientedToken doubleSidedMirror5 = new DoubleSidedMirror(4, false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, doubleSidedMirror5.getOrientation());
        assertFalse(doubleSidedMirror5.isMovable());

        OrientedToken doubleSidedMirror6 = new DoubleSidedMirror(5, false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, doubleSidedMirror6.getOrientation());
        assertFalse(doubleSidedMirror6.isMovable());

        OrientedToken doubleSidedMirror7 = new DoubleSidedMirror(6, false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, doubleSidedMirror7.getOrientation());
        assertFalse(doubleSidedMirror7.isMovable());

        OrientedToken doubleSidedMirror8 = new DoubleSidedMirror(7, false, Orientation.UP);
        assertEquals(Orientation.UP, doubleSidedMirror8.getOrientation());
        assertFalse(doubleSidedMirror8.isMovable());
    }

    @Test
    public void testDoubleSidedMirrorPropagateLaser() {
        DoubleSidedMirror doubleSidedMirror1 = new DoubleSidedMirror(0, false, Orientation.UP);
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror1.propagateLazer(Orientation.DOWN));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror1.propagateLazer(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror1.propagateLazer(Orientation.UP));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror1.propagateLazer(Orientation.LEFT));

        DoubleSidedMirror doubleSidedMirror2 = new DoubleSidedMirror(1, false, Orientation.RIGHT);
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror2.propagateLazer(Orientation.DOWN));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror2.propagateLazer(Orientation.LEFT));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror2.propagateLazer(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror2.propagateLazer(Orientation.UP));

        DoubleSidedMirror doubleSidedMirror3 = new DoubleSidedMirror(2, false, Orientation.DOWN);
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror3.propagateLazer(Orientation.UP));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror3.propagateLazer(Orientation.LEFT));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror3.propagateLazer(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror3.propagateLazer(Orientation.DOWN));

        DoubleSidedMirror doubleSidedMirror4 = new DoubleSidedMirror(3, false, Orientation.LEFT);
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror4.propagateLazer(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror4.propagateLazer(Orientation.LEFT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror4.propagateLazer(Orientation.UP));
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror4.propagateLazer(Orientation.DOWN));
    }

    @Test
    public void testDoubleSidedMirrorPropagateLaserWithNullOrientation() {
        DoubleSidedMirror doubleSidedMirror = new DoubleSidedMirror(0, false, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> doubleSidedMirror.propagateLazer(null));
    }

    @Test
    public void testDoubleSidedMirrorInitialStateWithNullOrientation() {
        assertThrows(IllegalArgumentException.class, () -> new DoubleSidedMirror(0, true, null));
    }

    @Test
    public void testDoubleSidedMirrorSetOrientationWithNullOrientation() {
        DoubleSidedMirror doubleSidedMirror = new DoubleSidedMirror(0, true, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> doubleSidedMirror.setOrientation(null));
    }

    @Test
    public void testDoubleSidedMirrorId() {
        Token doubleSidedMirror = new DoubleSidedMirror(0, false, Orientation.UP);
        assertEquals(0, doubleSidedMirror.id());
    }
}
