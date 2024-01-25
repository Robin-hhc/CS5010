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
  public void addSubtractTest() {
    int[][] except;
    // Test exist rectangle
    testAdd.add(0, 0, 5, 10);
    except = new int[][] {{5, 10, 2, 3}, {0, 0, 5, 10}};
    assertEquals(except, testAdd.getBoxes());

    // Test all four edges overlap
    testAdd.add(1, 1, 3, 8);
    except = new int[][] {{5, 10, 2, 3}, {0, 0, 1, 10}, {4, 0, 1, 10}, {1, 0, 3, 1}, {1, 9, 3, 1}, {1, 1, 3, 8}};
    assertEquals(except, testAdd.getBoxes());

    // Test only upper width edge non-overlaps
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(1, 1, 3, 15);
    except = new int[][] {{0, 0, 1, 10}, {4, 0, 1, 10}, {1, 0, 3, 1}, {1, 1, 3, 15}};
    assertEquals(except, testAdd.getBoxes());

    // Test only lower width edge non-overlaps
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(1, -1, 3, 5);
    except = new int[][] {{0, 0, 1, 10}, {4, 0, 1, 10}, {1, 4, 3, 6}, {1, -1, 3, 5}};
    assertEquals(except, testAdd.getBoxes());

    // Test only right height edge non-overlaps
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(1, 1, 6, 8);
    except = new int[][] {{0, 0, 5, 1}, {0, 9, 5, 1}, {0, 1, 1, 8}, {1, 1, 6, 8}};
    assertEquals(except, testAdd.getBoxes());

    // Test only left height edge non-overlaps
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(-1, 1, 4, 8);
    except = new int[][] {{0, 0, 5, 1}, {0, 9, 5, 1}, {3, 1, 2, 8}, {-1, 1, 4, 8}};
    assertEquals(except, testAdd.getBoxes());

    // Test lower-left corner overlaps (Two edges overlap)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(1, 1, 5, 10);
    except = new int[][] {{0, 0, 1, 10}, {1, 0, 4, 1}, {1, 1, 5, 10}};
    assertEquals(except, testAdd.getBoxes());

    // Test upper-left corner overlaps (Two edges overlap)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(1, -1, 5, 10);
    except = new int[][] {{0, 0, 1, 10}, {1, 9, 4, 1}, {1, -1, 5, 10}};
    assertEquals(except, testAdd.getBoxes());

    // Test lower-right corner overlaps (Two edges overlap)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(-1, 1, 5, 10);
    except = new int[][] {{0, 0, 4, 1}, {4, 0, 1, 10}, {-1, 1, 5, 10}};
    assertEquals(except, testAdd.getBoxes());

    // Test upper-right corner overlaps (Two edges overlap)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(-1, -1, 5, 10);
    except = new int[][] {{0, 9, 4, 1}, {4, 0, 1, 10}, {-1, -1, 5, 10}};
    assertEquals(except, testAdd.getBoxes());

    // Test left edge and right edge overlaps (Two edges overlap)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(1, -1, 3, 12);
    except = new int[][] {{0, 0, 1, 10}, {4, 0, 1, 10}, {1, -1, 3, 12}};
    assertEquals(except, testAdd.getBoxes());

    // Test upper edge and Lower edge overlaps (Two edges overlap)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(-1, 1, 7, 8);
    except = new int[][] {{0, 0, 5, 1}, {0, 9, 5, 1}, {-1, 1, 7, 8}};
    assertEquals(except, testAdd.getBoxes());

    // Test left edge overlaps (One edge overlaps)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(1, -1, 5, 12);
    except = new int[][] {{0, 0, 1, 10}, {1, -1, 5, 12}};
    assertEquals(except, testAdd.getBoxes());

    // Test right edge overlaps (One edge overlaps)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(-1, -1, 5, 12);
    except = new int[][] {{4, 0, 1, 10}, {-1, -1, 5, 12}};
    assertEquals(except, testAdd.getBoxes());

    // Test lower edge overlaps (One edge overlaps)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(-1, 1, 7, 10);
    except = new int[][] {{0, 0, 5, 1}, {-1, 1, 7, 10}};
    assertEquals(except, testAdd.getBoxes());

    // Test upper edge overlaps (One edge overlaps)
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(-1, -1, 7, 10);
    except = new int[][] {{0, 9, 5, 1}, {-1, -1, 7, 10}};
    assertEquals(except, testAdd.getBoxes());

    // Test input rectangle have this rectangle inside
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(-1, -1, 7, 12);
    except = new int[][] {{-1, -1, 7, 12}};
    assertEquals(except, testAdd.getBoxes());

    // Test non-overlap
    testAdd = new SimpleBoxSet();
    testAdd.add(0, 0, 5, 10);
    testAdd.add(5, 10, 2, 3);
    except = new int[][] {{0, 0, 5, 10}, {5, 10, 2, 3}};
    assertEquals(except, testAdd.getBoxes());
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
