package Classes.Token;

import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;

public class OneSidedMirrorTest {
    @Test
    public void testOneSidedMirrorInitialState() {
        OrientedToken oneSidedMirror = new OneSidedMirror(new TokenID("0"), true, Orientation.UP);

        assertEquals(Orientation.UP, oneSidedMirror.getOrientation());
        assertTrue(oneSidedMirror.isMovable());
    }

    @Test
    public void testOneSidedMirrorInitialStateWithParameters() {
        OrientedToken oneSidedMirror1 = new OneSidedMirror(new TokenID("0"), true, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, oneSidedMirror1.getOrientation());
        assertTrue(oneSidedMirror1.isMovable());

        OrientedToken oneSidedMirror2 = new OneSidedMirror(new TokenID("1"), true, Orientation.DOWN);
        assertEquals(Orientation.DOWN, oneSidedMirror2.getOrientation());
        assertTrue(oneSidedMirror2.isMovable());

        OrientedToken oneSidedMirror3 = new OneSidedMirror(new TokenID("2"), true, Orientation.LEFT);
        assertEquals(Orientation.LEFT, oneSidedMirror3.getOrientation());
        assertTrue(oneSidedMirror3.isMovable());

        OrientedToken oneSidedMirror4 = new OneSidedMirror(new TokenID("3"), true, Orientation.UP);
        assertEquals(Orientation.UP, oneSidedMirror4.getOrientation());
        assertTrue(oneSidedMirror4.isMovable());

        OrientedToken oneSidedMirror5 = new OneSidedMirror(new TokenID("4"), false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, oneSidedMirror5.getOrientation());
        assertFalse(oneSidedMirror5.isMovable());

        OrientedToken oneSidedMirror6 = new OneSidedMirror(new TokenID("5"), false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, oneSidedMirror6.getOrientation());
        assertFalse(oneSidedMirror6.isMovable());

        OrientedToken oneSidedMirror7 = new OneSidedMirror(new TokenID("6"), false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, oneSidedMirror7.getOrientation());
        assertFalse(oneSidedMirror7.isMovable());

        OrientedToken oneSidedMirror8 = new OneSidedMirror(new TokenID("7"), false, Orientation.UP);
        assertEquals(Orientation.UP, oneSidedMirror8.getOrientation());
        assertFalse(oneSidedMirror8.isMovable());
    }

    @Test
    public void testOneSidedMirrorPropagateLaser() {
        OneSidedMirror oneSidedMirror1 = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);
        assertEquals(Collections.emptySet(), oneSidedMirror1.propagateLaser(Orientation.DOWN));
        assertEquals(Collections.emptySet(), oneSidedMirror1.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.LEFT), oneSidedMirror1.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.UP), oneSidedMirror1.propagateLaser(Orientation.LEFT));

        OneSidedMirror oneSidedMirror2 = new OneSidedMirror(new TokenID("1"), false, Orientation.RIGHT);
        assertEquals(Collections.emptySet(), oneSidedMirror2.propagateLaser(Orientation.DOWN));
        assertEquals(Collections.emptySet(), oneSidedMirror2.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.UP), oneSidedMirror2.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), oneSidedMirror2.propagateLaser(Orientation.UP));

        OneSidedMirror oneSidedMirror3 = new OneSidedMirror(new TokenID("2"), false, Orientation.DOWN);
        assertEquals(Collections.emptySet(), oneSidedMirror3.propagateLaser(Orientation.UP));
        assertEquals(Collections.emptySet(), oneSidedMirror3.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.DOWN), oneSidedMirror3.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), oneSidedMirror3.propagateLaser(Orientation.DOWN));

        OneSidedMirror oneSidedMirror4 = new OneSidedMirror(new TokenID("3"), false, Orientation.LEFT);
        assertEquals(Collections.emptySet(), oneSidedMirror4.propagateLaser(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), oneSidedMirror4.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.DOWN), oneSidedMirror4.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.LEFT), oneSidedMirror4.propagateLaser(Orientation.DOWN));
    }

    @Test
    public void testOneSidedMirrorPropagateLaserWithNullOrientation() {
        OneSidedMirror oneSidedMirror = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> oneSidedMirror.propagateLaser(null));
    }

    @Test
    public void testOneSidedMirrorInitialStateWithNullOrientation() {
        assertThrows(IllegalArgumentException.class, () -> new OneSidedMirror(new TokenID("0"), false, null));
    }

    @Test
    public void testOneSidedMirrorSetOrientationWithNullOrientation() {
        OneSidedMirror oneSidedMirror = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> oneSidedMirror.setOrientation(null));
    }

    @Test
    public void testOneSidedMirrorId() {
        OrientedToken oneSidedMirror = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);
        assertEquals(new TokenID("0"), oneSidedMirror.id());
    }

    @Test
    public void testOneSidedMirrorToString() {
        OrientedToken oneSidedMirror = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);
        assertEquals("OneSidedMirror, id: 0, movable: false, orientation: UP", oneSidedMirror.toString());
    }

    @Test
    public void testOneSidedMirrorStrictlyEquals() {
        OrientedToken oneSidedMirror = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);
        OrientedToken oneSidedMirror2 = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);
        OrientedToken oneSidedMirror3 = new OneSidedMirror(new TokenID("1"), false, Orientation.UP);
        OrientedToken oneSidedMirror4 = new OneSidedMirror(new TokenID("0"), true, Orientation.UP);
        OrientedToken oneSidedMirror5 = new OneSidedMirror(new TokenID("0"), false, Orientation.DOWN);

        assertTrue(oneSidedMirror.strictlyEquals(oneSidedMirror2));
        assertFalse(oneSidedMirror.strictlyEquals(oneSidedMirror3));
        assertFalse(oneSidedMirror.strictlyEquals(oneSidedMirror4));
        assertFalse(oneSidedMirror.strictlyEquals(oneSidedMirror5));
    }

    @Test
    public void testOneSidedMirrorEquals() {
        OrientedToken oneSidedMirror = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);
        OrientedToken oneSidedMirror2 = new OneSidedMirror(new TokenID("0"), false, Orientation.UP);
        OrientedToken oneSidedMirror3 = new OneSidedMirror(new TokenID("1"), false, Orientation.UP);
        OrientedToken oneSidedMirror4 = new OneSidedMirror(new TokenID("0"), true, Orientation.UP);
        OrientedToken oneSidedMirror5 = new OneSidedMirror(new TokenID("0"), false, Orientation.DOWN);

        assertEquals(oneSidedMirror, oneSidedMirror2);
        assertEquals(oneSidedMirror, oneSidedMirror3);
        assertEquals(oneSidedMirror, oneSidedMirror4);
        assertNotEquals(oneSidedMirror, oneSidedMirror5);
    }
}
