package Classes.Utils;

public class Coordinate {

    /**
     * X coordinate
     */
    private final int x;

    /**
     * Y coordinate
     */
    private final int y;

    /**
     * Default constructor
     */
    public Coordinate() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Parameterized constructor
     *
     * @param x int - X coordinate
     * @param y int - Y coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the X coordinate
     *
     * @return int - X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y coordinate
     *
     * @return int - Y coordinate
     */
    public int getY() {
        return y;
    }

}
