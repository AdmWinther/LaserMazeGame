package step;

import Model.Classes.Laser.Laser;
import Model.Classes.Level.Level;
import Model.Classes.Level.PlayableLevel;
import Model.Classes.SolutionChecker;
import Model.Classes.Token.LaserGun;
import Model.Classes.Token.Target;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Orientation;
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
		placed[2][2] = laserGun;

		Orientation relPos = Orientation.valueOf(arg0);

		int x = 2;
		int y = 2;
		switch (relPos) {
			case UP:
				y -= 1;
				break;
			case DOWN:
				y += 1;
				break;
			case LEFT:
				x -= 1;
				break;
			default:
				x += 1;
				break;
		}

		checkpoint = new Model.Classes.Token.Checkpoint(false, Orientation.valueOf(arg0));
		placed[x][y] = checkpoint;

		switch (relPos) {
			case UP:
				y -= 1;
				break;
			case DOWN:
				y += 1;
				break;
			case LEFT:
				x -= 1;
				break;
			default:
				x += 1;
				break;
		}

		target = new Target(false, Orientation.valueOf(arg0));
		placed[x][y] = target;

		level = new PlayableLevel("test", placed, Set.of());
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
