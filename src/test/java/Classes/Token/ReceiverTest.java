package Classes.Token;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collections;

public class ReceiverTest {
    @Test
    public void testReceiveInitialState() {
        OrientedToken receiver = new Receiver();

        assertEquals(Orientation.UP, receiver.getOrientation());
        assertFalse(receiver.isMovable());
    }

    @Test
    public void testReceiverInitialStateWithParameters() {
        OrientedToken receiver1 = new Receiver(false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, receiver1.getOrientation());
        assertFalse(receiver1.isMovable());

        OrientedToken receiver2 = new Receiver(false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, receiver2.getOrientation());
        assertFalse(receiver2.isMovable());

        OrientedToken receiver3 = new Receiver(false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, receiver3.getOrientation());
        assertFalse(receiver3.isMovable());

        OrientedToken receiver4 = new Receiver(false, Orientation.UP);
        assertEquals(Orientation.UP, receiver4.getOrientation());
        assertFalse(receiver4.isMovable());
    }

    @Test
    public void testReceiverAfterSetMovable() {
        OrientedToken receiver = new Receiver();
        receiver.setMovable(true);

        assertEquals(Orientation.UP, receiver.getOrientation());
        assertTrue(receiver.isMovable());
    }

    @Test
    public void testBlockPropagateLaser() {
        Receiver receiver = new Receiver();

        assertEquals(Collections.emptySet(), receiver.propagateLaser(Orientation.UP));
        assertEquals(Collections.emptySet(), receiver.propagateLaser(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), receiver.propagateLaser(Orientation.DOWN));
        assertEquals(Collections.emptySet(), receiver.propagateLaser(Orientation.LEFT));
    }
}