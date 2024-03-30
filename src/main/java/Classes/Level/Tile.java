package Classes.Level;

import Classes.Token.Orientation;
import Classes.Token.Token;
import Classes.Utils.Coordinate;

import java.util.Set;

public class Tile {

    private Coordinate coordinate;
    private Token token;

    public Tile(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Tile(Coordinate coordinate, Token token) {
        this.coordinate = coordinate;
        this.token = token;
    }

    public Token getToken() {
        return null;
    }

    public Set<Orientation> propagateLazer(Orientation orientation) {
        return null;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
