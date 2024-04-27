package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.*;
import Model.Classes.TokenManager.FlexibleTokenManager;
import Model.Interfaces.Inventory;

import java.util.Set;

public final class EditableLevel extends Level {

    private final FlexibleTokenManager tokenManager;
    private final LaserManager laserManager;

    public EditableLevel(String name, Token[][] placedTokens, Set<Token> unplacedTokens, Inventory inventory ) {
        super(name,placedTokens.length,placedTokens[0].length);
        this.tokenManager = new FlexibleTokenManager(placedTokens, unplacedTokens, inventory);
        tokenManager.setPlacedTokensMovability(true);
        tokenManager.setUnplacedTokensMovability(true);
        this.laserManager = new LaserManager(tokenManager, width, height);
    }

   @Override
    public FlexibleTokenManager tokenManager() {
        return tokenManager;
    }

    @Override
    public LaserManager laserManager() {
        return laserManager;
    }

    @Override
    public void reset() {
        tokenManager.reset();
    }

    @Override
    public Laser generateLaser() {
        return laserManager.generateLaser();
    }

}
