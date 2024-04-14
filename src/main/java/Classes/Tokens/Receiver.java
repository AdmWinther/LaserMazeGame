package Classes.Tokens;

import Classes.Orientation;

import java.util.HashSet;
import java.util.Set;

public class Receiver extends Token {
    public Receiver(boolean isMovable, Orientation orientation) {
        super(isMovable);
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation incomingLaserOrientation) {
        return new HashSet<Orientation>();
    }
}
