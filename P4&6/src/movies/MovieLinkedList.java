package movies;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class implements MovieList and provides a list of movies. Movies can be added and removed.
 * We can also get a subset of these movies.
 */
public class MovieLinkedList implements MovieList{
  ArrayList<Movie> movies;
  MovieLinkedList next;

  /**
   * Constructor of the class, initialize an empty ArrayList<Movie> to store the movies.
   */
  public MovieLinkedList(){
    this.movies = new ArrayList<Movie>();
  }

  @Override
  public void add(int index, Movie movie) throws IndexOutOfBoundsException {
    if (index < 0 || index > this.movies.size()) {
      throw new IndexOutOfBoundsException("Index invalid");
    }
    this.movies.add(index, movie);
  }

  @Override
  public boolean remove(Movie movie) {
    for(int i=0; i < this.movies.size(); i++) {
      if (this.movies.get(i).compareTo(movie) == 0) {
        this.movies.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public MovieList moviesMade(TimeIndicator timeIndicator, int year) {
    MovieLinkedList res = new MovieLinkedList();
    for (Movie m : this.movies) {
      if (timeIndicator == TimeIndicator.BEFORE && m.getYear() < year) {
        res.add(0, m);
      }
      if (timeIndicator == TimeIndicator.DURING && m.getYear() == year) {
        res.add(0, m);
      }
      if (timeIndicator == TimeIndicator.AFTER && m.getYear() > year) {
        res.add(0, m);
      }
    }
    return res;
  }

  @Override
  public MovieList moviesDirectedBy(Person director) {
    MovieLinkedList res = new MovieLinkedList();
    for (Movie m : this.movies) {
      if (Objects.equals(m.getDirector().getFirstName(), director.getFirstName()) && Objects.equals(m.getDirector().getLastName(), director.getLastName())) {
        res.add(0, m);
      }
    }
    return res;
  }
}
