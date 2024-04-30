package Model.Classes.Level;

import Model.Classes.Token.Token;
import Model.Classes.Utils.DataReader;
import Model.Constants.SandboxInventory;
import Model.Interfaces.Builder;
import Model.Interfaces.Inventory;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
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


    /**
     * Builds the level from the ID
     * It is an overload of the build(Boolean b) method that assumes the level is not editable.
     * Identical to build(false)
     * @return the level built from the ID or returns null if there was an error
     * @throws IllegalArgumentException if the levelID is NEW_LEVEL, but it is not editable
     * @see PlayableLevel
     * @see EditableLevel
     * @author Hugo Demule
     */
    @Override
    public Level build() {
        return build(false);
    }

    /**
     * Builds the level from the ID
     *
     * @param editable the type of the level to build (true for EditableLevel, false for PlayableLevel)
     * @return the level built from the ID or null if there was an error
     * @throws IllegalArgumentException if the levelID is NEW_LEVEL, but it is not editable
     * @author Hugo Demule
     * @see PlayableLevel
     * @see EditableLevel
     */
    @Override
    public Level build(boolean editable) {

        if (id.equals(LevelID.NEW_LEVEL)) {
            if (!editable)
                throw new IllegalArgumentException("Cannot build a new empty playable level.");

            String fileName = generateFilenameWithTimestamp();
            return new EditableLevel(fileName, new Token[7][7], new HashSet<>(), new SandboxInventory());
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

    /**
     * Generates a filename with the current date and time
     *
     * @return a string with the filename with the current date and time
     * @author Adam Winther
     */
    @NotNull
    private static String generateFilenameWithTimestamp() {
        // get current data and precise time
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());

        return "Sandbox Level | " + currentDate + " " + currentTime;
    }
}
