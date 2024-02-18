import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import movies.Movie;
import movies.MovieLinkedList;
import movies.MovieList;
import movies.Person;
import movies.TimeIndicator;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Movie class.
 */
public class MovieLinkedListTest {
  private Person person1;
  private Movie movie1;
  private Person person2;
  private Movie movie2;
  private MovieLinkedList test;

  /**
   * Set up movies to use for our tests.
   */
  @Before
  public void setUp() {
    this.person1 = new Person("p1f", "p1l");
    this.movie1 = new Movie("movie1", this.person1, 1960);
    this.person2 = new Person("p2f", "p2l");
    this.movie2 = new Movie("movie2", this.person2, 1961);
    this.test = new MovieLinkedList();
  }

  /**
   * Test add method with invalid index.
   */
  @Test
  public void testAddError() {
    String errorMessage1 = "";
    try {
      test.add(-1, this.movie1);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      test.add(1, this.movie1);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    assertEquals("Index invalid", errorMessage1);
    assertEquals("Index invalid", errorMessage2);
  }

  /**
   * Test add and remove methods.
   */
  @Test public void testAddRemove() {
    test.add(0, this.movie1);
    test.add(0, this.movie2);
    test.add(0, this.movie1);
    assertTrue(test.remove(this.movie1));
    assertTrue(test.remove(this.movie1));
    assertTrue(test.remove(this.movie2));
  }

  /**
   * Test moviesMade method.
   */
  @Test public void testMoviesMade() {
    test.add(0, this.movie1);
    test.add(0, this.movie2);
    MovieList res = test.moviesMade(TimeIndicator.BEFORE, 1961);
    assertTrue(res.remove(this.movie1));
    assertFalse(res.remove(this.movie2));
    res = test.moviesMade(TimeIndicator.AFTER, 1960);
    assertFalse(res.remove(this.movie1));
    assertTrue(res.remove(this.movie2));
    res = test.moviesMade(TimeIndicator.DURING, 1961);
    assertFalse(res.remove(this.movie1));
    assertTrue(res.remove(this.movie2));
  }

  /**
   * Test moviesDirectedBy method.
   */
  @Test public void testMoviesDirectedBy() {
    test.add(0, this.movie1);
    test.add(0, this.movie2);
    MovieList res = test.moviesDirectedBy(this.person1);
    assertTrue(res.remove(this.movie1));
    assertFalse(res.remove(this.movie2));
    res = test.moviesDirectedBy(this.person2);
    assertFalse(res.remove(this.movie1));
    assertTrue(res.remove(this.movie2));
  }
}

  