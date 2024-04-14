package Classes.Token;

import Classes.Utils.Orientation;

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
}
