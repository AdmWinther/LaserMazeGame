package Classes.Token;

import Classes.Utils.Orientation;

/**
 * Token is an abstract class that represents a token in the game.
 */
public abstract class Token {

    private boolean movable;

    /**
     * Constructor for Token.
     *
     * @param movable whether the token is movable
     * @author Nathan Gromb
     */
    public Token(boolean movable) {
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

    public abstract Orientation propagateLaser(Orientation orientation);
}
