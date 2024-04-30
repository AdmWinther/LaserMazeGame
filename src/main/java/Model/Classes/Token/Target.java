package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.Collections;
import java.util.Set;

/**
 * Class that represents a target token
 */
public class Target extends OrientedToken {
    /**
     * Constructor of the Target
     *
     * @param movable     true if the token is movable, false otherwise
     * @param orientation the orientation of the token
     */
    public Target(boolean movable, Orientation orientation) {
        super(movable, orientation);
    }

    /**
     * Propagates the laser. Gives the direction of the laser after hitting the token
     * @param orientation the orientation of the incoming laser
     * @return a set of Orientation as propagation direction of the laser after hitting the token
     * @author Nathab Gromb
     */
    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        return Collections.emptySet();
    }

    /**
     * Method that returns a copy of the Target token
     *
     * @return a copy of the Target token
     */
    @Override
    public Token copy() {
        return new Target(this.isMovable(), this.getOrientation());
    }
}
