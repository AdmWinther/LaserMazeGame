package Classes.Laser;

import java.util.HashSet;
import java.util.Set;


public class Laser {
    private Set<LaserFragment> fragments;

    public Laser() {
        fragments = new HashSet<>();
    }

    public void addFragment(LaserFragment fragment) {
        fragments.add(fragment);
    }

    public Set<LaserFragment> getFragments() {
        return fragments;
    }
}
