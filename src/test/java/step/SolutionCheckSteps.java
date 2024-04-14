package step;


import Classes.*;
import Classes.Laser.Laser;
import Classes.Tokens.Block;
import Classes.Tokens.LaserGun;
import Classes.Tokens.Receiver;
import Classes.Tokens.Token;
import Classes.Utils.Coordinate;

import Classes.Utils.DataReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionCheckSteps {
    Level level;
    Laser laser;
    boolean result;

//    @Given("I have a level that contains a board with a laser gun and a target")
//    public void iHaveALevelThatContainsABoardWithALaserGunAndATarget() {
//        board = new Board(5, 5);
//        level = new Level(board);
//
//        Token laserGun = new LaserGun(true, Orientation.RIGHT);
//        Token target = new Target(true, Orientation.LEFT);
//
//        level.addToken(laserGun);
//        level.addToken(target);
//
//        level.placeToken(laserGun, new Coordinate(0, 0));
//        level.placeToken(target, new Coordinate(4, 0));
//
//    }
//
//    @When("I check the solution")
//    public void iCheckTheSolution() {
//        result = SolutionChecker.check(level);
//    }
//
//    @Then("The laser should propagate from the laser gun")
//    public void theLaserShouldPropagateFromTheLaserGun() {
//        laser = level.generateLaser();
//    }
//
//    @And("The solution checker should return {string}")
//    public void andTheSolutionCheckerShouldReturn(String arg0) {
//        boolean expected = Boolean.parseBoolean(arg0);
//
//        assertEquals(expected, result);
//    }
//
//    @And("there is a block in the way of the laser")
//    public void thereIsABlockInTheWayOfTheLaser() {
//        Token block = new Block(false);
//        level.addToken(block);
//        level.placeToken(block, new Coordinate(0, 2));
//    }
//
//    @Given("I have the level with ID {string}")
//    public void iHaveTheLevelWithID(String arg0) {
//        LevelID levelID = new LevelID("5x5_LaseGun@(2,3)_Right");
//        LevelBuilder levelBuilder = new LevelBuilder(levelID);
//        level = levelBuilder.build();
//    }
//
//    @When("I run GenerateSingleLaserFragment")
//    public void iRunGenerateSingleLaserFragment() {
//        level.generateSingleFragmetn(level.tokenManager().getPlacedTokens());
//    }
}
