package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.*;

import java.util.HashSet;
import java.util.Set;

public final class SandboxLevel extends Level {

    private final FlexibleTokenManager tokenManager;
    private final LaserManager laserManager;
    private final Set<Class<? extends Token>> inventory;

    public SandboxLevel(String name, Token[][] placedTokens, Set<Token> unplacedTokens, Set<Class<? extends Token>> inventory ) {
        super(name,placedTokens.length,placedTokens[0].length);
        this.tokenManager = new FlexibleTokenManager(placedTokens, unplacedTokens);
        this.laserManager = new LaserManager(tokenManager, width, height);
        this.inventory = new HashSet<>(inventory);
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
