package Model.Classes.Laser;

import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;

import java.util.ArrayList;
import java.util.List;


public class Laser {
    //hold the index of the fragment that is currently being processed.
    private int fragmentIndex;

    //hold the branches of the laser. If laser is split, it will have multiple branches.
    private List<LaserBranch> laserBranches;

    //Holds a list of all the fragments that the laser has.
    private final List<LaserFragment> fragments;

    /**
     * Constructor for the Laser class.
     * Initiate the Laser class with an empty list of fragments.
     * @author Adam Winther
     */
    public Laser() {
        fragments = new ArrayList<>();
        this.fragmentIndex = 0;
        this.laserBranches = new ArrayList<LaserBranch>();
    }

    /**
     * Get the index of the fragment that is currently being processed.
     * @return the index of the fragment that is currently being processed.
     * @author Adam Winther
     */
    public int getFragmentIndex() {
        return fragmentIndex;
    }

    /**
     * Increment the index of the fragment that is currently being processed.
     * @author Adam Winther
     */
    public void incrementFragmentIndex() {
        this.fragmentIndex +=1;
    }

    /**
     * Add a new fragment to the fragment list.
     * @author Adam Winther
     */
    public void addFragment(int laserBranchNumber, LaserFragment fragment) {
        Orientation orientation = LaserFragment.getFragmentOrientation(fragment);
        boolean continueBranch;

        //check if the fragment is already in the list.
        if (this.containsFragment(fragment)) {
            //if the fragment is already in the list, discontinue the branch.
            continueBranch = false;
        } else {
            fragments.add(fragment);
            //if the fragment is not in the list, continue the branch.
            continueBranch = true;
        }

        this.laserBranches.set(laserBranchNumber, new LaserBranch(fragment.to(), orientation, continueBranch));
    }

    /**
     * Get the list of the fragments that is currently being processed.
     * @author Adam Winther
     */
    public List<LaserFragment> getFragments() {
        return fragments;
    }

    /**
     * Check if the laser contains a fragment.
     * @param fragment the fragment to check if the laser contains.
     * @return true if the laser contains the fragment, false otherwise.
     * @author Adam Winther
     */
    public boolean containsFragment(LaserFragment fragment) {
        for (LaserFragment f : fragments) {
            if (f.from().equals(fragment.from()) && f.to().equals(fragment.to())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get the last fragment in the fragment list.
     * @author Adam Winther
     */
    public void reset(){
        this.fragments.clear();
        this.fragmentIndex = 0;
        this.laserBranches.clear();
    }

    /**
     * Get the last fragment in the fragment list.
     * @author Adam Winther
     */
    private LaserBranch getLaserBranch(int i){
        return laserBranches.get(i);
    }

    /**
     *  Get the orientation of the laser tip.
     *  @return the orientation of the laser tip11.
     *  @author Adam Winther
     */
    public Orientation getLaserTipOrientation(int i){
        return laserBranches.get(i).orientation();
    }

    /**
     *  Get the coordinate of the laser tip.
     *  @return the coordinate of the laser tip.
     *  @author Adam Winther
     */
    public Coordinate getLaserTipCoordinate(int i){
        return laserBranches.get(i).coordinate();
    }

    /**
     *  Get the number of laser branches.
     *  @return the number of laser branches.
     *  @author Adam Winther
     */
    public int getLaserBranchCount() {
        return this.laserBranches.size();
    }

    /**
     *  Add a new laser branch.
     *  @param fragment the fragment to add to the laser fragments. It is the first fragment for the new branch.
     *  @author Adam Winther
     */
    public void newLaserBranch(LaserFragment fragment) {
        Coordinate coordinate = fragment.to();
        Orientation orientation = LaserFragment.getFragmentOrientation(fragment);
        this.laserBranches.add(new LaserBranch(coordinate, orientation, false));
        this.addFragment(this.getLaserBranchCount()-1,fragment);
    }

    /**
     *  Refine the branches of the laser. remove those branches that must not be continued from the list of branches.
     *  @author Adam Winther
     */
    public void refineBranches() {
        laserBranches.removeIf(branch -> !branch.continueBranch());
    }

    /**
     *  Discontinue a branch of the laser.
     *  @param laserBranchNumber the number of the branch to discontinue.
     *  @author Adam Winther
     */
    public void discontinueBranch(int laserBranchNumber) {
        Coordinate coordinate = new Coordinate(0,0);
        Orientation orientation = Orientation.UP;
        this.laserBranches.set(laserBranchNumber, (new LaserBranch(coordinate, orientation, false )));
    }
}