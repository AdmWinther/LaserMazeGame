package step;

import Classes.Laser.Laser;
import Classes.Laser.LaserFragment;
import Classes.Level;
import Classes.LevelID;
import Classes.LevelBuilder;
import Classes.Utils.Coordinate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaserSteps {
    private Level level;
    private Laser laser;

    @Given("To test LaserGenerator, I have the level with ID {string}")
    public void toTestLaserGeneratorIHaveTheLevelWithID(String arg0) {
        LevelID levelID = new LevelID(arg0);
        LevelBuilder levelBuilder = new LevelBuilder(levelID);
        level = levelBuilder.build();
    }

    @When("I run generateLaser")
    public void iRungenerateFirstLaserFragment() {
        laser = level.generateLaser();
    }

    @Then("{int} LaserFragment must be generated")
    public void laserfragmentMustBeGenerated(int arg0) {
        assertEquals(laser.getFragments().size(), arg0);
    }
}
