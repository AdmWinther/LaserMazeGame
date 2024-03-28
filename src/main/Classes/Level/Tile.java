package Classes.Level;

import Classes.Token.Token;
import Classes.Utils.Coordinate;

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
    
}
