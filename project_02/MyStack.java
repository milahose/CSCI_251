/**
 * class MyStack: A stack class implemented by using ArrayList
 * All stack elements are stored in an ArrayList. The top element
 * has index top
 * 
 * @author Mila Hose
 * @version 6/24/20
 */
import java.util.ArrayList;

public class MyStack<E> {
  private ArrayList<E> list; // used to store elements in stack
  private int top; // the index of top element
  
  /**
   * constructor construct an empty stack
   */
  public MyStack() {
    list = new ArrayList<E>();
    top = 0;
  }
  
  /**
   * push a given element on the top of the stack
   */
  public void push(E item) {
    list.add(top, item);
  }
  
  /**
   * isEmpty return true if the stack is empty; false otherwise
   * @return true if the stack is empty; false otherwise
   */
  public boolean isEmpty() {
    return list.size() == 0;   
  }
  
  /**
   * peek Return the top element
   */
  public E peek() {
    return list.isEmpty() ? null : list.get(top);
  }
  
  /**
   * pop Remove the top element from the stack. If the stack is empty, nothing happen
   */
  public void pop() {
    if (!list.isEmpty()) list.remove(top);
  }
  
  /**
   * size return the size of the stack
   * @return number of elements in stack
   */
  public int size() {
    return list.size();
  }
}