package Classes.Token;

import Interfaces.LazerPropagator;

/**
 * Token is an abstract class that represents a token in the game.
 */
public abstract class Token implements LazerPropagator {

    private final int id;
    private boolean movable;

    /**
     * Constructor for Token.
     *
     * @param movable whether the token is movable
     * @author Nathan Gromb
     */
    public Token(int id, boolean movable) {
        if (id < 0) {
            throw new IllegalArgumentException("Token id must be non-negative.");
        }
        
        this.id = id;
        this.movable = movable;
    }

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
    public int id() {
        return id;
    }
}
