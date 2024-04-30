package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Interfaces.TokenManager;

/**
 * Abstract class representing a level in the game.
 * This class in inherited by EditableLevel and PlayableLevel.
 * Contains the width and height of the level, as well as the name and id.
 * Contains abstract methods for the token manager and laser manager.
 * Contains an abstract method for resetting the level.
 * Contains abstract method for generating a laser.
 * Contains a constructor for the level.
 * Contains getters for the id and name of the level.
 * @author Lina Mounan
 */
public abstract class Level {
    private final int width;
    private final int height;
    private final LevelID id;
    private final String name;

    /**
     * Constructor for the Level class.
     * @param name the name of the level
     * @param width the width of the level
     * @param height the height of the level
     * @author Lina Mounan, Hugo Demule
     */
    public Level(String name, int width, int height) {
        this.name = name;
        this.height = height;
        this.width = width;
        String cleanedName = name.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
        this.id = new LevelID(cleanedName);
    }

    /**
     * Get the TokenManager of the level
     * @author Lina Mounan, Leonard Amsler
     */
    public abstract TokenManager tokenManager();

    /**
     * Get the LaserManager of the level
     * @return the LaserManager of the level
     * @author Lina Mounan
     */
    public abstract LaserManager laserManager();

    /**
     * Resets the level
     * Resetting the level will reset the TokenManager of the level.
     * @author Lina Mounan
     */
    public abstract void reset();

    /**
     * Get the id of the level
     * @return the id of the level
     * @author Lina Mounan
     */
    public LevelID id() {
        return id;
    }

    /**
     * Get the name of the level
     * @return the name of the level
     * @author Lina Mounan
     */
    public String name() {
        return name;
    }

    /**
     * Generate a laser for the level
     * @return the laser of the level
     * @author Lina Mounan
     */
    public abstract Laser generateLaser();

    /**
     * Get the width of the level
     * @return the width of the level
     * @author Lina Mounan
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the level
     * @return the height of the level
     * @author Lina Mounan
     */
    public int getHeight() {
        return height;
    }
}
