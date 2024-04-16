package Controller;

import Model.Classes.Level;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LevelController {

    Level level;

    public LevelController(Level level) {
        this.level = level;
    }

    public int getWidth() {
        return level.getWidth();
    }

    public int getHeight() {
        return level.getHeight();
    }

    public Set<Token> getUnplacedTokens() {
        return level.tokenManager().getUnplacedTokens();
    }

    public Map<Coordinate, Token> getPlacedTokens() {
        Token[][] tokens = level.tokenManager().getPlacedTokens();

        Map<Coordinate, Token> placedTokens = new HashMap<>();
        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0; j < tokens[0].length; j++) {
                if (tokens[i][j] != null) {
                    placedTokens.put(new Coordinate(i, j), tokens[i][j]);
                }
            }
        }

        return placedTokens;
    }

    public Token getTokenAtCoordinate(Coordinate coordinate) {
        return level.tokenManager().getTokenAt(coordinate);
    }

    public void resetLevel() {
        level.resetLevel();
    }

    public void moveToken(Coordinate from, Coordinate to) {
        level.tokenManager().moveToken(from, to);
    }

    public void placeToken(Token token, Coordinate coordinate) {
        level.tokenManager().transferTokenToPlacedTokens(token, coordinate);
    }

    public void removeToken(Token token) {
        level.tokenManager().transferTokenToUnplacedTokens(token);
    }

}
