package tictactoe;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class TicTacToeConsoleController implements TicTacToeController {
  private final Appendable out;
  private final Scanner scan;

  public TicTacToeConsoleController(Readable in, Appendable out) {
    this.out = out;
    this.scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    boolean isLastMoveValid = true;
    try {
      while (!m.isGameOver()) {
        if (isLastMoveValid) {
          out.append(m.toString()).append("\n");
          out.append("Enter a move for ").append(m.getTurn().toString()).append(":\n");
        }
        String row = null, col = null;
        try {
          row = scan.next();
          if (quitCheck(row, m)) {return;}
          col = scan.next();
          if (quitCheck(col, m)) {return;}
          m.move(Integer.parseInt(row)-1, Integer.parseInt(col)-1);
          isLastMoveValid = true;
        } catch (NumberFormatException e) {
          isLastMoveValid = false;
          out.append("Not a valid number: ").append(e.getMessage().split("\"")[1]).append("\n");
        } catch (IllegalArgumentException e) {
          isLastMoveValid = false;
          out.append("Not a valid move: ").append(row).append(", ").append(col).append("\n");
        } catch (IllegalStateException e) {
          out.append(e.getMessage()).append("\n");
        } catch (NoSuchElementException e) {
          out.append("Lack of arguments\n");
          return;
        }
      }
      out.append(m.toString()).append("\n");
      if (m.getWinner() != null) {
        out.append("Game is over! ").append(m.getWinner().toString()).append(" wins.");
      } else {
        out.append("Game is over! Tie game.");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }

  private boolean quitCheck(String s, TicTacToe m) throws IOException {
    if (Objects.equals(s, "q") || Objects.equals(s, "Q")) {
      out.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
      return true;
    }
    return false;
  }
}
