package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.*;
import Model.Classes.TokenManager.FlexibleTokenManager;
import Model.Interfaces.Inventory;

import java.util.Set;

/**
 * EditableLevel class.
 * EditableLevel is a subclass of the Level class. EditableLevel is used in the sandbox.
 * @author Hugo De Lemos, Lin Mounan
 */
public final class EditableLevel extends Level {

    private final FlexibleTokenManager tokenManager;
    private final LaserManager laserManager;

    /**
     * Constructor for the EditableLevel class.
     * Initiate the EditableLevel class.
     * @param name the name of the level.
     * @param placedTokens a 2D array of placed tokens.
     * @param unplacedTokens a set of unplaced tokens.
     * @param inventory the inventory of the level.
     * @author Hugo De Lemos, Lina Mounan
     */
    public EditableLevel(String name, Token[][] placedTokens, Set<Token> unplacedTokens, Inventory inventory ) {
        super(name,placedTokens.length,placedTokens[0].length);
        this.tokenManager = new FlexibleTokenManager(placedTokens, unplacedTokens, inventory);
        tokenManager.setPlacedTokensMovability(true);
        tokenManager.setUnplacedTokensMovability(true);
        this.laserManager = new LaserManager(tokenManager, this.getWidth(), this.getHeight());
    }

    /**
     * Get the token manager of the level.
     * @return the token manager of the level.
     * @author Lina Mounan
     */
   @Override
    public FlexibleTokenManager tokenManager() {
        return tokenManager;
    }

    /**
     * Get the laser manager of the level.
     * @return the laser manager of the level.
     * @author Lina Mounan
     */
    @Override
    public LaserManager laserManager() {
        return laserManager;
    }

    /**
     * Reset the level. Resting the level will reset the TokenManager of the level. Reset will take off all placeable
     * tokens from the board.
     * @author Lina Mounan
     */
    @Override
    public void reset() {
        tokenManager.reset();
    }

    /**
     * Generate laser.
     * @return Laser object including the LaserFragments.
     * @author Lina Mounan
     */
    @Override
    public Laser generateLaser() {
        return laserManager.generateLaser();
    }

}
