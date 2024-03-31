package Classes.Token;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;


public class DoubleSidedMirrorTest {
    @Test
    public void testDoubleSidedMirrorInitialState() {
        OrientedToken doubleSidedMirror = new DoubleSidedMirror();

        Assert.assertEquals(Orientation.UP, doubleSidedMirror.getOrientation());
        assertTrue(doubleSidedMirror.isMovable());
    }

    @Test
    public void testDoubleSidedMirrorInitialStateWithParameters() {
        OrientedToken doubleSidedMirror1 = new DoubleSidedMirror(true, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, doubleSidedMirror1.getOrientation());
        assertTrue(doubleSidedMirror1.isMovable());

        OrientedToken doubleSidedMirror2 = new DoubleSidedMirror(true, Orientation.DOWN);
        assertEquals(Orientation.DOWN, doubleSidedMirror2.getOrientation());
        assertTrue(doubleSidedMirror2.isMovable());

        OrientedToken doubleSidedMirror3 = new DoubleSidedMirror(true, Orientation.LEFT);
        assertEquals(Orientation.LEFT, doubleSidedMirror3.getOrientation());
        assertTrue(doubleSidedMirror3.isMovable());

        OrientedToken doubleSidedMirror4 = new DoubleSidedMirror(true, Orientation.UP);
        assertEquals(Orientation.UP, doubleSidedMirror4.getOrientation());
        assertTrue(doubleSidedMirror4.isMovable());

        OrientedToken doubleSidedMirror5 = new DoubleSidedMirror(false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, doubleSidedMirror5.getOrientation());
        assertFalse(doubleSidedMirror5.isMovable());

        OrientedToken doubleSidedMirror6 = new DoubleSidedMirror(false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, doubleSidedMirror6.getOrientation());
        assertFalse(doubleSidedMirror6.isMovable());

        OrientedToken doubleSidedMirror7 = new DoubleSidedMirror(false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, doubleSidedMirror7.getOrientation());
        assertFalse(doubleSidedMirror7.isMovable());

        OrientedToken doubleSidedMirror8 = new DoubleSidedMirror(false, Orientation.UP);
        assertEquals(Orientation.UP, doubleSidedMirror8.getOrientation());
        assertFalse(doubleSidedMirror8.isMovable());
    }

    @Test
    public void testDoubleSidedMirrorPropagateLaser() {
        DoubleSidedMirror doubleSidedMirror1 = new DoubleSidedMirror(false, Orientation.UP);
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror1.propagateLaser(Orientation.DOWN));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror1.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror1.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror1.propagateLaser(Orientation.LEFT));

        DoubleSidedMirror doubleSidedMirror2 = new DoubleSidedMirror(false, Orientation.RIGHT);
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror2.propagateLaser(Orientation.DOWN));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror2.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror2.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror2.propagateLaser(Orientation.UP));

        DoubleSidedMirror doubleSidedMirror3 = new DoubleSidedMirror(false, Orientation.DOWN);
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror3.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror3.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror3.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror3.propagateLaser(Orientation.DOWN));

        DoubleSidedMirror doubleSidedMirror4 = new DoubleSidedMirror(false, Orientation.LEFT);
        assertEquals(Set.of(Orientation.UP), doubleSidedMirror4.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.DOWN), doubleSidedMirror4.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.RIGHT), doubleSidedMirror4.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.LEFT), doubleSidedMirror4.propagateLaser(Orientation.DOWN));
    }
}
