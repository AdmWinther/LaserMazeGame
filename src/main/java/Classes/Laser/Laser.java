package Classes.Laser;

import java.util.ArrayList;
import java.util.List;

public class Laser {
    private final ArrayList<LaserFragment> fragments;

    public Laser(){
        this.fragments = new ArrayList<LaserFragment>();
    }

    public void addFragment(LaserFragment laserFragment) {
        if(laserFragment != null) fragments.add(laserFragment);
    }

    public List<LaserFragment> getFragments(){
        return fragments;
    }

}
