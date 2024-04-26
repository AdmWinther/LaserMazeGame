package Model.Classes.Level;

import Model.Classes.Token.Token;
import Model.Classes.Utils.DataReader;
import Model.Interfaces.Builder;
import Model.Interfaces.Inventory;
import Model.constants.SandboxInventory;

import java.util.Set;

/**
 * A class that builds a Level from their ID
 *
 * @author Hugo Demule
 * @see PlayableLevel
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


    @Override
    public Level build() {
        return build(false);
    }

    /**
     * Builds the level from the ID
     *
     * @param editable the type of the level to build (true for EditableLevel, false for PlayableLevel)
     * @return the level built from the ID or null if there was an error
     * @author Hugo Demule
     * @see PlayableLevel
     * @see EditableLevel
     */
    @Override
    public Level build(boolean editable) {

        if (id.equals(LevelID.NEW_LEVEL)) {
            if (!editable) throw new IllegalArgumentException("Cannot build a new empty playable level.");
            return new EditableLevel("New Level", new Token[7][7], Set.of(), new SandboxInventory());
        }

        try {
            String name = DataReader.readLevelIDName(id);
            Token[][] placedTokens = DataReader.readLevelIDPlacedTokens(id);
            Set<Token> unplacedTokens = DataReader.readLevelIDUnplacedTokens(id);
            if (editable) {
                Inventory inventory = new SandboxInventory();
                return new EditableLevel(name, placedTokens, unplacedTokens, inventory);
            } else {
                return new PlayableLevel(name, placedTokens, unplacedTokens);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
