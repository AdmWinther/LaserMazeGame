package Classes;

import Classes.Token.Block;
import Classes.Token.Token;
import Classes.Utils.Coordinate;

public class Printer {
    private int width;
    private int height;

    public String draw(Level level){
        this.height = level.tokenManager().getHeightY();
        this.width = level.tokenManager().getWidthX();

        //initializing the boardPrint
        StringBuilder boardPrint = new StringBuilder();

        boardPrint.append(this.columnNumberLine(width));

        //add _________ as a line under the first row
        boardPrint.append(this.addLine(width));

        //add rows
        boardPrint.append(this.drawBoardGrid(level));

        //add _________ as a line under the last row
        boardPrint.append(this.addLine(width));

        //Make the first row numbering based on tokenNameMaxLength spacing
        boardPrint.append(this.columnNumberLine(width));

        System.out.println(boardPrint);
        return(boardPrint.toString());
    }

    private StringBuilder drawBoardGrid(Level level) {
        StringBuilder grid = new StringBuilder();
        for (int i = 0; i < this.height; i++) {
            grid.append(" ").append(i).append("|");
            for (int j = 0; j < this.width; j++) {
                if(level.tokenManager().isEmpty(new Coordinate(i, j))){
                    grid.append(" ".repeat(3));
                } else {
                    Token token = level.tokenManager().getTokenAt(new Coordinate(i, j));

                    if(token instanceof Block){
                        grid.append("B".repeat(3));
                    }
                }
            }
            grid.append("|").append(i);
            grid.append("\n");
        }
        return grid;
    }

    private StringBuilder columnNumberLine(int width){
        StringBuilder firstLine = new StringBuilder();
        firstLine.append(" ".repeat(3));
        for (int i = 0; i < width; i++) {
            firstLine.append(" ").append(i).append(" ");
        }
        firstLine.append("\n");
        return firstLine;
    }

    private StringBuilder addLine(int width){
        StringBuilder line = new StringBuilder();
        line.append(" ".repeat(3));
        line.append("_".repeat((width)*3));
        line.append("\n");
        return line;

    }
}
