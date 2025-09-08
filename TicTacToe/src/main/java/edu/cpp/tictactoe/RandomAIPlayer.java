package edu.cpp.tictactoe;
import java.util.ArrayList;
import java.util.Random;

public class RandomAIPlayer extends Player {
    public RandomAIPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public Move nextMove(Board board){
        ArrayList<Move> availableMoves = getAvailableMoves(board);
        Random rand = new Random();

        int randIndex = rand.nextInt(availableMoves.size());
        return availableMoves.get(randIndex);
    }
    private ArrayList<Move> getAvailableMoves (Board board){
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
}
