package Classes.Tokens;

import Classes.Orientation;

import java.util.HashSet;
import java.util.Set;

public class OneSidedMirror extends Token {
    public OneSidedMirror(boolean isMovable, Orientation orientation) {
        super(isMovable);
        this.setOrientation(orientation);
    }
    /*
    Situation /. is considered UP
    Situation .\ is considered RIGHT
    Situation ./ is considered DOWN
    Situation \. is considered LEFT
    */

    @Override
    public Set<Orientation> propagateLaser(Orientation incommingLaserOrientation) {
        HashSet<Orientation> propagatedLaser = new HashSet<Orientation>();
        if(this.getOrientation() == Orientation.UP){
            // doubleSidedMirror is in the position /.
            if(incommingLaserOrientation == Orientation.UP) propagatedLaser.add(null);
            if(incommingLaserOrientation == Orientation.DOWN) propagatedLaser.add(Orientation.LEFT);
            if(incommingLaserOrientation == Orientation.LEFT) propagatedLaser.add(null);
            if(incommingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(Orientation.UP);
        } else if(this.getOrientation() == Orientation.RIGHT){
            // doubleSidedMirror is in the position .\
            if(incommingLaserOrientation == Orientation.UP) propagatedLaser.add(null);
            if(incommingLaserOrientation == Orientation.DOWN) propagatedLaser.add(Orientation.RIGHT);
            if(incommingLaserOrientation == Orientation.LEFT) propagatedLaser.add(Orientation.UP);
            if(incommingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(null);
        } else if(this.getOrientation() == Orientation.DOWN){
            // doubleSidedMirror is in the position ./
            if(incommingLaserOrientation == Orientation.UP) propagatedLaser.add(Orientation.RIGHT);
            if(incommingLaserOrientation == Orientation.DOWN) propagatedLaser.add(null);
            if(incommingLaserOrientation == Orientation.LEFT) propagatedLaser.add(Orientation.DOWN);
            if(incommingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(null);
        } else if(this.getOrientation() == Orientation.LEFT){
            // doubleSidedMirror is in the position \.
            if(incommingLaserOrientation == Orientation.UP) propagatedLaser.add(Orientation.LEFT);
            if(incommingLaserOrientation == Orientation.DOWN) propagatedLaser.add(null);
            if(incommingLaserOrientation == Orientation.LEFT) propagatedLaser.add(null);
            if(incommingLaserOrientation == Orientation.RIGHT) propagatedLaser.add(Orientation.DOWN);
        }
        return propagatedLaser;
    }
}
