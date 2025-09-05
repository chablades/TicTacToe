package edu.cpp.tictactoe;
public class Move {
    private final int row;
    private final int col;
    private final Mark mark;

    // Constructor
    public Move(int row, int col, Mark mark) {
        if(row < 0 || col < 0){
            throw new IllegalArgumentException("Row and Col must be >= 0");
        }

        this.row = row;
        this.col = col;
        this.mark = mark;
    }

    //Getters
    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public Mark getMark() {
        return mark;
    }

}
