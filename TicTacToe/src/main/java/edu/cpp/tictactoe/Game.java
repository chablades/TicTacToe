package edu.cpp.tictactoe;

public class Game {
    //Game constructor that takes two Players and Board
    private final Player p1;
    private final Player p2;
    private final Board board;
    private Player current;

    public Game(Player p1, Player p2, Board board) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
        this.current = p1; // Player 1 always starts first
    }

    public void run() {
        boolean running = true;
        // run () loop
        while (board.winner().isEmpty() && !board.isFull()) {
            //Ask current player for Move, pace the move on the board, then print board
            Move move = current.nextMove(board);
            board.place(move);
            printBoard();
            // Check winner() or draw, if neither, continue the game
            if (board.winner().isEmpty()) {
                swapCurrent();
            }

            //Game Over (if board.winner().isEmpty is false)
            if (board.winner().isPresent()) {
                System.out.println("Winner: ");
            } else {
                System.out.println("It's a draw.");
            }
        }
    }

    private void swapCurrent() {
        current = (current == p1) ? p2 : p1;
    }

    private void printBoard() {
        int size = board.getSize();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print(symbol(board.getCell(r, c)));
                if (c < size - 1) System.out.print(" | ");
            }
            System.out.println();
            if (r < size - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    private char symbol(Mark m) {
        return switch (m) {
            case X -> 'X';
            case O -> 'O';
            case EMPTY -> ' ';
        };
    }

}