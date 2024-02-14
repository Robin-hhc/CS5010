package kdtree;
import java.util.ArrayList;

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

  public ArrayList<Point2D> allPointsWithinCircle(Point2D center, double radius) {
    return new ArrayList<>();
  }
}
