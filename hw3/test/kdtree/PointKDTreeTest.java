package kdtree;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the PointKDTree class.
 */
public class PointKDTreeTest {
  Point2D[] points;
  @Before
  public void setUp() {
    this.points = new Point2D[]{new Point2D(0, 0), new Point2D(10, 10), new Point2D(20, 20), new Point2D(10, 10)};
  }

  @Test
  public void constructorTestError() {
    String errorMessage1 = "";
    try {
      new PointKDTree(null);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    assertEquals("Points list invalid", errorMessage1);
  }

  @Test
  public void constructorTest() {
    PointKDTree res = new PointKDTree(this.points);
    Arrays.sort(this.points, (a, b) -> Integer.compare(a.x, b.x));
    Point2D[] resSorted = res.getPoints().toArray(new Point2D[0]);
    Arrays.sort(resSorted, (a, b) -> Integer.compare(a.x, b.x));
    Assert.assertArrayEquals(this.points, resSorted);
  }

  @Test
  public void addTest() {
    PointKDTree res = new PointKDTree(points);
    res.add(new Point2D(0, 10));
    Point2D[] expect = new Point2D[]{new Point2D(0, 0), new Point2D(0, 10), new Point2D(10, 10), new Point2D(10, 10), new Point2D(20, 20)};
    Point2D[] resSorted = res.getPoints().toArray(new Point2D[0]);
    Arrays.sort(resSorted, (a, b) -> Integer.compare(a.x, b.x));
    Assert.assertArrayEquals(expect, resSorted);
  }

  @Test
  public void allPointsWithinCircleTest() {
    PointKDTree res = new PointKDTree(points);
//    for (Point2D p: res.allPointsWithinCircle(new Point2D(-1, 10), 11)) {
//      System.out.print(p.x);
//      System.out.println(p.y);
//    }
    Point2D[] expect = new Point2D[]{new Point2D(0, 0), new Point2D(10, 10), new Point2D(10, 10)};
    Assert.assertArrayEquals(expect, res.allPointsWithinCircle(new Point2D(-1, 10), 11).toArray(new Point2D[0]));
  }
}
