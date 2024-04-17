package Classes.Token;

import Classes.Utils.Orientation;

import java.util.Set;

public class DoubleSidedMirror extends OrientedToken{

    public DoubleSidedMirror(boolean movable, Orientation orientation) {
        super(movable, orientation);
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        return null;
    }
}
