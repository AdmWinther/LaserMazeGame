package Classes.Tokens;

import Classes.Orientation;

import java.util.Set;

/**
 * Token is an abstract class that represents a token in the game.
 */
public abstract class Token {

    private boolean movable;
    private Orientation orientation;

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

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public abstract Set<Orientation> propagateLaser(Orientation incomingLaserOrientation);
}
