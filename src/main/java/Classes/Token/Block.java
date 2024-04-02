package Classes.Token;

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
    public Block(TokenID id, boolean movable) {
        super(id, movable);
    }

    /**
     * Returns a string representation of the block.
     *
     * @return a string representation of the block
     * @author Nathan Gromb
     */
    @Override
    public String toString() {
        return "Block, id: " + id() + ", movable: " + isMovable();
    }

    /**
     * Returns whether the block is equal to another object (not strictly, i.e. they have the same function).
     *
     * @param o the object to compare to
     * @return whether the block is equal to the object
     * @author Nathan Gromb
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Block;
    }

    /**
     * Returns whether the block is equal to another object.
     *
     * @param o the object to compare to
     * @return whether the block is equal to the object
     * @author Nathan Gromb
     */
    @Override
    public boolean strictlyEquals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block block)) return false;
        return id() == block.id() && isMovable() == block.isMovable();
    }

    /**
     * Propagate the lazer.
     *
     * @param from the orientation the lazer is coming from
     * @return the orientations the lazer is going to
     * @author Nathan Gromb
     */
    @Override
    public Set<Orientation> propagateLaser(Orientation from) {
        if (from == null) {
            throw new IllegalArgumentException("Orientation cannot be null");
        }

        return Collections.emptySet();
    }
}
