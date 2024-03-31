package tictactoe;
import static org.junit.Assert.assertEquals;

import tictactoe.FailingAppendable;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and appendable.
 */
public class TicTacToeControllerTest {

  private TicTacToeModel model;
  private StringBuilder log;

  @Before
  public void setUp() {
    model = new TicTacToeModel();
    log = new StringBuilder();
  }
  // ADDITIONAL TEST CASES TO IMPLEMENT:
  // Play game to completion, where there is a winner
  // Input where the q comes instead of an integer for the row
  // Input where the q comes instead of an integer for the column
  // Input where non-integer garbage comes instead of an integer for the row
  // Input where non-integer garbage comes instead of an integer for the column
  // Input where the move is integers, but outside the bounds of the board
  // Input where the move is integers, but invalid because the cell is occupied
  // Multiple invalid moves in a row of various kinds
  // Input including valid moves interspersed with invalid moves, game is played to completion
  // What happens when the input ends "abruptly" -- no more input, but not quit, and game not over
  // THIS IS NOT AN EXHAUSTIVE LIST

  @Test
  public void testGameCompletionWithWinner() {
    StringReader input = new StringReader("1 1 1 2 2 2 1 3 3 3");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    System.out.print(log);
    assertTrue(log.toString().contains("Game is over! X wins."));
  }

  @Test
  public void testQuitInsteadOfRow() {
    StringReader input = new StringReader("q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals(12, lines.length);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  ", lastMsg);
  }

  @Test
  public void testQuitInsteadOfCol() {
    StringReader input = new StringReader("1 q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals(12, lines.length);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  ", lastMsg);
  }

  @Test
  public void testNonIntegerInputForRow() {
    StringReader input = new StringReader("two 2");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid number: two", lines[6]);
  }

  @Test
  public void testNonIntegerInputForCol() {
    StringReader input = new StringReader("2 two");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid number: two", lines[6]);
  }

  @Test
  public void testMoveOutOfBounds() {
    StringReader input = new StringReader("4 4");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid move: 4, 4", lines[6]);
  }

  @Test
  public void testMoveToOccupiedCell() {
    StringReader input = new StringReader("1 1 1 1 q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid move: 1, 1", lines[12]);
  }

  @Test
  public void testMultipleInvalidMoves() {
    StringReader input = new StringReader("1 1 0 0 1 1 one 1 1 one q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    String[] lines = log.toString().split("\n");
    assertEquals("Not a valid move: 0, 0", lines[12]);
    assertEquals("Not a valid move: 1, 1", lines[13]);
    assertEquals("Not a valid number: one", lines[14]);
    assertEquals("Not a valid number: one", lines[15]);
  }

  @Test
  public void testValidMovesInterspersedWithInvalidMoves() {
    StringReader input = new StringReader("1 1 0 0 1 2 1 1 2 1 one 1 1 one 1 3 3 1 q");
    TicTacToeController controller = new TicTacToeConsoleController(input, log);
    controller.playGame(model);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Not a valid move: 0, 0\n"
            + " X | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Not a valid move: 1, 1\n"
            + " X | O |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Not a valid number: one\n"
            + "Not a valid number: one\n"
            + " X | O | O\n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | O | O\n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Game is over! X wins.", log.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testInputEndsAbruptly() {
    StringReader input = new StringReader("1 1 1 2"); // No more input after valid moves, no quit command
    Appendable failingLog = new FailingAppendable();
    TicTacToeController controller = new TicTacToeConsoleController(input, failingLog);
    controller.playGame(model);
  }

  @Test
  public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(13, lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    // note the entire sequence of user inputs for the entire game is in this one string:
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(60, lines.length);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

}
