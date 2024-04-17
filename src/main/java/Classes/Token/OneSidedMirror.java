package Classes.Token;

import Classes.Utils.Orientation;

import java.util.Collections;
import java.util.Set;

public final class OneSidedMirror extends OrientedToken{
    public OneSidedMirror(boolean movable, Orientation orientation) {
        super(movable, orientation);
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        return Collections.emptySet();
    }
}
