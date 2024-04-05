package step;

import Classes.Token.Block;
import Classes.Board;
import Classes.Level;
import Classes.Token.Token;
import Classes.Utils.Coordinate;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TokenPlacementSteps {
    Board board;
    Level level;

    @Given("I have a level that contains an empty board")
    public void iHaveALevelThatContainsAnEmptyBoard() {
        board = new Board(5, 5);
        level = new Level(board);
        Printer.draw(Level);
    }

    @And("I try to place a token at position \\({int}, {int})")
    public void iTryToPlaceATokenToPosition(int x, int y) {
        Token token = new Block(true);
        level.addToken(token);

        Coordinate position = new Coordinate(x, y);

        board.placeToken(token, position);
    }

    @Then("A token should be placed at position \\({int}, {int})")
    public void tokenShouldBePlacedAtPosition(int x, int y) {
        assertFalse(board.isPositionEmpty(new Coordinate(x, y)));
    }

    @And("The board should reflect the change")
    public void theBoardShouldReflectTheChange() {
        Printer.draw(Level);
    }


}
