package Model.Classes.Level;

import Model.Classes.Token.*;
import Model.Classes.Utils.DataReader;
import Model.Interfaces.Builder;

import java.util.HashSet;
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
        return build("playable");
    }

    /**
     * Builds the level from the ID
     * @param type the type of the level to build ("playable" or "editable")
     * @return the level built from the ID or null if there was an error
     * @author Hugo Demule
     * @see PlayableLevel
     * @see EditableLevel
     */
    @Override
    public Level build(String type) {
        try {
            String name = DataReader.readLevelIDName(id);
            Token[][] placedTokens = DataReader.readLevelIDPlacedTokens(id);
            Set<Token> unplacedTokens = DataReader.readLevelIDUnplacedTokens(id);
            if (type.equals("editable")) {
                /* TODO : implement this in another place */
                Set<Class<? extends Token>> inventory = new HashSet<>(
                        Set.of(Block.class,
                                DoubleSidedMirror.class,
                                LaserGun.class,
                                OneSidedMirror.class,
                                Splitter.class,
                                Target.class)
                );
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
