package Classes.Token;

import Classes.Utils.Orientation;

public final class Mirror extends OrientedToken{
    public Mirror(boolean movable, Orientation orientation) {
        super(movable, orientation);
    }

    @Override
    public Orientation propagateLaser(Orientation orientation) {
        return null;
    }
}
