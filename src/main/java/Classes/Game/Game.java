package Classes.Game;
import Classes.Utils.DataReader;
public class Game implements Runnable {
    private Player player;
    public static void main(String[] args) {
        System.out.println("This is the main menu.");
        String content = DataReader.Read("./text.txt");
        System.out.println(content);
    }

    @Override
    public void run() {
        //todo; add the actions required to run!
    }

    public void stop() {
        //Todo: add the actions required on exit.
    }
}
