package Model.Classes.Laser;

import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

import java.util.ArrayList;
import java.util.List;


public class Laser {
    private final List<LaserFragment> fragments;

    private Coordinate laserTipCoordinate;

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

    public Coordinate getLaserTipCoordinate(){
        return this.laserTipCoordinate;
    }

    public void setLaserTipCoordinate(Coordinate coordinate){
        this.laserTipCoordinate = coordinate;
    }

    public Orientation laserTipOrientation(){
        int index = this.fragments.size();
        //todo: Generate the Orientation based on the latest fragment.
        return Orientation.UP;
    }
}