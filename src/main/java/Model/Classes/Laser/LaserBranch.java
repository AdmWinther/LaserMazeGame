package Model.Classes.Laser;

import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

/**
 * Represents a branch of the laser.
 * A branch is a part of the laser. When laser hits a Splitter, a new branch is created.
 * It has the coordinate and the orientation of the tip of the branch and a boolean that
 * indicates if the branch should continue.
 * @Author Adam Winther
 */
public record LaserBranch(Coordinate coordinate, Orientation orientation, boolean continueBranch) {

}
