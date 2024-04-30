package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a one-sided mirror token
 */
public class OneSidedMirror extends OrientedToken {
    public OneSidedMirror(boolean isMovable, Orientation orientation) {
        super(isMovable, orientation);
    }
    /*
    Situation /. is considered LEFT
    Situation .\ is considered UP
    Situation ./ is considered RIGHT
    Situation \. is considered DOWN
    */

    /**
     * Propagates the laser. Gives the direction of the laser after hitting the token
     * @return a set of Orientation as propagation of the laser
     * @param incomingLaserOrientation the orientation of the incoming laser
     * @author Adam Winther
     */
    @Override
    public Set<Orientation> propagateLaser(Orientation incomingLaserOrientation) {
        HashSet<Orientation> propagatedLaser = new HashSet<Orientation>();
        if (this.getOrientation() == Orientation.LEFT) {
            // doubleSidedMirror is in the position /.
            if (incomingLaserOrientation == Orientation.UP) propagatedLaser.add(null);
            if (incomingLaserOrientation == Orientation.DOWN) propagatedLaser.add(Orientation.LEFT);
            if (incomingLaserOrientation == Orientation.LEFT) propagatedLaser.add(null);
            if (incomingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(Orientation.UP);
        } else if (this.getOrientation() == Orientation.UP) {
            // doubleSidedMirror is in the position .\
            if (incomingLaserOrientation == Orientation.UP) propagatedLaser.add(null);
            if (incomingLaserOrientation == Orientation.DOWN) propagatedLaser.add(Orientation.RIGHT);
            if (incomingLaserOrientation == Orientation.LEFT) propagatedLaser.add(Orientation.UP);
            if (incomingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(null);
        } else if (this.getOrientation() == Orientation.RIGHT) {
            // doubleSidedMirror is in the position ./
            if (incomingLaserOrientation == Orientation.UP) propagatedLaser.add(Orientation.RIGHT);
            if (incomingLaserOrientation == Orientation.DOWN) propagatedLaser.add(null);
            if (incomingLaserOrientation == Orientation.LEFT) propagatedLaser.add(Orientation.DOWN);
            if (incomingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(null);
        } else if (this.getOrientation() == Orientation.DOWN) {
            // doubleSidedMirror is in the position \.
            if (incomingLaserOrientation == Orientation.UP) propagatedLaser.add(Orientation.LEFT);
            if (incomingLaserOrientation == Orientation.DOWN) propagatedLaser.add(null);
            if (incomingLaserOrientation == Orientation.LEFT) propagatedLaser.add(null);
            if (incomingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(Orientation.DOWN);
        }
        return propagatedLaser;
    }

    /**
     * Method that returns a copy of the One-Sided Mirror token
     *
     * @return a copy of the One-Sided Mirror token
     * @author Nathan Gromb
     */
    @Override
    public Token copy() {
        return new OneSidedMirror(this.isMovable(), this.getOrientation());
    }
}
