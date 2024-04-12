package Classes.Utils;

public enum Orientation {
    UP("UP"),
    DOWN("DOWN"),
    LEFT("LEFT"),
    RIGHT("RIGHT");

    private final String value;

    Orientation(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return this.value;
    }


}