/**
 * This is an abstract class for each piece in the chess game. This class implements most of the
 * method that a piece commonly have in the game.
 */
public abstract class ChessSample implements ChessPiece {
  private int row;
  private int col;
  private Color color;

  /**
   * Constructor of the class. Store the row, column and color for a piece.
   *
   * @param row the current row of this chess
   * @param col the current column of this chess
   * @param color the color of this chess
   */
  public ChessSample(int row, int col, Color color) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Illegal position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  @Override
  public int getRow() { return row;}

  @Override
  public int getColumn() { return col;}

  @Override
  public Color getColor() { return color;}

  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.getColor() != piece.getColor()) && this.canMove(
            piece.getRow(),
            piece.getColumn());
  }
}
