package edu.cpp.tictactoe;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private static final Scanner input = new Scanner(System.in);
    public HumanPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public Move nextMove(Board board){
        while (true) {
            try {
                System.out.print("Player " + getMark() + ", enter your move (row and column, separated by space): ");
                int row;
                int col;

                if (input.hasNextInt()) {
                    row = input.nextInt();
                    if (input.hasNextInt()) {
                        col = input.nextInt();
                        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                            throw new Exception("Coordinate out of bounds. Please try again.");
                        }
                        return new Move(row, col, getMark());
                    }
                    else {
                        throw new Exception("Please enter two integers (row and column, separated by space)");
                    }
                }
                else {
                    throw new Exception("Please enter two integers (row and column, separated by space)");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }

        }
    }
}