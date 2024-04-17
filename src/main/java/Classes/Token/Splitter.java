package Classes.Token;

import Classes.Token.OrientedToken;
import Classes.Utils.Orientation;

import java.util.Set;

public class Splitter extends OrientedToken {

    public Splitter(boolean b, Orientation orientation) {
        super(b,orientation);
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        return null;
    }
}
