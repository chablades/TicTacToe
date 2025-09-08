package edu.cpp.tictactoe;

import java.util.Random;

public class GameTest {
    public static void main(String[] args) {
        // Create a 3x3 board
        Board board = new Board(3);

        // Create two players (assuming Player takes a name and a Mark)
        Player p1 = new HumanPlayer(Mark.X);
        Player p2 = new RandomAIPlayer(Mark.O);

        // Create the game
        Game game = new Game(p1, p2, board);

        // Run the game
        game.run();
    }
}