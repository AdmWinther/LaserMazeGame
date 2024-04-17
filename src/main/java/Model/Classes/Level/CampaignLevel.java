package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Classes.Token.StrictTokenManager;
import Model.Classes.Token.Token;

import java.util.Set;

public final class CampaignLevel extends Level{


    /**
     * Constructor for Level class
     *
     * @param placedTokens   the tokens that are already placed on the board
     * @param unplacedTokens the tokens that are not placed on the board
     */
    private final StrictTokenManager strictTokenManager;
    private final LaserManager laserManager;


    public CampaignLevel(String name, Token[][] placedTokens, Set<Token> unplacedTokens) {
        super(name,placedTokens.length,placedTokens[0].length);
        this.strictTokenManager = new StrictTokenManager(placedTokens, unplacedTokens);
        this.laserManager = new LaserManager(strictTokenManager, width, height);
    }

    @Override
    public StrictTokenManager tokenManager() {
        return strictTokenManager;
    }

    @Override
    public LaserManager laserManager() {
        return laserManager;
    }

    @Override
    public void reset() {
        strictTokenManager.reset();
    }

    @Override
    public Laser generateLaser() {
        return laserManager.generateLaser();
    }
}
