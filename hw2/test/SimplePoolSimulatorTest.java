import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import sim.SimplePoolSimulator;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the SimpleBoxSet class.
 * Since the only way to test advance, start and constructor is test the attribute with get methods,
 * their tests are included in the get methods' tests. The standalone tests for these three methods
 * only test the exceptions for them.
 */
public class SimplePoolSimulatorTest {

  SimplePoolSimulator test;
  @Before
  public void setUp() {
    test = new SimplePoolSimulator(400,400,"simple");
  }

  @Test
  public void constructorTestError() {
    String errorMessage1 = "";
    try {
      new SimplePoolSimulator(-1, 100, "simple");
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      new SimplePoolSimulator(100, -1, "simple");
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    String errorMessage3 = "";
    try {
      new SimplePoolSimulator(100, 100, "sss");
    } catch (Exception e) {
      errorMessage3 = e.getMessage();
    }
    assertEquals("Width Invalid", errorMessage1);
    assertEquals("Height Invalid", errorMessage2);
    assertEquals("Type Invalid", errorMessage3);
  }

  @Test
  public void getTableWidthTest() {
    assertEquals(400, test.getTableWidth(), 0);
  }

  @Test
  public void getTableHeightTest() {
    assertEquals(400, test.getTableHeight(), 0);
  }

  @Test
  public void getStatusTestSimple() {
    assertEquals("Status: Ball not set up", test.getStatus());
    test.start(200,20,20,23,1.5,1.5);
    assertEquals("Status: Simulation started", test.getStatus());
    test.advance();
    assertEquals("Status: Ball hit right edge", test.getStatus());
    test.advance();
    assertEquals("Status: Ball hit top edge", test.getStatus());
    test.advance();
    assertEquals("Status: Ball hit left edge", test.getStatus());
    test.advance();
    assertEquals("Status: Ball hit bottom edge", test.getStatus());
    test.advance();
    assertEquals("Status: Ball is stationary", test.getStatus());
  }

}
