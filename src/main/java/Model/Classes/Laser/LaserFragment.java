package Model.Classes.Laser;

import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

//public record LaserFragment(int index, Coordinate from, Coordinate to) {
public class LaserFragment {

    private final Coordinate from;
    private final Coordinate to;
    private final int index;

    public LaserFragment(int index, Coordinate from, Coordinate to) {
        if (from.x() != to.x() && from.y() != to.y()) {
            throw new IllegalArgumentException("Laser fragment must be either horizontal or vertical");
        }

        if (Math.abs(from.x() - to.x()) > 1 || Math.abs(from.y() - to.y()) > 1) {
            // As the previous test ensures that the fragment is either horizontal or vertical,
            // we know that at most one of the two conditions will be true
            throw new IllegalArgumentException("Laser fragment must have a length equal to 1");
        }
        this.from = from;
        this.to = to;
        this.index = index;
    }

    public Coordinate from(){
        return this.from;
    }

    public Coordinate to(){
        return this.to;
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

    public static Orientation getFragmentOrientation(LaserFragment fragment) {
        int deltaX = fragment.to().x() - fragment.from().x();
        int deltaY = fragment.to().y() - fragment.from().y();

        if(deltaY == 0 && deltaX ==1){
            return Orientation.RIGHT;
        }

        if(deltaY == 0 && deltaX ==-1){
            return Orientation.LEFT;
        }

        if(deltaY == 1 && deltaX ==0){
            return Orientation.DOWN;
        }

        if(deltaY == -1 && deltaX ==0){
            return Orientation.UP;
        }
        return null;
    }
}
