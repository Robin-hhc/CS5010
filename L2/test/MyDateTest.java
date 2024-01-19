import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the MyDate class.
 */
public class MyDateTest {
    private MyDate test;
    private MyDate testError;

    @Before
    public void setUp() {
        test = new MyDate(18, 1, 2024);
        testError = new MyDate(0, 0, 0);
    }
}
