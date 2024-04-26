package Model.Classes;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserFragment;
import Model.Classes.Level.PlayableLevel;
import Model.Classes.Token.Target;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Interfaces.TokenManager;

public class SolutionChecker {

    public static boolean check(PlayableLevel level) {
        return check(level.tokenManager(), level.generateLaser());
    }


    public static Boolean check(TokenManager tokenManager, Laser laser) {
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

        boolean didLaserHitTargetWithCorrectOrientation = false;
        for (LaserFragment fragment : laser.getFragments()) {
            if (fragment.to().equals(targetPos) && fragment.getOrientation() == expectedOrientation) {
                didLaserHitTargetWithCorrectOrientation = true;
                break;
            }
        }

        boolean areAllTokensUsed = tokenManager.getUnplacedTokensSize() == 0;

        return didLaserHitTargetWithCorrectOrientation && areAllTokensUsed;
    }
}
