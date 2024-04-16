package step;

import Model.Classes.Level;
import Model.Classes.LevelBuilder;
import Model.Classes.Token.*;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.DataReader;
import Model.Classes.Utils.DataWriter;
import Model.Classes.Utils.Orientation;
import Model.constants.FilePaths;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DataWriterSteps {

    String parentDirPath = FilePaths.SANDBOX_LEVELS_PATH;
    Level initialLevel;
    Level finalLevel;

    @Given("an empty level of size {int}x{int}")
    public void anEmptyLevelOfSizeX(int widthX, int heightY) {
        Token[][] placedTokens = new Token[widthX][heightY];
        Set<Token> unplacedTokens = Set.of();
        initialLevel = new Level("dataWriterStepsEmptyLevel", placedTokens, unplacedTokens);
    }

    @Given("a level of size {int}x{int} with a UP LaserGun in \\({int} {int}) and a DOWN Target in \\({int} {int}), with a DoubleSidedMirror and a SingleSidedMirror to place")
    public void aLevelOfSizeXWithAUPLaserGunInAndADOWNTargetInWithADoubleSidedMirrorAndASingleSidedMirrorToPlace(int widthX, int heightY, int x1, int y1, int x2, int y2) {
        Token[][] placedTokens = new Token[widthX][heightY];
        placedTokens[x1][y1] = new LaserGun(false, Orientation.UP);
        placedTokens[x2][y2] = new Target(false, Orientation.DOWN);

        Set<Token> unplacedTokens = Set.of(new DoubleSidedMirror(true, Orientation.UP), new OneSidedMirror(true, Orientation.UP));

        initialLevel = new Level("dataWriterStepsComplexLevel", placedTokens, unplacedTokens);
    }

    @When("I write the level to a file")
    public void iWriteTheLevelToAFile() {
        DataWriter.write(initialLevel, parentDirPath);
    }

    @Then("I should retrieve the initial level using DataReader")
    public void iShouldRetrieveTheInitialLevelUsingDataReader() {
        LevelBuilder levelBuilder = new LevelBuilder(initialLevel.id());
        finalLevel = levelBuilder.build();

        assertEquals(initialLevel.name(), finalLevel.name());
        assertEquals(initialLevel.tokenManager().getWidthX(), finalLevel.tokenManager().getWidthX());
        assertEquals(initialLevel.tokenManager().getHeightY(), finalLevel.tokenManager().getHeightY());

        // Only checking size here
        assertEquals(initialLevel.tokenManager().getUnplacedTokens().size(), finalLevel.tokenManager().getUnplacedTokens().size());

        for (int i = 0; i < initialLevel.tokenManager().getHeightY(); i++) {
            for (int j = 0; j < initialLevel.tokenManager().getWidthX(); j++) {
                Token currentInitialToken = initialLevel.tokenManager().getTokenAt(new Coordinate(i, j));
                Token currentFinalToken = finalLevel.tokenManager().getTokenAt(new Coordinate(i, j));
                if (currentInitialToken == null || currentFinalToken == null) {
                    assertNull(currentInitialToken);
                    assertNull(currentFinalToken);
                } else {
                    assertEquals(currentInitialToken.type(), currentFinalToken.type());
                    if (currentInitialToken instanceof OrientedToken)
                        assertEquals(((OrientedToken) currentInitialToken).getOrientation().toString(),
                                ((OrientedToken)currentFinalToken).getOrientation().toString());
                }
            }
        }
    }


}
