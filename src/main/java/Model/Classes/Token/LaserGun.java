package Model.Classes.Token;

import Model.Classes.Laser.LaserFragment;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

import java.util.HashSet;
import java.util.Set;
/**
 * Class that represents a laser gun token
 */
public class LaserGun extends OrientedToken {
    public LaserGun(boolean isMovable, Orientation orientation) {
        super(isMovable, orientation);
    }

    /**
     * Propagates the laser. Gives the direction of the laser after hitting the token
     * @return a set of Orientation as propagation of the laser
     * @param incomingLaserOrientation the orientation of the incoming laser
     * @author Adam Winther
     */
    @Override
    public Set<Orientation> propagateLaser(Orientation incomingLaserOrientation) {
        return new HashSet<Orientation>();
    }

    /**
     * Method that returns a copy of the Laser Gun token
     *
     * @return a copy of the Laser Gun token
     * @author Nathan Gromb
     */
    @Override
    public Token copy() {
        return new LaserGun(this.isMovable(), this.getOrientation());
    }
}
