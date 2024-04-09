package Classes.Tokens;

import Classes.Orientation;

public class Receiver extends Token {

    Orientation orientation;

    public Receiver(boolean isMovable, Orientation orientation) {
        super(isMovable);
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
