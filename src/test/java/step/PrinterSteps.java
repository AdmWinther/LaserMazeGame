package step;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterSteps {
    /*
    Board board;
    Level level;
    String printed;

    @Given("I have an empty board that contains a board {int} by {int}")
    public void iHaveABoardThatContainsABoardBy(int arg0, int arg1) {
        board = new Board(arg0, arg1);
        level = new Level(board);
    }

    @When("level call printer draw and pass the level to it")
    public void levelCallPrinterAndPassTheBoardToIt() {
        Printer printer = new Printer();
        this.printed = printer.draw(level);
    }

    @Then("the Printer prints the empty board")
    public void thePrinterPrintsTheBoardOnTheScreen() {
        String expected = """
                    0  1  2  3  4  5  6\s
                   _____________________
                 0|                     |0
                 1|                     |1
                 2|                     |2
                 3|                     |3
                 4|                     |4
                 5|                     |5
                 6|                     |6
                   _____________________
                    0  1  2  3  4  5  6\s
                """;
        assertEquals(this.printed, expected);
    }

    @And("a block token is placed on the cell {int} and {int}")
    public void aBlockTokenIsPlacedOnTheCellAnd(int arg0, int arg1) {
        Token movableToken = new Block(true);
<<<<<<< HEAD
        level.addAndPlaceToken(movableToken, new Coordinate(arg0, arg1));
=======
        level.addToken(movableToken);
        level.placeToken(movableToken, new Coordinate(arg0,arg1));
>>>>>>> origin/dataRetrievalTests
    }

    @Then("the Printer prints the board with a block token on the cell {int} and {int}")
    public void thePrinterPrintsTheBoardWithABlockTokenOnTheCellAnd(int arg0, int arg1) {
        String expected = """
                    0  1  2  3  4  5  6\s
                   _____________________
                 0|                     |0
                 1|                     |1
                 2|                     |2
                 3|                     |3
                 4|      BBB            |4
                 5|                     |5
                 6|                     |6
                   _____________________
                    0  1  2  3  4  5  6\s
                """;
        //System.out.println(expected);
        //System.out.println(this.printed);
        assertEquals(this.printed, expected);
    }
     */
}
