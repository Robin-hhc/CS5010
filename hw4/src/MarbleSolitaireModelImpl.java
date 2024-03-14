import java.security.InvalidKeyException;
import java.util.ArrayList;

/**
 * A class that represents a Marble Solitaire game object.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  Integer[][] board;

  /**
   * Default constructor for no input. Initialize the game board as arm thickness 3 with the empty
   * slot at the center).
   */
  public MarbleSolitaireModelImpl() {
    this.board = new Integer[][] {{2, 2, 1, 1, 1, 2, 2},
                                  {2, 2, 1, 1, 1, 2, 2},
                                  {1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 0, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1},
                                  {2, 2, 1, 1, 1, 2, 2},
                                  {2, 2, 1, 1, 1, 2, 2}};
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
    this.board = new Integer[][] {{2, 2, 1, 1, 1, 2, 2},
                                  {2, 2, 1, 1, 1, 2, 2},
                                  {1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1},
                                  {2, 2, 1, 1, 1, 2, 2},
                                  {2, 2, 1, 1, 1, 2, 2}};
    if (emptyRow < 0 || emptyRow >= 7 || emptyColumn < 0 || emptyColumn >= 7 || this.board[emptyRow][emptyColumn] != 1) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d, %d)", emptyRow, emptyColumn));
    }
    this.board[emptyRow][emptyColumn] = 0;
  }

  /**
   * Constructor that initialize a game board with the empty slot at the center. Throw an
   * IllegalArgumentException if the arm thickness is not a positive odd number.
   * @param armNum the given arm thickness
   * @throws IllegalArgumentException
   */
  public MarbleSolitaireModelImpl(int armNum) throws IllegalArgumentException {
    if (armNum <= 0 || armNum % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness can not be even.");
    }
    int len = 3*armNum-2;
    this.board = new Integer[len][len];
    for (int i = 1; i < len; i++) {
      for (int j = 1; j < len; j++) {
        if ((i < armNum-1 || len-i <= armNum-1) && (j < armNum-1 || len-j <= armNum-1)) {
          this.board[i][j] = 2;
        } else if (i == len/2 && j == len/2) {
          this.board[i][j] = 0;
        } else {
          this.board[i][j] = 1;
        }
      }
    }
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
      throw new IllegalArgumentException("Arm thickness can not be even.");
    }
    int len = 3*armNum-2;
    this.board = new Integer[len][len];
    for (int i = 1; i < len; i++) {
      for (int j = 1; j < len; j++) {
        if ((i < armNum-1 || len-i <= armNum-1) && (j < armNum-1 || len-j <= armNum-1)) {
          this.board[i][j] = 2;
        } else {
          this.board[i][j] = 1;
        }
      }
    }
    if (emptyRow < 0 || emptyRow >= len || emptyColumn < 0 || emptyColumn >= len || this.board[emptyRow][emptyColumn] != 1) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d, %d)", emptyRow, emptyColumn));
    }
    this.board[emptyRow][emptyColumn] = 0;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}