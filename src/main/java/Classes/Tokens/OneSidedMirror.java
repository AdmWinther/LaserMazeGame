package Classes.Tokens;

import Classes.Orientation;

public class OneSidedMirror extends Token {
    private final Orientation orientation;

    public OneSidedMirror(boolean isMovable, Orientation orientation) {
        super(isMovable);
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
