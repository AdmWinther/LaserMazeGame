package Model.Classes.Token;

import Model.Interfaces.LaserPropagator;

/**
 * Token is an abstract class that represents a token in the game.
 */
public abstract class Token implements LaserPropagator {

    private boolean movable;
    private final TokenID id;

    /**
     * Constructor for Token.
     *
     * @param movable whether the token is movable
     * @author Nathan Gromb
     */
    public Token(boolean movable) {
        this.movable = movable;
        this.id = new TokenID(this.type() + "_" + hashCode());
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

    public TokenID id() {
        return id;
    }

    public String type(){
        return this.getClass().getSimpleName();
    }

    public abstract Token copy();
}
