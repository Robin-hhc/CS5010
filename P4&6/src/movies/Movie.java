package movies;

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
    return String.format("%s (%s %s, %d)", this.title, this.director.getFirstName(), this.director.getLastName(), this.year);
  }

  @Override public int compareTo(Movie o) {
    // TODO
    if (this.title.equals(o.getTitle()) &&
            this.director.getFirstName().equals(o.getDirector().getFirstName()) &&
            this.director.getLastName().equals(o.getDirector().getLastName()) &&
            this.year == o.getYear()) {
      return 0;
    }
    return -1;
  }

  /**
   * Overrides the equals method. Compare string ignore the case difference.
   * @param obj the input object need to be compared.
   * @return true if the name are the same. False otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (! (obj instanceof Movie o)) {
      return false;
    }
    return this.title.equalsIgnoreCase(o.getTitle()) &&
            this.director.getFirstName().equalsIgnoreCase(o.getDirector().getFirstName()) &&
            this.director.getLastName().equalsIgnoreCase(o.getDirector().getLastName()) &&
            this.year == o.getYear();
  }
}
