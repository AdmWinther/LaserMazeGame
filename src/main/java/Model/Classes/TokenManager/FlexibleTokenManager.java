package Model.Classes.TokenManager;

import Model.Classes.Token.Checkpoint;
import Model.Classes.Token.LaserGun;
import Model.Classes.Token.Target;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Model.Interfaces.Inventory;
import Model.Interfaces.TokenManager;

import java.util.*;

/**
 * Class that is used as token manager for a sandbox level in level edit mode.
 */
public class FlexibleTokenManager implements TokenManager {

    private final Token[][] placedTokens;
    private final Set<Token> unplacedTokens;
    boolean placedLaser;
    private Inventory inventory;

    public FlexibleTokenManager(Token[][] placedTokens, Set<Token> unplacedTokens, Inventory inventory) {
        this(placedTokens, unplacedTokens);
        this.inventory = inventory;
    }

    /**
     * Constructor of the FlexibleTokenManager
     *
     * @param placedTokens  the placed tokens in the level
     * @param unplacedTokens the unplaced tokens in the level
     * @author Nathan Gromb, Hugo Demule
     */
    public FlexibleTokenManager(Token[][] placedTokens, Set<Token> unplacedTokens) {
        if (!correctInput(placedTokens))
            throw new IllegalArgumentException();
        this.placedTokens = placedTokens;
        this.unplacedTokens = unplacedTokens;
        if (findLaserGunPosition() != null) placedLaser = true;
        // check if an object of type LaserGun is inside unplacedTokens
        for (Token token : unplacedTokens) {
            if (token instanceof LaserGun) {
                placedLaser = true;
                break;
            }
        }
    }

    /**
     * Checks if the input given are correct meaning that the placed tokens has at most one target
     * and one laser
     *
     * @param placedTokens given placed tokens in the level
     * @return true if the level has at most one target and one laser
     * @author Nathat Gromb
     */
    private boolean correctInput(Token[][] placedTokens) {
        if (placedTokens == null)
            return true;
        int l = 0;
        for (Token[] row : placedTokens) {
            for (Token token : row) {
                if (token instanceof LaserGun) {
                    placedLaser = true;
                    l++;
                }
            }
        }
        return l < 2;
    }

    /**
     * Helper function to find the position of a specific token type
     *
     * @param type token type to find
     * @return the Coordinate of that type if found,null if not
     * @author Hugo Demule
     */
    private List<Coordinate> findTypePosition(Class<? extends Token> type) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int x = 0; x < placedTokens.length; x++) {
            for (int y = 0; y < placedTokens[0].length; y++) {
                if (placedTokens[x][y] != null && placedTokens[x][y].getClass() == type) {
                    coordinates.add(new Coordinate(x, y));
                }
            }
        }

        if (coordinates.isEmpty())
            return null;

        return coordinates;
    }

    /**
     * Set all placed tokens to unmovable
     * @author Hugo Demule
     * @param movability must be true if the token is movable, false otherwise
     * @author Hugo Demule
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
     * @author Hugo Demule
     * @param movability must be true if the token is movable, false otherwise
     * @author Hugo Demule
     */
    public void setUnplacedTokensMovability(boolean movability) {
        for (Token token : unplacedTokens) {
            token.setMovable(movability);
        }
    }

    /**
     * Add a token to the placed tokens table at the given position
     *
     * @param token    the token to place
     * @param position the given position
     * @return true if the token has been placed successfully, false otherwise
     * @author Nathan Gromb
     */
    public boolean addToPlacedTokens(Token token, Coordinate position) {
        if (Objects.isNull(token))
            return false;
        if (Objects.isNull(position))
            return false;
        if (checkBounds(position))
            return false;
        if (checkAttributes())
            return false;
        if (!isEmpty(position))
            return false;

        if (placedLaser && token instanceof LaserGun)
            return false;

        if (token instanceof LaserGun)
            placedLaser = true;

        placedTokens[position.x()][position.y()] = token;
        return true;
    }

    /**
     * Checks if the position is out of bounds.
     *
     * @param position The position to be checked.
     * @return True if the position is out of bounds, false otherwise.
     * @author Nathan Gromb
     */
    public boolean checkBounds(Coordinate position) {
        if (position == null)
            return true;
        return position.x() < 0 || position.x() >= placedTokens.length || position.y() < 0 || position.y() >= placedTokens[0].length;
    }

    /**
     * Checks if the attributes of the simpleTokenManager class are null.
     *
     * @return True if any of the attributes is null, false otherwise.
     */
    public boolean checkAttributes() {
        return (placedTokens == null) || (unplacedTokens == null);
    }

    /**
     * Add the token to the unplaced tokens Set
     *
     * @param token the token to add
     * @return true if the token has been added
     */
    public boolean addToUnplacedTokens(Token token) {
        if (Objects.isNull(token))
            return false;
        if (!isAddable(token))
            return false;
        if (token instanceof LaserGun)
            placedLaser = true;

        return unplacedTokens.add(token);
    }

    private boolean isAddable(Token token) {
        if (Objects.isNull(token)) return false;
        return !(token instanceof LaserGun) || !placedLaser;
    }

    /**
     * Reset the flexible token manager of the level
     * In flexible token manager reset will transfer all placed tokens to the unplaced tokens
     */
    @Override
    public void reset() {
        for (int i = 0; i < placedTokens.length; i++) {
            for (int j = 0; j < placedTokens[0].length; j++) {
                Token token = placedTokens[i][j];
                if (token == null || !token.isMovable())
                    continue;
                placedTokens[i][j] = null;
                unplacedTokens.add(token);
            }
        }
    }

    /**
     * Transfer a token from the placed tokens to the unplaced tokens
     *
     * @param position the position of the token to transfer
     * @return true if the token has been transferred, false otherwise
     */
    @Override
    public boolean transferTokenToPlacedTokens(Token token, Coordinate position) {
        if (checkAttributes())
            return false;
        if (checkBounds(position))
            return false;
        if (!isEmpty(position))
            return false;
        if (!unplacedTokens.remove(token))
            return false;

        placedTokens[position.x()][position.y()] = token;
        return true;
    }

    /**
     * Transfer a token from the unplaced tokens to the placed tokens
     *
     * @param position the position of the token that must be transferred
     * @return true if the token has been transferred, false otherwise
     */
    @Override
    public boolean transferTokenToUnplacedTokens(Coordinate position) {
        if (checkAttributes())
            return false;
        if (checkBounds(position))
            return false;
        if (isEmpty(position))
            return false;

        Token token = placedTokens[position.x()][position.y()];
        placedTokens[position.x()][position.y()] = null;
        return unplacedTokens.add(token);
    }

    /**
     * Transfer a token from the placed tokens to the unplaced tokens
     *
     * @param token the token to be transferred
     * @return true if the token has been transferred, false otherwise
     */
    @Override
    public boolean transferTokenToUnplacedTokens(Token token) {
        if (checkAttributes())
            return false;
        if (token == null)
            return false;

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

    /**
     * Move a token from a position to another
     *
     * @param from the position of the token to move
     * @param to   the position where the token should be moved
     * @return true if the token has been moved, false otherwise
     */
    @Override
    public boolean moveToken(Coordinate from, Coordinate to) {
        if (checkAttributes()) {
            // Attributes non valid
            return false;
        }
        if (checkBounds(from) || checkBounds(to)) {
            // Bounds non valid
            return false;
        }
        if (isEmpty(from) || !isEmpty(to)) {
            // Positions non valid
            return false;
        }

        Token token = placedTokens[from.x()][from.y()];

        placedTokens[from.x()][from.y()] = null;
        placedTokens[to.x()][to.y()] = token;
        return true;
    }

    /**
     * Get the token at the given position
     *
     * @param position the coordinate of the token
     * @return the token at the given coordinate, null if there is no token at this position
     */
    @Override
    public Token getTokenAt(Coordinate position) {
        if (checkAttributes())
            return null;
        if (checkBounds(position))
            return null;
        return placedTokens[position.x()][position.y()];
    }

    /** Generate a copy of the placed tokens array
     *
     * @return a copy of the placed tokens array
     */
    @Override
    public Token[][] getPlacedTokens() {
        // return a copy of the placedTokens array
        return placedTokens.clone();
    }

    /**
     * @return a copy of the unplacedTokens set.
     */
    @Override
    public Set<Token> getUnplacedTokens() {
        // returning a copy of the unplacedTokens set
        return new HashSet<>(unplacedTokens);
    }

    /**
     * Get the size of the unplaced tokens set
     *
     * @return the size of the unplaced tokens set
     */
    @Override
    public int getUnplacedTokensSize() {
        return unplacedTokens.size();
    }

    /**
     * Get the location of the laser gun
     * @return Coordinate of the laserGun, null if there is no laserGun
     */
    @Override
    public Coordinate findLaserGunPosition() {
        List<Coordinate> coordinates = findTypePosition(LaserGun.class);
        if (coordinates != null)
            return coordinates.get(0);
        else
            return null;
    }

    /**
     * Get the location of the target
     * @return Coordinate of the target, null if there is no target
     */
    @Override
    public Set<Coordinate> findTargetPosition() {
        List<Coordinate> coordinates = findTypePosition(Target.class);
        if (coordinates != null)
            return new HashSet<>(coordinates);
        else
            return null;
    }

    /**
     * Get the location of the checkpoints
     * @return a set Coordinates which are the coordinates of the checkpoints, or null if there is no checkpoint
     */
    @Override
    public Set<Coordinate> findCheckpointsPosition() {
        List<Coordinate> coordinates = findTypePosition(Checkpoint.class);
        if (coordinates != null)
            return new HashSet<>(coordinates);
        else
            return null;
    }

    /**
     * Check if the given position is empty
     *
     * @param position the position to check
     * @return true if the position is empty, false otherwise
     */
    @Override
    public boolean isEmpty(Coordinate position) {
        return placedTokens[position.x()][position.y()] == null;
    }

    /**
     * Get the inventory of the level
     *
     * @return the inventory of the level
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * removes a token, if it is in the set of unplaced tokens, or in the placed tokens it will be removed.
     *
     * @param token the token to be removed
     */
    public void removeToken(Token token) {
        if (token == null)
            return;
        if (unplacedTokens.contains(token)) {
            removeFromUnplacedTokens(token);
        } else {
            Coordinate pos = getTokenCoordinate(token);

            if (pos != null) {
                removeFromPlacedTokens(pos);
            }
        }
    }

    /**
     * Removes the token from the unplaced tokens Set
     *
     * @param token the token to remove
     * @return true if the token has been removed, false otherwise
     */
    public boolean removeFromUnplacedTokens(Token token) {
        if (Objects.isNull(token))
            return false;
        if (token instanceof LaserGun)
            placedLaser = false;
        return unplacedTokens.remove(token);
    }

    /**
     * Get the coordinate of a token
     *
     * @param token the token to get the coordinate
     * @return the coordinate of the token, null if the token is not in the placed tokens
     */
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

    /**
     * Removes the placed token at given position
     *
     * @param position the position where the token should be removed
     * @return the Token that has been removed, null otherwise
     */
    public Token removeFromPlacedTokens(Coordinate position) {
        if (Objects.isNull(position))
            return null;
        if (checkBounds(position))
            return null;
        if (checkAttributes())
            return null;

        Token token = placedTokens[position.x()][position.y()];
        if (token instanceof LaserGun)
            placedLaser = false;

        placedTokens[position.x()][position.y()] = null;

        return token;
    }
}