package step;

import Model.Classes.Laser.Laser;
import Model.Classes.Level.Level;
import Model.Classes.Level.PlayableLevel;
import Model.Classes.SolutionChecker;
import Model.Classes.Token.LaserGun;
import Model.Classes.Token.Target;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Orientation;
import Vue.Utils.Position;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Checkpoint {
	Level level;
	Token checkpoint;
	Token laserGun;
	Token target;
	Laser laser;
	boolean result;

	@Given("a level with a laser gun and a checkpoint and target on its {string}")
	public void aLevelWithALaserGunAndACheckpointOnIts(String arg0) {
		Token[][] placed = new Token[5][5];
		laserGun = new LaserGun(false, Orientation.valueOf(arg0));
		Position pos = new Position(2, 2);
		placed[pos.x()][pos.y()] = laserGun;

		Orientation relPos = Orientation.valueOf(arg0);

		checkpoint = new Model.Classes.Token.Checkpoint(false, Orientation.valueOf(arg0));
		pos = offsetWithOrientation(relPos, pos);
		placed[pos.x()][pos.y()] = checkpoint;

		target = new Target(false, Orientation.valueOf(arg0));
		pos = offsetWithOrientation(relPos, pos);
		placed[pos.x()][pos.y()] = target;

		level = new PlayableLevel("test", placed, Set.of());
	}

	private Position offsetWithOrientation(Orientation o, Position p) {
		return switch (o) {
			case UP -> new Position(p.x(), p.y() - 1);
			case DOWN -> new Position(p.x(), p.y() + 1);
			case LEFT -> new Position(p.x() - 1, p.y());
			default -> new Position(p.x() + 1, p.y());
		};
	}

	@And("the laser gun is facing {string}")
	public void theLaserGunIsFacing(String arg0) {
		Orientation o = Orientation.valueOf(arg0);

		((LaserGun) laserGun).setOrientation(o);
	}

	@And("the checkpoint is facing {string}")
	public void theCheckpointIsFacing(String arg0) {
		Orientation o = Orientation.valueOf(arg0);

		((Model.Classes.Token.Checkpoint) checkpoint).setOrientation(o);
	}

	@And("the target is facing {string}")
	public void theTargetIsFacing(String arg0) {
		Orientation o = Orientation.valueOf(arg0);

		((Model.Classes.Token.Target) target).setOrientation(o);
	}

	@When("the player shoots the laser")
	public void thePlayerShootsTheLaser() {
		laser = level.laserManager().generateLaser();
	}

	@And("the level is checked for completion")
	public void theLevelIsCheckedForCompletion() {
		result = SolutionChecker.check(level.tokenManager(), laser);
	}

	@Then("the level checker returns {string}")
	public void theLevelCheckerReturns(String arg0) {
		boolean expected = Boolean.parseBoolean(arg0);
		assertEquals(expected, result);
	}
}
