import java.util.ArrayList;

/**
 * A class that represents a Marble Solitaire game object.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  ArrayList<Integer> board;
  public MarbleSolitaireModelImpl() {
    this.board = new ArrayList<>();
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