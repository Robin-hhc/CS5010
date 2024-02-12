import java.util.ArrayList;

public class PointKDTree implements SetOfPoints{

  public PointKDTree (Point2D[] points) throws IllegalArgumentException {
    if (points == null) { throw new IllegalArgumentException("Points list invalid.");}

  }

  @Override
  public boolean add(Point2D point) {
    return false;
  }

  @Override
  public Point2D[] getPoints() {
    return new Point2D[0];
  }

  @Override
  public Point2D[] allPointsWithinCircle(Point2D center, double radius) {
    return new Point2D[0];
  }

  @Override
  public Point2D closestPoint(Point2D point) {
    return null;
  }
}
