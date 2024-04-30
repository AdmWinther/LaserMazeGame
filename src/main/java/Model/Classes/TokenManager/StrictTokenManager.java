package Model.Classes.TokenManager;

import Model.Classes.Token.Token;
import Model.Interfaces.TokenManager;
import Model.Classes.Utils.Coordinate;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a strict token manager
 * This class is used for levels when the laserMaze must be solved.
 */

public class StrictTokenManager implements TokenManager {
    /*
     * This class is a wrapper for the FlexibleTokenManager
     * The aim is to hide those methods of the FlexibleTokenManager that can be used to modify the tokens
     * Therefore, it only exposes the methods that are needed to solve the laserMaze
     */

    private final FlexibleTokenManager tokenManager;


    public StrictTokenManager(Token[][] placedTokens, Set<Token> unplacedTokens) {

        // Hard Copy of the placedTokens array
        Token[][] copyOfPlacedTokens = new Token[placedTokens.length][placedTokens[0].length];
        for (int i = 0; i < placedTokens.length; i++) {
            System.arraycopy(placedTokens[i], 0, copyOfPlacedTokens[i], 0, placedTokens[i].length);
        }

        // Hard Copy of the unplacedTokens set
        Set<Token> copyOfUnplacedTokens = new HashSet<>(unplacedTokens);
        this.tokenManager = new FlexibleTokenManager(copyOfPlacedTokens, copyOfUnplacedTokens);
    }


    @Override
    public void reset() {
        tokenManager.reset();
    }

    @Override
    public boolean transferTokenToPlacedTokens(Token token, Coordinate position) {
        return tokenManager.transferTokenToPlacedTokens(token, position);
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
        return tokenManager.moveToken(from, to);
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
    public Set<Coordinate> findTargetPosition() {
        return tokenManager.findTargetPosition();
    }

    @Override
    public Set<Coordinate> findCheckpointsPosition() {
        return tokenManager.findCheckpointsPosition();
    }

    @Override
    public boolean isEmpty(Coordinate coordinate) {
        return tokenManager.isEmpty(coordinate);
    }
}