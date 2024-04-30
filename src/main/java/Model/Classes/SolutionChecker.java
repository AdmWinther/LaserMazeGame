package Model.Classes;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserFragment;
import Model.Classes.Level.PlayableLevel;
import Model.Classes.Token.Checkpoint;
import Model.Classes.Token.Target;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Interfaces.TokenManager;

import java.util.Set;

public class SolutionChecker {

	public static boolean check(PlayableLevel level) {
		return check(level.tokenManager(), level.generateLaser());
	}


	/**
	 * Checks if the laser hits all the targets and passes through all checkpoints in the level
	 *
	 * @param tokenManager The token manager of the level
	 * @param laser        The laser to check
	 * @return True if the laser hits all the targets and checkpoints, false otherwise
	 * @author
	 */
	@SuppressWarnings("JavadocDeclaration")
	public static Boolean check(TokenManager tokenManager, Laser laser) {
		Set<Coordinate> targetPos = tokenManager.findTargetPosition();
		Set<Coordinate> checkpointsPos = tokenManager.findCheckpointsPosition();

		if (targetPos == null) {
			return false;
		}

		int numTargets = targetPos.size();
		int numCheckpoint;

		if (checkpointsPos == null) {
			numCheckpoint = 0;
		} else {
			numCheckpoint = checkpointsPos.size();
		}

		int hitTarget = 0;
		int hitCheck = 0;

		for (LaserFragment fragment : laser.getFragments()) {

			if (targetPos.contains(fragment.to())) {
				Target target = (Target) tokenManager.getTokenAt(fragment.to());
				if (expectedOrientationTarget(target.getOrientation(), fragment.getOrientation())) {
					++hitTarget;
				}
			}

			if (numCheckpoint != 0) {
				if (checkpointsPos.contains(fragment.to())) {
					Checkpoint checkpoint = (Checkpoint) tokenManager.getTokenAt(fragment.to());
					if (expectedOrientationCheck(checkpoint.getOrientation(), fragment.getOrientation())) {
						++hitCheck;
					}
				}
			}
			if (hitCheck == numCheckpoint && hitTarget == numTargets)
				break;
		}

		boolean laserHitTarget = hitTarget == numTargets;
		boolean laserHitCheckpoint = hitCheck == numCheckpoint;
		boolean areAllTokensUsed = tokenManager.getUnplacedTokensSize() == 0;

		return laserHitTarget && laserHitCheckpoint && areAllTokensUsed;
	}

	/**
	 * Checks if the orientation of the laser is correct for the target
	 *
	 * @param targetOrientation The orientation of the target
	 * @param laserOrientation  The orientation of the laser
	 * @return True if the orientation of the laser is correct for the target, false otherwise
	 */
	private static boolean expectedOrientationTarget(Orientation targetOrientation, Orientation laserOrientation) {
		return switch (targetOrientation) {
			case UP -> Orientation.DOWN == laserOrientation;
			case DOWN -> Orientation.UP == laserOrientation;
			case LEFT -> Orientation.RIGHT == laserOrientation;
			case RIGHT -> Orientation.LEFT == laserOrientation;
		};
	}

	/**
	 * Checks if the orientation of the laser is correct for the checkpoint
	 *
	 * @param checkOrientation The orientation of the checkpoint
	 * @param laserOrientation The orientation of the laser
	 * @return True if the orientation of the laser is correct for the checkpoint, false otherwise
	 */
	private static boolean expectedOrientationCheck(Orientation checkOrientation, Orientation laserOrientation) {
		// laser has to be parallel to the checkpoint
		return switch (checkOrientation) {
			case LEFT, RIGHT -> (Orientation.RIGHT == laserOrientation) || (Orientation.LEFT == laserOrientation);
			case UP, DOWN -> (Orientation.DOWN == laserOrientation) || (Orientation.UP == laserOrientation);
		};
	}
}
