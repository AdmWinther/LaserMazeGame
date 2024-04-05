package step;

import Classes.Block;
import Classes.Board;
import Classes.Level;
import Classes.Token;
import Classes.Printer;
import Classes.Utils.Coordinate;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenPlacementSteps {
    Board board;
    Level level;

    Token movableToken;
    Token unmovableToken;

    @Given("I have a level that contains an empty board")
    public void iHaveALevelThatContainsAnEmptyBoard() {
        board = new Board(5, 5);
        level = new Level(board);
        Printer printer = new Printer();
        printer.draw(level);
    }

    @And("I try to place a movable token at position \\({int}, {int})")
    public void iTryToPlaceAMovableTokenAtPosition(int x, int y) {
        movableToken = new Block(true);
        Coordinate position = new Coordinate(x, y);

        level.addToken(movableToken);
        level.placeToken(movableToken, position);
    }

    @Then("A token should be placed at position \\({int}, {int})")
    public void tokenShouldBePlacedAtPosition(int x, int y) {
        assertFalse(board.isPositionEmpty(new Coordinate(x, y)));
    }

    @And("The board should reflect the change")
    public void theBoardShouldReflectTheChange() {
        Printer printer = new Printer();
        printer.draw(level);
    }


    @Given("I have a level that contains a board with an unmovable token placed at \\({int}, {int})")
    public void iHaveALevelThatContainsABoardWithAUnmovableTokenPlacedAt(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        board = new Board(5, 5);
        level = new Level(board);
        unmovableToken = new Block(false);
        level.placeToken(unmovableToken, coordinate);
    }

    @Then("the placement at \\({int}, {int}) should be rejected")
    public void thePlacementAtShouldBeRejected(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        assertNotEquals(board.tokenAt(coordinate), movableToken);
    }

    @When("I select an unmovable token at position \\({int}, {int})")
    public void iSelectAnUnmovableTokenAtPosition(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        unmovableToken = board.tokenAt(coordinate);
    }

    @Then("The token at position \\({int}, {int}) should still be there")
    public void theTokenAtPositionShouldStillBeThere(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        assertEquals(unmovableToken, board.tokenAt(coordinate));
    }
}