package Model.Classes.Token;

import Model.Classes.Laser.LaserFragment;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

import java.util.HashSet;
import java.util.Set;

public class LaserGun extends OrientedToken {
    public LaserGun(boolean isMovable, Orientation orientation) {
        super(isMovable, orientation);
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation incomingLaserOrientation) {
        return new HashSet<Orientation>();
    }

    @Override
    public Token copy() {
        return new LaserGun(this.isMovable(), this.getOrientation());
    }
}
