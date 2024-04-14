package Classes.Laser;

import Classes.Utils.Coordinate;
import Classes.Utils.Orientation;

public record LaserFragment(Coordinate from, Coordinate to) {
    public LaserFragment {
        if (from.x() != to.x() && from.y() != to.y()) {
            throw new IllegalArgumentException("Laser fragment must be either horizontal or vertical");
        }

        if (Math.abs(from.x() - to.x()) > 1 || Math.abs(from.y() - to.y()) > 1) {
            // As the previous test ensures that the fragment is either horizontal or vertical,
            // we know that at most one of the two conditions will be true
            throw new IllegalArgumentException("Laser fragment must have a length equal to 1");
        }

    }

    public Orientation getOrientation() {
        if (from.x() == to.x() && from.y() == to.y() - 1) {
            return Orientation.DOWN;
        } else if (from.x() == to.x() && from.y() == to.y() + 1) {
            return Orientation.UP;
        } else if (from.x() == to.x() - 1 && from.y() == to.y()) {
            return Orientation.RIGHT;
        } else {
            return Orientation.LEFT;
        }
    }
}
