package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.Collections;
import java.util.Set;

/**
 * A class that represents a block token in the game.
 * It blocks any laser that hits it.
 */
public final class Block extends Token {

    /**
     * Constructor for Block.
     *
     * @param movable whether the token is movable
     * @author Nathan Gromb
     */
    public Block(boolean movable) {
        super(movable);
    }

    /**
     * Propagate the laser.
     * @param orientation the orientation of the incoming laser
     * @return an empty set cause the laser is blocked
     */
    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        return Collections.emptySet();
    }

    /**
     * Copy the block.
     * @return a new block
     */
    @Override
    public Token copy() {
        return new Block(this.isMovable());
    }
}
