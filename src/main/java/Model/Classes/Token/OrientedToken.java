package Model.Classes.Token;

import Model.Classes.Utils.Orientation;
import Model.Interfaces.Orientable;
/**
 * Abstract class that represents an oriented token
 * This is a superclass for all the tokens except the Block token
 */
public abstract class OrientedToken extends Token implements Orientable {
    private Orientation orientation;

    /**
     * Constructor of the OrientedToken
     *
     * @param movable     true if the token is movable, false otherwise
     * @param orientation the orientation of the token
     * @author Nathat Gromb
     */
    public OrientedToken(boolean movable, Orientation orientation) {
        super(movable);
        this.orientation = orientation;
    }

    /**
     * Method that returns the orientation of the token
     *
     * @return the orientation of the token
     * @author Nathan Gromb
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Method that sets the orientation of the token
     *
     * @param orientation the new orientation of the token
     * @author Nathan Gromb
     */
    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
