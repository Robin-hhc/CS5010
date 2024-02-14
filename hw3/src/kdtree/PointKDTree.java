package kdtree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A PointKDTree class that extends SetOfPoints class. Implemented a structure to store the set of
 * points as a KDTree.
 */
public class PointKDTree implements SetOfPoints{
  Node root;

  /**
   * Constructs a {@code PointKDTree} object.
   * Set a Node object as the root of the KDTree and store all input points into the tree
   * @param points the input points that the PointKDTree initially have.
   */
  public PointKDTree (Point2D[] points) throws IllegalArgumentException {
    if (points == null) { throw new IllegalArgumentException("Points list invalid");}
    Point2D[] px, py;
    Arrays.sort(points, (a, b) -> Integer.compare(a.x, b.x));
    px = points.clone();
    Arrays.sort(points, (a, b) -> Integer.compare(a.y, b.y));
    py = points.clone();
    this.root = this.buildKdTree(px, py, 0);
  }

  /**
   * Helper method for the constructor, build the whole KDTree recursively.
   * @param px the input points that sorted on x-axis.
   * @param py the input points that sorted on y-axis.
   * @param depth current depth of the tree.
   * @return Node type object as the current root.
   */
  private Node buildKdTree(Point2D[] px, Point2D[] py, int depth) {
    if (px.length == 0) {
      return null;
    } else if (px.length == 1) {
      return new Node(px[0], new ArrayList<Point2D>(List.of(px)), 1, 0, -px[0].x);
    }

    int a, b, c;
    Point2D p;
    if (depth %2 == 0) { //use vertical line
      //line is x+by+c
      a = 1;
      b = 0;
      c = -px[px.length/2].x;
      p = px[px.length/2];
    }
    else {
      //line is ax+by+c
      a = 0;
      b = 1;
      c = -py[py.length/2].y;
      p = py[py.length/2];
    }

    ArrayList<Point2D> on = new ArrayList<Point2D>();
    ArrayList<Point2D> pxBefore = new ArrayList<Point2D>();
    ArrayList<Point2D> pxAfter = new ArrayList<Point2D>();
    ArrayList<Point2D> pyBefore = new ArrayList<Point2D>();
    ArrayList<Point2D> pyAfter = new ArrayList<Point2D>();
    for (int i = 0; i<px.length; i++) {
      double sd = signedDistance(px[i],a,b,c);
      if (sd<0) {
        pxBefore.add(px[i]);
      }
      else if (sd>0) {
        pxAfter.add(px[i]);
      }
      else {
        on.add(px[i]);
      }
    }
    for (int i = 0; i<py.length; i++) {
      double sd = signedDistance(py[i],a,b,c);
      if (sd<0) {
        pyBefore.add(py[i]);
      }
      else if (sd>0) {
        pyAfter.add(py[i]);
      }
    }
    Node left = this.buildKdTree(pxBefore.toArray(new Point2D[0]), pyBefore.toArray(new Point2D[0]), depth+1);
    Node right = this.buildKdTree(pxAfter.toArray(new Point2D[0]), pyAfter.toArray(new Point2D[0]), depth+1);
    return new Node(p, left, right, on, a, b, c);
  }

  /**
   * Helper method for the constructor, calculated the signed distance of point p from line ax+by+c=0
   * @param p the input point.
   * @param a the input constant.
   * @param b the input constant.
   * @param c the input constant.
   * @return result of signed distance with formula ax+by+c=0.
   */
  private double signedDistance(Point2D p, int a, int b, int c) { //find the signed distance of point T from line ax+by+c=0
    return a*p.x + b*p.y + c;
  }

  @Override
  public void add(Point2D point) {
    Node curr = this.root;
    while (curr != null) {
      double sd = signedDistance(point, curr.a, curr.b, curr.c);
      if (sd < 0) {
        if (curr.left == null) {
          ArrayList<Point2D> on = new ArrayList<Point2D>();
          on.add(point);
          curr.left = new Node(point, on, curr.b, curr.a, -curr.b*point.x-curr.a*point.y);
          return;
        }
        curr = curr.left;
      } else if (sd > 0){
        if (curr.right == null) {
          ArrayList<Point2D> on = new ArrayList<Point2D>();
          on.add(point);
          curr.right = new Node(point, on, curr.b, curr.a, -curr.b*point.x-curr.a*point.y);
          return;
        }
        curr = curr.right;
      } else {
        curr.on.add(point);
        return;
      }
    }
  }

  @Override
  public List<Point2D> getPoints() {
    return this.root.getPoints();
  }

  @Override
  public List<Point2D> allPointsWithinCircle(Point2D center, double radius) {
    return this.root.allPointsWithinCircle(center, radius);
  }

  @Override
  public Point2D closestPoint(Point2D point) {
    return this.root.closestPoint(point);
  }
}
