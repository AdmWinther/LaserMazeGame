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
     *
     * @author Nathan Gromb
     */
    public Receiver() {
        super(false, Orientation.UP);
    }

    /**
     * Constructor for Receiver. Sets movable to false.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public Receiver(boolean movable, Orientation orientation) {
        super(movable, orientation);
    }

    /**
     * Propagate the lazer.
     *
     * @param from the orientation the lazer is coming from
     * @return the orientations the lazer is going to
     * @author Nathan Gromb
     */
    @Override
    public Set<Orientation> propagateLazer(Orientation from) {
        if (from == null) {
            throw new IllegalArgumentException("Orientation cannot be null");
        }

        return Collections.emptySet();
    }
}
