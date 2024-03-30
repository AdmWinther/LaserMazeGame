package Classes.Token;

import Classes.Utils.Coordinate;

import java.util.Collections;
import java.util.Set;

/**
 * Receiver is a class that represents an objective in the game.
 * A receiver can never be moved.
 */
public class Receiver extends OrientedToken  {

    /**
     * Default constructor for Receiver. Sets movable to false and orientation to UP.
     */
    public Receiver() {
        super(false, Orientation.UP);
    }

    /**
     * Constructor for Receiver. Sets movable to false.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     */
    public Receiver(boolean movable, Orientation orientation) {
        super(movable, orientation);
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

    @Override
    public Set<Orientation> propagateLazer(Orientation from) {
        return null;
    }
}
