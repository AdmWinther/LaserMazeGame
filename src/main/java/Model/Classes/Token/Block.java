package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.Collections;
import java.util.Set;

/**
 * Block is a class that represents a block token in the game.
 * It blocks any lazer that hits it.
 * A block can never be moved.
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

    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        return Collections.emptySet();
    }
}
