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
  public void emergencyTest() {
    this.test.add(new SingleBloodPressureRecord("123", 90, 121));
    assertTrue(this.test.emergency());
  }
}
