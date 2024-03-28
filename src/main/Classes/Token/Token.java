package Classes.Token;

import Classes.Utils.Coordinate;

import java.util.Set;

public abstract class Token {

    public abstract boolean isMovable();

    public abstract void setCoordinate(Coordinate coordinate);

    public abstract void setIsPlaced(Boolean isPlaced);

    public Orientation getOrientation() {
        return null;
    }

    public abstract void setOrientation(Orientation orientation);

    public abstract Set<Orientation> propagateLazer(Orientation orientation);
}
