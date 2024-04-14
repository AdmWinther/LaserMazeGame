package Classes.Laser;

import Classes.Orientation;
import Classes.TokenManager;
import Classes.Tokens.*;
import Classes.Utils.Coordinate;

import java.util.Set;


public class LaserGenerator {

    private Orientation laserTipOrientation;
    private Coordinate laserTipCoordinate;
    private boolean laserContinue = false;
    private final TokenManager tokenManager;

    public LaserGenerator(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
        this.laserContinue = false;
    }

    private boolean isCoordinateOnTheBoard(Coordinate coordinate){
        if(coordinate.x() < tokenManager.getWidthX() && coordinate.x()>= 0){
            return coordinate.y() < tokenManager.getHeightY() && coordinate.y() >= 0;
        }
        return false;
    }

    private Coordinate oneCellInDirectionOfOrientation(Coordinate from) {
        if(this.laserTipOrientation == Orientation.UP) return new Coordinate(from.x(), from.y()+1);
        if(this.laserTipOrientation == Orientation.DOWN) return new Coordinate(from.x(), from.y()-1);
        if(this.laserTipOrientation == Orientation.RIGHT) return new Coordinate(from.x()+1, from.y());
        if(this.laserTipOrientation == Orientation.LEFT) return new Coordinate(from.x()-1, from.y());
        return null;
    }

    private Coordinate oneCellInDirectionOfOrientation(Coordinate from, Orientation newOrientation) {
        if(newOrientation == Orientation.UP) return new Coordinate(from.x(), from.y()+1);
        if(newOrientation == Orientation.DOWN) return new Coordinate(from.x(), from.y()-1);
        if(newOrientation == Orientation.RIGHT) return new Coordinate(from.x()+1, from.y());
        if(newOrientation == Orientation.LEFT) return new Coordinate(from.x()-1, from.y());
        return null;
    }

    private LaserFragment continueLaserInSameDirectionOneCell() {
        if(laserContinue) {
            Coordinate from = laserTipCoordinate;
            Coordinate to = oneCellInDirectionOfOrientation(from);

            if(to != null){
                if(isCoordinateOnTheBoard(to)){
                    this.laserTipCoordinate = to;
                    return new LaserFragment(from, to);
                }
                else{
                    this.laserContinue = false;
                }
            }
        }
        return null;
    }

    private LaserFragment generateLaserFragmentFromLaserGun() {
        LaserGun laserGun = tokenManager.getLaserGun();
        Coordinate laserGunPosition = tokenManager.findLaserGunPosition();
        this.laserTipOrientation = laserGun.getOrientation();
        Coordinate laserFragmentTo = oneCellInDirectionOfOrientation(laserGunPosition);
        if(laserFragmentTo != null){
            if(isCoordinateOnTheBoard(laserFragmentTo)){    //Check if laser is not shooting out of the board.
                this.laserContinue = true;
                this.laserTipCoordinate = laserFragmentTo;
                return new LaserFragment(laserGunPosition, laserFragmentTo);
            }
        }
        return null;
    }

    private LaserFragment generateTokenLaserPropagation(){
        Token tokenInCell = tokenManager.getTokenAt(laserTipCoordinate);
        Set<Orientation> propagatedLaser = tokenInCell.propagateLaser(laserTipOrientation);
        for (Orientation propagatedlaserOrientation : propagatedLaser){
            if(propagatedlaserOrientation == null){
                laserContinue = false;
                return null;
            } else {
                Coordinate laserFragmentTo = oneCellInDirectionOfOrientation(laserTipCoordinate ,propagatedlaserOrientation);
                if(laserFragmentTo != null){
                    if(isCoordinateOnTheBoard(laserFragmentTo)){    //Check if laser is not shooting out of the board.
                        this.laserContinue = true;
                        LaserFragment generatedLaserFragment = new LaserFragment(this.laserTipCoordinate, laserFragmentTo);
                        this.laserTipCoordinate = laserFragmentTo;
                        this.laserTipOrientation = propagatedlaserOrientation;
                        return generatedLaserFragment;
                    }
                }
            }
        }
        return null;
    }

    public Laser generateLaser() {

        Laser laser = new Laser();

        laser.addFragment(generateLaserFragmentFromLaserGun());   //If laser is not shooting at a wall or a block it generates the first fragment.
        while(laserContinue){    //if laserFragments is empty, laser is shooting in the wrong direction.
            Token tokenAtLaserTipCoordinate = tokenManager.getTokenAt(laserTipCoordinate);
            if(tokenAtLaserTipCoordinate == null){
                laser.addFragment(continueLaserInSameDirectionOneCell());
            } else {
                laser.addFragment(generateTokenLaserPropagation());
            }
        }
        return laser;
    }
}
