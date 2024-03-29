/**
 * This is a class of a piece called Knight in the chess game. This class extends the common methods
 * from ChessSample and implements the unique move rules for the Rock.
 */
public class Knight extends ChessSample{

  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Can this chess piece be moved from its current location to the location
   * (row,col). A Knight can move in a L shape.
   *
   * @param row the row to which this chess piece can be moved
   * @param col the col to which this chess piece can be moved
   * @return true if it can be moved to this position, false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    if ((row < 0) || (col < 0) || (row >= 8) || (col >= 8)) {
      return false;
    }
    return ((this.getRow()+2 == row) && (this.getColumn()+1 == col)) ||
            ((this.getRow()+2 == row) && (this.getColumn()-1 == col)) ||
            ((this.getRow()-2 == row) && (this.getColumn()+1 == col)) ||
            ((this.getRow()-2 == row) && (this.getColumn()-1 == col)) ||
            ((this.getRow()+1 == row) && (this.getColumn()+2 == col)) ||
            ((this.getRow()+1 == row) && (this.getColumn()-2 == col)) ||
            ((this.getRow()-1 == row) && (this.getColumn()+2 == col)) ||
            ((this.getRow()-1 == row) && (this.getColumn()-2 == col));
  }

}
