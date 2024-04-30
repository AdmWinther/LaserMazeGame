package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.Set;

/**
 * Class that represents a checkpoint token
 */
public class Checkpoint extends OrientedToken {

	/**
	 * Constructor of the Checkpoint
	 *
	 * @param movable     true if the token is movable, false otherwise
	 * @param orientation the orientation of the token
	 * @author Lina Mounan
	 */
	public Checkpoint(boolean movable, Orientation orientation) {
		super(movable, orientation);
	}

	/**
	 * Propagates the laser. Gives the direction of the laser after hitting the token
	 * @return a set of Orientation as propagation of the laser
	 * Checkpoint only propagates the laser in the same direction as of the incoming laser.
	 * @author Lina Mounan
	 * @param orientation the orientation of the incoming laser
	 */
	@Override
	public Set<Orientation> propagateLaser(Orientation orientation) {
		return switch (getOrientation()) {
			case UP, DOWN -> switch (orientation) {
				case UP, DOWN -> Set.of(orientation);
				default -> Set.of();
			};
			case LEFT, RIGHT -> switch (orientation) {
				case LEFT, RIGHT -> Set.of(orientation);
				default -> Set.of();
			};
		};
	}

	/**
	 * Method that returns a copy of the checkpoint token
	 *
	 * @return a copy of the checkpoint token
	 * @author Lina Mounan
	 */
	@Override
	public Token copy() {
		return new Checkpoint(this.isMovable(), this.getOrientation());
	}
}
