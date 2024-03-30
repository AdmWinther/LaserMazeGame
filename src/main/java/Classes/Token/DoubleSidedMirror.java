package Classes.Token;

import java.util.Set;

/**
 * DoubleSidedMirror is a class that represents a double-sided mirror token in the game.
 * The orientations UP and DOWN are the same, and the orientations LEFT and RIGHT are the same.
 */
public class DoubleSidedMirror extends OrientedToken {

    /**
     * Constructor for DoubleSidedMirror.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public DoubleSidedMirror(int id, boolean movable, Orientation orientation) {
        super(id, movable, orientation);
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
}
