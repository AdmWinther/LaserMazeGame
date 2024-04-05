package step;

import Classes.Board;
import Classes.Level;
import Classes.Printer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterSteps {
    Board board;
    Level level;
    String printed;

    @Given("I have a board that contains a board {int} by {int}")
    public void iHaveABoardThatContainsABoardBy(int arg0, int arg1) {
        board = new Board(arg0,arg1);
        level = new Level(board);
    }

    @When("level call printer draw and pass the level to it")
    public void levelCallPrinterAndPassTheBoardToIt() {
        Printer printer = new Printer();
        this.printed = printer.draw(level);
    }

    @Then("the Printer prints the board on the screen")
    public void thePrinterPrintsTheBoardOnTheScreen() {
        String expected = "    1  2  3  4  5  6  7 \n" +
                "   _____________________\n" +
                " 1|                     |1\n" +
                " 2|                     |2\n" +
                " 3|                     |3\n" +
                " 4|                     |4\n" +
                " 5|                     |5\n" +
                " 6|                     |6\n" +
                " 7|                     |7\n" +
                "   _____________________\n" +
                "    1  2  3  4  5  6  7 \n";
        //System.out.println(expected);
        //System.out.println(this.printed);
        assertEquals(this.printed,expected);
    }
}
