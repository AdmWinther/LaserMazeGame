package Model.Classes.Token;

import Model.Classes.Utils.Coordinate;

import java.util.HashSet;
import java.util.Set;


public class TokenManager {

    private final Token[][] placedTokens;
    private Set<Token> unplacedTokens;


    public TokenManager(Token[][] placedTokens, Set<Token> unplacedTokens) {

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
     *
     * @param position The position to be checked.
     * @return True if the position is out of bounds, false otherwise.
     */
    private boolean checkBounds(Coordinate position) {
        if (position == null) return true;
        return position.x() < 0
                || position.x() >= placedTokens.length
                || position.y() < 0
                || position.y() >= placedTokens[0].length;
    }

    public boolean isEmpty(Coordinate position) {
        return placedTokens[position.x()][position.y()] == null;
    }

    /**
     * Checks if the attributes of the TokenManager class are null.
     *
     * @return True if any of the attributes is null, false otherwise.
     */
    private boolean checkAttributes() {
        return (placedTokens == null) || (unplacedTokens == null);
    }

    public boolean transferTokenToPlacedTokens(Token token, Coordinate position) {
        if (checkAttributes()) return false;
        if (checkBounds(position)) return false;
        if (!isEmpty(position)) return false;
        if (!unplacedTokens.remove(token)) return false;

        placedTokens[position.x()][position.y()] = token;
        return true;
    }

    /**
     * Transfers a token to the level at the specified position.
     *
     * @param position The position on the board where the token should be transferred.
     * @implNote This method is RECOMMENDED compared to the method with the Token instance parameter.
     */
    public boolean transferTokenToUnplacedTokens(Coordinate position) {
        if (checkAttributes()) return false;
        if (checkBounds(position)) return false;
        if (isEmpty(position)) return false;

        Token token = placedTokens[position.x()][position.y()];
        placedTokens[position.x()][position.y()] = null;
        return unplacedTokens.add(token);
    }

    /**
     * Transfers a token from the board to the level.
     *
     * @param token The token to be transferred.
     * @implNote This method is NOT RECOMMENDED compared to the method with the Coordinate instance parameter.
     */
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

    public Token getTokenAt(Coordinate position) {
        if (checkAttributes()) return null;
        if (checkBounds(position)) return null;
        return placedTokens[position.x()][position.y()];
    }

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

    public int getUnplacedTokensSize() {
        return unplacedTokens.size();
    }

    /**
     * @return a copy of the unplacedTokens set.
     */
    public Set<Token> getUnplacedTokens() {
        // returning a copy of the unplacedTokens set
        return new HashSet<>(unplacedTokens);
    }

    public void reset() {
        for (Token[] placedToken : placedTokens) {
            for (int j = 0; j < placedTokens[0].length; j++) {
                Token token = placedToken[j];
                if (token == null || !token.isMovable()) continue;
                transferTokenToUnplacedTokens(token);
            }
        }
    }

    /**
     *
     * @return a copy of the placedTokens array
     */
    public Token[][] getPlacedTokens() {
        // return a copy of the placedTokens array
        return placedTokens.clone();
    }


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

    public Coordinate findLaserGunPosition() {
        return findTypePosition(LaserGun.class);
    }

    public Coordinate findTargetPosition() {
        return findTypePosition(Target.class);
    }

}
