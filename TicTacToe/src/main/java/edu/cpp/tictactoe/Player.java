package edu.cpp.tictactoe;
public abstract class Player {

    private Mark mark;

    // Constructor
    public Player(Mark mark) {
        this.mark = mark;
    }

    // get the Mark of the Player
    public Mark getMark() {
        return mark;
    }

    // Abstract class (subclasses will implement): Get the next move
    public abstract Move nextMove(Board b);
}
