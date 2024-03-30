package Classes.Token;

import java.util.Collections;
import java.util.Set;

/**
 * LazerGun is a class that represents a lazer gun token in the game.
 * It is the source of the lazer.
 * A receiver can never be moved.
 */
public final class LazerGun extends OrientedToken {

    /**
     * Constructor for LazerGun.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public LazerGun(int id, boolean movable, Orientation orientation) {
        super(id, movable, orientation);
    }

    /**
     * Returns a string representation of the lazer gun.
     *
     * @return a string representation of the lazer gun
     * @author Nathan Gromb
     */
    @Override
    public String toString() {
        return "LazerGun, id: %d, movable: %b, orientation: %s".formatted(id(), isMovable(), getOrientation());
    }

    /**
     * Returns whether the lazer gun is equal to another object (not strictly, i.e. they have the same function).
     *
     * @param o the object to compare to
     * @return whether the lazer gun is equal to the object
     * @author Nathan Gromb
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LazerGun)) return false;
        LazerGun lazerGun = (LazerGun) o;

        return getOrientation() == lazerGun.getOrientation();
    }

    /**
     * Returns whether the lazer gun is equal to another object.
     *
     * @param o the object to compare to
     * @return whether the lazer gun is equal to the object
     * @author Nathan Gromb
     */
    @Override
    public boolean strictlyEquals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LazerGun)) return false;
        LazerGun lazerGun = (LazerGun) o;
        return id() == lazerGun.id() && isMovable() == lazerGun.isMovable()
                && getOrientation() == lazerGun.getOrientation();
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
