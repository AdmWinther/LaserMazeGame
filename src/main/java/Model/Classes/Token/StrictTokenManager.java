package Model.Classes.Token;

import Model.Classes.Utils.Coordinate;

import java.util.HashSet;
import java.util.Set;


public class StrictTokenManager extends TokenManager {


    public StrictTokenManager(Token[][] placedTokens, Set<Token> unplacedTokens) {
        super(placedTokens,unplacedTokens);
    }

    @Override
    public boolean addToPlacedTokens(Token token, Coordinate position) {
        return false;
    }

    @Override
    public Token removeFromPlacedTokens(Coordinate position) {
        return null;
    }

    @Override
    public boolean addToUnplacedTokens(Token token) {
        return false;
    }

    @Override
    public boolean removeFromUnplacedTokens(Token token) {
        return false;
    }
}