import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Person class.
 */
public class SimpleMoneyTest {

  SimpleMoney addOn;
  @Before
  public void setUp() {
    addOn = new SimpleMoney(100, 90);
  }

  @Test
  public void testConstructor() {
    SimpleMoney t = new SimpleMoney(100, 99);
    assertEquals(t.getDecimalValue(), 100.99, 0);
  }

  @Test
  public void testConstructorError() {
    String errorMessage = "";
    try {
      SimpleMoney t = new SimpleMoney(-100, 99);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid dollars", errorMessage);
    try {
      SimpleMoney t = new SimpleMoney(100, -99);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid cents", errorMessage);
    try {
      SimpleMoney t = new SimpleMoney(100, 101);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid cents", errorMessage);
  }

  @Test
  public void testAddMoney() {
    SimpleMoney test = new SimpleMoney(1, 1);
    assertEquals(101.91, test.add(addOn).getDecimalValue(), 0);
    test = new SimpleMoney(1, 11);
    assertEquals(102.01, test.add(addOn).getDecimalValue(), 0);
  }

  @Test
  public void testAdd() {
    SimpleMoney test = new SimpleMoney(1, 1);
    assertEquals(101.91, test.add(100, 90).getDecimalValue(), 0);
    test = new SimpleMoney(1, 11);
    assertEquals(102.01, test.add(100, 90).getDecimalValue(), 0);
  }

  @Test
  public void testAddError() {
    String errorMessage = "";
    try {
      SimpleMoney t = new SimpleMoney(-100, 99);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid dollars", errorMessage);
    try {
      SimpleMoney t = new SimpleMoney(100, -99);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid cents", errorMessage);
    try {
      SimpleMoney t = new SimpleMoney(100, 101);
    } catch (Exception e) {
      errorMessage = e.getMessage();
    }
    assertEquals("Invalid cents", errorMessage);
  }

  @Test
  public void testGetDecimalValue() {
    assertEquals(100.90, addOn.getDecimalValue(), 0);
  }

  @Test
  public void testToString() {
    assertEquals("100.90", addOn.toString());
  }
}
