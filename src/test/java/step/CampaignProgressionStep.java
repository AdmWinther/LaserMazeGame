package step;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CampaignProgressionStep {

    String username;
    int level;

    @Given("I have a username {string}")
    public void iHaveAUsername(String arg0) {
        username = arg0;
    }


    @And("My campaign progression is saved to the level {int}")
    public void myCampaignProgressionIsSavedToTheLevel(int arg0) {
        level = arg0;
        Model.Classes.Game.CampaignProgression.saveProgression(username, level);
    }

    @When("I load my campaign progression")
    public void iLoadMyCampaignProgression() {
        level = Model.Classes.Game.CampaignProgression.getProgression(username);
    }

    @Then("I should be at the level {int} of the campaign")
    public void iShouldBeAtTheLevelOfTheCampaign(int arg0) {
        assertEquals(level, arg0);
    }

    @And("I have completed the {int} of the campaign and I save the campaign progression")
    public void iHaveCompletedTheOfTheCampaignAndISaveTheCampaignProgression(int arg0) {
        level = arg0;
        Model.Classes.Game.CampaignProgression.saveProgression(username, level);
    }

    @Then("The campaign progression should be saved to the level {int}")
    public void theCampaignProgressionShouldBeSavedToTheLevelLevel(int level) {
        int progression = Model.Classes.Game.CampaignProgression.getProgression(username);
        assertEquals(level, progression);
    }


    @When("We reset the campaign progression for the user {string}")
    public void weResetTheCampaignProgressionForTheUser(String arg0) {
        Model.Classes.Game.CampaignProgression.resetProgression(arg0);
    }

    @Then("No progression should be saved for the user {string}")
    public void noProgressionShouldBeSavedForTheUser(String arg0) {
        int progression = Model.Classes.Game.CampaignProgression.getProgression(arg0);
        assertEquals(-1, progression);
    }


    @When("We reset the campaign progression for all users")
    public void weResetTheCampaignProgressionForAllUsers() {
        Model.Classes.Game.CampaignProgression.resetAllProgression();
    }


}
