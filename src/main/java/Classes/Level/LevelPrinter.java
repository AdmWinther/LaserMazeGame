package Classes.Level;

import Classes.Lazer.Lazer;
import Classes.Token.Token;

import java.util.Set;

public class LevelPrinter {

    public static void print(Level level) {
        System.out.println("Printing level");
    }

    public static void print(Board board, Lazer lazer) {
        System.out.println("Printing board and lazer");
    }

    public static void print(Set<Token> tokens) {
        System.out.println("Printing tokens");
    }

}
