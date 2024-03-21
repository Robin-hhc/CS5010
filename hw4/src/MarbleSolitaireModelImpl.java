import java.security.InvalidKeyException;
import java.util.ArrayList;
import static java.lang.Math.abs;

/**
 * A class that represents a Marble Solitaire game object.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private Integer[][] board;
  private final int size;
  private int score;
  private static final int INVALID = 2;
  private static final int MARBLE = 1;
  private static final int EMPTY = 0;

  /**
   * Default constructor for no input. Initialize the game board as arm thickness 3 with the empty
   * slot at the center).
   */
  public MarbleSolitaireModelImpl() {
    this(3, 3, 3);
  }

  /**
   * Constructor that initialize the game board so that the arm thickness is 3 and the empty slot
   * is at the position (emptyRow, emptyColumn). If this specified position is invalid, it should
   * throw an IllegalArgumentException.
   * @param emptyRow given row number of the empty slot
   * @param emptyColumn given column number of the empty slot
   * @throws IllegalArgumentException
   */
  public MarbleSolitaireModelImpl(int emptyRow, int emptyColumn) throws IllegalArgumentException {
    this(3, emptyRow, emptyColumn);
  }

  /**
   * Constructor that initialize a game board with the empty slot at the center. Throw an
   * IllegalArgumentException if the arm thickness is not a positive odd number.
   * @param armNum the given arm thickness
   * @throws IllegalArgumentException
   */
  public MarbleSolitaireModelImpl(int armNum) throws IllegalArgumentException {
    this(armNum, (armNum * 3 - 2) / 2, (armNum * 3 - 2) / 2);
  }

  /**
   * Fourth constructor that take the arm thickness, row and column. It should initialize a game
   * board with the given empty slot and given arm thickness. It should throw an
   * IllegalArgumentException if the arm thickness is not a positive odd number, or the empty cell position is invalid.
   * @param emptyRow given row number of the empty slot
   * @param emptyColumn given column number of the empty slot
   * @param armNum the given arm thickness
   * @throws IllegalArgumentException
   */
  public MarbleSolitaireModelImpl(int armNum, int emptyRow, int emptyColumn) throws IllegalArgumentException {
    if (armNum <= 0 || armNum % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness not valid.");
    }
    this.size = 3*armNum-2;
    this.score = 4*armNum*armNum-4;
    this.board = new Integer[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if ((i < armNum-1 || size-i <= armNum-1) && (j < armNum-1 || size-j <= armNum-1)) {
          this.board[i][j] = INVALID;
        } else {
          this.board[i][j] = MARBLE;
        }
      }
    }
    if (emptyRow < 0 || emptyRow >= size || emptyColumn < 0 || emptyColumn >= size || this.board[emptyRow][emptyColumn] != MARBLE) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d, %d)", emptyRow, emptyColumn));
    }
    this.board[emptyRow][emptyColumn] = EMPTY;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move");
    }
    board[(toRow+fromRow)/2][(toCol+fromCol)/2] = EMPTY;
    board[toRow][toCol] = MARBLE;
    board[fromRow][fromCol] = EMPTY;
    score--;
  }

  /**
   * Helper function to check if a move is valid or not. Check for if two points in the board, if
   * form point have a marble, to point is empty, the two points have is only 1 step away from
   * each other vertically or horizontally and there is a marble in between them.
   * @param fromRow the row number of the position to be moved from
   * (starts at 0)
   * @param fromCol the column number of the position to be moved from
   * (starts at 0)
   * @param toRow the row number of the position to be moved to
   * (starts at 0)
   * @param toCol the column number of the position to be moved to
   * (starts at 0)
   * @return true if the move is valid. False otherwise.
   */
  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    if ((fromRow < 0 || fromRow >= size || fromCol < 0 || fromCol >= size) ||
            (toRow < 0 || toRow >= size || toCol < 0 || toCol >= size) ||
            this.board[fromRow][fromCol] != MARBLE || this.board[toRow][toCol] != EMPTY ||
            !((toRow == fromRow && abs(toCol-fromCol) == 2) || (toCol == fromCol && abs(toRow-fromRow) == 2))) {
      return false;
    }
    return this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == MARBLE;
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        // For each marble, check if a move is possible
        if (board[row][col] == 1) {
          // Check all four directions for a valid move
          if (isValidMove(row, col, row + 2, col) ||
                  isValidMove(row, col, row - 2, col) ||
                  isValidMove(row, col, row, col + 2) ||
                  isValidMove(row, col, row, col - 2)) {
            return false; // Found at least one valid move, so game is not over
          }
        }
      }
    }
    return true;
  }

  @Override
  public String getGameState() {
    StringBuilder msg = new StringBuilder(2*size*size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (board[i][j] == MARBLE) {
          msg.append("O ");
        } else if (board[i][j] == EMPTY) {
          msg.append("_ ");
        } else {
          msg.append("  ");
        }
      }
      msg.deleteCharAt(msg.length()-1);
      msg.append("\n");
    }
    msg.deleteCharAt(msg.length()-1);
    return msg.toString();
  }

  @Override
  public int getScore() {
    return score;
  }
}