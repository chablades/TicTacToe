package edu.cpp.tictactoe;
import java.util.Optional;

public class Board {
    private Mark[][] grid;
    private final int size;

    // Constructor
    public Board(int size) {
        this.size = size;
        this.grid = new Mark[size][size];

        reset();
    }

    // Place move onto board
    public void place(Move mv) {
        grid[mv.row][mv.col] = mv.mark;
    }

    // Get cell (current player) on board
    public Mark getCell(int r, int c) {
        return grid[r][c];
    }

    // Check if board is full
    public boolean isFull() {
        for (int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if board has winner
    public Optional<Mark> winner() {

        // Check rows
        for (int i = 0; i < grid.length; i++) {
            Mark cell = grid[i][0];
            if (cell != Mark.EMPTY) {
                boolean winner = true;

                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] != cell) {
                        winner = false;
                        break;
                    }
                }

                if (winner) return Optional.of(cell);
            }
        }

        // Check columns
        for (int i = 0; i < grid.length; i++) {
            Mark cell = grid[0][i];
            if (cell != Mark.EMPTY) {
                boolean winner = true;

                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[j][i] != cell) {
                        winner = false;
                        break;
                    }
                }

                if (winner) return Optional.of(cell);
            }
        }

        // Check diagonal (Top Left to Bottom Right)
        Mark cell = grid[0][0];
        if (cell != Mark.EMPTY) {

            boolean winner = true;

            for (int i = 0; i < grid[0].length; i++) {
                if (grid[i][i] != cell) {
                    winner = false;
                    break;
                }
            }
            if (winner) return Optional.of(cell);
        }

        // Check diagonal (Top Right to Bottom Left)
        cell = grid[0][grid.length - 1];
        if (cell != Mark.EMPTY) {

            boolean winner = true;

            for (int i = 0; i < grid[0].length; i++) {
                if (grid[i][grid.length - i - 1] != cell) {
                    winner = false;
                    break;
                }
            }
            if (winner) return Optional.of(cell);
        }

        return Optional.empty();
    }

    // Reset board
    public void reset() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Mark.EMPTY;
            }
        }
    }

    //Getter for size
    public int getSize() {
        return size;
    }
}
