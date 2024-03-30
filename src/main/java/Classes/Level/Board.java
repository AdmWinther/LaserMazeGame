package Classes.Level;

import Classes.Token.Token;
import Classes.Utils.Coordinate;
import Classes.Utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Board {
    private List<Tile> tiles;
    private final int HEIGHT;
    private final int WIDTH;

    public Board(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.tiles = createTiles();
    }

    /**
     * Creates the tiles based of a set of pair of token and coordinates
     * @param height
     * @param width
     * @param tokens
     */
    public Board(int height, int width, Set<Pair<Token, Coordinate>> tokens){
        this.HEIGHT = height;
        this.WIDTH = width;
        this.tiles = createTiles(tokens);
    }


    /**
     * Initializing the board with empty tiles
     * @return the initialized board
     */
    private ArrayList<Tile> createTiles(){
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                tiles.add(new Tile(null, new Coordinate(x,y)));
            }
        }
        return tiles;
    }


    //creating the tiles based on the Token/coordinate pairs
    private ArrayList<Tile> createTiles(Set<Pair<Token, Coordinate>> tokens){
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        //need to isolate the tokens and the coordinates for each pair, (using lambda notation??)
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                tiles.add(new Tile(null,new Coordinate(x,y)));
            }
        }
        return tiles;
    }

    public void moveToken(Token token, Coordinate coordinate){
        Tile t = tileAt(coordinate);
        if (t!=null){
            if (t.hasToken()){
                System.out.println("can't move the token the tile is already taken");
                return;
            }else{
                t.setToken(token);
            }
        }else {
            System.out.println("Incorrect coordinate");
            return;
        }

    }

    public Tile tileAt(Coordinate coordinate){
        for (Tile t:tiles) {
            if (t.getCoordinate().equals(coordinate) )return t;
        }
        return null;
    }


    /*    *//**
     * Generate the lazer
     *
     * @return Lazer - Lazer object
     * @author Léonard AMSLER - s231715
     *//*
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
    }*/

    /**
     * finds the coordinate of a given token
     * @param token
     * @return
     */
    Coordinate getTokenCoordinate(Token token){
        for (Tile t:tiles) {
            if (t.getToken().equals(token) ){
                return t.getCoordinate();
            }
        }
        return null;
    }

    /**
     * Getter for the height of the board
     * @return height
     */
    public int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * Getter for the width of the board
     * @return width
     */
    public int getWIDTH() {
        return WIDTH;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Board other = (Board) obj;
        if(WIDTH!=other.WIDTH && HEIGHT!=other.HEIGHT){
            return false;
        }
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Coordinate coor = new Coordinate(x,y);
                Tile thisTile = this.tileAt(coor);
                Tile thatTile = other.tileAt(coor);
                if(thisTile!=null && thatTile!=null){
                    if(!thisTile.getToken().equals(thatTile.getToken())){
                        return false;
                    }
                }else return false; //considering that a null tile and an empty tile is the same?

            }
        }
        return true;
    }


}
