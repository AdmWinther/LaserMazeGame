package step;

import Model.Classes.Level.Level;
import Model.Classes.Level.PlayableLevel;
import Model.Classes.SolutionChecker;
import Model.Classes.Token.*;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenPlacementSteps {
	Token[][] placed;
	Set<Token> unplaced;

	Level level;
	boolean result;

	@Given("I have a {int} by {int} level")
	public void iHaveALevelThatContainsAnEmptyWidthByHeightBoard(int width, int height) {
		placed = new Token[width][height];
		unplaced = new HashSet<>();
	}

	@And("The level is built")
	public void theLevelIsBuilt() {
		level = new PlayableLevel("placementTest", placed, unplaced);
	}

	@And("I add a unplaced mirror")
	public void iAddAUnplacedMirror() {
		unplaced.add(new OneSidedMirror(true, Orientation.RIGHT));
	}

	@And("I add a placed mirror at position \\({int}, {int})")
	public void iAddAPlacedMirrorAtPosition(int tokenX, int tokenY) {
		placed[tokenX][tokenY] = new OneSidedMirror(true, Orientation.RIGHT);
	}

	@And("I add a placed unmovable mirror at position \\({int}, {int})")
	public void iAddAPlacedUnmovableMirrorAtPosition(int placedTokenX, int placedTokenY) {
		placed[placedTokenX][placedTokenY] = new OneSidedMirror(false, Orientation.RIGHT);
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

	@When("I try to place the unplaced mirror at position \\({int}, {int})")
	public void iTryToPlaceTheUnplacedMirrorAtPosition(int tokenX, int tokenY) {
		Token token = level.tokenManager().getUnplacedTokens().iterator().next();

		result = level.tokenManager().transferTokenToPlacedTokens(token, new Coordinate(tokenX, tokenY));
	}

	@When("I try to move the token from cell \\({int}, {int}) to \\({int}, {int})")
	public void iTryToMoveTheTokenFromCellTo(int placedTokenX, int placedTokenY, int destX, int destY) {
		result = level.tokenManager().moveToken(new Coordinate(placedTokenX, placedTokenY), new Coordinate(destX, destY));
	}

	@Then("Cell \\({int}, {int}) must be occupied by a token")
	public void cellTokenXTokenYMustBeOccupiedByAToken(int tokenX, int tokenY) {
		assert level.tokenManager().getTokenAt(new Coordinate(tokenX, tokenY)) != null;
	}

	@Then("Cell \\({int}, {int}) must be empty")
	public void cellTokenXTokenYMustBeEmpty(int tokenX, int tokenY) {
		assert level.tokenManager().getTokenAt(new Coordinate(tokenX, tokenY)) == null;
	}

	@Then("The action should fail")
	public void theActionShouldFail() {
		assert !result;
	}

	@When("I start the level and check the solution")
	public void iStartTheLevelAndCheckTheSolution() {
		level = new PlayableLevel("solutionCheckTest", placed, unplaced);

		result = SolutionChecker.check((PlayableLevel) level);
	}

	@And("The solution checker should return {string}")
	public void theSolutionCheckerShouldReturn(String arg0) {
		boolean expected = Boolean.parseBoolean(arg0);

		assertEquals(expected, result);
	}
}
