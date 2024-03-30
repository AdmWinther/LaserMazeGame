package Classes.Token;

import java.util.Collections;
import java.util.Set;

/**
 * Receiver is a class that represents an objective in the game.
 * A receiver can never be moved.
 */
public final class Receiver extends OrientedToken {

    /**
     * Constructor for Receiver.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public Receiver(int id, boolean movable, Orientation orientation) {
        super(id, movable, orientation);
    }

    /**
     * Returns a string representation of the receiver.
     *
     * @return a string representation of the receiver
     * @author Nathan Gromb
     */
    @Override
    public String toString() {
        return "Receiver, id: %d, movable: %b, orientation: %s".formatted(id(), isMovable(), getOrientation());
    }

    /**
     * Returns whether the receiver is equal to another object.
     *
     * @param o the object to compare to
     * @return whether the receiver is equal to the object
     * @author Nathan Gromb
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receiver)) return false;
        Receiver receiver = (Receiver) o;
        return id() == receiver.id() && isMovable() == receiver.isMovable()
                && getOrientation() == receiver.getOrientation();
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
