package Model.Classes.Level;

import Model.Classes.Laser.Laser;
import Model.Classes.Laser.LaserManager;
import Model.Interfaces.TokenManager;

public abstract class Level {

    private final LevelID id;
    private  final String name;
    public final int width;
    public final int height;

    public Level(String name,int width,int height){
        this.name = name;
        this.height = height;
        this.width = width;
        this.id = new LevelID(name + "_" + hashCode());
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

    public abstract Laser generateLaser() ;
}
