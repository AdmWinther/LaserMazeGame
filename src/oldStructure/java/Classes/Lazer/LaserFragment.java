package Classes.Lazer;

import Classes.Utils.Coordinate;

public class LaserFragment {
    private Coordinate from;
    private Coordinate to;

    // Constructor
    public LaserFragment(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    // Getter for from coordinate
    public Coordinate getFrom() {
        return from;
    }

    // Setter for from coordinate
    public void setFrom(Coordinate from) {
        this.from = from;
    }

    // Getter for to coordinate
    public Coordinate getTo() {
        return to;
    }

    // Setter for to coordinate
    public void setTo(Coordinate to) {
        this.to = to;
    }

}
