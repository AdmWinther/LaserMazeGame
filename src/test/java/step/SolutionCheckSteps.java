package step;

import Classes.Board;
import Classes.Laser.Laser;
import Classes.Level;
import Classes.SolutionChecker;
import Classes.Token.Block;
import Classes.Token.LaserGun;
import Classes.Token.Target;
import Classes.Token.Token;
import Classes.Utils.Coordinate;
import Classes.Utils.Orientation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionCheckSteps {
    Board board;
    Level level;
    Laser laser;
    boolean result;

    @Given("I have a level that contains a board with a laser gun and a target")
    public void iHaveALevelThatContainsABoardWithALaserGunAndATarget() {
        board = new Board(5, 5);
        level = new Level(board);

        Token laserGun = new LaserGun(true, Orientation.RIGHT);
        Token target = new Target(true, Orientation.LEFT);

        level.addToken(laserGun);
        level.addToken(target);

        level.placeToken(laserGun, new Coordinate(0, 0));
        level.placeToken(target, new Coordinate(0, 4));

    }

    @When("I check the solution")
    public void iCheckTheSolution() {
        result = SolutionChecker.check(level);
    }

    @Then("The laser should propagate from the laser gun")
    public void theLaserShouldPropagateFromTheLaserGun() {
        laser = level.generateLaser();
    }

    @And("The solution checker should return {string}")
    public void andTheSolutionCheckerShouldReturn(String arg0) {
        boolean expected = Boolean.parseBoolean(arg0);

        assertEquals(expected, result);
    }

    @And("there is a block in the way of the laser")
    public void thereIsABlockInTheWayOfTheLaser() {
        Token block = new Block(false);
        level.addToken(block);
        level.placeToken(block, new Coordinate(0, 2));
    }
}
