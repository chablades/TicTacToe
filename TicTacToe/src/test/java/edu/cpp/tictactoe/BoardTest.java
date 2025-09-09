package edu.cpp.tictactoe;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {
    @Test
    void placeValidMark(){
        Board b = new Board(3, 3);
        b.place(new Move(0, 0, Mark.X));
        assertEquals(Mark.X,b.getCell(0, 0));
    }

    @Test
    void rejectOccupiedMark(){
        Board b = new Board(3, 3);
        b.place(new Move(0, 0, Mark.X));
        assertThrows(IllegalArgumentException.class, () -> b.place(new Move(0, 0, Mark.O)));
    }

    @Test
    void rejectOutofBoundsMark(){
        Board b = new Board(3, 3);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> b.place(new Move(3, 0, Mark.O)));
    }

    @Test
    void validMovesCheck(){
        Board b = new Board(3, 3);
        b.place(new Move(0, 0, Mark.O));
        b.place(new Move(0, 1, Mark.X));
        b.place(new Move(0, 2, Mark.O));
        b.place(new Move(1, 0, Mark.X));
        b.place(new Move(1, 1, Mark.O));
        b.place(new Move(1, 2, Mark.X));
        assertEquals(Optional.empty(), b.winner());
        assertEquals(Mark.X,b.getCell(0, 1));
    }

    @Test
    void rowWinDetected() {
        Board b = new Board(3, 3);
        b.place(new Move(0, 0, Mark.X));
        b.place(new Move(1, 0, Mark.O));
        b.place(new Move(0, 1, Mark.X));
        b.place(new Move(1, 1, Mark.O));
        b.place(new Move(0, 2, Mark.X));
        assertEquals(Optional.of(Mark.X), b.winner());
    }
    @Test
    void colWinDetected() {
        Board b = new Board(3, 3);
        b.place(new Move(0, 0, Mark.X));
        b.place(new Move(1, 2, Mark.O));
        b.place(new Move(0, 1, Mark.X));
        b.place(new Move(1, 0, Mark.O));
        b.place(new Move(0, 2, Mark.X));
        assertEquals(Optional.of(Mark.X), b.winner());
    }
    @Test
    void diagonalWinDetected(){
        Board b = new Board(3, 3);
        b.place(new Move(0, 0, Mark.X));
        b.place(new Move(0, 2, Mark.O));
        b.place(new Move(1, 1, Mark.X));
        b.place(new Move(2, 0, Mark.O));
        b.place(new Move(2, 2, Mark.X));
        assertEquals(Optional.of(Mark.X), b.winner());
    }

    @Test
    void oppositeDiagonalWinDetected(){
        Board b = new Board(3, 3);
        b.place(new Move(0, 2, Mark.X));
        b.place(new Move(1, 2, Mark.O));
        b.place(new Move(1, 1, Mark.X));
        b.place(new Move(2, 1, Mark.O));
        b.place(new Move(2, 0, Mark.X));
        assertEquals(Optional.of(Mark.X), b.winner());
    }

    @Test
    void boardResetClearsAllCellsAndWinner() {
        Board b = new Board(3, 3);
        b.place(new Move(0, 0, Mark.X));
        b.place(new Move(0, 1, Mark.X));
        b.place(new Move(0, 2, Mark.X)); // X wins
        assertEquals(Optional.of(Mark.X), b.winner());

        b.reset();

        // All cells should be empty
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertNull(b.getCell(r, c), "Cell (" + r + "," + c + ") should be empty after reset");
            }
        }
        // Winner should be cleared
        assertEquals(Optional.empty(), b.winner());
    }


}