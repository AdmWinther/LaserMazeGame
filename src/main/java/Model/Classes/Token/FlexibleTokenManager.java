package Model.Classes.Token;

import Model.Classes.Utils.Coordinate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class FlexibleTokenManager implements TokenManager {

    private Token[][] placedTokens;
    private Set<Token> unplacedTokens;
    private FixedTokenManager tokenManager;

    public FlexibleTokenManager(Token[][] placedTokens, Set<Token> unplacedTokens) {
        this.placedTokens = new Token[placedTokens.length][placedTokens[0].length];
        for (int i = 0; i < placedTokens.length; i++) {
            System.arraycopy(placedTokens[i], 0, this.placedTokens[i], 0, placedTokens[i].length);
        }

        // Hard Copy of the unplacedTokens set
        this.unplacedTokens = new HashSet<>(unplacedTokens);
        tokenManager = new FixedTokenManager(placedTokens,unplacedTokens);
    }


    /**
     * Add a token to the placed tokens table at the given position
     * @param token the token to place
     * @param position the given position
     * @return true if the token has been placed successfully, false otherwise
     */
    public boolean addToPlacedTokens(Token token, Coordinate position){
        if (Objects.isNull(token)) return false;
        if (Objects.isNull(position)) return false;
        if (tokenManager.checkBounds(position)) return false;
        if (tokenManager.checkAttributes()) return false;
        if (!tokenManager.isEmpty(position)) return false;

        placedTokens[position.x()][position.y()] = token;
        tokenManager = new FixedTokenManager(placedTokens,unplacedTokens);
        return true;
    }

    /**
     * Removes the placed token at given position
     * @param position the position where the token should be removed
     * @return the Token that has been removed, null otherwise
     */
    public Token removeFromPlacedTokens(Coordinate position) {
        if (Objects.isNull(position)) return null;
        if (tokenManager.checkBounds(position)) return null;
        if (tokenManager.checkAttributes()) return null;

        Token token = placedTokens[position.x()][position.y()];
        placedTokens[position.x()][position.y()] = null;
        tokenManager = new FixedTokenManager(placedTokens,unplacedTokens);
        return token;
    }

    /**
     * Add the token to the unplaced tokens Set
     * @param token the token to add
     * @return true if the token has been added
     */
    public boolean addToUnplacedTokens(Token token) {
        if (Objects.nonNull(token) && unplacedTokens.add(token)) {
            tokenManager = new FixedTokenManager(placedTokens, unplacedTokens);
            return true;
        }
        return false;
    }

    /**
     * Removes the token from the unplaced tokens Set
     * @param token the token to remove
     * @return true if the token has been removed, false otherwise
     */
    public boolean removeFromUnplacedTokens(Token token) {
    if (Objects.nonNull(token) && unplacedTokens.remove(token)) {
        tokenManager = new FixedTokenManager(placedTokens, unplacedTokens);
        return true;
    }
    return false;
    }

    @Override
    public void reset() {
        tokenManager.reset();
    }

    @Override
    public boolean transferTokenToPlacedTokens(Token token, Coordinate position) {
        return tokenManager.transferTokenToPlacedTokens(token,position);
    }

    @Override
    public boolean transferTokenToUnplacedTokens(Coordinate position) {
        return tokenManager.transferTokenToUnplacedTokens(position);
    }

    @Override
    public boolean transferTokenToUnplacedTokens(Token token) {
        return tokenManager.transferTokenToUnplacedTokens(token);
    }

    @Override
    public boolean moveToken(Coordinate from, Coordinate to) {
        return tokenManager.moveToken(from,to);
    }

    @Override
    public Token getTokenAt(Coordinate position) {
        return tokenManager.getTokenAt(position);
    }

    @Override
    public Token[][] getPlacedTokens() {
        return tokenManager.getPlacedTokens();
    }

    @Override
    public Set<Token> getUnplacedTokens() {
        return tokenManager.getUnplacedTokens();
    }

    @Override
    public int getUnplacedTokensSize() {
        return tokenManager.getUnplacedTokensSize();
    }

    @Override
    public Coordinate findLaserGunPosition() {
        return tokenManager.findLaserGunPosition();
    }

    @Override
    public Coordinate findTargetPosition() {
        return tokenManager.findTargetPosition();
    }
}