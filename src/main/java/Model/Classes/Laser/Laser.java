package Model.Classes.Laser;

import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;

import java.util.ArrayList;
import java.util.List;


public class Laser {
    private int fragmentIndex;

    private List<LaserBranch> laserBranches;
    private final List<LaserFragment> fragments;

    public Laser() {
        fragments = new ArrayList<>();
        this.fragmentIndex = 0;
        this.laserBranches = new ArrayList<LaserBranch>();
    }

    public int getFragmentIndex() {
        return fragmentIndex;
    }

    public void incrementFragmentIndex() {
        this.fragmentIndex +=1;
    }

    public void addFragment(int laserBranchNumber, LaserFragment fragment) {
        fragments.add(fragment);

        Orientation orientation = LaserFragment.getFragmentOrientation(fragment);
        Coordinate coordinate = fragment.to();
        this.laserBranches.set(laserBranchNumber, new LaserBranch(coordinate, orientation, true));
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

    public void reset(){
        this.fragments.clear();
        this.fragmentIndex = 0;
        this.laserBranches.clear();
    }

    public LaserBranch getLaserBranch(int i){
        return laserBranches.get(i);
    }

    public Orientation getLaserTipOrientation(int i){
        return laserBranches.get(i).orientation();
    }

    public Coordinate getLaserTipCoordinate(int i){
        return laserBranches.get(i).coordinate();
    }

    public int getLaserBranchCount() {
        return this.laserBranches.size();
    }

    public void newLaserBranch(LaserFragment fragment) {
        Coordinate coordinate = fragment.to();
        Orientation orientation = LaserFragment.getFragmentOrientation(fragment);
        this.laserBranches.add(new LaserBranch(coordinate, orientation, false));
        this.addFragment(this.getLaserBranchCount()-1,fragment);
    }

    public void refineBranches() {
        laserBranches.removeIf(branch -> !branch.continueBranch());
    }

    public void discontinueBranch(int laserBranchNumber) {
        Coordinate coordinate = new Coordinate(0,0);
        Orientation orientation = Orientation.UP;
        this.laserBranches.set(laserBranchNumber, (new LaserBranch(coordinate, orientation, false )));
    }
}