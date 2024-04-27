package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Interfaces.TokenManager;

public abstract class Level {

    public final int width;
    public final int height;
    private final LevelID id;
    private final String name;

    public Level(String name, int width, int height) {
        this.name = name;
        this.height = height;
        this.width = width;
        String cleanedName = name.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
        this.id = new LevelID(cleanedName);
    }


    public abstract TokenManager tokenManager();

    public abstract LaserManager laserManager();

    /**
     * Resets the level
     */
    public abstract void reset();

    public LevelID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public abstract Laser generateLaser();
}
