import java.util.ArrayList;
import java.util.Arrays;

public class Test {
  static int i = 0;
  static int j = 0;

  public static void main(String[] args) {
    int[] x = {1, 2, 5, 4};
    Arrays.sort(x);
    ArrayList<Integer> y = new ArrayList<Integer>();
    for (int i: x) {
      y.add(i);
    }
    for (int i: y) {
      System.out.print(i);
    }
  }
}