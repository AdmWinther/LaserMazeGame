package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.Set;

public class Checkpoint extends OrientedToken{

    public Checkpoint(boolean movable, Orientation orientation) {
       super(movable,orientation);
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        return Set.of(orientation);
    }

    @Override
    public Token copy() {
        return new Checkpoint(this.isMovable(),this.getOrientation());
    }
}
