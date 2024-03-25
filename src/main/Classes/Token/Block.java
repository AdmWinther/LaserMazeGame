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
     * Default constructor for Block. Sets movable to false.
     */
    public Block() {
        super(false);
    }

    @Override
    public Set<Orientation> propagateLazer(Orientation from) {
        return Collections.emptySet();
    }
}