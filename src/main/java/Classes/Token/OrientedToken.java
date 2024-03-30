package Classes.Token;

import Interfaces.Orientable;

/**
 * OrientedToken is an abstract class that represents a token in the game that has an orientation.
 */
public abstract class OrientedToken extends Token implements Orientable {
    private Orientation orientation;

    /**
     * Default constructor for OrientedToken. Sets movable to true and orientation to UP.
     *
     * @author Nathan Gromb
     */
    public OrientedToken() {
        super();
        this.orientation = Orientation.UP;
    }

    /**
     * Constructor for OrientedToken.
     *
     * @param movable     whether the token is movable
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    public OrientedToken(boolean movable, Orientation orientation) {
        super(movable);

        if (orientation == null) {
            throw new IllegalArgumentException("Orientation cannot be null");
        }

        this.orientation = orientation;
    }

    /**
     * Get the orientation of the token.
     *
     * @return the orientation of the token
     * @author Nathan Gromb
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Set the orientation of the token.
     *
     * @param orientation the orientation of the token
     * @author Nathan Gromb
     */
    @Override
    public void setOrientation(Orientation orientation) {
        if (orientation == null) {
            throw new IllegalArgumentException("Orientation cannot be null");
        }

        this.orientation = orientation;
    }
}
