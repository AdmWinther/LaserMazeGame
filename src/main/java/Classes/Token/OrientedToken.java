package Classes.Token;

import Classes.Utils.Orientation;

public abstract class OrientedToken extends Token {
    private Orientation orientation;

    public OrientedToken(boolean movable, Orientation orientation) {
        super(movable);
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
