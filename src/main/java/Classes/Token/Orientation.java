package Classes.Token;

public enum Orientation {
    /**
     * Up orientation
     */
    UP("U"),

    /**
     * Down orientation
     */
    DOWN("D"),

    /**
     * Left orientation
     */
    LEFT("L"),

    /**
     * Right orientation
     */
    RIGHT("R");

    private final String value;

    Orientation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
