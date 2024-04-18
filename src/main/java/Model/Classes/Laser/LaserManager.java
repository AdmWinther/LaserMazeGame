package Model.Classes.Laser;

import Model.Classes.Token.LaserGun;
import Model.Classes.Token.Token;
import Model.Classes.Token.TokenManager;
import Model.Classes.Utils.Coordinate;
import Model.Classes.Utils.Orientation;

import java.util.Set;


public class LaserManager {

    private Laser laser;
    private final TokenManager tokenManager;
    private Orientation laserTipOrientation;
    private boolean laserContinue = false;

    private final int boardWidth;
    private final int boardHeight;

    public LaserManager(TokenManager tokenManager, int boardWidth, int boardHeight) {
        this.tokenManager = tokenManager;
        this.laserContinue = false;
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

    private Coordinate oneCellInDirectionOfOrientation(Coordinate from) {
        if (this.laserTipOrientation == Orientation.UP) return new Coordinate(from.x(), from.y() - 1);
        if (this.laserTipOrientation == Orientation.DOWN) return new Coordinate(from.x(), from.y() + 1);
        if (this.laserTipOrientation == Orientation.RIGHT) return new Coordinate(from.x() + 1, from.y());
        if (this.laserTipOrientation == Orientation.LEFT) return new Coordinate(from.x() - 1, from.y());
        return null;
    }

    private Coordinate oneCellInDirectionOfOrientation(Coordinate from, Orientation newOrientation) {
        if (newOrientation == Orientation.UP) return new Coordinate(from.x(), from.y() - 1);
        if (newOrientation == Orientation.DOWN) return new Coordinate(from.x(), from.y() + 1);
        if (newOrientation == Orientation.RIGHT) return new Coordinate(from.x() + 1, from.y());
        if (newOrientation == Orientation.LEFT) return new Coordinate(from.x() - 1, from.y());
        return null;
    }

    private LaserFragment continueLaserInSameDirectionOneCell() {
        if (laserContinue) {
            Coordinate from = this.laser.getLaserTipCoordinate();   //laserTipCoordinate;
            Coordinate to = oneCellInDirectionOfOrientation(from);

            if (to != null) {
                if (isCoordinateOnTheBoard(to)) {
                    this.laser.setLaserTipCoordinate(to);
                    return new LaserFragment(from, to);
                } else {
                    this.laserContinue = false;
                }
            }
        }
        return null;
    }

    private LaserFragment generateLaserFragmentFromLaserGun() {
        LaserGun laserGun = (LaserGun)tokenManager.getTokenAt(tokenManager.findLaserGunPosition());
        Coordinate laserGunPosition = tokenManager.findLaserGunPosition();
        this.laserTipOrientation = laserGun.getOrientation();
        Coordinate laserFragmentTo = oneCellInDirectionOfOrientation(laserGunPosition);
        if (laserFragmentTo != null) {
            if (isCoordinateOnTheBoard(laserFragmentTo)) {    //Check if laser is not shooting out of the board.
                this.laserContinue = true;
                this.laser.setLaserTipCoordinate(laserFragmentTo);
                return new LaserFragment(laserGunPosition, laserFragmentTo);
            }
        }
        return null;
    }

    private LaserFragment generateTokenLaserPropagation() {
        Token tokenInCell = tokenManager.getTokenAt(this.laser.getLaserTipCoordinate());
        Set<Orientation> propagatedLaser = tokenInCell.propagateLaser(laserTipOrientation);
        for (Orientation propagatedLaserOrientation : propagatedLaser) {
            if (propagatedLaserOrientation == null) {
                laserContinue = false;
                return null;
            } else {
                Coordinate laserFragmentTo = oneCellInDirectionOfOrientation(laser.getLaserTipCoordinate(), propagatedLaserOrientation);
                if (laserFragmentTo != null) {
                    if (isCoordinateOnTheBoard(laserFragmentTo)) {    //Check if laser is not shooting out of the board.
                        this.laserContinue = true;
                        LaserFragment generatedLaserFragment = new LaserFragment(this.laser.getLaserTipCoordinate(), laserFragmentTo);
                        this.laser.setLaserTipCoordinate(laserFragmentTo);
                        this.laserTipOrientation = propagatedLaserOrientation;
                        return generatedLaserFragment;
                    }
                }
            }
        }
        return null;
    }

    public Laser generateLaser() {

        this.laser.addFragment(generateLaserFragmentFromLaserGun());   //If laser is not shooting at a wall or a block it generates the first fragment.
        while (laserContinue) {    //if laserFragments is empty, laser is shooting in the wrong direction.
            Token tokenAtLaserTipCoordinate = tokenManager.getTokenAt(laser.getLaserTipCoordinate());
            LaserFragment fragment;
            if (tokenAtLaserTipCoordinate == null) {
                fragment = continueLaserInSameDirectionOneCell();
            } else {
                fragment = generateTokenLaserPropagation();
            }
            if (fragment == null){
                return this.laser;
            }
            this.laser.addFragment(fragment);
            System.out.println("New laser fragment added: " + fragment);
        }
        return this.laser;
    }
}
