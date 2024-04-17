package Model.Classes.Utils;

public enum Orientation {
    /**
     * Up orientation
     */
    UP("UP"),

    /**
     * Down orientation
     */
    DOWN("DOWN"),

    /**
     * Left orientation
     */
    LEFT("LEFT"),

    /**
     * Right orientation
     */
    RIGHT("RIGHT");

    private final String value;

    Orientation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
