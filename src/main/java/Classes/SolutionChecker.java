package Classes;

import Classes.Laser.Laser;
import Classes.Laser.LaserFragment;
import Classes.Token.Target;
import Classes.Token.Token;
import Classes.Utils.Coordinate;
import Classes.Utils.Orientation;

public class SolutionChecker {
    public static boolean check(Level level) {
        Laser laser = level.laserManager().generateLaser();

        Coordinate targetPos;
        if ((targetPos = level.tokenManager().findTargetPosition()) == null) {
            return false;
        }

        Target target = (Target) level.tokenManager().getTokenAt(targetPos);
        Orientation expectedOrientation = switch (target.getOrientation()) {
            case UP -> Orientation.DOWN;
            case DOWN -> Orientation.UP;
            case LEFT -> Orientation.RIGHT;
            case RIGHT -> Orientation.LEFT;
        };

        for (LaserFragment fragment : laser.getFragments()) {
            if (fragment.to().equals(targetPos) && fragment.getOrientation() == expectedOrientation) {
                return true;
            }
        }

        return false;
    }
}
