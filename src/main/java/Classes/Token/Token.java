package Classes.Token;

import Interfaces.LaserPropagator;

/**
 * Token is an abstract class that represents a token in the game.
 */
public abstract class Token implements LaserPropagator {

    private final TokenID id;
    private boolean movable;

    /**
     * Constructor for Token.
     *
     * @param movable whether the token is movable
     * @author Nathan Gromb
     */
    public Token(TokenID id, boolean movable) {
        this.id = id;
        this.movable = movable;
    }

    /**
     * Returns whether the token is equal to another object.
     *
     * @param o the object to compare to
     * @return whether the token is equal to the object
     * @author Nathan Gromb
     */
    public abstract boolean strictlyEquals(Object o);

    /**
     * Returns whether the token is movable.
     *
     * @return whether the token is movable
     * @author Nathan Gromb
     */
    public boolean isMovable() {
        return movable;
    }

    /**
     * Sets the movable property of the token.
     *
     * @param movable whether the token is movable
     * @author Nathan Gromb
     */
    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    /**
     * Returns the id of the token.
     *
     * @return the id of the token
     * @author Nathan Gromb
     */
    public TokenID id() {
        return id;
    }
}
