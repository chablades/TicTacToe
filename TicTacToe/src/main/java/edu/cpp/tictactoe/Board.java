package edu.cpp.tictactoe;
import java.util.Arrays;
import java.util.Optional;

public class Board {
    private final Mark[][] grid;
    private final int size;
    private final int winCondition;

    // Constructor
    public Board(int size, int winCondition) {
        if (size < 3) {
            throw new IllegalArgumentException("Board size must be at least 3");
        }
        if (winCondition > size) {
            throw new IllegalArgumentException("Win condition cannot be greater than board size");
        }
        this.size = size;
        this.winCondition = winCondition;
        this.grid = new Mark[size][size];
        reset();
    }


    // Place move onto board
    public void place(Move mv) {
        if (grid[mv.getRow()][mv.getCol()] != Mark.EMPTY) {
            throw new IllegalArgumentException("Cell already taken at (" + mv.getRow() + ", " + mv.getCol() + ")");
        }
        grid[mv.getRow()][mv.getCol()] = mv.getMark();
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
        for (int r = 0; r < size; r++) {
            for (int c = 0; c <= size - winCondition; c++) {
                Mark cell = grid[r][c];
                if (cell != Mark.EMPTY) {
                    boolean winner = true;
                    for (int k = 1; k < winCondition; k++) {
                        if (grid[r][c + k] != cell) {
                            winner = false;
                            break;
                        }
                    }
                    if (winner) return Optional.of(cell);
                }
            }
        }


        // Check columns
        for (int c = 0; c < size; c++) {
            for (int r = 0; r <= size - winCondition; r++) {
                Mark cell = grid[r][c];
                if (cell != Mark.EMPTY) {
                    boolean winner = true;
                    for (int k = 1; k < winCondition; k++) {
                        if (grid[r + k][c] != cell) {
                            winner = false;
                            break;
                        }
                    }
                    if (winner) return Optional.of(cell);
                }
            }
        }


        // Check diagonal (Top Left to Bottom Right)
        for (int r = 0; r <= size - winCondition; r++) {
            for (int c = 0; c <= size - winCondition; c++) {
                Mark cell = grid[r][c];
                if (cell != Mark.EMPTY) {
                    boolean winner = true;
                    for (int k = 1; k < winCondition; k++) {
                        if (grid[r + k][c + k] != cell) {
                            winner = false;
                            break;
                        }
                    }
                    if (winner) return Optional.of(cell);
                }
            }
        }


        // Check diagonal (Top Right to Bottom Left)
        for (int r = 0; r <= size - winCondition; r++) {
            for (int c = winCondition - 1; c < size; c++) {
                Mark cell = grid[r][c];
                if (cell != Mark.EMPTY) {
                    boolean winner = true;
                    for (int k = 1; k < winCondition; k++) {
                        if (grid[r + k][c - k] != cell) {
                            winner = false;
                            break;
                        }
                    }
                    if (winner) return Optional.of(cell);
                }
            }
        }
        return Optional.empty();

    }

    // Reset board
    public void reset() {
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], Mark.EMPTY);
        }
    }

    public int getSize(){
        return size;
    }

    public int getWinCondition() {return winCondition; }

}