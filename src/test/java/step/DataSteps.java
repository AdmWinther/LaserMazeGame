package step;

import Classes.Level;
import Classes.LevelBuilder;
import Classes.LevelID;
import Classes.Tokens.LaserGun;
import Classes.Tokens.Receiver;
import Classes.Utils.Coordinate;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class DataSteps {
    LevelID levelID;
    Level level;

    @Given("I have a level stored with a five-by-five grid, empty, with no token")
    public void iHaveALevelStoredWithAFiveByFiveGridEmptyWithNoToken() {
        levelID = new LevelID("level_5x5_EmptyBoard_NoTokenToPlace");
    }

    @Given("I have a level stored with a ten-by-ten grid, with a LEFT LaserGun at \\(two, three) and a UP Receiver at \\(five, six), with a RIGHT DoubleSidedMirror to place.")
    public void iHaveALevelStoredWithATenByTenGridWithALEFTLaserGunAtTwoThreeAndAUPReceiverAtFiveSixWithARIGHTDoubleSidedMirrorToPlace() {
        levelID = new LevelID("level_10x10_2TokensOnBoard_1TokenToPlace");
    }

    @When("I retrieve the level")
    public void iRetrieveTheLevel() {
        LevelBuilder levelBuilder = new LevelBuilder(levelID);
        level = levelBuilder.build();
    }

    @Then("The grid should be of size {int} by {int}")
    public void theGridShouldBeOfSizeBy(int height, int width) {
        assertEquals(level.tokenManager().getHeightY(), height);
        assertEquals(level.tokenManager().getWidthX(), width);
    }

    @And("The grid should be empty")
    public void theGridShouldBeEmpty() {
        for (int i = 0; i < level.tokenManager().getHeightY(); i++) {
            for (int j = 0; j < level.tokenManager().getWidthX(); j++) {
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
for (int i = 0; i < level.tokenManager().getHeightY(); i++) {
            for (int j = 0; j < level.tokenManager().getWidthX(); j++) {
                if (i == x1 && j == y1) {
                    assertTrue(level.tokenManager().getTokenAt(new Coordinate(i, j)) instanceof LaserGun);
                } else if (i == x2 && j == y2) {
                    assertTrue(level.tokenManager().getTokenAt(new Coordinate(i, j)) instanceof Receiver);
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


}
