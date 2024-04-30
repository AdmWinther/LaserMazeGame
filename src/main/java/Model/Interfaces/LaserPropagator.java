package Model.Interfaces;

import Model.Classes.Utils.Orientation;

import java.util.Set;

public interface LaserPropagator {
    Set<Orientation> propagateLaser(Orientation orientation);
}
