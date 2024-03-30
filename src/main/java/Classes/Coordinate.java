package main.java.Classes;

public class Coordinate {
    private int x;
    private int y;

    /**
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x coordinate
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for x coordinate
     * @param x new x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for y coordinate
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for y coordinate
     * @param y new y
     */
    public void setY(int y) {
        this.x = x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return Integer.compare(other.x, x) == 0 && Integer.compare(other.y, y) == 0;
    }


}
