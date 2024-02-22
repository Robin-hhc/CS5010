package movies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A class for sorting movies.
 */
public class Driver {
  /**
   * Sorts a list of movies in place using the Insertion Sort algorithm.
   *
   * @param movies The list of movies to be sorted.
   */
  public static void sortTitle(List<Movie> movies) {
    for (int i = 1; i < movies.size(); i++) {
      Movie key = movies.get(i);
      int j = i - 1;

      while (j >= 0 && movies.get(j).compareTo(key) > 0) {
        movies.set(j + 1, movies.get(j));
        j = j - 1;
      }
      movies.set(j + 1, key);
    }
  }

  /**
   * Sorts a list of movies in place using the Insertion Sort algorithm.
   *
   * @param movies The list of movies to be sorted.
   */
  public static void sortDirector(List<Movie> movies) {
    for (int i = 1; i < movies.size(); i++) {
      Movie key = movies.get(i);
      int j = i - 1;

      while (j >= 0 && movies.get(j).compareTo(key) > 0) {
        movies.set(j + 1, movies.get(j));
        j = j - 1;
      }
      movies.set(j + 1, key);
    }
  }

  /**
   * Sorts a list of movies in place using the Insertion Sort algorithm.
   *
   * @param movies The list of movies to be sorted.
   */
  public static void sortYear(List<Movie> movies) {
    for (int i = 1; i < movies.size(); i++) {
      Movie key = movies.get(i);
      int j = i - 1;

      while (j >= 0 && movies.get(j).compareTo(key) > 0) {
        movies.set(j + 1, movies.get(j));
        j = j - 1;
      }
      movies.set(j + 1, key);
    }
  }

  /**
   * Reads movies from a CSV file and returns a list of Movie objects.
   *
   * @param filename The path to the CSV file containing movie data.
   * @return A list of Movie objects created from the CSV file data.
   */
  public static List<Movie> readMoviesFromCSV(String filename) throws IOException{
    List<Movie> movies = new LinkedList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] attributes = line.split(",");
        Movie movie = createMovie(attributes);
        movies.add(movie);
      }
    } catch (IOException e) {
      throw new IOException("Filename invalid.");
    }
    return movies;
  }

  /**
   * Helper method to create a Movie object from an array of attributes.
   * Assumes the director's name in the CSV is a full name that needs to be split into first and last names.
   *
   * @param metadata An array of String representing the movie attributes.
   * @return A new Movie object based on the provided metadata.
   */
  private static Movie createMovie(String[] metadata) {
    String title = metadata[0];
    // Split the director's name into first and last names
    String[] nameParts = metadata[1].trim().split(" ");
    String firstName = nameParts[0];
    String lastName = nameParts.length > 1 ? nameParts[nameParts.length - 1] : ""; // Handle single names
    Person director = new Person(firstName, lastName);
    int year = Integer.parseInt(metadata[2]);
    return new Movie(title, director, year);
  }

  public static void main(String[] args) throws IOException {
    String csvFile = "/Users/robinhuang/Desktop/CS5010/P4&6/src/p6-longer_list_movies.csv";
    List<Movie> movies = readMoviesFromCSV(csvFile);

    System.out.println("Movies before sorting:");
    for (Movie movie : movies) {
      System.out.println(movie);
    }

    sortTitle(movies);

    System.out.println("\nMovies after sorting:");
    for (Movie movie : movies) {
      System.out.println(movie);
    }
  }
}
