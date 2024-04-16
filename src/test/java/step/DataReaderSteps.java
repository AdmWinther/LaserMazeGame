package step;

import Model.Classes.Level;
import Model.Classes.LevelBuilder;
import Model.Classes.LevelID;
import Model.Classes.Token.LaserGun;
import Model.Classes.Token.OneSidedMirror;
import Model.Classes.Token.OrientedToken;
import Model.Classes.Token.Target;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class DataReaderSteps {
    LevelID levelID;
    Level level;

    @Given("I have a level stored with a five-by-five grid, empty, with no token")
    public void iHaveALevelStoredWithAFiveByFiveGridEmptyWithNoToken() {
        levelID = new LevelID("5x5_EmptyBoard_NoToken");
    }

    @Given("I have the level with ID {string}")
    public void iHaveTheLevelWithID(String arg0) {
        levelID = new LevelID(arg0);
        LevelBuilder levelBuilder = new LevelBuilder(levelID);
        level = levelBuilder.build();
    }

    @Given("I have a level stored with a ten-by-ten grid, with a LEFT LaserGun at \\(two, three) and a UP Receiver at \\(five, six), with a RIGHT DoubleSidedMirror to place.")
    public void iHaveALevelStoredWithATenByTenGridWithALEFTLaserGunAtTwoThreeAndAUPReceiverAtFiveSixWithARIGHTDoubleSidedMirrorToPlace() {
        levelID = new LevelID("10x10_2TokensOnBoard_1TokenToPlace");
    }

    @When("I retrieve the level")
    public void iRetrieveTheLevel() {
        LevelBuilder levelBuilder = new LevelBuilder(levelID);
        level = levelBuilder.build();
    }

    @Then("The grid should be of size {int} by {int}")
    public void theGridShouldBeOfSizeBy(int height, int width) {
        assertEquals(level.height, height);
        assertEquals(level.width, width);
    }

    @And("The grid should be empty")
    public void theGridShouldBeEmpty() {
        for (int i = 0; i < level.height; i++) {
            for (int j = 0; j < level.width; j++) {
                assertNull(level.tokenManager().getTokenAt(new Coordinate(i, j)));
            }
        }
    }

    @And("The list of placeable tokens should be empty")
    public void theListOfPlaceableTokensShouldBeEmpty() {
        assertEquals(level.tokenManager().getUnplacedTokensSize(), 0);
    }

    @And("The grid should be empty except for a LEFT LaserGun at \\({int}, {int}) and a UP Receiver at \\({int}, {int})")
    public void theGridShouldBeEmptyExceptForALEFTLaserGunAtAndAUPReceiverAt(int x1, int y1, int x2, int y2) {
        for (int i = 0; i < level.height; i++) {
            for (int j = 0; j < level.width; j++) {
                if (i == x1 && j == y1) {
                    assertInstanceOf(LaserGun.class, level.tokenManager().getTokenAt(new Coordinate(i, j)));
                } else if (i == x2 && j == y2) {
                    assertInstanceOf(Target.class, level.tokenManager().getTokenAt(new Coordinate(i, j)));
                } else {
                    assertNull(level.tokenManager().getTokenAt(new Coordinate(i, j)));
                }
            }
        }
    }

    @And("The list of placeable tokens should contain a RIGHT DoubleSidedMirror.")
    public void theListOfPlaceableTokensShouldContainARIGHTDoubleSidedMirror() {
        assertEquals(level.tokenManager().getUnplacedTokensSize(), 1);
    }


    @And("The grid should be empty except for a RIGHT LaserGun at \\({int}, {int}) and a UP OneSidedMirror at \\({int}, {int})")
    public void theGridShouldBeEmptyExceptForARIGHTLaserGunAtAndAUPOneSidedMirrorAt(int x1, int y1, int x2, int y2) {
        for (int i = 0; i < level.height; i++) {
            for (int j = 0; j < level.width; j++) {
                if (i == x1 && j == y1) {
                    assertInstanceOf(LaserGun.class, level.tokenManager().getTokenAt(new Coordinate(i, j)));
                    assertEquals(((OrientedToken) level.tokenManager().getTokenAt(new Coordinate(i, j))).getOrientation(), Orientation.RIGHT);
                } else if (i == x2 && j == y2) {
                    assertInstanceOf(OneSidedMirror.class, level.tokenManager().getTokenAt(new Coordinate(i, j)));
                    assertEquals(((OrientedToken) level.tokenManager().getTokenAt(new Coordinate(i, j))).getOrientation(), Orientation.UP);
                } else {
                    assertNull(level.tokenManager().getTokenAt(new Coordinate(i, j)));
                }
            }
        }
    }
}
