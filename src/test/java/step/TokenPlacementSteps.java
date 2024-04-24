package step;

import Model.Classes.Level.PlayableLevel;
import Model.Classes.Token.Token;


public class TokenPlacementSteps {
    PlayableLevel level;

    Token movableToken;
    Token unmovableToken;
    /*

    @Given("I have a level that contains an empty board")
    public void iHaveALevelThatContainsAnEmptyBoard() {
        board = new Board(5, 5);
        level = new Level(board);
        Printer printer = new Printer();
        printer.draw(level);
    }

    @Given("I have a level that contains a board with a movable token placed at \\({int}, {int})")
    public void iHaveALevelThatContainsABoardWithAMovableTokenPlacedAt(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        board = new Board(5, 5);
        movableToken = new Block(true);
        board.placeToken(movableToken, coordinate);
        level = new Level(board);
    }

    @Given("I have a level that contains a board with an unmovable token placed at \\({int}, {int})")
    public void iHaveALevelThatContainsABoardWithAnUnmovableTokenPlacedAt(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        unmovableToken = new Block(false);
        board = new Board(5, 5);
        board.placeToken(unmovableToken, coordinate);
        level = new Level(board);
    }

    @And("I try to place a movable token at position \\({int}, {int})")
    public void iTryToPlaceAMovableTokenAtPosition(int x, int y) {
        movableToken = new Block(true);
        Coordinate position = new Coordinate(x, y);

        level.addToken(movableToken);
        level.placeToken(movableToken, position);
    }

    @And("The board should reflect the change")
    public void theBoardShouldReflectTheChange() {
        Printer printer = new Printer();
        printer.draw(level);
    }


    @When("I select an unmovable token at position \\({int}, {int})")
    public void iSelectAnUnmovableTokenAtPosition(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        unmovableToken = board.getTokenAt(coordinate);
    }

    @When("I try to move the token from cell \\({int}, {int}) to \\({int}, {int})")
    public void iTryToMoveTheTokenFromCellTo(int x1, int y1, int x2, int y2) {
        Coordinate from = new Coordinate(x1, y1);
        Coordinate to = new Coordinate(x2, y2);
        level.moveTokenFromTo(from, to);
    }

    @Then("the placement at \\({int}, {int}) should be rejected")
    public void thePlacementAtShouldBeRejected(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        assertFalse(level.placeToken(movableToken, coordinate));
    }

    @Then("A token should be placed at position \\({int}, {int})")
    public void tokenShouldBePlacedAtPosition(int x, int y) {
        //assertFalse(board.isPositionEmpty(new Coordinate(x, y)));
    }

    @And("Cell \\({int}, {int}) must be empty")
    public void cellMustBeEmpty(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        assertTrue(board.isPositionEmpty(coordinate));
    }

    @Then("Cell \\({int}, {int}) must be occupied by the unmovable token")
    public void cellMustBeOccupiedByTheUnmovableToken(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        assertEquals(unmovableToken, board.getTokenAt(coordinate));
    }

    @Then("Cell \\({int}, {int}) must be occupied by the movable token")
    public void cellMustBeOccupiedByTheMovableToken(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        assertEquals(movableToken, board.getTokenAt(coordinate));
    }

    @Then("the cell \\({int}, {int}) should be occupied by the unmovable token")
    public void theCellShouldBeOccupiedByTheUnmovableToken(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y);
        assertEquals(unmovableToken, board.getTokenAt(coordinate));
    }
     */
}