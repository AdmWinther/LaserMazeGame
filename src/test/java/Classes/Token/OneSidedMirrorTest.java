package Classes.Token;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;

public class OneSidedMirrorTest {
    @Test
    public void testOneSidedMirrorInitialState() {
        OrientedToken oneSidedMirror = new OneSidedMirror();

        Assert.assertEquals(Orientation.UP, oneSidedMirror.getOrientation());
        assertTrue(oneSidedMirror.isMovable());
    }

    @Test
    public void testOneSidedMirrorInitialStateWithParameters() {
        OrientedToken oneSidedMirror1 = new OneSidedMirror(true, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, oneSidedMirror1.getOrientation());
        assertTrue(oneSidedMirror1.isMovable());

        OrientedToken oneSidedMirror2 = new OneSidedMirror(true, Orientation.DOWN);
        assertEquals(Orientation.DOWN, oneSidedMirror2.getOrientation());
        assertTrue(oneSidedMirror2.isMovable());

        OrientedToken oneSidedMirror3 = new OneSidedMirror(true, Orientation.LEFT);
        assertEquals(Orientation.LEFT, oneSidedMirror3.getOrientation());
        assertTrue(oneSidedMirror3.isMovable());

        OrientedToken oneSidedMirror4 = new OneSidedMirror(true, Orientation.UP);
        assertEquals(Orientation.UP, oneSidedMirror4.getOrientation());
        assertTrue(oneSidedMirror4.isMovable());

        OrientedToken oneSidedMirror5 = new OneSidedMirror(false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, oneSidedMirror5.getOrientation());
        assertFalse(oneSidedMirror5.isMovable());

        OrientedToken oneSidedMirror6 = new OneSidedMirror(false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, oneSidedMirror6.getOrientation());
        assertFalse(oneSidedMirror6.isMovable());

        OrientedToken oneSidedMirror7 = new OneSidedMirror(false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, oneSidedMirror7.getOrientation());
        assertFalse(oneSidedMirror7.isMovable());

        OrientedToken oneSidedMirror8 = new OneSidedMirror(false, Orientation.UP);
        assertEquals(Orientation.UP, oneSidedMirror8.getOrientation());
        assertFalse(oneSidedMirror8.isMovable());
    }

    @Test
    public void testOneSidedMirrorPropagateLaser() {
        OneSidedMirror oneSidedMirror1 = new OneSidedMirror(false, Orientation.UP);
        assertEquals(Collections.emptySet(), oneSidedMirror1.propagateLaser(Orientation.DOWN));
        assertEquals(Collections.emptySet(), oneSidedMirror1.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.LEFT), oneSidedMirror1.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.UP), oneSidedMirror1.propagateLaser(Orientation.LEFT));

        OneSidedMirror oneSidedMirror2 = new OneSidedMirror(false, Orientation.RIGHT);
        assertEquals(Collections.emptySet(), oneSidedMirror2.propagateLaser(Orientation.DOWN));
        assertEquals(Collections.emptySet(), oneSidedMirror2.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.UP), oneSidedMirror2.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), oneSidedMirror2.propagateLaser(Orientation.UP));

        OneSidedMirror oneSidedMirror3 = new OneSidedMirror(false, Orientation.DOWN);
        assertEquals(Collections.emptySet(), oneSidedMirror3.propagateLaser(Orientation.UP));
        assertEquals(Collections.emptySet(), oneSidedMirror3.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.DOWN), oneSidedMirror3.propagateLaser(Orientation.RIGHT));
        assertEquals(Set.of(Orientation.RIGHT), oneSidedMirror3.propagateLaser(Orientation.DOWN));

        OneSidedMirror oneSidedMirror4 = new OneSidedMirror(false, Orientation.LEFT);
        assertEquals(Collections.emptySet(), oneSidedMirror4.propagateLaser(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), oneSidedMirror4.propagateLaser(Orientation.UP));
        assertEquals(Set.of(Orientation.DOWN), oneSidedMirror4.propagateLaser(Orientation.LEFT));
        assertEquals(Set.of(Orientation.LEFT), oneSidedMirror4.propagateLaser(Orientation.DOWN));
    }
}
