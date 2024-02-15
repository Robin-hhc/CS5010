import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the SingleBloodPressureRecord class.
 */
public class SingleBloodPressureRecordTest {
  SingleBloodPressureRecord test;

  @Before
  public void setUp() {
    this.test = new SingleBloodPressureRecord("123", 10, 20);
  }

  @Test
  public void ConstructorTestError() {
    String errorMessage1 = "";
    try {
      new SingleBloodPressureRecord("", 10, 20);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      new SingleBloodPressureRecord("123", -10, 20);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    String errorMessage3 = "";
    try {
      new SingleBloodPressureRecord("123", 10, -20);
    } catch (Exception e) {
      errorMessage3 = e.getMessage();
    }
    String errorMessage4 = "";
    try {
      new SingleBloodPressureRecord("123", 21, 20);
    } catch (Exception e) {
      errorMessage4 = e.getMessage();
    }
    assertEquals("Invalid ID", errorMessage1);
    assertEquals("Invalid Blood Pressure", errorMessage2);
    assertEquals("Invalid Blood Pressure", errorMessage3);
    assertEquals("Invalid Blood Pressure", errorMessage4);
  }

  @Test
  public void getIDTest() {
    assertEquals("123", this.test.getID());
  }

  @Test
  public void getSystolicReadingTest() {
    assertEquals(10, this.test.getSystolicReading(), 0);
  }

  @Test
  public void getDiastolicReadingTest() {
    assertEquals(20, this.test.getDiastolicReading(), 0);
  }

  @Test
  public void updateSystolicReadingTestError() {
    String errorMessage1 = "";
    try {
      this.test.updateSystolicReading(21);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      this.test.updateSystolicReading(-1);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    assertEquals("Invalid Blood Pressure", errorMessage2);
    assertEquals("Invalid Blood Pressure", errorMessage1);
  }

  @Test
  public void updateDiastolicReadingTestError() {
    String errorMessage1 = "";
    try {
      this.test.updateDiastolicReading(9);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      this.test.updateDiastolicReading(-1);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    assertEquals("Invalid Blood Pressure", errorMessage2);
    assertEquals("Invalid Blood Pressure", errorMessage1);
  }

  @Test
  public void updateSystolicReadingTest() {
    this.test.updateSystolicReading(15);
    assertEquals(15, this.test.getSystolicReading(), 0);
  }

  @Test
  public void updateDiastolicReadingTest() {
    this.test.updateDiastolicReading(15);
    assertEquals(15, this.test.getDiastolicReading(), 0);
  }

  @Test
  public void equalityTestPart1() {
    SingleBloodPressureRecord test2 = new SingleBloodPressureRecord("123", 10.99, 19.01);
    assertTrue(this.test.equality(test2));
    test2 = new SingleBloodPressureRecord("122", 10.99, 19.01);
    assertFalse(this.test.equality(test2));
  }

  @Test
  public void equalityTestPart2() {
    SingleBloodPressureRecord test2 = new SingleBloodPressureRecord("123", 10.99, 20.99);
    assertTrue(this.test.equality(test2));
    test2 = new SingleBloodPressureRecord("123", 10, 19.99);
    assertFalse(this.test.equality(test2));
    test2 = new SingleBloodPressureRecord("123", 9.99, 20);
    assertFalse(this.test.equality(test2));
    test2 = new SingleBloodPressureRecord("122", 10, 20);
    assertFalse(this.test.equality(test2));
  }
}
