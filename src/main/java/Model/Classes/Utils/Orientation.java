package Model.Classes.Utils;

public enum Orientation {
    /**
     * Up orientation
     */
    UP("UP"),

    /**
     * Right orientation
     */
    RIGHT("RIGHT"),

    /**
     * Down orientation
     */
    DOWN("DOWN"),

    /**
     * Left orientation
     */
    LEFT("LEFT");

    private final String value;

    Orientation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
