package edu.cpp.tictactoe;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    /**
     * Test double for Player that returns moves from a predefined list.
     */
    static class ScriptedPlayer extends Player {
        private final List<Move> moves;
        private int index = 0;

        ScriptedPlayer(Mark mark, List<Move> moves) {
            super(mark);
            this.moves = moves;
        }

        @Override
        public Move nextMove(Board board) {
            if (index >= moves.size()) {
                throw new IllegalStateException("No more scripted moves");
            }
            return moves.get(index++);
        }
    }

    @Test
    void scriptedGameEndsWithRowWinForX() {
        Board board = new Board(3,3);

        // X will win on the top row
        ScriptedPlayer p1 = new ScriptedPlayer(Mark.X, List.of(
                new Move(0, 0, Mark.X),
                new Move(0, 1, Mark.X),
                new Move(0, 2, Mark.X)
        ));

        ScriptedPlayer p2 = new ScriptedPlayer(Mark.O, List.of(
                new Move(1, 0, Mark.O),
                new Move(1, 1, Mark.O)
        ));

        Game game = new Game(p1, p2, board);
        game.run();

        assertEquals(Optional.of(Mark.X), board.winner());
        assertFalse(board.isFull()); // ended early
    }

    @Test
    void scriptedGameEndsWithRowWinForO() {
        Board board = new Board(3,3);

        // X will win on the top row
        ScriptedPlayer p1 = new ScriptedPlayer(Mark.X, List.of(
                new Move(0, 0, Mark.O),
                new Move(0, 1, Mark.O),
                new Move(0, 2, Mark.O)
        ));

        ScriptedPlayer p2 = new ScriptedPlayer(Mark.O, List.of(
                new Move(1, 0, Mark.X),
                new Move(1, 1, Mark.X)
        ));

        Game game = new Game(p1, p2, board);
        game.run();

        assertEquals(Optional.of(Mark.O), board.winner());
        assertFalse(board.isFull()); // ended early
    }

    @Test
    void scriptedGameEndsInDraw() {
        Board board = new Board(3,3);

        // Sequence of moves that fills the board with no winner
        ScriptedPlayer p1 = new ScriptedPlayer(Mark.X, List.of(
                new Move(0, 0, Mark.X),
                new Move(0, 2, Mark.X),
                new Move(1, 2, Mark.X),
                new Move(1, 1, Mark.X),
                new Move(2, 1, Mark.X)
        ));

        ScriptedPlayer p2 = new ScriptedPlayer(Mark.O, List.of(
                new Move(0, 1, Mark.O),
                new Move(1, 0, Mark.O),
                new Move(2, 0, Mark.O),
                new Move(2, 2, Mark.O)
        ));

        Game game = new Game(p1, p2, board);
        game.run();

        assertTrue(board.isFull());
        assertEquals(Optional.empty(), board.winner());
    }

    @Test
    void scriptedGameFourByFourWin3() {
        Board board = new Board(4,3);

        // X will win on the top row
        ScriptedPlayer p1 = new ScriptedPlayer(Mark.X, List.of(
                new Move(3, 0, Mark.X),
                new Move(3, 1, Mark.X),
                new Move(3, 2, Mark.X)
        ));

        ScriptedPlayer p2 = new ScriptedPlayer(Mark.O, List.of(
                new Move(1, 0, Mark.O),
                new Move(1, 1, Mark.O)
        ));

        Game game = new Game(p1, p2, board);
        game.run();

        assertEquals(Optional.of(Mark.X), board.winner());
        assertFalse(board.isFull()); // ended early
    }

    @Test
    void scriptedGameFourByFourWin4() {
        Board board = new Board(4,4);

        // X will win on the top row
        ScriptedPlayer p1 = new ScriptedPlayer(Mark.X, List.of(
                new Move(3, 0, Mark.X),
                new Move(3, 1, Mark.X),
                new Move(3, 2, Mark.X),
                new Move(3, 3, Mark.X)
        ));

        ScriptedPlayer p2 = new ScriptedPlayer(Mark.O, List.of(
                new Move(1, 0, Mark.O),
                new Move(1, 1, Mark.O),
                new Move(1, 2, Mark.O)
        ));

        Game game = new Game(p1, p2, board);
        game.run();

        assertEquals(Optional.of(Mark.X), board.winner());
        assertFalse(board.isFull()); // ended early
    }

    @Test
    void invalidMoveThrowsException() {
        Board board = new Board(3,3);
        ScriptedPlayer p1 = new ScriptedPlayer(Mark.X, List.of(
                new Move(0, 0, Mark.X),
                new Move(0, 0, Mark.X) // same spot
        ));
        ScriptedPlayer p2 = new ScriptedPlayer(Mark.O, List.of(
                new Move(1, 1, Mark.O)
        ));
        Game game = new Game(p1, p2, board);
        assertThrows(IllegalStateException.class, game::run);
    }



}