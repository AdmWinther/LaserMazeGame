package Classes.Utils;

public record Coordinate(int x, int y) {
    public Coordinate add(Coordinate other) {
        return new Coordinate(x() + other.x(), y() + other.y());
    }
}
