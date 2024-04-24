package Model.Classes;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserFragment;
import Model.Classes.Level.PlayableLevel;
import Model.Classes.Token.Target;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

public class SolutionChecker {
    public static boolean check(PlayableLevel level) {
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
