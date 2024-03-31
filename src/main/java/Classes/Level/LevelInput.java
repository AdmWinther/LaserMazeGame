package Classes.Level;

import Classes.Utils.Coordinate;

import java.util.Scanner;

public class LevelInput {

    /**
     * Select a token
     *
     * @return int - Token Index selected
     * @author Léonard Amsler - s231715
     */
    public static int selectToken() {
        int result = -1;

        while (result < 0) {
            System.out.println("\nYou need to select a token (0-n)");

            // Get the user input
            Scanner scanner = new Scanner(System.in);
            try {
                result = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }

        return result;
    }

    /**
     * Select a coordinate
     *
     * @return Coordinate - Coordinate selected
     * @author Léonard Amsler - s231715
     */
    public static Coordinate getNewPosition() {
        Coordinate result = new Coordinate(-1, -1);

        while (result.x() < 0 || result.y() < 0) {
            System.out.println("\nYou need to select a coordinate (Ex: 0 0)");

            // Get the user input
            Scanner scanner = new Scanner(System.in);
            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                result = new Coordinate(x, y);
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }

        return result;
    }

    /**
     * Select an orientation
     *
     * @return Orientation - Orientation selected
     * @author Léonard Amsler - s231715
     */
    public static int selectOrientation() {
        int result = -1;

        while (result < 0) {
            System.out.println("\nYou need to select an orientation (UP=0, DOWN=1, LEFT=2, RIGHT=3)");

            // Get the user input
            Scanner scanner = new Scanner(System.in);
            try {
                result = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }

        return result;
    }

}
