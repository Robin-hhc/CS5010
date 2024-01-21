import java.util.ArrayList;
import java.util.List;

public class SimpleBoxSet implements box.BoxSet {

  private List<int[]> boxes;

  /**
   * Constructs a {@code RectangleSet} object.
   */
  public SimpleBoxSet() { this.boxes = new ArrayList<int[]>();}

  @Override
  public void add(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0) {
      throw new IllegalArgumentException("Width invalid");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Height invalid");
    }

    this.subtract(x, y, width, height);
    this.boxes.add(new int[] {x, y, width, height});
    for (int[] rec : this.boxes) {
      System.out.printf("(%d, %d, %d, %d)", rec[0], rec[1], rec[2], rec[3]);
    }
    System.out.println("");
    System.out.println("====================================================");
  }

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
        newBoxes.add(new int[]{xMin, yMin, x-xMin, y-yMin});
        newBoxes.add(new int[]{x+width, yMin, xMax-x-width, yMax-yMin});
      } else if (x+width > xMin && x+width < xMax && y+height > yMin && y+height < yMax) { // Upper-right corner overlaps (Two edges overlap)
        newBoxes.add(new int[]{xMin, y+height, x-xMin, yMax-y-height});
        newBoxes.add(new int[]{x, yMin, xMax-x, yMax-yMin});
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
