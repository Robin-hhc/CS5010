import java.util.ArrayList;

/**
 * This class represents a stack of any objects, using composition with ArrayList.
 */
public class MyStack<T> {
  private ArrayList<T> stack;

  /**
   * Constructor of the class, initialize an ArrayList to store elements in the stack.
   */
  public MyStack() { this.stack = new ArrayList<T>();}

  /**
   * Add element to the tail of the Arraylist, which is the top of the stack.
   * @param item The input item that should be push to the top of the stack.
   */
  public void push(T item) { this.stack.addFirst(item);}

  /**
   * Remove element at the tail of the Arraylist, which is the top of the stack.
   * @return the item on top of the stack, which is the element in the end of the ArrayList.
   */
  public T pop() { return this.stack.remove(0);}

  /**
   * Peek the element at the tail of the Arraylist, which is the top of the stack.
   * @return the item on top of the stack, which is the element in the end of the ArrayList.
   */
  public T peek() { return stack.get(0);}

  /**
   * Check if the stack is empty.
   * @return true if the stack contains no elements, false otherwise.
   */
  public boolean isEmpty() { return stack.isEmpty();}
}
