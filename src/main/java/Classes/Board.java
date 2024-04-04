package Classes;

import Classes.Token;
import Classes.Utils.Coordinate;

public class Board {
    private final int height;
    private final int width;

    private Token[][] board;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;

        board = new Token[height][width];
    }

    public boolean placeToken(Token token, Coordinate position) {
        if (isCoordinateInBoard(position) && isPositionEmpty(position)) {
            board[position.y()][position.x()] = token;
            return true;
        }

        return false;
    }

    public boolean isCoordinateInBoard(Coordinate coordinate) {
        return coordinate.x() >= 0 && coordinate.x() < width && coordinate.y() >= 0 && coordinate.y() < height;
    }

    public boolean isPositionEmpty(Coordinate position) {
        return board[position.x()][position.y()] == null;
    }
}
