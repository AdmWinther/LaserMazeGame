package Classes.Token;

import Classes.Utils.Coordinate;

import java.util.Set;

public class Block extends Token {

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {

    }

    @Override
    public void setIsPlaced(Boolean isPlaced) {

    }

    @Override
    public void setOrientation(Orientation orientation) {

    }

    @Override
    public Set<Orientation> propagateLazer(Orientation orientation) {
        return null;
    }
}
