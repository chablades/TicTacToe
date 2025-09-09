package edu.cpp.tictactoe;

import java.util.Optional;

public class MinimaxAIPlayer extends Player {
    private final int maxDepth;
    private int size;
    private int winCondition;
    private Mark[][] grid;

    public MinimaxAIPlayer(Mark mark, int depth) {
        super(mark);
        this.maxDepth = Math.max(1, depth);
    }

    @Override
    public Move nextMove(Board b) {
        size = b.getSize();
        winCondition = b.getWinCondition();
        grid = new Mark[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = b.getCell(r, c);
            }
        }

        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c] == Mark.EMPTY) {
                    grid[r][c] = getMark();
                    int score = minimax(grid, maxDepth, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    grid[r][c] = Mark.EMPTY;
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(r, c, getMark());
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(Mark[][] grid, int depth, boolean maximizing, int alpha, int beta) {
        Integer term = evalTerm(grid, depth);
        if (term != null) return term;
        if (depth == 0) return 0;

        if (maximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (grid[r][c] == Mark.EMPTY) {
                        grid[r][c] = getMark();
                        bestScore = Math.max(bestScore, minimax(grid, depth - 1, false, alpha, beta));
                        grid[r][c] = Mark.EMPTY;
                        alpha = Math.max(bestScore, alpha);
                        if (beta <= alpha) return bestScore;
                    }
                }
            }
            return bestScore == Integer.MIN_VALUE ? 0 : bestScore;
        }
        else {
            int bestScore = Integer.MAX_VALUE;
            Mark opponent = getMark() == Mark.X ? Mark.O : Mark.X;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (grid[r][c] == Mark.EMPTY) {
                        grid[r][c] = opponent;
                        bestScore = Math.min(bestScore, minimax(grid, depth - 1, true, alpha, beta));
                        grid[r][c] = Mark.EMPTY;
                        beta = Math.min(bestScore, beta);
                        if (beta <= alpha) return bestScore;
                    }
                }
            }
            return bestScore == Integer.MAX_VALUE ? 0 : bestScore;
        }


    }

    private Integer evalTerm(Mark[][] grid, int depth) {
        if (winner().equals(Optional.of(getMark()))) return 1000 + depth;
        if (!winner().equals(Optional.of(getMark())) && !winner().equals(Optional.empty())) return -1000 - depth;
        if (isFull()) return 0;
        return null;
    }

    private Optional<Mark> winner() {

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

    private boolean isFull() {
        for  (int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if  (grid[r][c] == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


}
