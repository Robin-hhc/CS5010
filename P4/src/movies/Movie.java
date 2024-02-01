package movies;

import javax.management.openmbean.OpenMBeanConstructorInfo;

/**
 * This class represents a movie. The movie has a title, director, and year of release.
 */
public class Movie implements Comparable<Movie> {
  private String title;
  private Person director;
  private int year;

  /**
   * Constructs a Movie object and initializes it to the movie's title, director and year.
   *
   * @param title     the title of this movie
   * @param director  the name of the movie's director
   * @param year      the year the movie was released
   */
  public Movie(String title, Person director, int year) {
    this.title = title;
    this.director = director;
    this.year = year;
  }

  /**
   * Get the title of the movie.
   *
   * @return the title of the movie
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Get the name of the director of the movie.
   *
   * @return the name of the director of the movie
   */
  public Person getDirector() {
    return this.director;
  }

  /**
   * Get the year of the movie.
   *
   * @return the year of the movie
   */
  public int getYear() {
    return this.year;
  }

  @Override
  public String toString() {
    // TODO
    // Example: The Apartment (Billy Wilder, 1960)
    return ""; // Replace with String

  }

  @Override public int compareTo(Movie o) {
    // TODO
    return 0;
  }
  
}
