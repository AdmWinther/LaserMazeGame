package Model.Classes.Token;

import Model.Classes.Utils.Coordinate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class FixedTokenManager implements TokenManager {

    private final Token[][] placedTokens;
    private final Set<Token> unplacedTokens;


    public FixedTokenManager(Token[][] placedTokens, Set<Token> unplacedTokens) {

        // Hard Copy of the placedTokens array
        this.placedTokens = new Token[placedTokens.length][placedTokens[0].length];
        for (int i = 0; i < placedTokens.length; i++) {
            System.arraycopy(placedTokens[i], 0, this.placedTokens[i], 0, placedTokens[i].length);
        }

        // Hard Copy of the unplacedTokens set
        this.unplacedTokens = new HashSet<>(unplacedTokens);
    }
    

    /**
     * Checks if the position is out of bounds.
     * @param position The position to be checked.
     * @return True if the position is out of bounds, false otherwise.
     */
    public  boolean checkBounds(Coordinate position) {
        if (position == null) return true;
        return position.x() < 0
                || position.x() >= placedTokens.length
                || position.y() < 0
                || position.y() >= placedTokens[0].length;
    }

    @Override
    public boolean isEmpty(Coordinate position) {
        return placedTokens[position.x()][position.y()] == null;
    }

    /**
     * Checks if the attributes of the simpleTokenManager class are null.
     * @return True if any of the attributes is null, false otherwise.
     */
    public boolean checkAttributes() {
        return (placedTokens == null) || (unplacedTokens == null);
    }

    @Override
    public boolean transferTokenToPlacedTokens(Token token, Coordinate position) {
        if (checkAttributes()) return false;
        if (checkBounds(position)) return false;
        if (!isEmpty(position)) return false;
        if (!unplacedTokens.remove(token)) return false;

        placedTokens[position.x()][position.y()] = token;
        return true;
    }


    @Override
    public boolean transferTokenToUnplacedTokens(Coordinate position) {
        if (checkAttributes()) return false;
        if (checkBounds(position)) return false;
        if (isEmpty(position)) return false;

        Token token = placedTokens[position.x()][position.y()];
        placedTokens[position.x()][position.y()] = null;
        return unplacedTokens.add(token);
    }


    @Override
    public boolean transferTokenToUnplacedTokens(Token token) {
        if (checkAttributes()) return false;
        if (token == null) return false;

        for (int i = 0; i < placedTokens.length; i++) {
            for (int j = 0; j < placedTokens[0].length; j++) {
                if (placedTokens[i][j] == token && token.isMovable()) {
                    placedTokens[i][j] = null;
                    return unplacedTokens.add(token);
                }
            }
        }
        return false;
    }

    @Override
    public Token getTokenAt(Coordinate position) {
        if (checkAttributes()) return null;
        if (checkBounds(position)) return null;
        return placedTokens[position.x()][position.y()];
    }

    @Override
    public boolean moveToken(Coordinate from, Coordinate to) {
        if (checkAttributes()) {
            System.out.println("Attributes non valid");
            return false;
        }
        if (checkBounds(from) || checkBounds(to)) {
            System.out.println("Bounds non valid");
            return false;
        }
        if (isEmpty(from) || !isEmpty(to)) {
            System.out.println("Token at from: " + getTokenAt(from) + "and at to: " + getTokenAt(to));
            System.out.println("Positions non valid");
            return false;
        }

        Token token = placedTokens[from.x()][from.y()];
        if (!token.isMovable()) {
            System.out.println("Token is not movable");
        }

        placedTokens[from.x()][from.y()] = null;
        placedTokens[to.x()][to.y()] = token;
        return true;
    }

    @Override
    public int getUnplacedTokensSize() {
        return unplacedTokens.size();
    }

    /**
     * @return a copy of the unplacedTokens set.
     */
    @Override
    public Set<Token> getUnplacedTokens() {
        // returning a copy of the unplacedTokens set
        return new HashSet<>(unplacedTokens);
    }

    @Override
    public void reset() {
        for (int i = 0; i < placedTokens.length; i++) {
            for (int j = 0; j < placedTokens[0].length; j++) {
                Token token = placedTokens[i][j];
                if (token == null || !token.isMovable()) continue;
                    placedTokens[i][j] = null;
                    unplacedTokens.add(token);

            }
        }
    }

    @Override
    public Token[][] getPlacedTokens() {
        // return a copy of the placedTokens array
        return placedTokens.clone();
    }

    /**
     * Helper function to find the position of a specific token type
     * @param type token type to find
     * @return the Coordinate of that type if found,null if not
     */
    private Coordinate findTypePosition(Class<? extends Token> type) {
        for (int x = 0; x < placedTokens.length; x++) {
            for (int y = 0; y < placedTokens[0].length; y++) {
                if (placedTokens[x][y] != null && placedTokens[x][y].getClass() == type) {
                    return new Coordinate(x, y);
                }
            }
        }

        return null;
    }

    @Override
    public Coordinate findLaserGunPosition() {
        return findTypePosition(LaserGun.class);
    }

    @Override
    public Coordinate findTargetPosition() {
        return findTypePosition(Target.class);
    }


}
