package Classes.Game;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class PlayerTest extends TestCase {

    private Player myPlayer;
    public void testEmptyConstructor(){
        this.myPlayer = new Player();
        String name = myPlayer.getName();
        assertEquals(name, "No Name");
    }

    public void testConstructorWithName(){
        this.myPlayer = new Player("John");
        String name = this.myPlayer.getName();
        assertEquals(name, "John");
    }

    public void testSetName(){
        this.myPlayer = new Player();
        myPlayer.setName("Jack");
        assertEquals(myPlayer.getName(), "Jack");
    }

    public void testSetLevelGetLevel(){
        this.myPlayer = new Player();
        this.myPlayer.setLevel(5);
        assertEquals(5, myPlayer.getLevel());
    }
}
