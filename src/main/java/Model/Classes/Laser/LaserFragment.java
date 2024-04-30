package Model.Classes.Laser;

import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

/**
 * Represents a fragment of the laser.
 * A fragment is a part of the laser. It has a starting coordinate and an ending coordinate.
 * The fragment can be either horizontal or vertical and have a length of 1.
 *
 * @Author Adam Winther
 */
public class LaserFragment {

	private final Coordinate from;
	private final Coordinate to;

	/**
	 * Constructor for the LaserFragment class.
	 *
	 * @param index The index of the fragment.
	 * @param from  The starting coordinate of the fragment.
	 * @param to    The ending coordinate of the fragment.
	 * @throws IllegalArgumentException if the fragment is not horizontal or vertical or if the fragment has a length different from 1.
	 * @author Adam Winther
	 */
	public LaserFragment(int index, Coordinate from, Coordinate to) {
		if (from.x() != to.x() && from.y() != to.y()) {
			// To make sures that the fragment is either horizontal or vertical.
			throw new IllegalArgumentException("Laser fragment must be either horizontal or vertical");
		}

		if (Math.abs(from.x() - to.x()) > 1 || Math.abs(from.y() - to.y()) > 1) {
			// To make sures that the fragment is of the length 1.
			throw new IllegalArgumentException("Laser fragment must have a length equal to 1");
		}
		this.from = from;
		this.to = to;
		//todo: remove index
	}

	/**
	 * Get the orientation of the current fragment.
	 *
	 * @return Orientation of the fragment based on the coordinates of the start and end of the fragment.
	 * @author Adam Winther
	 */
	public Orientation getOrientation() {
		//todo: remove this part of the method. It is duplicate of the getFragmentOrientation method.
		//        if (from.x() == to.x() && from.y() == to.y() - 1) {
		//            return Orientation.DOWN;
		//        } else if (from.x() == to.x() && from.y() == to.y() + 1) {
		//            return Orientation.UP;
		//        } else if (from.x() == to.x() - 1 && from.y() == to.y()) {
		//            return Orientation.RIGHT;
		//        } else {
		//            return Orientation.LEFT;
		//        }
		return getFragmentOrientation(this);
	}

	/**
	 * A static method to get the orientation of any fragment.
	 *
	 * @param fragment The fragment to get the orientation of.
	 * @return The orientation of the fragment.
	 * @author Adam Winther
	 */
	public static Orientation getFragmentOrientation(LaserFragment fragment) {
		int deltaX = fragment.to().x() - fragment.from().x();
		int deltaY = fragment.to().y() - fragment.from().y();

		if (deltaY == 0 && deltaX == 1) {
			return Orientation.RIGHT;
		}

		if (deltaY == 0 && deltaX == -1) {
			return Orientation.LEFT;
		}

		if (deltaY == 1 && deltaX == 0) {
			return Orientation.DOWN;
		}

		if (deltaY == -1 && deltaX == 0) {
			return Orientation.UP;
		}
		return null;
	}

	/**
	 * Get the coordinate of the end of the laser fragment.
	 *
	 * @return Coordinate of the end of the laser fragment.
	 * @author Adam Winther
	 */
	public Coordinate to() {
		return this.to;
	}

	/**
	 * Get the coordinate of the end of the laser fragment.
	 *
	 * @return Coordinate of the end of the laser fragment.
	 * @author Adam Winther
	 */
	public Coordinate from() {
		return this.from;
	}
}
