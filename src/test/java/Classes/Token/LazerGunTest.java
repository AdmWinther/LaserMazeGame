package Classes.Token;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class LazerGunTest {
    @Test
    public void testReceiveInitialState() {
        OrientedToken lazergun = new LazerGun();

        Assert.assertEquals(Orientation.UP, lazergun.getOrientation());
        assertFalse(lazergun.isMovable());
    }

    @Test
    public void testLazerGunInitialStateWithParameters() {
        OrientedToken lazergun1 = new LazerGun(false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, lazergun1.getOrientation());
        assertFalse(lazergun1.isMovable());

        OrientedToken lazergun2 = new LazerGun(false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, lazergun2.getOrientation());
        assertFalse(lazergun2.isMovable());

        OrientedToken lazergun3 = new LazerGun(false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, lazergun3.getOrientation());
        assertFalse(lazergun3.isMovable());

        OrientedToken lazergun4 = new LazerGun(false, Orientation.UP);
        assertEquals(Orientation.UP, lazergun4.getOrientation());
        assertFalse(lazergun4.isMovable());
    }

    @Test
    public void testLazerGunAfterSetMovable() {
        OrientedToken lazergun = new LazerGun();
        lazergun.setMovable(true);

        assertEquals(Orientation.UP, lazergun.getOrientation());
        assertTrue(lazergun.isMovable());
    }

    @Test
    public void testBlockPropagateLaser() {
        LazerGun lazergun = new LazerGun();

        assertEquals(Collections.emptySet(), lazergun.propagateLazer(Orientation.UP));
        assertEquals(Collections.emptySet(), lazergun.propagateLazer(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), lazergun.propagateLazer(Orientation.DOWN));
        assertEquals(Collections.emptySet(), lazergun.propagateLazer(Orientation.LEFT));
    }
}
