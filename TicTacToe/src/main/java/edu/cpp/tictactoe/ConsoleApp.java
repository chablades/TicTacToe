package edu.cpp.tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        //Ask user to make any size board n
        Scanner input = new Scanner(System.in);
        int board_size;
        int win_condition;
        //Ask for desired board size
        while (true) {
            try {
                System.out.println("What is your desired board grid? (Has to be n > 2) :");
                board_size = input.nextInt();

                if (board_size > 2) {
                    break; // valid input, exit loop
                } else {
                    System.out.println("Invalid board size. Board size must be greater than 2.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number greater than 2.");
                input.nextLine(); // clear invalid input from buffer
            }
        }
        //Ask for desired win condition (
        while (true) {
            try {
                System.out.println("What is your desired win condition (Has to be 2 < i <= board_size) :");
                win_condition = input.nextInt();

                if (2 < win_condition  && win_condition <= board_size) {
                    break; // valid input, exit loop
                } else {
                    System.out.println("Invalid win condition. Win condition must be greater than 2 and must not be greater than the board size");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number greater than 2.");
                input.nextLine(); // clear invalid input from buffer
            }
        }

        //Create board based on given board size and win condition
        Board board = new Board(board_size, win_condition);
        //Create Human Player and AI Players (depending on what the user wants to play)
        Player human_Player = new HumanPlayer(Mark.X);
        Player random_ai_Player = new RandomAIPlayer(Mark.O);
        Player smart_ai_Player = new SmartAIPlayer((Mark. O));
        Player miniMax_ai_Player = new MinimaxAIPlayer((Mark. O), 5);

        //Mark the easy AI as random, mark the normal AI as smart_ai, and mark mini_max AI as hard
        Player chosen_player;
        while (true) {
            try {
                System.out.println("Choose difficulty (by integer): easy: 1, normal: 2, hard: 3");
                int difficulty = input.nextInt();
                switch (difficulty) {
                    case 1:
                        System.out.println("You have chosen 'easy'");
                        chosen_player = random_ai_Player;
                        break;
                    case 2:
                        System.out.println("You have chosen 'normal'");
                        chosen_player = smart_ai_Player;
                        break;
                    case 3:
                        System.out.println("You have chosen 'hard'");
                        chosen_player = miniMax_ai_Player; // replace with minimax_ai_Player later
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        continue; // ask again
                }
                break; // exit loop once a valid choice is made
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please type the exact integer for difficulty");
                input.nextLine();
            }
        }

        Game game = new Game(human_Player , chosen_player , board);


        System.out.println("Tic Tac Toe");
        game.run();

    }
}