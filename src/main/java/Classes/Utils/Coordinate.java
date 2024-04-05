package Classes.Utils;

public record Coordinate(int x, int y) {
    public Coordinate add(Coordinate other) {
        return new Coordinate(x() + other.x(), y() + other.y());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate other = (Coordinate) obj;
        return x() == other.x() && y() == other.y();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x()) * 31 + Integer.hashCode(y());
    }
}
