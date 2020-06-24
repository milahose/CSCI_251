/**
 * CSCI463ProjectTwo: Use MyStack and MyQueue to write a project that check if a sentence is palindrom
 * 
 * @author Your name
 * @version Date
 */
import java.util.Scanner;

public class CSCI251ProjectTwo {
  public static void main(String [] args) {
    Scanner input = new Scanner(System.in);
    String sentence;
    String again;
    do {
      System.out.println("Enter a sentence, I will tell you if it is a palindrome: ");
      sentence = input.nextLine();
      if(isPalindrome(sentence))
        System.out.println("\"" + sentence + "\" is a palindrome!");
      else
        System.out.println("\"" + sentence + "\" is not a palindrome!");
      System.out.println("Do you want another test (\"YES\" or \"NO\"): ");
      again = input.nextLine();
    } while(again.equalsIgnoreCase("YES"));
  }
  
  /**
   * isPalindrome returns true if the given String is a palindrome,
   * false otherwise 
   */
  public static boolean isPalindrome(String sentence) {
    // declare a MyStack s
    MyStack<Character> s = new MyStack<>();
    // declare a MyQueue q
    MyQueue<Character> q = new MyQueue<>();
    for (int i = 0; i < sentence.length(); i++) {
      // if ith character in sentence is a letter
      // convert to upper case and push it into s and q
      char c = sentence.charAt(i); 
      if(Character.isLetter(c)) {
        s.push(Character.toUpperCase(c));
        q.push(Character.toUpperCase(c));
      }
    }

    while(!s.isEmpty()){
      // if the front of the queue not match the top of stack
      if(q.peek() != s.peek()) {
        // Not a palindrome, return false
        return false; 
      }

      // pop out top of the stack and front of the queue
      s.pop(); 
      q.pop();
    }

    return true;
  }
}