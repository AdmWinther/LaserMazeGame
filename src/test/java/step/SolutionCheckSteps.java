package step;

import Model.Classes.Level.PlayableLevel;
import Model.Classes.SolutionChecker;
import Model.Classes.Token.DoubleSidedMirror;
import Model.Classes.Token.LaserGun;
import Model.Classes.Token.Target;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Orientation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionCheckSteps {
	Set<Token> unplaced;
	Token[][] placed;
	PlayableLevel level;
	boolean result;

	@Given("I have a {int} by {int} level")
	public void iHaveAByLevel(int arg0, int arg1) {
		unplaced = new HashSet<>();
		placed = new Token[arg0][arg1];
	}

	@And("the level contains a laser gun at {int}, {int} with orientation {string}")
	public void theLevelContainsALaserGunAtWithOrientation(int arg0, int arg1, String arg2) {
		placed[arg0][arg1] = new LaserGun(false, Orientation.valueOf(arg2));
	}

	@And("the level contains a target at {int}, {int} with orientation {string}")
	public void theLevelContainsATargetAtWithOrientation(int arg0, int arg1, String arg2) {
		placed[arg0][arg1] = new Target(false, Orientation.valueOf(arg2));
	}

	@And("the level contains a mirror at {int}, {int} with orientation {string}")
	public void theLevelContainsAMirrorAtMirror_xMirror_yWithOrientation(int arg0, int arg1, String arg2) {
		placed[arg0][arg1] = new DoubleSidedMirror(false, Orientation.valueOf(arg2));
	}

	@When("I start the level and check the solution")
	public void iStartTheLevelAndCheckTheSolution() {
		level = new PlayableLevel("solutionCheckTest", placed, unplaced);

		result = SolutionChecker.check(level);
	}

	@And("The solution checker should return {string}")
	public void theSolutionCheckerShouldReturn(String arg0) {
		boolean expected = Boolean.parseBoolean(arg0);

		assertEquals(expected, result);
	}
}
