package Classes.Level;

import Classes.Lazer.Lazer;
import Classes.Token.Token;

import java.util.Set;

public class LevelPrinter {
    public void Print(Level level){
        System.out.println("I am printing the board.");
        for(int i = 0; i < Board.getHEIGHT())
    }

    public void Print(Level level, Lazer laser){
        System.out.println("I am printing the board and the laser.");
    }

    public void Print(Set<Token> tokens){
        System.out.println("I am printing the tokens");
    }
    
}
