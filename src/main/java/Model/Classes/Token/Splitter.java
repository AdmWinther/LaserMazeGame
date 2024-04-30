package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a splitter token
 */
public class Splitter extends OrientedToken {
    public Splitter(boolean isMovable, Orientation orientation) {
        super(isMovable, orientation);
    }

    /*
    The Splitter can have only these two positions / and \
    The position / is considered left and up->RIGHT
    The position \ is considered right and down -> UP and Down
    */
    /**
     * Propagates the laser. Gives the direction of the laser after hitting the token
     * @return a set of Orientation as propagation of the laser
     * @param incomingLaserOrientation the orientation of the incoming laser
     */
    @Override
    public Set<Orientation> propagateLaser(Orientation incomingLaserOrientation) {

        Set<Orientation> propagatedLaser = new HashSet<>();
        if (this.getOrientation() == Orientation.LEFT || this.getOrientation() == Orientation.RIGHT) {
            // doubleSidedMirror is in the position /
            switch (incomingLaserOrientation){
                case UP ->{
                    propagatedLaser.add(Orientation.UP);
                    propagatedLaser.add(Orientation.RIGHT);
                }
                case DOWN -> {
                    propagatedLaser.add(Orientation.DOWN);
                    propagatedLaser.add(Orientation.LEFT);
                }
                case LEFT -> {
                    propagatedLaser.add(Orientation.LEFT);
                    propagatedLaser.add(Orientation.DOWN);
                }
                case RIGHT -> {
                    propagatedLaser.add(Orientation.RIGHT);
                    propagatedLaser.add(Orientation.UP);
                }
            }
        } else {
            // doubleSidedMirror is in the position \
            switch (incomingLaserOrientation){
                case UP -> {
                    propagatedLaser.add(Orientation.UP);
                    propagatedLaser.add(Orientation.LEFT);
                }
                case DOWN -> {
                    propagatedLaser.add(Orientation.DOWN);
                    propagatedLaser.add(Orientation.RIGHT);
                }
                case LEFT -> {
                    propagatedLaser.add(Orientation.LEFT);
                    propagatedLaser.add(Orientation.UP);
                }
                case RIGHT -> {
                    propagatedLaser.add(Orientation.RIGHT);
                    propagatedLaser.add(Orientation.DOWN);
                }
            }
        }
        return propagatedLaser;
    }

    /**
     * Method that returns a copy of the Splitter token
     *
     * @return a copy of the Splitter token
     * @author Nathan Gromb
     */
    @Override
    public Token copy() {
        return new Splitter(this.isMovable(), this.getOrientation());
    }
}
