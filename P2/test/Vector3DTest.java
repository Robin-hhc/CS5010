import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Vector3D class.
 */
public class Vector3DTest {
    private Vector3D test;
    private Vector3D testError;

    @Before
    public void setUp() {
        test = new Vector3D(99.99, 55.55, 88.88);
        testError = new Vector3D(0, 0, 0);
    }

    @Test
    public void testGetX() {
        assertEquals(99.99, test.getX(), 0);
    }

    @Test
    public void testGetY() {
        assertEquals(55.55, test.getY(), 0);
    }

    @Test
    public void testGetZ() {
        assertEquals(88.88, test.getZ(), 0);
    }

    @Test
    public void testToString() {
        assertEquals("(99.990000, 55.550000, 88.880000)", test.toString());
    }

    @Test
    public void testGetMagnitude() {
        double ans = Math.sqrt(99.99*99.99 + 55.55*55.55 + 88.88*88.88);
        assertEquals(ans, test.getMagnitude(), 0);
    }

    @Test
    public void testNormalize() {
        double mag = Math.sqrt(99.99*99.99 + 55.55*55.55 + 88.88*88.88);
        Vector3D ans = new Vector3D(99.99/mag, 55.55/mag, 88.88/mag);
        Vector3D result = test.normalize();
        assertEquals(ans.getX(), result.getX(), 0);
        assertEquals(ans.getY(), result.getY(), 0);
        assertEquals(ans.getZ(), result.getZ(), 0);
    }

    @Test
    public void testNormalizeError() {
        String errorMessage = "";
        try {
            Vector3D result = testError.normalize();
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Magnitude can not be Zero", errorMessage);
    }
}
