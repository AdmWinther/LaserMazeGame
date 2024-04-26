package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.HashSet;
import java.util.Set;

public class Splitter extends OrientedToken {
    public Splitter(boolean isMovable, Orientation orientation) {
        super(isMovable, orientation);
    }

    /*
    The Splitter can have only these two positions / and \
    The position / is considered left and up->RIGHT
    The position \ is considered right and down -> UP and Down
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

    @Override
    public Token copy() {
        return new Splitter(this.isMovable(), this.getOrientation());
    }
}
