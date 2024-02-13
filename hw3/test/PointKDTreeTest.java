import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the PointKDTree class.
 */
public class PointKDTreeTest {
  Point2D[] points;
  @Before
  public void setUp() {
    this.points = new Point2D[]{new Point2D(0, 0), new Point2D(1, 1), new Point2D(2, 2), new Point2D(1, 1)};
  }

  @Test
  public void constructorTest() {
    PointKDTree res = new PointKDTree(this.points);
    Arrays.sort(this.points, (a, b) -> Integer.compare(a.x, b.x));
    Point2D[] resSorted = res.getPoints();
    Arrays.sort(resSorted, (a, b) -> Integer.compare(a.x, b.x));
    Assert.assertArrayEquals(this.points, resSorted);
  }

  @Test
  public void addTest() {
    PointKDTree res = new PointKDTree(this.points);
    res.add(new Point2D(0, 1));
    Point2D[] expect = new Point2D[]{new Point2D(0, 1), new Point2D(0, 0), new Point2D(1, 1), new Point2D(1, 1), new Point2D(2, 2)};
    Point2D[] resSorted = res.getPoints();
    Arrays.sort(resSorted, (a, b) -> Integer.compare(a.x, b.x));
    Assert.assertArrayEquals(expect, resSorted);
  }
}
