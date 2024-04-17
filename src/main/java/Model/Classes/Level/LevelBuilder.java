package Model.Classes.Level;

import Model.Classes.Token.Token;
import Model.Classes.Utils.DataReader;
import Model.Interfaces.Builder;

import java.util.Set;

/**
 * A class that builds a Level from their ID
 *
 * @author Hugo Demule
 * @see CampaignLevel
 */
public class LevelBuilder implements Builder<CampaignLevel> {
    private LevelID id;

    /**
     * Creates a new LevelBuilder with a given ID
     *
     * @param id the ID of the level
     * @author Hugo Demule
     */
    public LevelBuilder(LevelID id) {
        this.id = id;
    }

    /**
     * Sets the ID of the level
     *
     * @param id the ID of the level
     * @author Hugo Demule
     */
    public void setID(LevelID id) {
        this.id = id;
    }

    /**
     * Builds the level from the ID
     *
     * @return the level built from the ID or null if there was an error
     * @author Hugo Demule
     * @see CampaignLevel
     */
    @Override
    public CampaignLevel build() {
        try {
            String name = DataReader.readLevelIDName(id);
            Token[][] placedTokens = DataReader.readLevelIDPlacedTokens(id);
            Set<Token> unplacedTokens = DataReader.readLevelIDUnplacedTokens(id);
            return new CampaignLevel(name, placedTokens, unplacedTokens);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
