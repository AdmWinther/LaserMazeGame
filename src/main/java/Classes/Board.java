package Classes;

import Classes.Laser.Laser;
import Classes.Laser.LaserFragment;
import Classes.Token.LaserGun;
import Classes.Token.Target;
import Classes.Token.Token;
import Classes.Utils.Coordinate;
import Classes.Utils.Orientation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private final int height;
    private final int width;

    private final Token[][] tokens;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;

        tokens = new Token[height][width];
    }

    public boolean placeToken(Token token, Coordinate position) {
        if (isCoordinateInBoard(position) && isPositionEmpty(position)) {
            tokens[position.y()][position.x()] = token;
            return true;
        }

        return false;
    }

    public boolean isCoordinateInBoard(Coordinate coordinate) {
        return coordinate.x() >= 0 && coordinate.x() < width && coordinate.y() >= 0 && coordinate.y() < height;
    }

    public boolean isPositionEmpty(Coordinate position) {
        return tokens[position.x()][position.y()] == null;
    }

    public Laser generateLaser() {
        Coordinate laserGunPosition = findLaserGunPosition();
        if (laserGunPosition == null) {
            // If there is no laser gun, return null
            return null;
        }

        LaserGun laserGun = (LaserGun) tokens[laserGunPosition.y()][laserGunPosition.x()];

        // There is a laser gun, so we can create a laser
        Laser laser = new Laser();

        Coordinate dest = laserGunPosition.add(getOrientationOffset(laserGun.getOrientation()));
        if (!isCoordinateInBoard(dest)) {
            return laser;
        }

        LaserFragment nextFragment = new LaserFragment(laserGunPosition, dest);

        List<LaserFragment> toExtend = new ArrayList<>();
        laser.addFragment(nextFragment);
        toExtend.add(nextFragment);

        while (!toExtend.isEmpty()) {
            List<LaserFragment> nextToExtend = new ArrayList<>();

            for (LaserFragment fragment : toExtend) {
                if (!isPositionEmpty(fragment.to())) { // Case 1: The fragment to extend collides with a token
                    Token token = tokens[fragment.to().x()][fragment.to().y()];

                    // Create a new fragment for each orientation the laser propagates to
                    for (Orientation orientation : token.propagateLaser(fragment.getOrientation())) {
                        dest = fragment.to().add(getOrientationOffset(orientation));

                        if (!isCoordinateInBoard(dest)) {
                            continue;
                        }

                        nextFragment = new LaserFragment(fragment.to(), dest);
                        if (!laser.containsFragment(nextFragment)) {
                            nextToExtend.add(nextFragment);
                            laser.addFragment(nextFragment);
                        }
                    }
                } else { // Case 2: The fragment to extend does not collide with a token
                    dest = fragment.to().add(getOrientationOffset(fragment.getOrientation()));

                    if (!isCoordinateInBoard(dest)) {
                        continue;
                    }

                    nextFragment = new LaserFragment(fragment.to(), dest);
                    if (!laser.containsFragment(nextFragment)) {
                        nextToExtend.add(nextFragment);
                        laser.addFragment(nextFragment);
                    }
                }
            }

            toExtend = nextToExtend;
        }

        return laser;
    }

    public Coordinate findLaserGunPosition() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tokens[y][x] instanceof LaserGun) {
                    return new Coordinate(x, y);
                }
            }
        }

        return null;
    }

    public Coordinate findTargetPosition() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tokens[y][x] instanceof Target) {
                    return new Coordinate(x, y);
                }
            }
        }

        return null;
    }

    public Target getTarget() {
        Coordinate targetPos = findTargetPosition();
        if (targetPos == null) {
            return null;
        }

        return (Target) tokens[targetPos.y()][targetPos.x()];
    }

    private Coordinate getOrientationOffset(Orientation orientation) {
        return switch (orientation) {
            case UP -> new Coordinate(0, -1);
            case DOWN -> new Coordinate(0, 1);
            case LEFT -> new Coordinate(-1, 0);
            case RIGHT -> new Coordinate(1, 0);
            default -> new Coordinate(0, 0);
        };
    }
}
