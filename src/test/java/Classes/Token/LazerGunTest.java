package Classes.Token;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class LazerGunTest {
    @Test
    public void testReceiveInitialState() {
        OrientedToken lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);

        assertEquals(Orientation.UP, lazergun.getOrientation());
        assertFalse(lazergun.isMovable());
    }

    @Test
    public void testLazerGunInitialStateWithParameters() {
        OrientedToken lazergun1 = new LaserGun(new TokenID("0"), false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, lazergun1.getOrientation());
        assertFalse(lazergun1.isMovable());

        OrientedToken lazergun2 = new LaserGun(new TokenID("1"), false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, lazergun2.getOrientation());
        assertFalse(lazergun2.isMovable());

        OrientedToken lazergun3 = new LaserGun(new TokenID("2"), false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, lazergun3.getOrientation());
        assertFalse(lazergun3.isMovable());

        OrientedToken lazergun4 = new LaserGun(new TokenID("3"), false, Orientation.UP);
        assertEquals(Orientation.UP, lazergun4.getOrientation());
        assertFalse(lazergun4.isMovable());
    }

    @Test
    public void testLazerGunAfterSetMovable() {
        OrientedToken lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);
        lazergun.setMovable(true);

        assertEquals(Orientation.UP, lazergun.getOrientation());
        assertTrue(lazergun.isMovable());
    }

    @Test
    public void testLazerGunPropagateLaser() {
        LaserGun lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);

        assertEquals(Collections.emptySet(), lazergun.propagateLaser(Orientation.UP));
        assertEquals(Collections.emptySet(), lazergun.propagateLaser(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), lazergun.propagateLaser(Orientation.DOWN));
        assertEquals(Collections.emptySet(), lazergun.propagateLaser(Orientation.LEFT));
    }

    @Test
    public void testLazerGunPropagateLaserWithNullOrientation() {
        LaserGun lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> lazergun.propagateLaser(null));
    }

    @Test
    public void testLazerGunInitialStateWithNullOrientation() {
        assertThrows(IllegalArgumentException.class, () -> new LaserGun(new TokenID("0"), false, null));
    }

    @Test
    public void testLazerGunSetOrientationWithNullOrientation() {
        OrientedToken lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);
        assertThrows(IllegalArgumentException.class, () -> lazergun.setOrientation(null));
    }

    @Test
    public void testLazerGunId() {
        OrientedToken lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);
        assertEquals(new TokenID("0"), lazergun.id());
    }

    @Test
    public void testLazerGunToString() {
        OrientedToken lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);
        assertEquals("LazerGun, id: 0, movable: false, orientation: UP", lazergun.toString());
    }

    @Test
    public void testLazerGunStrictlyEquals() {
        OrientedToken lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);
        OrientedToken lazergun2 = new LaserGun(new TokenID("0"), false, Orientation.UP);
        OrientedToken lazergun3 = new LaserGun(new TokenID("1"), false, Orientation.UP);
        OrientedToken lazergun4 = new LaserGun(new TokenID("0"), true, Orientation.UP);
        OrientedToken lazergun5 = new LaserGun(new TokenID("0"), false, Orientation.DOWN);

        assertTrue(lazergun.strictlyEquals(lazergun2));
        assertFalse(lazergun.strictlyEquals(lazergun3));
        assertFalse(lazergun.strictlyEquals(lazergun4));
        assertFalse(lazergun.strictlyEquals(lazergun5));
    }

    @Test
    public void testLazerGunEquals() {
        OrientedToken lazergun = new LaserGun(new TokenID("0"), false, Orientation.UP);
        OrientedToken lazergun2 = new LaserGun(new TokenID("0"), false, Orientation.UP);
        OrientedToken lazergun3 = new LaserGun(new TokenID("1"), false, Orientation.UP);
        OrientedToken lazergun4 = new LaserGun(new TokenID("0"), true, Orientation.UP);
        OrientedToken lazergun5 = new LaserGun(new TokenID("0"), false, Orientation.DOWN);

        assertEquals(lazergun, lazergun2);
        assertEquals(lazergun, lazergun3);
        assertEquals(lazergun, lazergun4);
        assertNotEquals(lazergun, lazergun5);
    }
}
