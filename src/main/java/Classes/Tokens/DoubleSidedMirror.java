package Classes.Tokens;

import Classes.Orientation;

import java.util.HashSet;
import java.util.Set;

public class DoubleSidedMirror extends Token {
    public DoubleSidedMirror(boolean isMovable, Orientation orientation) {
        super(isMovable);
    }

    /*
    Double sided mirror can have only these two positions // and \\
    The position // is considered left and up
    The position \\ is considered right and down
    */
    @Override
    public Set<Orientation> propagateLaser(Orientation incomingLaserOrientation) {

        Set<Orientation> propagatedLaser = new HashSet<Orientation>();
        if(this.getOrientation() == Orientation.LEFT || this.getOrientation() == Orientation.UP){
            // doubleSidedMirror is in the position //
            if(incomingLaserOrientation == Orientation.UP) propagatedLaser.add(Orientation.RIGHT);
            if(incomingLaserOrientation == Orientation.DOWN) propagatedLaser.add(Orientation.LEFT);
            if(incomingLaserOrientation == Orientation.LEFT) propagatedLaser.add(Orientation.DOWN);
            if(incomingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(Orientation.UP);
        } else {
            // doubleSidedMirror is in the position \\
            if(incomingLaserOrientation == Orientation.UP) propagatedLaser.add(Orientation.LEFT);
            if(incomingLaserOrientation == Orientation.DOWN) propagatedLaser.add(Orientation.RIGHT);
            if(incomingLaserOrientation == Orientation.LEFT) propagatedLaser.add(Orientation.UP);
            if(incomingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(Orientation.DOWN);
        }
        return propagatedLaser;
    }
}
