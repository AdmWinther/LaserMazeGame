package Classes.Token;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class LazerGunTest {
    @Test
    public void testReceiveInitialState() {
        OrientedToken lazergun = new LaserGun();

        Assert.assertEquals(Orientation.UP, lazergun.getOrientation());
        assertFalse(lazergun.isMovable());
    }

    @Test
    public void testLazerGunInitialStateWithParameters() {
        OrientedToken lazergun1 = new LaserGun(false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, lazergun1.getOrientation());
        assertFalse(lazergun1.isMovable());

        OrientedToken lazergun2 = new LaserGun(false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, lazergun2.getOrientation());
        assertFalse(lazergun2.isMovable());

        OrientedToken lazergun3 = new LaserGun(false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, lazergun3.getOrientation());
        assertFalse(lazergun3.isMovable());

        OrientedToken lazergun4 = new LaserGun(false, Orientation.UP);
        assertEquals(Orientation.UP, lazergun4.getOrientation());
        assertFalse(lazergun4.isMovable());
    }

    @Test
    public void testLazerGunAfterSetMovable() {
        OrientedToken lazergun = new LaserGun();
        lazergun.setMovable(true);

        assertEquals(Orientation.UP, lazergun.getOrientation());
        assertTrue(lazergun.isMovable());
    }

    @Test
    public void testBlockPropagateLaser() {
        LaserGun lazergun = new LaserGun();

        assertEquals(Collections.emptySet(), lazergun.propagateLaser(Orientation.UP));
        assertEquals(Collections.emptySet(), lazergun.propagateLaser(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), lazergun.propagateLaser(Orientation.DOWN));
        assertEquals(Collections.emptySet(), lazergun.propagateLaser(Orientation.LEFT));
    }
}
