package Model.Classes.Laser;

import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

public record LaserBranch(Coordinate coordinate, Orientation orientation, boolean continueBranch) {

}
