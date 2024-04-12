package Classes;

import Classes.Tokens.Token;
import Classes.Utils.Coordinate;

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

    public void printTokens() {
        System.out.println("Placed Tokens:");
        StringBuilder sb = new StringBuilder();
        // null null null null null null null null null
        // null null null null null null null null null
        // null null null null null null null null null
        // null null null null null null null null null

        for (int i = 0; i < placedTokens.length; i++) {
            for (int j = 0; j < placedTokens[0].length; j++) {
                if (placedTokens[i][j] == null) {
                    sb.append("null ");
                } else {
                    sb.append(placedTokens[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());


        System.out.println("Unplaced Tokens:");
        for (Token token : unplacedTokens) {
            System.out.println(token);
        }
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

    /**
     * Returns the width of the board.
     *
     * @return The width of the board. -1 if the attributes are null.
     */
    public int getWidthX() {
        if (checkAttributes()) return -1;
        return placedTokens.length;
    }

    /**
     * Returns the height of the board.
     *
     * @return The height of the board. -1 if the attributes are null.
     */
    public int getHeightY() {
        if (checkAttributes()) return -1;
        return placedTokens[0].length;
    }

    public int getUnplacedTokensSize() {
        return unplacedTokens.size();
    }

    public Set<Token> getUnplacedTokens() {
        return unplacedTokens;
    }

    public void resetLevel() {
        for (int i = 0; i < placedTokens.length; i++) {
            for (int j = 0; j < placedTokens[0].length; j++) {
                Token token = placedTokens[i][j];
                if (token == null || !token.isMovable()) continue;
                transferTokenToUnplacedTokens(token);
            }
        }
    }
}
