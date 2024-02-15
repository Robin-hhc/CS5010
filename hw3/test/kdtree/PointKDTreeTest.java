package kdtree;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    res.add(new Point2D(30, 30));
    expect = new Point2D[]{new Point2D(0, 0), new Point2D(0, 10), new Point2D(10, 10), new Point2D(10, 10), new Point2D(20, 20), new Point2D(30, 30)};
    resSorted = res.getPoints().toArray(new Point2D[0]);
    Arrays.sort(resSorted, (a, b) -> Integer.compare(a.x, b.x));
    Assert.assertArrayEquals(expect, resSorted);

    res.add(new Point2D(-1, -1));
    expect = new Point2D[]{new Point2D(-1, -1), new Point2D(0, 0), new Point2D(0, 10), new Point2D(10, 10), new Point2D(10, 10), new Point2D(20, 20), new Point2D(30, 30)};
    resSorted = res.getPoints().toArray(new Point2D[0]);
    Arrays.sort(resSorted, (a, b) -> Integer.compare(a.x, b.x));
    Assert.assertArrayEquals(expect, resSorted);

    res.add(new Point2D(0, 0));
    expect = new Point2D[]{new Point2D(-1, -1), new Point2D(0, 0), new Point2D(0, 10), new Point2D(0, 0), new Point2D(10, 10), new Point2D(10, 10), new Point2D(20, 20), new Point2D(30, 30)};
    resSorted = res.getPoints().toArray(new Point2D[0]);
    Arrays.sort(resSorted, (a, b) -> Integer.compare(a.x, b.x));
    Assert.assertArrayEquals(expect, resSorted);
  }

  @Test
  public void allPointsWithinCircleTest() {
    PointKDTree res = new PointKDTree(points);
    Point2D[] expect = new Point2D[]{new Point2D(0, 0), new Point2D(10, 10), new Point2D(10, 10)};
    Assert.assertArrayEquals(expect, res.allPointsWithinCircle(new Point2D(-1, 10), 11).toArray(new Point2D[0]));
    expect = new Point2D[]{new Point2D(10, 10), new Point2D(10, 10)};
    Assert.assertArrayEquals(expect, res.allPointsWithinCircle(new Point2D(4, 10), 7).toArray(new Point2D[0]));

  }

  @Test
  public void closestPointTest() {
    PointKDTree res = new PointKDTree(points);
    assertEquals(this.points[0], res.closestPoint(new Point2D(1, 1)));
    assertEquals(this.points[1], res.closestPoint(new Point2D(11, 11)));
    assertEquals(this.points[2], res.closestPoint(new Point2D(15, 15)));
  }
}
