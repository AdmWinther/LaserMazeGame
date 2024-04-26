package Model.Classes.Level;

public record LevelID(String value) {
    public final static LevelID NEW_LEVEL = new LevelID("-1");

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LevelID) {
            return ((LevelID) obj).value().equals(value);
        }
        return false;
    }
}
