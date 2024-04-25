package Model.Classes;

import Model.Classes.Token.Token;
import Model.Classes.Utils.DataReader;
import Model.Interfaces.Builder;

import javax.xml.crypto.Data;
import java.util.Set;

/**
 * A class that builds a Level from their ID
 *
 * @author Hugo Demule
 * @see Level
 */
public class LevelBuilder implements Builder<Level> {
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
     * @see Level
     */
    @Override
    public Level build() {
        try {
            String name = DataReader.readLevelIDName(id);
            int serialNumber = DataReader.readLevelSerialNr(id);
            Token[][] placedTokens = DataReader.readLevelIDPlacedTokens(id);
            Set<Token> unplacedTokens = DataReader.readLevelIDUnplacedTokens(id);
            return new Level(name, serialNumber, placedTokens, unplacedTokens);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
