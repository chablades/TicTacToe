package edu.cpp.tictactoe;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        //Create board
        Board board = new Board(3);
        //Create Human Player and AI Player
        Player human_Player = new HumanPlayer(Mark.X);
        Player ai_Player = new RandomAIPlayer(Mark.O);
        //
        Game game = new Game(human_Player , ai_Player, board);


        System.out.println("Tic Tac Toe");
        game.run();

    }
}