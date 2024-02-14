/**
 * This interface represents a set of 2D points, that can be searched
 */
package kdtree;
public interface SetOfPoints {
  /**
   * Add a point to the set.
   * @param point a Point object to add
   */
  void add (Point2D point);

  /**
   * returns a List of all the points currently in this set.
   * @return list of all the points currently in this set.
   */
  Point2D[] getPoints();

  /**
   * Find all the points in this set that lie inside or on the given circle field.
   * @param center the center if the circle.
   * @param radius the radius of the circle.
   * @return list of points in this set that lie inside or on this circle.
   */
  Point2D[] allPointsWithinCircle(Point2D center, double radius);

  /**
   * Find the point in this set that is closest to this query point. If no such point exists,
   * this method return null
   * @param point the input point.
   * @return point in this set that is closest to the given point.
   */
  Point2D closestPoint(Point2D point);
}
