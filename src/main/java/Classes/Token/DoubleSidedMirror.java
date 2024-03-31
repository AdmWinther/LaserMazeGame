package Classes.Token;

import java.util.Set;

/**
 * DoubleSidedMirror is a class that represents a double-sided mirror token in the game.
 * The orientations UP and DOWN are the same, and the orientations LEFT and RIGHT are the same.
 */
public final class DoubleSidedMirror extends OrientedToken {

    /**
     * Constructor for DoubleSidedMirror.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public DoubleSidedMirror(TokenID id, boolean movable, Orientation orientation) {
        super(id, movable, orientation);
    }

    /**
     * Returns a string representation of the double-sided mirror.
     *
     * @return a string representation of the double-sided mirror
     * @author Nathan Gromb
     */
    @Override
    public String toString() {
        return "DoubleSidedMirror, id: " + id() + " movable: %b, orientation: %s".formatted(isMovable(), getOrientation());
    }

    /**
     * Returns whether the double-sided mirror is equal to another object (not strictly, i.e. they have the same function).
     *
     * @param o the object to compare to
     * @return whether the double-sided mirror is equal to the object
     * @author Nathan Gromb
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DoubleSidedMirror)) return false;
        OrientedToken doubleSidedMirror = (DoubleSidedMirror) o;

        return switch (doubleSidedMirror.getOrientation()) {
            case UP, DOWN -> getOrientation() == Orientation.UP || getOrientation() == Orientation.DOWN;
            default -> getOrientation() == Orientation.LEFT || getOrientation() == Orientation.RIGHT;
        };
    }

    /**
     * Returns whether the double-sided mirror is equal to another object.
     *
     * @param o the object to compare to
     * @return whether the double-sided mirror is equal to the object
     * @author Nathan Gromb
     */
    @Override
    public boolean strictlyEquals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleSidedMirror doubleSidedMirror)) return false;
        return id() == doubleSidedMirror.id() && isMovable() == doubleSidedMirror.isMovable()
                && getOrientation() == doubleSidedMirror.getOrientation();
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
        return switch (getOrientation()) {
            case UP, DOWN -> // UP and DOWN are the same are the same and correspond to /
                    switch (from) {
                        case DOWN -> Set.of(Orientation.RIGHT);
                        case LEFT -> Set.of(Orientation.UP);
                        case RIGHT -> Set.of(Orientation.DOWN);
                        default -> Set.of(Orientation.LEFT);
                    };
            default -> // LEFT and RIGHT are the same and correspond to \
                    switch (from) {
                        case DOWN -> Set.of(Orientation.LEFT);
                        case LEFT -> Set.of(Orientation.DOWN);
                        case RIGHT -> Set.of(Orientation.UP);
                        default -> Set.of(Orientation.RIGHT);
                    };
        };
    }

    @Override
    public Orientation getOrientation() {
        return super.getOrientation();
    }

    @Override
    public void setOrientation(Orientation orientation) {
        super.setOrientation(orientation);
    }

    @Override
    public boolean isMovable() {
        return super.isMovable();
    }

    @Override
    public void setMovable(boolean movable) {
        super.setMovable(movable);
    }

}
