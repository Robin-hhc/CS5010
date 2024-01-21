import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the SimpleBoxSet class.
 */
public class SimpleBoxSetTest {
  private SimpleBoxSet testSubtract;
  private SimpleBoxSet testAdd;
  private SimpleBoxSet testGetBoxes;
  private SimpleBoxSet testSize;

  @Before
  public void setUp() {
    testSubtract = new SimpleBoxSet();
    testSubtract.add(0, 0, 5, 10);
    testSubtract.add(5, 10, 2, 3);
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(5, 10, 2, 3);
    testGetBoxes = new SimpleBoxSet();
    testGetBoxes.add(0, 0, 5, 10);
    testGetBoxes.add(5, 10, 2, 3);
    testSize = new SimpleBoxSet();
    testSize.add(0, 0, 5, 10);
    testSize.add(5, 10, 2, 3);
  }

  @Test
  public void subtractTest() {
    //    Test equal

    return;
  }

  @Test
  public void addTest() {
    int[][] except;
    //    Test exist rectangle
    System.out.println("Test exist rectangle");
    testAdd.add(0, 0, 5, 10);
    except = new int[][] {{5, 10, 2, 3}, {0, 0, 5, 10}};
    assertEquals(except, testAdd.getBoxes());

//    testAdd.add(-1, -1, 8, 14);
//    except = new int[][] {{-1, -1, 8, 14}};
//    assertEquals(except, testAdd.getBoxes());
  }

  @Test
  public void subtractTestError() {
    String errorMessage1 = "";
    try {
      testSubtract.subtract(0, 0, 0, 1);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      testSubtract.subtract(0, 0, 1, 0);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    assertEquals("Width invalid", errorMessage1);
    assertEquals("Height invalid", errorMessage2);
  }

  @Test
  public void addTestError() {
    String errorMessage1 = "";
    try {
      testAdd.add(0, 0, 0, 1);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      testAdd.add(0, 0, 1, 0);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    assertEquals("Width invalid", errorMessage1);
    assertEquals("Height invalid", errorMessage2);
  }

  @Test
  public void getBoxesTest() {
    int[][] except = new int[][] {{0, 0, 5, 10}, {5, 10, 2, 3}};
    assertEquals(except, testGetBoxes.getBoxes());
  }

  @Test
  public void size() {
    assertEquals(2, testSize.size(), 0);
  }
}
