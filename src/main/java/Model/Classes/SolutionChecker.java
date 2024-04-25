package Model.Classes;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserFragment;
import Model.Classes.Token.Target;
import Model.Classes.Token.TokenManager;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

public class SolutionChecker {
    public static boolean check(TokenManager tokenManager, Laser laser) {
        boolean didLaserHitTargetWithCorrectOrientation = false;
        boolean areAllTokensUsed = false;

//        Laser laser = level.laserManager().generateLaser();

        Coordinate targetPos;
        if ((targetPos = tokenManager.findTargetPosition()) == null) {
            return false;
        }

        Target target = (Target) tokenManager.getTokenAt(targetPos);
        Orientation expectedOrientation = switch (target.getOrientation()) {
            case UP -> Orientation.DOWN;
            case DOWN -> Orientation.UP;
            case LEFT -> Orientation.RIGHT;
            case RIGHT -> Orientation.LEFT;
        };

        for (LaserFragment fragment : laser.getFragments()) {
            if (fragment.to().equals(targetPos) && fragment.getOrientation() == expectedOrientation) {
                didLaserHitTargetWithCorrectOrientation = true;
//                return true;
            }
        }

        if(tokenManager.getUnplacedTokensSize() == 0) areAllTokensUsed = true;

        return didLaserHitTargetWithCorrectOrientation && areAllTokensUsed;
    }
}
