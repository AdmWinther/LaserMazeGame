package Model.Classes.TokenManager;

import Model.Classes.Token.Checkpoint;
import Model.Classes.Token.LaserGun;
import Model.Classes.Token.Target;
import Model.Classes.Token.Token;
import Model.Interfaces.TokenManager;
import Model.Classes.Utils.Coordinate;
import Model.Interfaces.Inventory;


import java.util.*;


public class FlexibleTokenManager implements TokenManager {

    boolean placedLaser;

    private final Token[][] placedTokens;
    private final Set<Token> unplacedTokens;

    private Inventory inventory;

    public FlexibleTokenManager(Token[][] placedTokens, Set<Token> unplacedTokens) {
        if(!correctInput(placedTokens)) throw new IllegalArgumentException();
        this.placedTokens = placedTokens;
        this.unplacedTokens = unplacedTokens;
    }

    public FlexibleTokenManager(Token[][] placedTokens, Set<Token> unplacedTokens, Inventory inventory) {
        this(placedTokens, unplacedTokens);
        this.inventory = inventory;
    }

    /**
     * Set all placed tokens to unmovable
     */
    public void setPlacedTokensMovability(boolean movability) {
        for (Token[] row : placedTokens) {
            for (Token token : row) {
                if (token != null) {
                    token.setMovable(movability);
                }
            }
        }
    }

    /**
     * Set all unplaced tokens to movable
     */
    public void setUnplacedTokensMovability(boolean movability) {
        for (Token token : unplacedTokens) {
            token.setMovable(movability);
        }
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
        if (checkBounds(position)) return false;
        if (checkAttributes()) return false;
        if (!isEmpty(position)) return false;
        if (placedLaser && token instanceof LaserGun) return false;

        if (token instanceof LaserGun) placedLaser = true;

        placedTokens[position.x()][position.y()] = token;
        return true;
    }

    /**
     * Removes the placed token at given position
     * @param position the position where the token should be removed
     * @return the Token that has been removed, null otherwise
     */
    public Token removeFromPlacedTokens(Coordinate position) {
        if (Objects.isNull(position)) return null;
        if (checkBounds(position)) return null;
        if (checkAttributes()) return null;


        Token token = placedTokens[position.x()][position.y()];
        placedTokens[position.x()][position.y()] = null;

        if (token instanceof LaserGun) placedLaser = false;

        return token;
    }

    /**
     * Add the token to the unplaced tokens Set
     * @param token the token to add
     * @return true if the token has been added
     */
    public boolean addToUnplacedTokens(Token token) {
        if (Objects.isNull(token)) return false;
        return unplacedTokens.add(token);
    }

    /**
     * Removes the token from the unplaced tokens Set
     * @param token the token to remove
     * @return true if the token has been removed, false otherwise
     */
    public boolean removeFromUnplacedTokens(Token token) {
        if (Objects.isNull(token)) return false;
        return unplacedTokens.remove(token);
    }

    /**
     * Checks if the input given are correct meaning that the placed tokens has at most one target
     * and one laser
     * @param placedTokens given placed tokens in the level
     * @return true if the level has at most one target and one laser
     */
    private boolean correctInput (Token[][] placedTokens) {
        if(placedTokens == null) return true;
        int l = 0;
        for (Token[] row : placedTokens) {
            for (Token token : row) {
                if (token instanceof LaserGun){
                    placedLaser = true;
                    l++;
                }
            }
        }
        return l < 2;
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

    /**
     * @return a copy of the unplacedTokens set.
     */
    @Override
    public Set<Token> getUnplacedTokens() {
        // returning a copy of the unplacedTokens set
        return new HashSet<>(unplacedTokens);
    }

    @Override
    public Token[][] getPlacedTokens() {
        // return a copy of the placedTokens array
        return placedTokens.clone();
    }

    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Helper function to find the position of a specific token type
     * @param type token type to find
     * @return the Coordinate of that type if found,null if not
     */
    private List<Coordinate> findTypePosition(Class<? extends Token> type) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int x = 0; x < placedTokens.length; x++) {
            for (int y = 0; y < placedTokens[0].length; y++) {
                if (placedTokens[x][y] != null && placedTokens[x][y].getClass() == type) {
                    coordinates.add(new Coordinate(x, y)) ;
                }
            }
        }

        return null;
    }

    @Override
    public Coordinate findLaserGunPosition() {
        List<Coordinate> coordinates = findTypePosition(LaserGun.class);
        if(coordinates!=null) return coordinates.get(0);
        else return null;
    }

    @Override
    public Set<Coordinate> findTargetPosition() {
        List<Coordinate> coordinates = findTypePosition(Target.class);
        if (coordinates!=null) return new HashSet<>(coordinates);
        else return null ;
    }

    @Override
    public Set<Coordinate> findCheckpointsPosition() {
        List<Coordinate> coordinates = findTypePosition(Checkpoint.class);
        if (coordinates!=null) return new HashSet<>(coordinates);
        else return null ;
    }

    public void removeToken(Token token) {
        if (token == null) return;

        if (unplacedTokens.contains(token)) {
            removeFromUnplacedTokens(token);
        } else {
            Coordinate pos = getTokenCoordinate(token);

            if (pos != null) {
                removeFromPlacedTokens(pos);
            }
        }
    }

    private Coordinate getTokenCoordinate(Token token) {
        for (int i = 0; i < placedTokens.length; i++) {
            for (int j = 0; j < placedTokens[0].length; j++) {
                if (placedTokens[i][j] == token) {
                    return new Coordinate(i, j);
                }
            }
        }
        return null;
    }
}