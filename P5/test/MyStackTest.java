import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the MyStack class.
 */
public class MyStackTest {
  private MyStack<Double> test;

  @Before
  public void setUp() {
    this.test = new MyStack<Double>();
  }

  @Test
  public void pushPopTest() {
    this.test.push(1.0);
    this.test.push(2.0);
    this.test.push(3.0);
    assertEquals(3.0, this.test.pop(), 0);
    assertEquals(2.0, this.test.pop(), 0);
    assertEquals(1.0, this.test.pop(), 0);
  }

  @Test
  public void isEmptyTest() {
    assertTrue(this.test.isEmpty());
    this.test.push(1.0);
    assertFalse(this.test.isEmpty());
  }

  @Test
  public void peekTest() {
    this.test.push(1.0);
    assertEquals(1.0 , this.test.peek(), 0);
  }
}
