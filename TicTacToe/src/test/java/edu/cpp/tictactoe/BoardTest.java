package edu.cpp.tictactoe;
import java.util.Scanner;

public class BoardTest {
    public static void main(String[] args) {
        // Create any size board n > 2
        Scanner input = new Scanner(System.in);
        System.out.println("What board size do you want? ");
        int board_size = input.nextInt();
        Board board = new Board(board_size);

        // Create two players (assuming Player takes a name and a Mark)
        Player p1 = new HumanPlayer(Mark.X);
        Player p2 = new HumanPlayer(Mark.O);

        // Create the game
        Game game = new Game(p1, p2, board);

        // Run the game
        game.run();
    }
}