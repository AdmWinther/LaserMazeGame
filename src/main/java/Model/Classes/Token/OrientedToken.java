package Model.Classes.Token;

import Model.Classes.Utils.Orientation;
import Model.Interfaces.Orientable;

public abstract class OrientedToken extends Token implements Orientable {
    private Orientation orientation;

    public OrientedToken(boolean movable, Orientation orientation) {
        super(movable);
        this.orientation = orientation;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
