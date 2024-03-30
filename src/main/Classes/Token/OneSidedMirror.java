package Classes.Token;

import java.util.Collections;
import java.util.Set;

/**
 * OneSidedMirror is a class that represents a one-sided mirror token in the game.
 */
public class OneSidedMirror extends OrientedToken {

    /**
     * Default constructor for OneSidedMirror. Sets movable to true and orientation to UP.
     *
     * @author Nathan Gromb
     */
    public OneSidedMirror() {
        super();
    }

    /**
     * Constructor for OneSidedMirror.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public OneSidedMirror(boolean movable, Orientation orientation) {
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

        return switch (getOrientation()) {
            case UP -> // UP corresponds to /, with the mirror on the upper side
                    switch (from) {
                        case UP -> Set.of(Orientation.LEFT);
                        case LEFT -> Set.of(Orientation.UP);
                        default -> Collections.emptySet();
                    };
            case DOWN -> // DOWN corresponds to / with the mirror on the lower side
                    switch (from) {
                        case DOWN -> Set.of(Orientation.RIGHT);
                        case RIGHT -> Set.of(Orientation.DOWN);
                        default -> Collections.emptySet();
                    };
            case LEFT -> // LEFT corresponds to \, with the mirror on the left side
                    switch (from) {
                        case DOWN -> Set.of(Orientation.LEFT);
                        case LEFT -> Set.of(Orientation.DOWN);
                        default -> Collections.emptySet();
                    };
            default -> // RIGHT corresponds to \, with the mirror on the right side
                    switch (from) {
                        case UP -> Set.of(Orientation.RIGHT);
                        case RIGHT -> Set.of(Orientation.UP);
                        default -> Collections.emptySet();
                    };
        };
    }
}
