package step;

import Classes.Block;
import Classes.Board;
import Classes.Level;
import Classes.Token;
import Classes.Utils.Coordinate;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class TokenPlacementSteps {
    Board board;
    Level level;

    Token movableToken;
    Token unmovableToken;
    boolean actionResult;

    @Given("I have a level that contains an empty board")
    public void iHaveALevelThatContainsAnEmptyBoard() {
        board = new Board(5, 5);
        level = new Level(board);
//        Printer printer = new Printer();
//        printer.draw(level);
    }

    @And("I try to place a movable token at position \\({int}, {int})")
    public void iTryToPlaceAMovableTokenAtPosition(int x, int y) {
        movableToken = new Block(true);
        Coordinate position = new Coordinate(x, y);

        actionResult = level.addAndPlaceToken(movableToken, position);
//        level.placeToken(movableToken, position);
    }

    @Then("A token should be placed at position \\({int}, {int})")
    public void tokenShouldBePlacedAtPosition(int x, int y) {
        assertTrue(actionResult);
    }

    @And("An unmovable token is placed at \\({int}, {int})")
    public void anUnmovableTokenIsPlacedAt(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        unmovableToken = new Block(false);
        actionResult = level.addAndPlaceToken(unmovableToken, coordinate);
    }

    @Then("the placement at \\({int}, {int}) should be rejected")
    public void thePlacementAtShouldBeRejected(int x, int y) {
        assertFalse(actionResult);
    }

    @When("I try to move the token from cell \\({int}, {int}) to \\({int}, {int})")
    public void iTryToMoveTheTokenFromCellTo(int arg0, int arg1, int arg2, int arg3) {
        actionResult = level.moveTokenFromTo(new Coordinate(arg0, arg1), new Coordinate(arg2, arg3));
    }

    @Then("The index of the token at position \\({int}, {int}) should be {int}")
    public void theIndexOfTheTokenAtPositionShouldBe(int arg0, int arg1, int arg2) {
        assertEquals(board.IndexOfTokenAt(new Coordinate(arg0, arg1)), arg2);
    }

    @And("Cell \\({int}, {int}) must be empty")
    public void cellMustBeEmpty(int arg0, int arg1) {
        assertTrue(board.isPositionEmpty(new Coordinate(arg0, arg1)));
    }

    @And("The action must be declined")
    public void theMovementMustBeDeclined() {
        assertFalse(actionResult);
    }

    @And("A movable token is placed at \\({int}, {int})")
    public void aMovableTokenIsPlacedAt(int arg0, int arg1) {
        Token movableToken = new Block(true);
        level.addAndPlaceToken(movableToken, new Coordinate(arg0, arg1));
    }

    @And("The action must be accepted")
    public void theMovementMustBeAccepted() {
        assertTrue(actionResult);
    }
}