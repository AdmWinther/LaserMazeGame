package Model.Classes.Token;

import Model.Classes.Utils.Orientation;

import java.util.Set;

public class Checkpoint extends OrientedToken {

	public Checkpoint(boolean movable, Orientation orientation) {
		super(movable, orientation);
	}

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

	@Override
	public Token copy() {
		return new Checkpoint(this.isMovable(), this.getOrientation());
	}
}
