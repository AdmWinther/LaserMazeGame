package Classes.Level;

import Classes.Lazer.Laser;
import Classes.Token.Token;

import java.util.Set;

public class LevelPrinter {

    public static void print(Level level) {
        System.out.println("\nLevel: " + level.getLevelName());
    }

    public static void print(Board board, Laser lazer) {
        System.out.println("\nBoard: ");
        System.out.println(board.toString());
    }

    public static void print(Set<Token> tokens) {
        System.out.println("\nTokens: ");
        int i = 0;
        for (Token token : tokens) {
            System.out.println("    " + i + ": " + token.toString());
            i++;
        }
    }

}
