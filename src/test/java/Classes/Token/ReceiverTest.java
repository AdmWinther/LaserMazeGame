package Classes.Token;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Collections;

public class ReceiverTest {
    @Test
    public void testReceiveInitialState() {
        OrientedToken receiver = new Receiver(0, false, Orientation.UP);

        assertEquals(Orientation.UP, receiver.getOrientation());
        assertFalse(receiver.isMovable());
    }

    @Test
    public void testReceiverInitialStateWithParameters() {
        OrientedToken receiver1 = new Receiver(0, false, Orientation.RIGHT);
        assertEquals(Orientation.RIGHT, receiver1.getOrientation());
        assertFalse(receiver1.isMovable());

        OrientedToken receiver2 = new Receiver(1, false, Orientation.DOWN);
        assertEquals(Orientation.DOWN, receiver2.getOrientation());
        assertFalse(receiver2.isMovable());

        OrientedToken receiver3 = new Receiver(2, false, Orientation.LEFT);
        assertEquals(Orientation.LEFT, receiver3.getOrientation());
        assertFalse(receiver3.isMovable());

        OrientedToken receiver4 = new Receiver(3, false, Orientation.UP);
        assertEquals(Orientation.UP, receiver4.getOrientation());
        assertFalse(receiver4.isMovable());
    }

    @Test
    public void testReceiverAfterSetMovable() {
        OrientedToken receiver = new Receiver(0, false, Orientation.UP);
        receiver.setMovable(true);

        assertEquals(Orientation.UP, receiver.getOrientation());
        assertTrue(receiver.isMovable());
    }

    @Test
    public void testBlockPropagateLaser() {
        Receiver receiver = new Receiver(0, false, Orientation.UP);

        assertEquals(Collections.emptySet(), receiver.propagateLazer(Orientation.UP));
        assertEquals(Collections.emptySet(), receiver.propagateLazer(Orientation.RIGHT));
        assertEquals(Collections.emptySet(), receiver.propagateLazer(Orientation.DOWN));
        assertEquals(Collections.emptySet(), receiver.propagateLazer(Orientation.LEFT));
    }

    @Test
    public void testReceiverPropagateLaserWithNullOrientation() {
        Receiver receiver = new Receiver(0, false, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> receiver.propagateLazer(null));
    }

    @Test
    public void testReceiverInitialStateWithNullOrientation() {
        assertThrows(IllegalArgumentException.class, () -> new Receiver(0, false, null));
    }

    @Test
    public void testReceiverSetOrientationWithNullOrientation() {
        Receiver receiver = new Receiver(0, false, Orientation.UP);

        assertThrows(IllegalArgumentException.class, () -> receiver.setOrientation(null));
    }

    @Test
    public void testReceiverId() {
        Token receiver = new Receiver(0, false, Orientation.UP);
        assertEquals(0, receiver.id());
    }
}