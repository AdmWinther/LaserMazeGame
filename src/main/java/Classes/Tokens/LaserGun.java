package Classes.Tokens;

import Classes.Orientation;

public class LaserGun extends Token {
    final Orientation orientation;

    public LaserGun(boolean isMovable, Orientation orientation) {
        super(isMovable);
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
