package Classes.Token;

import Classes.Utils.Coordinate;

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

    /**
     * Constructor for Block.
     *
     * @param movable whether the token is movable
     */
    public Block(boolean movable) {
        super(movable);
    }

    @Override
    public Set<Orientation> propagateLazer(Orientation from) {
        return Collections.emptySet();
    }

    @Override
    public boolean isMovable() {
        return super.isMovable();
    }

    @Override
    public void setMovable(boolean movable) {
        super.setMovable(movable);
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {

    }

    @Override
    public void setOrientation(Orientation orientation) {

    }

    @Override
    public void setIsPlaced(Boolean isPlaced) {

    }
}
