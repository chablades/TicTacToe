package edu.cpp.tictactoe;
import java.util.ArrayList;
import java.util.Random;

public class SmartAIPlayer extends Player {
    public SmartAIPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public Move nextMove(Board board) {
        ArrayList<Move> availableMoves = getAvailableMoves(board);
        Random rand = new Random();

        // Tries to win
        for (Move move : availableMoves) {
            Board copy = copyBoard(board);
            copy.place(move);
            if (copy.winner().isPresent() && copy.winner().get() == getMark()) {
                return move;
            }
        }

        // Blocks opponent
        Mark opponentMark = (getMark() == Mark.X) ? Mark.O : Mark.X;
        for (Move move : availableMoves) {
            Board copy = copyBoard(board);
            copy.place(new Move(move.getRow(), move.getCol(), opponentMark));
            if (copy.winner().isPresent() && copy.winner().get() == opponentMark) {
                return move;
            }
        }

        // Otherwise, random move
        return availableMoves.get(rand.nextInt(availableMoves.size()));
    }

    private ArrayList<Move> getAvailableMoves(Board board) {
        int size = board.getSize();
        ArrayList<Move> availableMoves = new ArrayList<>();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board.getCell(r, c) == Mark.EMPTY) {
                    availableMoves.add(new Move(r, c, getMark()));
                }
            }
        }
        return availableMoves;
    }

    // Make a deep copy of the board so we can simulate moves
    private Board copyBoard(Board original) {
        Board copy = new Board(original.getSize(), original.getWinCondition());
        for (int r = 0; r < original.getSize(); r++) {
            for (int c = 0; c < original.getSize(); c++) {
                Mark cell = original.getCell(r, c);
                if (cell != Mark.EMPTY) {
                    copy.place(new Move(r, c, cell));
                }
            }
        }
        return copy;
    }
}