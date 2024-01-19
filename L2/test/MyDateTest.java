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
        testError = new MyDate(1, 1, 0);
    }

    @Test
    public void testToStringZeros() {
        assertEquals("0000-01-01", testError.toString());
    }

    @Test
    public void testToString() {
        assertEquals("2024-01-18", test.toString());
    }

    @Test
    public void testConstructorThirtyOneDays() {
        MyDate test = new MyDate(31, 1, 2024);
        assertEquals("2024-01-31", test.toString());
    }

    @Test
    public void testConstructorThirtyDays() {
        MyDate test = new MyDate(30, 4, 2024);
        assertEquals("2024-04-30", test.toString());
    }

    @Test
    public void testConstructorNonLeapYear() {
        MyDate test = new MyDate(28, 2, 2023);
        assertEquals("2023-02-28", test.toString());
    }

    @Test
    public void testConstructorLeapYear() {
        MyDate test = new MyDate(29, 2, 2024);
        assertEquals("2024-02-29", test.toString());
    }

    @Test
    public void testConstructorYearError() {
        String errorMessage = "";
        try {
            MyDate test = new MyDate(29, 2, -1);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Year not valid", errorMessage);
    }

    @Test
    public void testConstructorMonthError() {
        String errorMessage1 = "";
        try {
            MyDate test = new MyDate(29, 13, 2000);
        } catch (Exception e) {
            errorMessage1 = e.getMessage();
        }
        String errorMessage2 = "";
        try {
            MyDate test = new MyDate(29, 0, 2000);
        } catch (Exception e) {
            errorMessage2 = e.getMessage();
        }
        assertEquals("Month not valid", errorMessage1);
        assertEquals("Month not valid", errorMessage2);
    }

    @Test
    public void testConstructorDaysError() {
        String errorMessage1 = "";
        try {
            MyDate test = new MyDate(32, 1, 2024);
        } catch (Exception e) {
            errorMessage1 = e.getMessage();
        }
        String errorMessage2 = "";
        try {
            MyDate test = new MyDate(0, 1, 2000);
        } catch (Exception e) {
            errorMessage2 = e.getMessage();
        }
        String errorMessage3 = "";
        try {
            MyDate test = new MyDate(30, 2, 2024);
        } catch (Exception e) {
            errorMessage3 = e.getMessage();
        }
        String errorMessage4 = "";
        try {
            MyDate test = new MyDate(29, 2, 2023);
        } catch (Exception e) {
            errorMessage4 = e.getMessage();
        }
        assertEquals("Day not valid", errorMessage1);
        assertEquals("Day not valid", errorMessage2);
        assertEquals("Day not valid", errorMessage3);
        assertEquals("Day not valid", errorMessage4);
    }

    @Test
    public void testAdvance() {
        test.advance(32);
        assertEquals("2024-02-19", test.toString());
        test.advance(-32);
        assertEquals("2024-01-18", test.toString());
        testError.advance(-1);
        assertEquals("0000-01-01", testError.toString());
    }
}
