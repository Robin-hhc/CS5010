import static org.junit.Assert.assertEquals;

import movies.Movie;
import movies.Person;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Movie class.
 */
public class MovieTest {
  private Person billyWilder;
  private Movie theApartment;

  /**
   * Set up movies to use for our tests.
   */
  @Before public void setUp() {
    this.billyWilder = new Person("Billy", "Wilder");
    this.theApartment = new Movie("The Apartment", this.billyWilder, 1960);
  }

  /**
   * Test the title of the movie.
   */
  @Test public void testTitle() {
    assertEquals("The Apartment", theApartment.getTitle());
  }

  /**
   * Test the director of the movie.
   */
  @Test public void testDirector() {
    assertEquals("Billy Wilder", theApartment.getDirector().toString());
  }

  /**
   * Test the year of the movie.
   */
  @Test public void testYear() {
    assertEquals(1960, theApartment.getYear());
  }

  /**
   * Test toString method.
   */
  @Test public void testToString() {
    assertEquals("The Apartment (Billy Wilder, 1960)", theApartment.toString());
  }


  @Test public void testCompareTo() {
    // TODO
    Movie test = new Movie("The Apartment2", this.billyWilder, 1960);
    assertEquals(-1, theApartment.compareTo(test), 0);
    test = new Movie("The Apartment", this.billyWilder, 1961);
    assertEquals(-1, theApartment.compareTo(test), 0);
    Person testPerson = new Person("Billy1", "Wilder");
    test = new Movie("The Apartment", testPerson, 1960);
    assertEquals(-1, theApartment.compareTo(test), 0);
    testPerson = new Person("Billy", "Wilder1");
    test = new Movie("The Apartment", testPerson, 1960);
    assertEquals(-1, theApartment.compareTo(test), 0);
    testPerson = new Person("Billy", "Wilder");
    test = new Movie("The Apartment", testPerson, 1960);
    assertEquals(0, theApartment.compareTo(test), 0);
  }

}

  