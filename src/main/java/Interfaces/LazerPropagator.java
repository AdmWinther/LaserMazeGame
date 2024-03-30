package Interfaces;


import Classes.Token.Orientation;

import java.util.Set;

/**
 * LazerPropagator is an interface that represents an object that can propagate a lazer.
 */
public interface LazerPropagator {

    /**
     * Get the orientations the lazer will be going to.
     *
     * @param from The orientation the lazer is coming from.
     * @return The orientations the lazer will be going to.
     */
    Set<Orientation> propagateLazer(Orientation from);
}
