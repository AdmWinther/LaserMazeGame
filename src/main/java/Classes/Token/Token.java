package Classes.Token;

import Classes.Utils.Coordinate;
import Interfaces.LaserPropagator;

import java.util.Set;

public abstract class Token implements LaserPropagator {

    private boolean movable;

    /**
     * Default constructor for Token. Sets movable to true.
     */
    public Token() {
        this.movable = true;
    }

    /**
     * Constructor for Token.
     *
     * @param movable whether the token is movable
     */
    public Token(boolean movable) {
        this.movable = movable;
    }

    /**
     * Returns whether the token is movable.
     *
     * @return whether the token is movable
     */
    public boolean isMovable() {
        return movable;
    }

    /**
     * Sets the movable property of the token.
     *
     * @param movable whether the token is movable
     */
    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public abstract void setCoordinate(Coordinate coordinate);

    public Orientation getOrientation() {
        return null;
    }

    public abstract Set<Orientation> propagateLazer(Orientation orientation);

    public int id() {
        return 0;
    }
}
