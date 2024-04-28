package Model.Interfaces;

import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;

import java.util.Set;

public interface TokenManager {

    /** Resets the level ie moves all the tokens back to the level */
    void reset();

    /**
     * Transfers a token to the given position.
     * @param token  token to be placed
     * @param position the position we want to transfer the token to
     * @return true if the token has been transferred, false if it can't of the arguments are not correct
     */
    boolean transferTokenToPlacedTokens(Token token, Coordinate position);

    /**
     * Transfers a token at the specified position to the level.
     * @param position The position of the token to be transferred.
     * @implNote This method is RECOMMENDED compared to the method with the Token instance parameter.
     */
    boolean transferTokenToUnplacedTokens(Coordinate position);

    /**
     * Transfers a token from the board to the level.
     * @param token The token to be transferred.
     * @implNote This method is NOT RECOMMENDED compared to the method with the Coordinate instance parameter.
     */
    boolean transferTokenToUnplacedTokens(Token token);

    /**
     * Moving a placed token from a position to another.
     * @param from the starting position
     * @param to the position we want to transfer the token to
     * @return true if the token has been moved, false if not or if the arguments are not correct
     */
    boolean moveToken(Coordinate from, Coordinate to);

    /**
     * Gives the token at a given position.
     * @param position the position we want to get the token from
     * @return the token at the given position
     */
    Token getTokenAt(Coordinate position);

    /** @return a copy of the placedTokens array*/
    Token[][] getPlacedTokens();

    /** @return a copy of the unplacedTokens set*/
    Set<Token> getUnplacedTokens();

    /** @return the size of the unplacedTokens set*/
    int getUnplacedTokensSize();

    /** @return the coordinate  position of the laser gun if there's one, null otherwise */
    Coordinate findLaserGunPosition();

    /** @return the coordinate position of the targets if there are, null otherwise */
    Set<Coordinate> findTargetPosition();

    /** @return the coordinate position of the checkpoints if there are, null otherwise */
    Set<Coordinate> findCheckpointsPosition();

    /**
     Checks if the specified coordinate is empty.
     @param coordinate the coordinate to be checked
     @return true if the coordinate is empty, false otherwise
     */
    boolean isEmpty(Coordinate coordinate);


}
