package Model.Classes.Laser;

import java.util.ArrayList;
import java.util.List;


public class Laser {
    private final List<LaserFragment> fragments;

    public Laser() {
        fragments = new ArrayList<>();
    }

    public void addFragment(LaserFragment fragment) {
        fragments.add(fragment);
    }

    public List<LaserFragment> getFragments() {
        return fragments;
    }

    public boolean containsFragment(LaserFragment fragment) {
        for (LaserFragment f : fragments) {
            if (f.from().equals(fragment.from()) && f.to().equals(fragment.to())) {
                return true;
            }
        }

        return false;
    }
}
