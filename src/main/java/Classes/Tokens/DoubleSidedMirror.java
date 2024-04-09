package Classes.Tokens;

import Classes.Orientation;

public class DoubleSidedMirror extends Token {
    final Orientation orientation;

    public DoubleSidedMirror(boolean isMovable, Orientation orientation) {
        super(isMovable);
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
