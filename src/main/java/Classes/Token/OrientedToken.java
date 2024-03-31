package Classes.Token;


import Interfaces.Orientable;

/**
 * OrientedToken is an abstract class that represents a token in the game that has an orientation.
 */
public abstract class OrientedToken extends Token implements Orientable {
    private Orientation orientation;

    /**
     * Default constructor for OrientedToken. Sets movable to true and orientation to UP.
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
     */
    public OrientedToken(boolean movable, Orientation orientation) {
        super(movable);
        this.orientation = orientation;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
