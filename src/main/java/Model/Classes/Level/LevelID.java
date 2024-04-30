package Model.Classes.Level;
/**
 * A class that represents the ID of a level
 * It is used to identify a level in the database
 * The LevelID("-1") is used to represent a new  empty level.
 * It is a public record
 * @see LevelBuilder
 * @see Level
 * @author Hugo Demule
 */
public record LevelID(String value) {
    public final static LevelID NEW_LEVEL = new LevelID("-1");

    /**
     * Overrides the equals method to compare two LevelIDs
     *
     * @return the value of the LevelID
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LevelID) {
            return ((LevelID) obj).value().equals(value);
        }
        return false;
    }
}
