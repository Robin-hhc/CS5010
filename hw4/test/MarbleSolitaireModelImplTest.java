import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarbleSolitaireModelImplTest {
  private MarbleSolitaireModel game;

  @Before
  public void setUp() {
    game = new MarbleSolitaireModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegativeArmThickness() {
    new MarbleSolitaireModelImpl(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEvenArmThickness() {
    new MarbleSolitaireModelImpl(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPositionConstructor() {
    new MarbleSolitaireModelImpl(3, 6, 6); // Invalid because it's an outer position
  }

  @Test
  public void testValidCustomEmptyConstructor() {
    MarbleSolitaireModel customGame = new MarbleSolitaireModelImpl(3, 4, 4);
    assertEquals("    O O O    \n" +
                          "    O O O    \n" +
                          "O O O O O O O\n" +
                          "O O O O O O O\n" +
                          "O O O O _ O O\n" +
                          "    O O O    \n" +
                          "    O O O    ", customGame.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOntoMarble() {
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    game.move(3, 0, 3, 2); // Invalid move, trying to move onto another marble
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromEmptySlot() {
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    game.move(3, 3, 3, 5); // Invalid move, trying to move from an empty slot
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveTooFar() {
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    game.move(1, 2, 4, 2); // Invalid move, too far
  }

  @Test
  public void testGameNotOverAfterOneMove() {
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    game.move(1, 3, 3, 3);
    assertFalse(game.isGameOver());
  }

  @Test
  public void testGameIsOver() {
    // Initialize a custom game state that's close to being over
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    // Perform a series of moves to reach a game over state
    game.move(1, 3, 3, 3);
    game.move(4, 3, 2, 3);
    game.move(3, 1, 3, 3);
    game.move(3, 4, 3, 2);
    game.move(3, 6, 3, 4);
    game.move(6, 3, 4, 3);
    assertTrue(game.isGameOver());
    assertEquals(26, game.getScore());
  }

  @Test
  public void testScoreAfterSeveralMoves() {
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    game.move(1, 3, 3, 3);
    game.move(4, 3, 2, 3);
    game.move(3, 1, 3, 3);
    assertEquals(29, game.getScore());
  }
}
