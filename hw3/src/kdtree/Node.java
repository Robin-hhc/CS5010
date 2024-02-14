package kdtree;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that store all information of a node on the KDTree.
 */
public class Node {
  protected Point2D value;
  protected int a, b, c;
  protected Node left, right;
  protected ArrayList<Point2D> on;

  /**
   * Constructs a leaf {@code Node} object with no left and right child.
   * @param point the input point that is on this node.
   * @param on the points on the dividing line of this node.
   * @param a 0 or 1 indicate if this node have dividing line on x-coordinate.
   * @param b 0 or 1 indicate if this node have dividing line on y-coordinate.
   * @param c value of the dividing line.
   */
  public Node(Point2D point, ArrayList<Point2D> on, int a, int b, int c) {
    this.value = point;
    this.left = null;
    this.right = null;
    this.on = on;
    this.a = a;
    this.b = b;
    this.c = c;
  }

  /**
   * Constructs a {@code Node} object with given left and right children.
   * @param point the input point that is on this node.
   * @param left the given left child of the node.
   * @param right the given right child of the node.
   * @param on the points on the dividing line of this node.
   * @param a 0 or 1 indicate if this node have dividing line on x-coordinate.
   * @param b 0 or 1 indicate if this node have dividing line on y-coordinate.
   * @param c value of the dividing line.
   */
  public Node(Point2D point, Node left, Node right, ArrayList<Point2D> on, int a, int b, int c) {
    this.value = point;
    this.left = left;
    this.right = right;
    this.on = on;
    this.a = a;
    this.b = b;
    this.c = c;
  }

  /**
   * Find all the points in the KDTree that root from in this node.
   * @return Arraylist of points in the KDTree that root from in this node.
   */
  public ArrayList<Point2D> getPoints() {
    ArrayList<Point2D> res = new ArrayList<Point2D>();
    if (this.left == null && this.right == null) {
    } else if (this.left == null) {
      res.addAll(this.right.getPoints());
    } else if (this.right == null) {
      res.add(this.value);
      res.addAll(this.left.getPoints());
    } else {
      res.addAll(this.right.getPoints());
      res.addAll(this.left.getPoints());
    }
    res.addAll(this.on);
    return res;
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

  /**
   * Find all the points rooted in this node that lie inside or on the given circle field recursively.
   * @param center the center if the circle.
   * @param radius the radius of the circle.
   * @return list of points in this set that lie inside or on this circle.
   */
  public List<Point2D> allPointsWithinCircle(Point2D center, double radius) {
    ArrayList<Point2D> ans = new ArrayList<>();
    if (this.left == null && this.right == null) {
      if (this.value.distance(center) <= radius) {
        ans.add(this.value);
      }
      return ans;
    }

    double sd = signedDistance(center, this.a, this.b, this.c);
    if (sd <= 0) {
      if (this.left != null) {
        ans.addAll(this.left.allPointsWithinCircle(center, radius));
      }
      if (-sd < radius && this.right != null) {
        ans.addAll(this.right.allPointsWithinCircle(center, radius));
      }
    } else {
      if (this.right != null) {
        ans.addAll(this.right.allPointsWithinCircle(center, radius));
      }
      if (sd < radius && this.left != null) {
        ans.addAll(this.left.allPointsWithinCircle(center, radius));
      }
    }
    for (Point2D p: this.on) {
      if (p.distance(center) <= radius) {
        ans.add(p);
      }
    }
    return ans;
  }

  /**
   * Find the point in this set that is closest to this query point. If no such point exists,
   * this method return null
   * @param point the input point.
   * @return point in this set that is closest to the given point.
   */
  public Point2D closestPoint(Point2D point) {
    Point2D resOn, resLeft = null, resRight = null;
    if (this.left != null) {
      resLeft = this.left.closestPoint(point);
    }
    if (this.right != null) {
      resRight = this.right.closestPoint(point);
    }

    resOn = this.value;
    for (Point2D p: this.on) {
      if (p.distance(point) < resOn.distance(point)) {
        resOn = p;
      }
    }
    if (resLeft != null && resLeft.distance(point) < resOn.distance(point)) {
      resOn = resLeft;
    }
    if (resRight != null && resRight.distance(point) < resOn.distance(point)) {
      resOn = resRight;
    }
    return resOn;
  }
}
