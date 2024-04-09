package Classes;

import Classes.Tokens.Token;
import Classes.Utils.Coordinate;
public class Board {
    private final int height;
    private final int width;

    private final Token[][] board;

    public Board(int widthX, int heightY) {
        this.width = widthX;
        this.height = heightY;

        board = new Token[widthX][heightY];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                board[i][j] = null;
            }
        }
    }

    public boolean placeToken(Token token, Coordinate position) {
        if (isCoordinateInBoard(position) && isPositionEmpty(position)) {
            board[position.x()][position.y()] = token;
            return true;
        }
        return false;
    }

    public boolean removeToken(Coordinate position) {
        if (isCoordinateInBoard(position)) {
            board[position.x()][position.y()] = null;
            return true;
        }
        return false;
    }

    public boolean isCoordinateInBoard(Coordinate coordinate) {
        return coordinate.x() >= 0 && coordinate.x() < width && coordinate.y() >= 0 && coordinate.y() < height;
    }


    /*public int IndexOfTokenAt(Coordinate coordinate){
        if (coordinate.x() >= width || coordinate.y() >= height) {
            return -1;
        }
        return board[coordinate.x()][coordinate.y()];
    }*/

    public Token getTokenAt(Coordinate position) {
        return board[position.x()][position.y()];
    }

    public boolean isPositionEmpty(Coordinate position) {
        return board[position.x()][position.y()] == null;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public Token removeTokenFromBoard(Coordinate fromCoordinate) {
        Token removedToken = board[fromCoordinate.x()][fromCoordinate.y()];
        board[fromCoordinate.x()][fromCoordinate.y()] = null;
        return removedToken;
    }
}
