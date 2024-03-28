package Classes.Token;

import java.util.Collections;
import java.util.Set;

/**
 * LazerGun is a class that represents a lazer gun token in the game.
 * It is the source of the lazer.
 * A receiver can never be moved.
 */
public class LazerGun extends OrientedToken {

    /**
     * Default constructor for LazerGun. Sets movable to false and orientation to UP.
     *
     * @author Nathan Gromb
     */
    public LazerGun() {
        super(false, Orientation.UP);
    }

    /**
     * Constructor for LazerGun.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public LazerGun(boolean movable, Orientation orientation) {
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
        return Collections.emptySet();
    }
}
