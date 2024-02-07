package Q31;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RectangleTest {
  Rectangle test;

  @Test
  public void ConstructorTest() {
    String errorMessage1 = "";
    try {
      new Rectangle(-1.0, 1.0, "white", false);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      new Rectangle(1.0, -1.0, "white", false);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    assertEquals("Invalid width and height", errorMessage1);
    assertEquals("Invalid width and height", errorMessage2);
  }

  @Test
  public void setHeightTest() {
    test = new Rectangle(1.0, 1.0, "white", false);
    String errorMessage1 = "";
    try {
      test.setHeight(-1.0);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    assertEquals("Invalid height", errorMessage1);
  }

  @Test
  public void setWidthTest() {
    test = new Rectangle(1.0, 1.0, "white", false);
    String errorMessage1 = "";
    try {
      test.setWidth(-1.0);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    assertEquals("Invalid width", errorMessage1);
  }
}