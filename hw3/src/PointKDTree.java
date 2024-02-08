public class PointKDTree implements SetOfPoints{

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
