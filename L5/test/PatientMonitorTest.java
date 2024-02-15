import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the PatientMonitor class.
 */
public class PatientMonitorTest {
  PatientMonitor test;

  @Before
  public void setUp() {
    this.test = new PatientMonitor();
    this.test.add(new SingleBloodPressureRecord("123", 10, 20));
  }

  @Test
  public void addRemoveTest() {
    assertEquals(1, test.getNumberOfRecords(), 0);
    BloodPressureRecord record = new SingleBloodPressureRecord("1234", 120, 80);
    test.add(record);
    assertEquals(2, test.getNumberOfRecords(), 0);
    test.remove(record);
    assertEquals(1, test.getNumberOfRecords(), 0);
    test.remove(record);
    assertEquals(0, test.getNumberOfRecords(), 0);
  }

  @Test
  public void emergencyTest() {
    assertFalse(test.emergency());
    test.add(new SingleBloodPressureRecord("123", 90, 121));
    assertTrue(test.emergency());
    // If we do not fix the count > 1 problem, this result one line above will return false.
    test.add(new SingleBloodPressureRecord("123", 90, 122));
    assertTrue(test.emergency());
  }
}
