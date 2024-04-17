package java.step;
import Model.Classes.Token.*;
import Model.Classes.Utils.Orientation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenSteps {

    OrientedToken token;
    OrientedToken laserGun;

    @Given("I have a single sided mirror token with a {string} orientation")
    public void iHaveASingleSidedMirrorTokenWithAOrientation(String orientation) {
        token = new OneSidedMirror(true,Orientation.valueOf(orientation));
    }

    @When("the lasergun has a  {string} orientation")
    public void theLasergunHasAOrientation(String orientation) {
        laserGun = new LaserGun(false,Orientation.valueOf(orientation));
    }


    @Then("the Laser is propagated {string}")
    public void theLaserIsPropagated(String o) {
        assertEquals(token.propagateLaser(laserGun.getOrientation()),Set.of(Orientation.valueOf(o)));
    }

    @Then("the laser is not reflected")
    public void theLaserIsNotReflected() {
        assertTrue(token.propagateLaser(laserGun.getOrientation()).isEmpty());
    }

    @When("the splitter has a  {string} orientation")
    public void theSplitterHasAOrientation(String o) {
        //token = new Splitter(true,Orientation.valueOf(o));
    }

    @Given("I have a double sided mirror token with a {string} orientation")
    public void iHaveADoubleSidedMirrorTokenWithAOrientation(String o) {
        token = new DoubleSidedMirror(true,Orientation.valueOf(o));
    }


    @Given("I have a splitter token with a {string} orientation")
    public void iHaveASplitterTokenWithAOrientation(String o) {
        //token = new Splitter(true, Orientation.valueOf(o));
    }

    @Then("the Laser is propagated {string} and {string}")
    public void theLaserIsPropagatedAnd(String arg0, String arg1) {
        Orientation o1 = Orientation.valueOf(arg0);
        Orientation o2 = Orientation.valueOf(arg1);
        Set<Orientation> orientations = token.propagateLaser(laserGun.getOrientation());
        assertTrue(orientations.contains(o1) && orientations.contains(o2));
    }
}
