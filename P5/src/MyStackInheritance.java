import java.util.ArrayList;

/**
 * This class represents a stack of any objects, using Inheritance that extends ArrayList.
 */
public class MyStackInheritance<T> extends ArrayList<T> {

  /**
   * Add element to the tail of the Arraylist, which is the top of the stack.
   * @param item The input item that should be push to the top of the stack.
   */
  public void push(T item) { super.addFirst(item);}

  /**
   * Remove element at the tail of the Arraylist, which is the top of the stack.
   * @return the item on top of the stack, which is the element in the end of the ArrayList.
   */
  public T pop() { return super.remove(0);}

  /**
   * Peek the element at the tail of the Arraylist, which is the top of the stack.
   * @return the item on top of the stack, which is the element in the end of the ArrayList.
   */
  public T peek() { return super.get(0);}

  /**
   * Check if the stack is empty.
   * @return true if the stack contains no elements, false otherwise.
   */
  public boolean isEmpty() { return super.isEmpty();}
}
