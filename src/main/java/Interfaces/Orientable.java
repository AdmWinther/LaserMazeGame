package Interfaces;

import Classes.Token.Orientation;

public interface Orientable {

    /**
     * Gets the orientation of the token.
     *
     * @return the orientation of the token
     */
    Orientation getOrientation();

    /**
     * Sets the orientation of the token.
     *
     * @param orientation the orientation of the token
     */
    void setOrientation(Orientation orientation);
}
