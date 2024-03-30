package java.Classes.Token;

import java.Classes.Utils.Coordinate;

import java.util.Set;

/**
 * DoubleSidedMirror is a class that represents a double-sided mirror token in the game.
 * The orientations UP and DOWN are the same, and the orientations LEFT and RIGHT are the same.
 */
public class DoubleSidedMirror extends OrientedToken {

    /**
     * Default constructor for DoubleSidedMirror. Sets movable to true and orientation to UP.
     */
    public DoubleSidedMirror() {
        super();
    }

    /**
     * Constructor for DoubleSidedMirror.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     */
    public DoubleSidedMirror(boolean movable, Orientation orientation) {
        super(movable, orientation);
    }

    @Override
    public Set<Orientation> propagateLazer(Orientation from) {
        return switch (getOrientation()) {
            case UP, DOWN -> // UP and DOWN are the same are the same and correspond to /
                    switch (from) {
                        case DOWN -> Set.of(Orientation.RIGHT);
                        case LEFT -> Set.of(Orientation.UP);
                        case RIGHT -> Set.of(Orientation.DOWN);
                        default -> Set.of(Orientation.LEFT);
                    };
            default -> // LEFT and RIGHT are the same and correspond to \
                    switch (from) {
                        case DOWN -> Set.of(Orientation.LEFT);
                        case LEFT -> Set.of(Orientation.DOWN);
                        case RIGHT -> Set.of(Orientation.UP);
                        default -> Set.of(Orientation.RIGHT);
                    };
        };
    }

    @Override
    public Orientation getOrientation() {
        return super.getOrientation();
    }

    @Override
    public void setOrientation(Orientation orientation) {
        super.setOrientation(orientation);
    }

    @Override
    public boolean isMovable() {
        return super.isMovable();
    }

    @Override
    public void setMovable(boolean movable) {
        super.setMovable(movable);
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {

    }

    @Override
    public void setIsPlaced(Boolean isPlaced) {

    }
}
