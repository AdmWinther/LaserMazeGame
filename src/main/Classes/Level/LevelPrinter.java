package Classes.Level;

import Classes.Lazer.Lazer;
import Classes.Token.Token;

import java.util.Set;

public class LevelPrinter {

    public static void printLevel(Level level) {
        System.out.println("Printing level");
    }

    public static void printBoardAndLazer(Board board, Lazer lazer) {
        System.out.println("Printing board and lazer");
    }

    public static void printTokens(Set<Token> tokens) {
        System.out.println("Printing tokens");
    }

}
