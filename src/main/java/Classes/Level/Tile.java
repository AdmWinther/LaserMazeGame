package Classes.Level;

import Classes.Token.Orientation;
import Classes.Token.Token;
import Classes.Utils.Coordinate;
import Interfaces.LaserPropagator;
import java.util.Set;

public class Tile implements LaserPropagator {

    private Token token;
    private Coordinate coordinate;

    public Tile(Token token, Coordinate coordinate) {
        this.token = token;
        this.coordinate = coordinate;
    }

    /**
     * Checks if the tile has a token, useful for the laser propagation
     * @return true if the tile has a token on it
     */
    public boolean hasToken(){
        return token!=null;
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation from) {
        if (!hasToken() ){
            return Set.of(from);
        } else return token.propagateLaser(from);
    }

    /**
     * Getter for the token of the tile
     * @return token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Setter for the token
     * @param token
     */
    public void setToken(Token token){
        this.token = token;
    }

    /**
     * Getter for the coordinates of the tile
     * @return coordinates
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
}
