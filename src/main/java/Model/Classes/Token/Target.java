package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.Collections;
import java.util.Set;

public class Target extends OrientedToken {
    public Target(boolean movable, Orientation orientation) {
        super(movable, orientation);
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        return Collections.emptySet();
    }

    @Override
    public Token copy() {
        return new Target(this.isMovable(), this.getOrientation());
    }
}
