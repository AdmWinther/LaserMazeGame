package Classes.Token;

import java.util.Collections;
import java.util.Set;

public final class Receiver extends OrientedToken {

    /**
     * Constructor for Receiver.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public Receiver(TokenID id, boolean movable, Orientation orientation) {
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
        return "Receiver, id: " + id() + " movable: %b, orientation: %s".formatted(isMovable(), getOrientation());
    }

    /**
     * Returns whether the receiver is equal to another object (not strictly, i.e. they have the same function).
     *
     * @param o the object to compare to
     * @return whether the receiver is equal to the object
     * @author Nathan Gromb
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Receiver receiver)) return false;

        return getOrientation() == receiver.getOrientation();
    }

    /**
     * Returns whether the receiver is equal to another object.
     *
     * @param o the object to compare to
     * @return whether the receiver is equal to the object
     */
    @Override
    public boolean strictlyEquals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receiver receiver)) return false;
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
    public Set<Orientation> propagateLaser(Orientation from) {
        if (from == null) {
            throw new IllegalArgumentException("Orientation cannot be null");
        }

        return Collections.emptySet();
    }
}
