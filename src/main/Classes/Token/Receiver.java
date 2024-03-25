package Classes.Token;

import java.util.Collections;
import java.util.Set;

/**
 * Receiver is a class that represents an objective in the game.
 * A receiver can never be moved.
 */
public class Receiver extends OrientedToken {

    /**
     * Default constructor for Receiver. Sets movable to false and orientation to UP.
     */
    public Receiver() {
        super(false, Orientation.UP);
    }

    /**
     * Constructor for Receiver. Sets movable to false.
     *
     * @param orientation the orientation of the token
     */
    public Receiver(Orientation orientation) {
        super(false, orientation);
    }

    @Override
    public Set<Orientation> propagateLazer(Orientation from) {
        return Collections.emptySet();
    }
}
