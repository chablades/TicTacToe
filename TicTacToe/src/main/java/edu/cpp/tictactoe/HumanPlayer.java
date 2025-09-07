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
                System.out.print("Player " + getMark() + ", enter your move (row and column): ");
                int row = input.nextInt();
                int col = input.nextInt();

                if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                    System.out.println("Coordinates out of bounds. Try again.");
                    continue;
                }

                return new Move(row, col, getMark());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two integers separated by space.");
                input.nextLine();
            }

        }
    }
}