package Classes.Token;

import java.util.Collections;
import java.util.Set;

/**
 * Block is a class that represents a block token in the game.
 * It blocks any lazer that hits it.
 * A block can never be moved.
 */
public class Block extends Token {

    /**
     * Constructor for Block.
     *
     * @param movable whether the token is movable
     * @author Nathan Gromb
     */
    public Block(int id, boolean movable) {
        super(id, movable);
    }

    @Override
    public String toString() {
        return "Block, id: %d, movable: %b".formatted(id(), isMovable());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block block = (Block) o;
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
    public Set<Orientation> propagateLazer(Orientation from) {
        if (from == null) {
            throw new IllegalArgumentException("Orientation cannot be null");
        }

        return Collections.emptySet();
    }
}
