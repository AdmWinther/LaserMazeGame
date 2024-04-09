package Classes;

import Classes.Utils.Coordinate;

public class Board {
    private final int height;
    private final int width;

    private final int[][] board;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;

        board = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                board[i][j] = -1;
            }
        }
    }

    public boolean placeToken(int tokenIndex, Coordinate position) {
        if (isCoordinateInBoard(position) && isPositionEmpty(position)) {
            board[position.y()][position.x()] = tokenIndex;
            return true;
        }
        return false;
    }

    public boolean isCoordinateInBoard(Coordinate coordinate) {
        return coordinate.x() >= 0 && coordinate.x() < width && coordinate.y() >= 0 && coordinate.y() < height;
    }

    /**
     * extracts
     *
     * @param coordinate
     * @return
     */
    public int IndexOfTokenAt(Coordinate coordinate){
        if (coordinate.x() >= width || coordinate.y() >= height) {
            return -1;
        }
        return board[coordinate.x()][coordinate.y()];
    }

    public boolean isPositionEmpty(Coordinate position) {
        return board[position.x()][position.y()] == -1;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public void removeTokenFromBoard(Coordinate fromCoordinate) {
        board[fromCoordinate.x()][fromCoordinate.y()] = -1;
    }
}
