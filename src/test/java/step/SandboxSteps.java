package step;

import Model.Classes.Level.EditableLevel;
import Model.Classes.Token.*;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Interfaces.Inventory;
import Model.constants.SandboxInventory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class SandboxSteps {

    EditableLevel level;
    Token token;
    Coordinate tokenCoordinate;
    Inventory inventory = new SandboxInventory();


    @Given("I have an empty sandbox level with all the possible tokens in my inventory")
    public void iHaveAnEmptySandboxLevelWithAllThePossibleTokensInMyInventory() {
        Token[][] placedTokens = new Token[5][5];
        Set<Token> unplacedTokens = new HashSet<>();
        level = new EditableLevel("test_level",placedTokens,unplacedTokens,inventory);

    }

    @When("I place a mirror token on position \\({int} {int})")
    public void iPlaceAMirrorTokenOnPosition(int x, int y) {
        token = new OneSidedMirror(true, Orientation.UP);
        tokenCoordinate = new Coordinate(x,y);
        level.tokenManager().addToPlacedTokens(token,tokenCoordinate);
    }

    @Then("the deck should be empty")
    public void theDeckShouldBeEmpty() {
        assertEquals(0,level.tokenManager().getUnplacedTokensSize());
    }

    @And("my board should contain a mirror token at the \\({int} {int}) position")
    public void myBoardShouldContainAMirrorTokenAtThePosition(int x, int y) {
        assertTrue(level.tokenManager().getTokenAt(new Coordinate(x,y)) instanceof OneSidedMirror);
    }

    @When("I place a mirror token in the deck")
    public void iPlaceAMirrorTokenInTheDeck() {
        level.tokenManager().addToUnplacedTokens(new OneSidedMirror(true,Orientation.UP));
    }

    @Then("the deck should contain the mirror")
    public void theDeckShouldContainTheMirror() {
        assertTrue(level.tokenManager().getUnplacedTokens().
                stream().anyMatch(c-> c instanceof OneSidedMirror));
    }

    @And("the board should be empty")
    public void theBoardShouldBeEmpty() {
        Token[][] placedTokens = level.tokenManager().getPlacedTokens();
        assertTrue(Arrays.stream(placedTokens).flatMap(Arrays::stream).allMatch(Objects::isNull));
    }

    @Given("I have a sandbox level and a mirror token placed at \\({int} {int})")
    public void iHaveASandboxLevelAndAMirrorTokenPlacedAt(int x, int y) {
        Token[][] placedTokens = new Token[5][5];
        token =  new OneSidedMirror(true,Orientation.UP);
        tokenCoordinate = new Coordinate(x,y);
        placedTokens[x][y] = token;
        Set<Token> unplacedTokens = new HashSet<>();
        level = new EditableLevel("test_level",placedTokens,unplacedTokens,inventory);
    }

    @When("I move the same mirror token in the deck")
    public void iMoveTheSameMirrorTokenInTheDeck() {
        level.tokenManager().transferTokenToUnplacedTokens(tokenCoordinate);
    }

    @Given("I have a sandbox level and a mirror token in my deck")
    public void iHaveASandboxLevelAndAMirrorTokenInMyDeck() {
        Token[][] placedTokens = new Token[5][5];
        token = new OneSidedMirror(true,Orientation.UP);
        Set<Token> unplacedTokens = new HashSet<>(){{add(token);}};
        level = new EditableLevel("test_level",placedTokens,unplacedTokens,inventory);
    }

    @When("I move the mirror from the deck to the board at position \\({int} {int})")
    public void iMoveTheMirrorFromTheDeckToTheBoardAtPosition(int x, int y) {
        tokenCoordinate = new Coordinate(x,y);
        level.tokenManager().transferTokenToPlacedTokens(token,tokenCoordinate);
    }

    @When("I delete the mirror")
    public void iDeleteTheMirror() {
        level.tokenManager().removeFromUnplacedTokens(token);
    }

    @When("I delete the mirror at \\({int} {int})")
    public void iDeleteTheMirrorAt(int x, int y) {
        level.tokenManager().removeFromPlacedTokens(new Coordinate(x,y));
    }

    @Given("a sandbox level with a laser gun place at \\({int} {int}) and an empty deck")
    public void aSandboxLevelWithALaserGunPlaceAtAndAnEmptyDeck(int x, int y) {
        Token[][] placedTokens = new Token[5][5];
        placedTokens[x][y] = new LaserGun(true,Orientation.UP);
        Set<Token> deck = new HashSet<>();
        level = new EditableLevel("test_level",placedTokens, deck,inventory);
    }


    @When("I try to place another laser gun at \\({int} {int})")
    public void iTryToPlaceAnotherLaserGunAt(int x, int y) {
        level.tokenManager().addToPlacedTokens(new LaserGun(true,Orientation.UP),
                new Coordinate(x,y));
    }
    @Then("the board should still contain one laser gun")
    public void theBoardShouldStillContainOneLaserGun() {
        Token[][] placedTokens = level.tokenManager().getPlacedTokens();
        assertEquals(1, Arrays.stream(placedTokens).flatMap(Arrays::stream).filter(t -> t instanceof LaserGun).count());
    }

    @Given("a sandbox level with a target placed at \\({int} {int}) and an empty deck")
    public void aSandboxLevelWithATargetPlaceAtAndAnEmptyDeck(int x, int y) {
        Token[][] placedTokens = new Token[5][5];
        placedTokens[x][y] = new Target(true,Orientation.UP);
        Set<Token> deck = new HashSet<>();
        level = new EditableLevel("test_level",placedTokens, deck,inventory);
    }

    @When("I try to place another target at \\({int} {int})")
    public void iTryToPlaceAnotherTargetAt(int x, int y) {
        level.tokenManager().addToPlacedTokens(new Target(true,Orientation.UP),
                new Coordinate(x,y));
    }


    @Then("the board should still contain one target")
    public void theBoardShouldStillContainOneTarget() {
        Token[][] placedTokens = level.tokenManager().getPlacedTokens();
        assertEquals(1, Arrays.stream(placedTokens).flatMap(Arrays::stream).filter(t -> t instanceof Target).count());
    }

}
