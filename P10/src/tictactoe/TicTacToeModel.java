package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private Player[][] board = new Player[3][3];
  private Player currentTurn = Player.X;
  private Player winner = null;
  private boolean gameOver = false;

  public TicTacToeModel() {
  }

  @Override
  public void move(int r, int c) {
    if (r < 0 || r >= 3 || c < 0 || c >= 3) {
      throw new IllegalArgumentException("Position is outside of the board");
    }
    if (board[r][c] != null) {
      throw new IllegalArgumentException("Space is already occupied");
    }
    if (gameOver) {
      throw new IllegalStateException("Game is already over");
    }

    board[r][c] = currentTurn;
    checkGameOver(r, c);
    currentTurn = (currentTurn == Player.X) ? Player.O : Player.X;
  }

  @Override
  public Player getTurn() {return currentTurn;}

  @Override
  public boolean isGameOver() {return gameOver;}

  @Override
  public Player getWinner() {return winner;}

  @Override
  public Player[][] getBoard() {return Arrays.stream(board).map(Player[]::clone).toArray(Player[][]::new);}

  @Override
  public Player getMarkAt(int r, int c) throws IllegalArgumentException {
    if (r < 0 || r >= 3 || c < 0 || c >= 3) {
      throw new IllegalArgumentException("Position is outside of the board");
    }
    return board[r][c];
  }

  /**
   * Helper function to check if the game is over after the last move. Assuming the move given is
   * always valid.
   * @param lastRow Row number of the last move
   * @param lastCol column number of the last move
   */
  private void checkGameOver(int lastRow, int lastCol) {
    // Check rows, columns, and diagonals for a win
    if (currentTurn == board[lastRow][0] && currentTurn == board[lastRow][1] && currentTurn == board[lastRow][2] ||
            currentTurn == board[0][lastCol] && currentTurn == board[1][lastCol] && currentTurn == board[2][lastCol] ||
            currentTurn == board[0][0] && currentTurn == board[1][1] && currentTurn == board[2][2] ||
            currentTurn == board[0][2] && currentTurn == board[1][1] && currentTurn == board[2][0]) {
      winner = currentTurn;
      gameOver = true;
    } else if (board[0][0] != null && board[0][1] != null && board[0][2] != null &&
            board[1][0] != null && board[1][1] != null && board[1][2] != null &&
            board[2][0] != null && board[2][1] != null && board[2][2] != null) {
      // Check for tie
      gameOver = true;
    }
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map( row -> " " + Arrays.stream(row).map(
            p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | "))).collect(
                    Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
    /**********
     List<String> rows = new ArrayList<>();
     for(Player[] row : getBoard()) {
     List<String> rowStrings = new ArrayList<>();
     for(Player p : row) {
     if(p == null) {
     rowStrings.add(" ");
     } else {
     rowStrings.add(p.toString());
     }
     }
     rows.add(" " + String.join(" | ", rowStrings));
     }
     return String.join("\n-----------\n", rows);
     ************/
  }
}
