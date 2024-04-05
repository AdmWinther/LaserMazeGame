package Classes;

import Classes.Laser.Laser;
import Classes.Laser.LaserFragment;
import Classes.Token.Target;
import Classes.Utils.Coordinate;
import Classes.Utils.Orientation;

public class SolutionChecker {
    public static boolean check(Level level) {
        Laser laser = level.generateLaser();

        Board board = level.getBoard();

        Coordinate targetPos = board.findTargetPosition();
        Target target = board.getTarget();
        if (targetPos == null) {
            return false;
        }

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
