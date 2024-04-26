package Model.Classes.Laser;

import Model.Classes.SolutionChecker;
import Model.Classes.Token.LaserGun;
import Model.Classes.Token.Token;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;
import Model.Classes.Utils.Pair;
import Model.Interfaces.TokenManager;

import java.util.ArrayList;
import java.util.Set;

public class LaserManager {

    private final TokenManager tokenManager;
    private final int boardWidth;
    private final int boardHeight;
    private Laser laser;
    private Orientation laserTipOrientation;
    private Coordinate laserTipCoordinate;
    private boolean laserContinue;

    public LaserManager(TokenManager tokenManager, int boardWidth, int boardHeight) {
        this.tokenManager = tokenManager;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.laser = new Laser();
    }

    private boolean isCoordinateOnTheBoard(Coordinate coordinate) {
        if (coordinate.x() < boardWidth && coordinate.x() >= 0) {
            return coordinate.y() < boardHeight && coordinate.y() >= 0;
        }
        return false;
    }

    private Coordinate oneCellInDirectionOfOrientation(Coordinate from, Orientation laserTipOrientation) {
        switch (laserTipOrientation) {
            case UP -> {
                return new Coordinate(from.x(), from.y() - 1);
            }
            case DOWN -> {
                return new Coordinate(from.x(), from.y() + 1);
            }
            case RIGHT -> {
                return new Coordinate(from.x() + 1, from.y());
            }
            case LEFT -> {
                return new Coordinate(from.x() - 1, from.y());
            }
        }
        return null;
    }

    private ArrayList<LaserFragment> continueLaserInSameDirectionOneCell(Coordinate laserTipCoordinate, Orientation laserTipOrientation) {

        Coordinate to = oneCellInDirectionOfOrientation(laserTipCoordinate, laserTipOrientation);

        if (to != null) {
            if (isCoordinateOnTheBoard(to)) {
                ArrayList<LaserFragment> fragments = new ArrayList<LaserFragment>();
                fragments.add(new LaserFragment(laser.getFragmentIndex(), laserTipCoordinate, to));
                return fragments;
            } else {
                return null;
            }
        }
        return null;

    }

    private LaserFragment generateLaserFragmentFromLaserGun() {
        LaserGun laserGun = (LaserGun) tokenManager.getTokenAt(tokenManager.findLaserGunPosition());
        Coordinate laserGunPosition = tokenManager.findLaserGunPosition();

        Coordinate laserFragmentTo = oneCellInDirectionOfOrientation(laserGunPosition, laserGun.getOrientation());
        if (laserFragmentTo != null) {
            if (isCoordinateOnTheBoard(laserFragmentTo)) {    //Check if laser is not shooting out of the board.
                return new LaserFragment(laser.getFragmentIndex(), laserGunPosition, laserFragmentTo);
            }
        }
        return null;
    }

    private ArrayList<LaserFragment> generateTokenLaserPropagation(Coordinate laserTipCoordinate, Orientation laserTipOrientation) {
        Token tokenInCell = tokenManager.getTokenAt(laserTipCoordinate);
        Set<Orientation> propagatedLaser = tokenInCell.propagateLaser(laserTipOrientation);
        ArrayList<LaserFragment> fragments = new ArrayList<LaserFragment>();
        for (Orientation propagatedLaserOrientation : propagatedLaser) {
            if (propagatedLaserOrientation == null) {
                return null;
            } else {
                Coordinate laserFragmentTo = oneCellInDirectionOfOrientation(laserTipCoordinate, propagatedLaserOrientation);
                if (laserFragmentTo != null) {
                    if (isCoordinateOnTheBoard(laserFragmentTo)) {    //Check if laser is not shooting out of the board.
                        fragments.add(new LaserFragment(laser.getFragmentIndex(), laserTipCoordinate, laserFragmentTo));
                    }
                }
            }
        }
        return fragments;
    }

    private ArrayList<LaserFragment> generateNextFragment(int laserBranchIndex) {

        Orientation laserTipOrientation = laser.getLaserTipOrientation(laserBranchIndex);
        Coordinate laserTipCoordinate = laser.getLaserTipCoordinate(laserBranchIndex);
        Token tokenAtLaserTipCoordinate = tokenManager.getTokenAt(laserTipCoordinate);
        ArrayList<LaserFragment> fragments;
        if (tokenAtLaserTipCoordinate == null) {
            fragments = continueLaserInSameDirectionOneCell(laserTipCoordinate, laserTipOrientation);
        } else {
            fragments = generateTokenLaserPropagation(laserTipCoordinate, laserTipOrientation);
        }
        return fragments;
    }

    public Laser generateLaser() {
        this.laser.reset();

        LaserFragment LaserGunFragment = generateLaserFragmentFromLaserGun();
        if (LaserGunFragment != null) {
            laser.newLaserBranch(LaserGunFragment);
            laser.incrementFragmentIndex();
        }

        while (laser.getLaserBranchCount() > 0) {
            for (int laserBranchNumber = 0; laserBranchNumber < laser.getLaserBranchCount(); laserBranchNumber++) {

                ArrayList<LaserFragment> fragments = generateNextFragment(laserBranchNumber);

                if (fragments == null || fragments.isEmpty()) {
                    laser.discontinueBranch(laserBranchNumber);
                } else {
                    this.laser.addFragment(laserBranchNumber, fragments.get(0));
                    if (fragments.size() > 1) {
                        laser.newLaserBranch(fragments.get(1));
                        laser.incrementFragmentIndex();
                    }
                }
            }
            laser.refineBranches();
            laser.incrementFragmentIndex();
        }
        return this.laser;
    }

    public Pair<Laser, Boolean> checkSolution() {
        Laser laser = generateLaser();
        Boolean correctSolution = SolutionChecker.check(this.tokenManager, this.laser);
        return new Pair<>(laser, correctSolution);
    }
}



