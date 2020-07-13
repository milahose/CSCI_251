/**
 * class MyGraph. Will use Matrix to represent a simple weighted directed graph. There is no loop at vertex.
 * No more than one edge from vertex i to another vertex j. The vertices are numbered as 1, 2, ..., n
 * The graph with n vertices is reprented by an (n+1) by (n+1) matrix with row 0 and column 0 unused.
 * If there is an edge from vertex i to vertex j (i != j), then entry on row i column j of the matrix will 
 * be 1. If there is no edge between vertex i to vertex j (i != j), then the entry on row i column j of the 
 * matrix will be Integer.MAX_VALUE
 * 
 * @author Mila Hose
 * @version 7/11/2020
 */
import java.util.*;
public class MyGraph {
  private int[][] graph;
  private int numberOfVertices;
  
  /**
   * create a graph with given number of vertices with no edges
   * @param numberOfVertices number of vertices of the graph
   */
  public MyGraph(int numberOfVertices){
    this.numberOfVertices = numberOfVertices;
    graph = new int[numberOfVertices+1][numberOfVertices+1];
  }
    
  /**
   * create a graph with given matrix representation
   * @param graph The matrix representation on the given graph. Assume column 0 and row 0 are not used
   */
  public MyGraph(int [][] graph){
    this.graph = graph;
    // change any 0 to infinity if the 0 is not on diagonal
    for(int i = 1; i < graph.length; i++){
      for(int j = 1; j < graph.length; j++){
        if(i == j) graph[i][j] = 0;
        else if(i != j && graph[i][j] == 0)
          graph[i][j] = Integer.MAX_VALUE;
      }
    }
    numberOfVertices = graph.length - 1; 
  }
    
  /**
   * return a String that represent the vertices in order if the BFS algorithm is used to traverse the graph
   * starting from the given vertex. If the startVertex does not exists, return an error message
   * @param startVertex The vertex where the traversal starts
   * @return A String that describes the vertices visited in order
   */
  public String bfs(int startVertex){
    // Check for invalid input
    if (startVertex < 1 || startVertex > numberOfVertices) {
      return "Sorry, " + startVertex + " is not a valid vertex.";
    }

    // Discovered vertices, beginning with user's input
    String discovered = startVertex + ", ";
    // Create a queue
    LinkedList<Integer> queue = new LinkedList<Integer>();
    // Add start vertex to queue
    queue.addLast(startVertex);

    while (!queue.isEmpty()) {
      // Get first value from queue
      int currentVertex = queue.removeFirst();  
      // Search the vertices
      for (int i = 1; i <= graph[currentVertex].length - 1; i++) {
        int current = graph[currentVertex][i];
        boolean notDiscovered = !discovered.contains(Integer.toString(i));
        // If vertex is eligable and not discovered
        if (current > 0 && current < Integer.MAX_VALUE && notDiscovered) {
          // Add to queue and discovered list
          queue.addLast(i);
          discovered += i + ", ";
        }
      }
    }
    
    // Remove trailing comma before returning
    return discovered.substring(0, discovered.length() - 2);
  }
  
  /**
   * return a String that represents the vertices in order if the DFS algorithm is used to traverse the graph
   * starting from the given vertex. If the startVertex does not exist, return an error message
   * @param startVertex The vertex where the traversal starts
   * @return A String that describes the vertices visited in order
   */
  public String dfs(int startVertex){
    // Check for invalid input
    if (startVertex < 1 || startVertex > numberOfVertices) {
      return "Sorry, " + startVertex + " is not a valid vertex.";
    }

    // Discovered vertices, beginning with user's input
    String discovered = startVertex + ", ";
    // Create a stack
    Stack<Integer> stack = new Stack<Integer>();
    // Add start vertex to stack
    stack.push(startVertex);

    while (!stack.isEmpty()) {
      // Get top of stack
      int currentVertex = stack.pop();
      // Search the vertices
      for (int i = 1; i <= graph[currentVertex].length - 1; i++) {
        int current = graph[currentVertex][i];
        boolean notDiscovered = !discovered.contains(Integer.toString(i));
        // If vertex is eligable and not discovered
        if (current > 0 && current < Integer.MAX_VALUE && notDiscovered) {
          // Add to stack and discovered list
          stack.push(i);
          discovered += i + ", ";
          // Move to next vertex
          break;
        }
      }
    }

    // Remove trailing comma before returning
    return discovered.substring(0, discovered.length() - 2);
  }
}