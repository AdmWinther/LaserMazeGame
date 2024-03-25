package Classes.Game;
import Classes.Utils.DataReader;
public class Game {
    private Player player;
    public static void main(String[] args) {
        System.out.println("This is the main menu.");
        String content = DataReader.Read("./text.txt");
        System.out.println(content);
    }
}
