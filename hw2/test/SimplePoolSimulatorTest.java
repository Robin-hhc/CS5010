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
  public void getBallRadiusTest() {
    assertEquals(Double.NEGATIVE_INFINITY, test.getBallRadius(), 0);
    test.start(200, 20, 20, 23, 1.5, 1.5);
    assertEquals(20, test.getBallRadius(), 0);
  }

  @Test
  public void getBallPositionXTestSimple() {
    test = new SimplePoolSimulator(400, 400, "simple");
    assertEquals(Double.NEGATIVE_INFINITY, test.getBallPositionX(), 0);
    test.start(200, 20, 20, 23, 1.5, 1.5);
    assertEquals(200, test.getBallPositionX(), 0);
    test.advance();
    assertEquals(380, test.getBallPositionX(), 0.001);
    test.advance();
    assertEquals(200, test.getBallPositionX(), 0.001);
    test.advance();
    assertEquals(20, test.getBallPositionX(), 0.001);
    test.advance();
    assertEquals(200, test.getBallPositionX(), 0.001);
    test.advance();
    assertEquals(380, test.getBallPositionX(), 0.001);
  }

  @Test
  public void getBallPositionYTestSimple() {
    test = new SimplePoolSimulator(400, 400, "simple");
    assertEquals(Double.NEGATIVE_INFINITY, test.getBallPositionX(), 0);
    test.start(200, 20, 20, 23, 1.5, 1.5);
    assertEquals(20, test.getBallPositionY(), 0);
    test.advance();
    assertEquals(200, test.getBallPositionY(), 0.001);
    test.advance();
    assertEquals(380, test.getBallPositionY(), 0.001);
    test.advance();
    assertEquals(200, test.getBallPositionY(), 0.001);
    test.advance();
    assertEquals(20, test.getBallPositionY(), 0.001);
    test.advance();
    assertEquals(200, test.getBallPositionY(), 0.001);
  }

  @Test
  public void getBallVelocityXTestSimple() {
    test = new SimplePoolSimulator(400, 400, "simple");
    assertEquals(0, test.getBallVelocityX(), 0);
    test.start(200, 20, 20, 23, 1.5, 1.5);
    double dx = 1.5 / Math.sqrt(1.5*1.5 + 1.5*1.5);
    assertEquals(23*dx, test.getBallVelocityX(), 0);
    test.advance();
    assertEquals(18*-dx, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(13*-dx, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(8*dx, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(3*dx, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(0, test.getBallVelocityX(), 0.001);
  }

  @Test
  public void getBallVelocityYTestSimple() {
    test = new SimplePoolSimulator(400, 400, "simple");
    assertEquals(0, test.getBallVelocityY(), 0);
    test.start(200, 20, 20, 23, 1.5, 1.5);
    double dy = 1.5 / Math.sqrt(1.5*1.5 + 1.5*1.5);
    assertEquals(23*dy, test.getBallVelocityY(), 0);
    test.advance();
    assertEquals(18*dy, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(13*-dy, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(8*-dy, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(3*dy, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(0, test.getBallVelocityY(), 0.001);
  }

  @Test
  public void getStatusTestSimple() {
    test = new SimplePoolSimulator(400,400,"simple");
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

  @Test
  public void getBallPositionXTestFriction() {
    test = new SimplePoolSimulator(400,400,"friction");
    assertEquals(Double.NEGATIVE_INFINITY, test.getBallPositionX(), 0);
    test.start(200,20,20,60,1.5,1.5);
    assertEquals(200, test.getBallPositionX(), 0);
    test.advance();
    assertEquals(380, test.getBallPositionX(), 0.001);
    test.advance();
    assertEquals(200, test.getBallPositionX(), 0.001);
    test.advance();
    assertEquals(20, test.getBallPositionX(), 0.001);
    test.advance();
    assertEquals(200, test.getBallPositionX(), 0.001);
    test.advance();
    assertEquals(261.9172, test.getBallPositionX(), 0.001);
  }

  @Test
  public void getBallPositionYTestFriction() {
    test = new SimplePoolSimulator(400,400,"friction");
    assertEquals(Double.NEGATIVE_INFINITY, test.getBallPositionX(), 0);
    test.start(200,20,20,60,1.5,1.5);
    assertEquals(20, test.getBallPositionY(), 0);
    test.advance();
    assertEquals(200, test.getBallPositionY(), 0.001);
    test.advance();
    assertEquals(380, test.getBallPositionY(), 0.001);
    test.advance();
    assertEquals(200, test.getBallPositionY(), 0.001);
    test.advance();
    assertEquals(20, test.getBallPositionY(), 0.001);
    test.advance();
    assertEquals(81.9172, test.getBallPositionY(), 0.001);
  }

  @Test
  public void getBallVelocityXTestFriction() {
    test = new SimplePoolSimulator(400, 400, "friction");
    assertEquals(0, test.getBallVelocityX(), 0);
    test.start(200, 20, 20, 60, 1.5, 1.5);
    assertEquals(42.4264, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(-35.8380, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(-28.6303, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(20.3386, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(9.2682, test.getBallVelocityX(), 0.001);
    test.advance();
    assertEquals(0, test.getBallVelocityX(), 0.001);
  }

  @Test
  public void getBallVelocityYTestFriction() {
    test = new SimplePoolSimulator(400, 400, "friction");
    assertEquals(0, test.getBallVelocityY(), 0);
    test.start(200, 20, 20, 60, 1.5, 1.5);
    assertEquals(42.4264, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(35.8380, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(-28.6303, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(-20.3386, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(9.2682, test.getBallVelocityY(), 0.001);
    test.advance();
    assertEquals(0, test.getBallVelocityY(), 0.001);
  }

  @Test
  public void getStatusTestFriction() {
    test = new SimplePoolSimulator(400,400,"friction");
    assertEquals("Status: Ball not set up", test.getStatus());
    test.start(200,20,20,60,1.5,1.5);
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
