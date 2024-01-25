import java.util.ArrayList;
import java.util.List;

public class SimpleBoxSet implements box.BoxSet {

  private List<int[]> boxes;

  /**
   * Constructs a {@code SimpleBoxSet} object.
   * Set an ArrayList object to store all boxes
   */
  public SimpleBoxSet() { this.boxes = new ArrayList<int[]>(); }

  /**
   * Add a given rectangle to this set, and make this set the result.
   * Subtract input rectangle from the boxes. Add the input rectangle to the boxes.
   * @param x the x-coordinate of the rectangle to be added
   * @param y the y-coordinate of the rectangle to be added
   * @param width the width of the rectangle to be added
   * @param height the height of the rectangle to be added
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  @Override
  public void add(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0) {
      throw new IllegalArgumentException("Width invalid");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Height invalid");
    }

    this.subtract(x, y, width, height);
    this.boxes.add(new int[] {x, y, width, height});
  }

  /**
   * Subtract the given rectangle from this set, and make this set the result.
   * Separate the input rectangles in 16 cases.
   * 1 case in all four edges overlap, 4 cases in three edges overlap, 6 cases in two edges overlap,
   * 4 cases in one edge overlap and 1 case of input rectangle have this rectangle inside.
   * @param x the x-coordinate of the rectangle to be subtracted
   * @param y the y-coordinate of the rectangle to be subtracted
   * @param width the width of the rectangle to be subtracted
   * @param height the height of the rectangle to be subtracted
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  @Override
  public void subtract(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0) {
      throw new IllegalArgumentException("Width invalid");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Height invalid");
    }

    List<int[]> newBoxes = new ArrayList<int[]>();
    for (int[] rec : this.boxes) {
      int xMax = rec[0]+rec[2];
      int yMax = rec[1]+rec[3];
      int xMin = rec[0];
      int yMin = rec[1];
      if (x > xMin && x+width < xMax && y > yMin && y+height < yMax) { // All four edges overlap
        newBoxes.add(new int[] {xMin, yMin, x-xMin, yMax-yMin});
        newBoxes.add(new int[] {x+width, yMin, xMax-x-width, yMax-yMin});
        newBoxes.add(new int[] {x, yMin, width, y-yMin});
        newBoxes.add(new int[] {x, y+height, width, yMax-y-height});
      } else if (x > xMin && x+width < xMax && y > yMin) { // Only upper width edge non-overlaps (Three edges overlap)
        newBoxes.add(new int[]{xMin, yMin, x-xMin, yMax-yMin});
        newBoxes.add(new int[]{x + width, yMin, xMax-x-width, yMax-yMin});
        newBoxes.add(new int[]{x, yMin, width, y-yMin});
      } else if (x > xMin && x+width < xMax && y+height < yMax) { // Only lower width edge non-overlaps (Three edges overlap)
        newBoxes.add(new int[]{xMin, yMin, x-xMin, yMax-yMin});
        newBoxes.add(new int[]{x+width, yMin, xMax-x-width, yMax-yMin});
        newBoxes.add(new int[] {x, y+height, width, yMax-y-height});
      } else if (y > yMin && y+height < yMax && x > xMin) { // Only right height edge non-overlaps (Three edges overlap)
        newBoxes.add(new int[]{xMin, yMin, xMax-xMin, y-yMin});
        newBoxes.add(new int[]{xMin, y + height, xMax-xMin, yMax-y-height});
        newBoxes.add(new int[]{xMin, y, x-xMin, height});
      } else if (y > yMin && y+height < yMax && x+width < xMax) { // Only left height edge non-overlaps (Three edges overlap)
        newBoxes.add(new int[]{xMin, yMin, xMax-xMin, y-yMin});
        newBoxes.add(new int[]{xMin, y + height, xMax-xMin, yMax-y-height});
        newBoxes.add(new int[]{x + width, y, xMax-x-width, height});
      } else if (x > xMin && x < xMax && y > yMin && y < yMax) { // Lower-left corner overlaps (Two edges overlap)
        newBoxes.add(new int[]{xMin, yMin, x-xMin, yMax-yMin});
        newBoxes.add(new int[]{x, yMin, xMax-x, y-yMin});
      } else if (x > xMin && x < xMax && y+height > yMin && y+height < yMax) { // Upper-left corner overlaps (Two edges overlap)
        newBoxes.add(new int[]{xMin, yMin, x-xMin, yMax-yMin});
        newBoxes.add(new int[]{x, y+height, xMax-x, yMax-y-height});
      } else if (x+width > xMin && x+width < xMax && y > yMin && y < yMax) { // Lower-right corner overlaps (Two edges overlap)
        newBoxes.add(new int[]{xMin, yMin, x+width-xMin, y-yMin});
        newBoxes.add(new int[]{x+width, yMin, xMax-x-width, yMax-yMin});
      } else if (x+width > xMin && x+width < xMax && y+height > yMin && y+height < yMax) { // Upper-right corner overlaps (Two edges overlap)
        newBoxes.add(new int[]{xMin, y+height, x+width-xMin, yMax-y-height});
        newBoxes.add(new int[]{x+width, yMin, xMax-x-width, yMax-yMin});
      } else if (x > xMin && x+width < xMax) { // Left edge and right edge overlaps (Two edges overlap)
        newBoxes.add(new int[]{xMin, yMin, x-xMin, yMax-yMin});
        newBoxes.add(new int[]{x+width, yMin, xMax-x-width, yMax-yMin});
      } else if (y > yMin && y+height < yMax) { // Upper edge and Lower edge overlaps (Two edges overlap)
        newBoxes.add(new int[]{xMin, yMin, xMax-xMin, y-yMin});
        newBoxes.add(new int[]{xMin, y+height, xMax-xMin, yMax-y-height});
      } else if (x > xMin && x < xMax) { // Left edge overlaps (One edge overlaps)
        newBoxes.add(new int[]{xMin, yMin, x-xMin, yMax-yMin});
      } else if (x+width > xMin && x+width < xMax) { // Right edge overlaps (One edge overlaps)
        newBoxes.add(new int[]{x+width, yMin, xMax-x-width, yMax-yMin});
      } else if (y > yMin && y < yMax) { // Lower edge overlaps (One edge overlaps)
        newBoxes.add(new int[]{xMin, yMin, xMax-xMin, y-yMin});
      } else if (y+height > yMin && y+height < yMax) { // Upper edge overlaps (One edge overlaps)
        newBoxes.add(new int[]{xMin, y+height, xMax-xMin, yMax-y-height});
      } else if (x <= xMin && x+width >= xMax && y <= yMin && y+height >= yMax){ // Check if input rectangle have this rectangle inside
        continue;
      } else { // Non-overlap
        newBoxes.add(new int[]{xMin, yMin, xMax-xMin, yMax-yMin});
      }
    }
    this.boxes = newBoxes;
  }

  /**
   * Get all the rectangles in this set.
   * Loop through all boxes store in the ArrayList, put them in the return Array
   * @return an array with each element containing exactly four numbers: the x, y, width and height
   *          of the rectangle in that order. For example, if there are two rectangles in this set, then
   *          the first rectangle would be (arr[0][0],arr[0][1],arr[0][2],arr[0][3]) and the second
   *          rectangle would be (arr[1][0],arr[1][1],arr[1][2],arr[1][3])
   */
  @Override
  public int[][] getBoxes() {
    int[][] ans = new int[this.boxes.size()][4];
    for (int i=0; i < this.boxes.size(); i++) {
      ans[i] = this.boxes.get(i);
    }
    return ans;
  }

  @Override
  public int size() {
    return this.boxes.size();
  }
}
