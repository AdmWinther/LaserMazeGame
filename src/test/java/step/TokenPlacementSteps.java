package java.step;

import Classes.Token.Block;
import Classes.Board;
import Classes.Level;
import Classes.Token.Token;
import Classes.Token.Block;
import Classes.Utils.Coordinate;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TokenPlacementSteps {
    Board board = new Board(4, 4);
    Level level = new Level(board);

    @Given("I have a level that contains a board")
    public void iHaveALevelThatContainsABoard() {

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

}
