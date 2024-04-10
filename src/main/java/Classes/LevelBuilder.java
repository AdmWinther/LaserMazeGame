package Classes;

import Classes.Tokens.Token;
import Classes.Utils.DataReader;
import Interfaces.Builder;

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
            String name = DataReader.readLevelIDName(id); // Useless for now
            Board board = DataReader.readLevelIDPlacedTokens(id);
            Set<Token> tokens = DataReader.readLevelIDUnplacedTokens(id);
            return new Level(board, tokens.stream().toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
