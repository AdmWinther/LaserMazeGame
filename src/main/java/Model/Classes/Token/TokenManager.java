package Model.Classes.Token;

import Model.Classes.Utils.Coordinate;

import java.util.Set;

public interface TokenManager {


    void reset();
    boolean transferTokenToPlacedTokens(Token token, Coordinate position);
    boolean transferTokenToUnplacedTokens(Coordinate position);
    boolean transferTokenToUnplacedTokens(Token token);
    boolean moveToken(Coordinate from, Coordinate to);

    Token getTokenAt(Coordinate position);
    Token[][] getPlacedTokens();
    Set<Token> getUnplacedTokens();
    int getUnplacedTokensSize();

    Coordinate findLaserGunPosition();
    Coordinate findTargetPosition();
    boolean isEmpty(Coordinate coordinate);
}
