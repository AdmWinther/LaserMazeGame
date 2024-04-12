package java.step;

import Classes.Utils.Orientation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MirrorSteps {
    @Given("I have a level that contains a laser gun at \\({double}) with a {string} orientation")
    public void iHaveALevelThatContainsALaserGunAtWithAOrientation(int arg0, int arg1, String arg2) {

    }

    @When("a mirror token is placed  with a {string} orientation at the <mirrorX> <mirrorY> position")
    public void aMirrorTokenIsPlacedWithAOrientationAtTheMirrorXMirrorYPosition(String arg0) {
    }

    @When("a double mirror token is placed at <mirrorX> <mirrorY> with a {string} orientation")
    public void aDoubleMirrorTokenIsPlacedAtMirrorXMirrorYWithAOrientation(String arg0) {
    }

    @Then("the Laser is propagated {string}")
    public void theLaserIsPropagated(String arg0) {
    }

    @Then("the laser is not reflected")
    public void theLaserIsNotReflected() {
    }





}
