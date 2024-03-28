package Classes.Level;

import Classes.Lazer.Lazer;
import Classes.Lazer.LazerFragment;
import Classes.Token.LazerGun;
import Classes.Token.Orientation;
import Classes.Token.Token;
import Classes.Utils.Coordinate;
import Classes.Utils.Pair;

import java.util.ArrayList;
import java.util.Set;

public class Board {

    public Set<Tile> tiles;

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public void setTokenCoordinate(Token token, Coordinate coordinate) {
    }

    public Coordinate getTokenCoordinate(Token token) {
        return null;
    }

    public boolean isCoordinateInBoard(Coordinate coordinate) {
        return false;
    }

    /**
     * Generate the lazer
     *
     * @return Lazer - Lazer object
     * @author Léonard AMSLER - s231715
     */
    public Lazer generateLazer() {
        Lazer lazer = new Lazer();
        ArrayList<Pair<Coordinate, Orientation>> toCheckCoordinates = new ArrayList<>();

        for (Tile tile : tiles) {
            Token token = tile.getToken();
            if (token instanceof LazerGun) {
                Orientation tokenOrientation = token.getOrientation();
                Pair<Coordinate, Orientation> newPair = new Pair<>(this.getTokenCoordinate(token), tokenOrientation);
                toCheckCoordinates.add(newPair);
            }
        }

        while (!toCheckCoordinates.isEmpty()) {
            Pair<Coordinate, Orientation> currentPair = toCheckCoordinates.get(0);
            Coordinate currentCoordinate = currentPair.first();
            Orientation currentOrientation = currentPair.second();
            toCheckCoordinates.remove(0);

            Tile currentTile = null;
            for (Tile tile : tiles) {
                if (tile.coordinate.equals(currentCoordinate)) {
                    currentTile = tile;
                    break;
                }
            }

            if (currentTile == null) {
                continue;
            }

            Set<Orientation> outOrientations = currentTile.propagateLazer(currentOrientation);

            for (Orientation outOrientation : outOrientations) {
                int x = currentCoordinate.getX();
                int y = currentCoordinate.getY();
                switch (outOrientation) {
                    case UP -> y--;
                    case DOWN -> y++;
                    case LEFT -> x--;
                    case RIGHT -> x++;
                }
                Coordinate outCoordinate = new Coordinate(x, y);
                LazerFragment lazerFragment = new LazerFragment(currentCoordinate, outCoordinate);
                lazer.addLazerFragment(lazerFragment);
                toCheckCoordinates.add(new Pair<>(outCoordinate, outOrientation));
            }
        }

        return lazer;
    }
}
