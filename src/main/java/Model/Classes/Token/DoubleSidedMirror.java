package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.HashSet;
import java.util.Set;
/**
 * Class that represents a double-sided mirror token
 */
public class DoubleSidedMirror extends OrientedToken {
    public DoubleSidedMirror(boolean isMovable, Orientation orientation) {
        super(isMovable, orientation);
    }

    /*
    Double-sided mirror can have only these two positions // and \\
    The position // is considered LEFT and RIGHT
    The position \\ is considered UP and Down
    */
    /**
     * Propagates the laser. Gives the direction of the laser after hitting the token
     * @return a set of Orientation as propagation of the laser
     * @param incomingLaserOrientation the orientation of the incoming laser
     * @author Adam Winther
     */
    @Override
    public Set<Orientation> propagateLaser(Orientation incomingLaserOrientation) {

        Set<Orientation> propagatedLaser = new HashSet<>();
        if (this.getOrientation() == Orientation.LEFT || this.getOrientation() == Orientation.RIGHT) {
            // doubleSidedMirror is in the position //
            if (incomingLaserOrientation == Orientation.UP) propagatedLaser.add(Orientation.RIGHT);
            if (incomingLaserOrientation == Orientation.DOWN) propagatedLaser.add(Orientation.LEFT);
            if (incomingLaserOrientation == Orientation.LEFT) propagatedLaser.add(Orientation.DOWN);
            if (incomingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(Orientation.UP);
        } else {
            // doubleSidedMirror is in the position \\
            if (incomingLaserOrientation == Orientation.UP) propagatedLaser.add(Orientation.LEFT);
            if (incomingLaserOrientation == Orientation.DOWN) propagatedLaser.add(Orientation.RIGHT);
            if (incomingLaserOrientation == Orientation.LEFT) propagatedLaser.add(Orientation.UP);
            if (incomingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(Orientation.DOWN);
        }
        return propagatedLaser;
    }

    /**
     * Method that returns a copy of the Double-Sided Mirror token
     *
     * @return a copy of the Double-Sided Mirror token
     * @author Nathan Gromb
     */
    @Override
    public Token copy() {
        return new DoubleSidedMirror(this.isMovable(), this.getOrientation());
    }
}
