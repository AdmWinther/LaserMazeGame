package Classes.Token;

import Classes.Utils.Coordinate;

import java.util.Collections;
import java.util.Set;

/**
 * LazerGun is a class that represents a lazer gun token in the game.
 * It is the source of the lazer.
 * A receiver can never be moved.
 */
public class LazerGun extends OrientedToken {

    /**
     * Default constructor for LazerGun. Sets movable to false and orientation to UP.
     */
    public LazerGun() {
        super(false, Orientation.UP);
    }

    /**
     * Constructor for LazerGun.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     */
    public LazerGun(boolean movable, Orientation orientation) {
        super(movable, orientation);
    }


    @Override
    public Set<Orientation> propagateLazer(Orientation from) {
        return Collections.emptySet();
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
