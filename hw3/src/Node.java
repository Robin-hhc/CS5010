import java.util.ArrayList;

/**
 * A class that store all information of a node on the KDTree.
 */
public class Node {
  Point2D value;
  int count;
  Node left, right;

  /**
   * Constructs a {@code Node} object with no left and right child.
   * @param point the input point that is on this node.
   * @param count the number of duplicate points on this node.
   */
  public Node(Point2D point, int count) {
    this.value = point;
    this.left = null;
    this.right = null;
    this.count = count;
  }

  /**
   * Constructs a {@code Node} object with given left and right children.
   * @param point the input point that is on this node.
   * @param count the number of duplicate points on this node.
   * @param left the given left child of the node.
   * @param right the given right child of the node.
   */
  public Node(Point2D point, Node left, Node right, int count) {
    this.value = point;
    this.left = left;
    this.right = right;
    this.count = count;
  }

  /**
   * Find all the points in the KDTree that root from in this node.
   * @return Arraylist of points in the KDTree that root from in this node.
   */
  public ArrayList<Point2D> getPoints() {
    ArrayList<Point2D> res = new ArrayList<Point2D>();
    if (this.value == null) {
      return res;
    } else if (this.left == null && this.right == null) {
    } else if (this.left == null) {
      res.addAll(this.right.getPoints());
    } else if (this.right == null) {
      res.add(this.value);
      res.addAll(this.left.getPoints());
    } else {
      res.addAll(this.right.getPoints());
      res.addAll(this.left.getPoints());
    }
    for (int i = 0; i < this.count; i++) {
      res.add(this.value);
    }
    return res;
  }
}
