/**
 * This interface represents a set of 2D points, that can be searched
 */
public interface SetOfPoints {
  /**
   * Add a given rectangle to this set, and make this set the result.
   * @param point the x-coordinate of the rectangle to be added
   * @return true if we add the point
   */
  boolean add (Point2D point);

  /**
   * Add a given rectangle to this set, and make this set the result.
   * @return IllegalArgumentException if the width or height of the rectangle are not positive
   */
  Point2D[] getPoints();

  Point2D[] allPointsWithinCircle(Point2D center, double radius);

  Point2D closestPoint(Point2D point);
}
