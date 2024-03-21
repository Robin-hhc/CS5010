import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarbleSolitaireModelImplTest {
  private MarbleSolitaireModel game;

  @Before
  public void setUp() {
    game = new MarbleSolitaireModelImpl();
  }

  @Test
  public void testEmptyConstructor() {
    MarbleSolitaireModel customGame = new MarbleSolitaireModelImpl();
    assertEquals("    O O O    \n" +
                          "    O O O    \n" +
                          "O O O O O O O\n" +
                          "O O O _ O O O\n" +
                          "O O O O O O O\n" +
                          "    O O O    \n" +
                          "    O O O    ", customGame.getGameState());
  }

  @Test
  public void testErrorConstructorPosition() {
    String errorMessage = "";
    try {
      new MarbleSolitaireModelImpl(-1, 0);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (-1, 0)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(0, -1);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (0, -1)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(7, 0);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (7, 0)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(0, 7);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (0, 7)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(1, 1);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (1, 1)", errorMessage);
  }

  @Test
  public void testConstructorPosition() {
    MarbleSolitaireModel customGame = new MarbleSolitaireModelImpl(4, 4);
    assertEquals("    O O O    \n" +
                          "    O O O    \n" +
                          "O O O O O O O\n" +
                          "O O O O O O O\n" +
                          "O O O O _ O O\n" +
                          "    O O O    \n" +
                          "    O O O    ", customGame.getGameState());
  }

  @Test
  public void testErrorConstructorArm() {
    String errorMessage1 = "";
    try {
      new MarbleSolitaireModelImpl(-3);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      new MarbleSolitaireModelImpl(4);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    assertEquals("Arm thickness not valid.", errorMessage1);
    assertEquals("Arm thickness not valid.", errorMessage2);
  }

  @Test
  public void testConstructorArm() {
    MarbleSolitaireModel customGame = new MarbleSolitaireModelImpl(5);
    assertEquals("        O O O O O        \n" +
                          "        O O O O O        \n" +
                          "        O O O O O        \n" +
                          "        O O O O O        \n" +
                          "O O O O O O O O O O O O O\n" +
                          "O O O O O O O O O O O O O\n" +
                          "O O O O O O _ O O O O O O\n" +
                          "O O O O O O O O O O O O O\n" +
                          "O O O O O O O O O O O O O\n" +
                          "        O O O O O        \n" +
                          "        O O O O O        \n" +
                          "        O O O O O        \n" +
                          "        O O O O O        ", customGame.getGameState());
  }

  @Test
  public void testErrorConstructorFull() {
    String errorMessage = "";
    try {
      new MarbleSolitaireModelImpl(3, -1, 0);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (-1, 0)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(3, 0, -1);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (0, -1)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(3, 7, 0);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (7, 0)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(3, 0, 7);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (0, 7)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(3, 1, 1);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid empty cell position (1, 1)", errorMessage);
    try {
      new MarbleSolitaireModelImpl(-3);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Arm thickness not valid.", errorMessage);
    try {
      new MarbleSolitaireModelImpl(4);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Arm thickness not valid.", errorMessage);
  }

  @Test
  public void testConstructorFull() {
    MarbleSolitaireModel customGame = new MarbleSolitaireModelImpl(5, 1, 7);
    assertEquals("        O O O O O        \n" +
                          "        O O O _ O        \n" +
                          "        O O O O O        \n" +
                          "        O O O O O        \n" +
                          "O O O O O O O O O O O O O\n" +
                          "O O O O O O O O O O O O O\n" +
                          "O O O O O O O O O O O O O\n" +
                          "O O O O O O O O O O O O O\n" +
                          "O O O O O O O O O O O O O\n" +
                          "        O O O O O        \n" +
                          "        O O O O O        \n" +
                          "        O O O O O        \n" +
                          "        O O O O O        ", customGame.getGameState());
  }

  @Test
  public void testErrorMove() {
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    String errorMessage = "";
    try {
      game.move(-1, 3, 1, 0); // fromRow < 0
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid move", errorMessage);
    try {
      game.move(3, -1, 1, 0); // fromCol < 0
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid move", errorMessage);
    try {
      game.move(1, 3, -1, 3); // toRow < 0
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid move", errorMessage);
    try {
      game.move(1, 3, 1, -1); // toCol < 0
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid move", errorMessage);
    try {
      game.move(3, 0, 3, 2); // Marble to marble
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid move", errorMessage);
    try {
      game.move(3, 3, 3, 5); // Empty to marble
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid move", errorMessage);
    try {
      game.move(1, 2, 4, 2); // Move too far
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid move", errorMessage);
    game.move(1, 3, 3, 3);
    try {
      game.move(3, 3, 1, 3); // No marble in between
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid move", errorMessage);
  }

  @Test
  public void testMove() {
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    game.move(1, 3, 3, 3);
    assertEquals("    O O O    \n" +
                          "    O _ O    \n" +
                          "O O O _ O O O\n" +
                          "O O O O O O O\n" +
                          "O O O O O O O\n" +
                          "    O O O    \n" +
                          "    O O O    ", game.getGameState());
    game.move(4, 3, 2, 3);
    assertEquals("    O O O    \n" +
                          "    O _ O    \n" +
                          "O O O O O O O\n" +
                          "O O O _ O O O\n" +
                          "O O O _ O O O\n" +
                          "    O O O    \n" +
                          "    O O O    ", game.getGameState());
  }

  @Test
  public void testGameIsOver() {
    // Initialize a custom game state that's close to being over
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    // Perform a series of moves to reach a game over state
    game.move(1, 3, 3, 3);
    assertFalse(game.isGameOver());
    game.move(4, 3, 2, 3);
    assertFalse(game.isGameOver());
    game.move(3, 1, 3, 3);
    assertFalse(game.isGameOver());
    game.move(3, 4, 3, 2);
    assertFalse(game.isGameOver());
    game.move(3, 6, 3, 4);
    assertFalse(game.isGameOver());
    game.move(6, 3, 4, 3);
    assertTrue(game.isGameOver());
    assertEquals(26, game.getScore());
  }

  @Test
  public void testScore() {
    MarbleSolitaireModel game = new MarbleSolitaireModelImpl();
    game.move(1, 3, 3, 3);
    assertEquals(31, game.getScore());
    game.move(4, 3, 2, 3);
    assertEquals(30, game.getScore());
    game.move(3, 1, 3, 3);
    assertEquals(29, game.getScore());
    game.move(3, 4, 3, 2);
    assertEquals(28, game.getScore());
    game.move(3, 6, 3, 4);
    assertEquals(27, game.getScore());
    game.move(6, 3, 4, 3);
    assertEquals(26, game.getScore());
  }
}
