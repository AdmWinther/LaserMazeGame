package Classes.Lazer;

import java.util.HashSet;
import java.util.Set;

public class Laser {

    private final Set<LaserFragment> laserFragments;

    /**
     * Constructor for the Laser class
     */
    public Laser() {
        laserFragments = new HashSet<>();
    }

    /**
     * Method to add a fragment to the laser
     *
     * @param fragment LaserFragment - the fragment to add
     */
    public void addLaserFragment(LaserFragment fragment) {
        laserFragments.add(fragment);
    }

    /**
     * Getter for the fragments in the laser
     *
     * @return Set<LaserFragment> - the fragments in the laser
     */
    public Set<LaserFragment> getFragments() {
        return new HashSet<>(laserFragments);
    }
}
