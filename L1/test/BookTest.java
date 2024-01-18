import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Book class.
 */
public class BookTest {

    private Book test;
    private Person robin;

    @Before
    public void setUp() {
        robin = new Person("Robin", "Huang", 2000);
        test = new Book("CS5010 lab01 test", robin, 6666);
    }

    @Test
    public void testFirst() {
        assertEquals("CS5010 lab01 test", test.getTitle());
    }

    @Test
    public void testSecond() {
        assertEquals(6666, test.getPrice(), 0);
    }

    @Test
    public void testGetAuthor() {
        assertEquals(robin, test.getAuthor());
    }

}
