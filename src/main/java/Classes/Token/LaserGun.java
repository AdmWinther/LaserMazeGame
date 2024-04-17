package Classes.Token;

import Classes.Laser.LaserFragment;
import Classes.Utils.Orientation;
import Classes.Utils.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class LaserGun extends OrientedToken {
    public LaserGun(boolean isMovable, Orientation orientation) {
        super(isMovable, orientation);
    }

    public LaserFragment generateFirstLaserFragment(Coordinate laserGunPosition) {
        switch (getOrientation()){
            case UP -> {
                return new LaserFragment(laserGunPosition, new Coordinate(laserGunPosition.x(), laserGunPosition.y()+1));
            }
            case DOWN -> {
                return new LaserFragment(laserGunPosition, new Coordinate(laserGunPosition.x(), laserGunPosition.y()-1));
            }
            case LEFT -> {
                return new LaserFragment(laserGunPosition, new Coordinate(laserGunPosition.x()-1, laserGunPosition.y()));
            }
            case RIGHT -> {
                return new LaserFragment(laserGunPosition, new Coordinate(laserGunPosition.x()+1, laserGunPosition.y()));
            }
        }
        return null;
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation incomingLaserOrientation) {
        return new HashSet<Orientation>();
    }
}
