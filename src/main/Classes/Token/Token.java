package Classes.Token;

import Classes.Utils.Coordinate;

public abstract class Token {

    public abstract boolean isMovable();

    public abstract void setCoordinate(Coordinate coordinate);

    public abstract void setOrientation(Orientation orientation);

    public abstract void setIsPlaced(Boolean isPlaced);
}
