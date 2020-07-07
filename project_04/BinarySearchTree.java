/**
 * BinarySearchTree. Represent a binary search tree
 * The student cannot change the public interface
 * 
 * @author Mila Hose
 * @version 7/5/20
 */
public class BinarySearchTree<E extends Comparable<E>> {
  TreeNode<E> root; // the root of the tree
  
  /**
   * constructor create a empty binary search tree by setting root to be null
   */
  public BinarySearchTree() {
    root = null;
  }
  
  /**
   * search the given data in this binary search tree
   * If the data is found, return a reference to the tree node
   * othewise, return null
   * @param data The target to search
   * @return a TreeNode reference to the node that contains the data
   *         if no node contains data, return null
   */
  public TreeNode<E> search(E data) {
    return search(root, data);
  }

  /**
   * search the given data in this binary search tree
   * If the data is found, return a reference to the tree node
   * othewise, return null
   * @param data The target to search
   * @return a TreeNode reference to the node that contains the data
   *         if no node contains data, return null
   */
  private TreeNode<E> search(TreeNode<E> node, E key) {
    if (node != null) { // node exists
      if (node.getData().compareTo(key) == 0) {
        return node; // root node
      } else if (node.getData().compareTo(key) > 0) {
        // Search left
        return search(node.getLeft(), key);
      } else {
        // Search right
        return search(node.getRight(), key);
      }
    }
    return null;
 }
  
  /**
   * insert given node to this binary search tree. If this tree 
   * was empty, the given node becomes the root of this tree.
   * @param newNode the given node to be inserted
   */
  public void insert(TreeNode<E> newNode) {
    insertRecursive(root, newNode);
  }
  
  /**
   * insert given data to this binary search tree. If this tree 
   * was empty, the given node becomes the root of this tree.
   * @param data the given data to be inserted
   */
  public void insert(E data) {
    TreeNode<E> newNode = new TreeNode<E>(data);

    if (root == null) {
      root = newNode;
      root.setParent(null);
    } else {
      insert(newNode);
    }
  }

  /**
   * insert given node to this binary search tree. If this tree 
   * was empty, the given node becomes the root of this tree.
   * @param parent the root node at which to start
   * @param newNode the given node to be inserted
   */
  private void insertRecursive(TreeNode<E> parent, TreeNode<E> newNode) {
    // Assign left node
    if (newNode.getData().compareTo(parent.getData()) < 0) {
      if (parent.getLeft() == null) {
        parent.setLeft(newNode);
        newNode.setParent(parent);
      } else {
       insertRecursive(parent.getLeft(), newNode); 
      }
      // Assign right node
    } else {
      if (parent.getRight() == null) {
        parent.setRight(newNode);
        newNode.setParent(parent);
      } else {
        insertRecursive(parent.getRight(), newNode);  
      }
    }
  }
  
  /**
   * remove the given data from this binary search tree and return
   * true. If the data is not in the tree, return false
   * @param data The target to remove
   */
  public boolean remove(E data) {
    TreeNode<E> node = search(data);
    TreeNode<E> parent = node.getParent();
    return remove(root, parent, node);
  }

  /**
   * remove the given data from this binary search tree and return
   * true. If the data is not in the tree, return false
   * @param data The target to remove
   */
  private boolean remove(TreeNode<E> tree, TreeNode<E> parent, TreeNode<E> node) {
    if (node == null) return false;
    // Case 1: Internal node with 2 children
    if (node.getLeft() != null && node.getRight() != null) {
      // Find successor and successor's parent
      TreeNode<E> successorNode = node.getRight();
      TreeNode<E> successorParent = node.getParent();

      while (successorNode.getLeft() != null) {
        successorParent = successorNode;
        successorNode = successorNode.getLeft();
      }

      // Copy the value from the successor node
      node.setData(successorNode.getData());
      // Recursively remove successor
      remove(tree, successorParent, successorNode);

      // Case 2: Root node (with 1 or 0 children)
    } else if (node == root) {
      if (node.getLeft() != null) {
        root = node.getLeft();
      } else {
        root = node.getRight();
      }
      // Case 3: Internal with left child only
    } else if (node.getLeft() != null) {
      // Replace node with node's left child
      if (parent.getLeft() == node) {
        parent.setLeft(node.getLeft());
      } else {
        parent.setRight(node.getLeft());
      }
      // Case 4: Internal with right child only OR leaf
    } else {
      // Replace node with node's right child
      if (parent.getLeft() == node) {
        parent.setLeft(node.getRight());
      } else {
        parent.setRight(node.getRight());
      }
    }

    return true;
  }
  
  /**
   * return a string representation of the tree
   * @return a String representation of the tree
   */
  public String toString(){
    return "(" + nodeTravesal(root) + ")";
  }

  /**
   * return a string representation of the tree
   * @return a String representation of the tree
   */
  private String nodeTravesal(TreeNode<E> treeNode) {
    if(treeNode == null) return "-";
    return treeNode.getData().toString()
      + "(" + nodeTravesal(treeNode.getLeft())
      +", " + nodeTravesal(treeNode.getRight()) + ")";
  }
  
  /**
   * return true if the tree is empty. False otherwise
   * @return true if the tree is empty; false othewise
   */
  public boolean isEmpty() {
    return root == null;
  }
  
  /**
   * return the height of the tree. Notice the height is defined as
   * the length of the longest path from nodes to root
   * @return the height of the tree
   */
  public int height() {
    return height(root);
  }

  /**
   * return the height of the tree. Notice the height is defined as
   * the length of the longest path from nodes to root
   * @return the height of the tree
   */
  private int height(TreeNode<E> tree) {
    if (tree == null) return -1;
    // Get left height
    int leftHeight = height(tree.getLeft());
    // Get right height
    int rightHeight = height(tree.getRight());
    // Return max(leftHeight, rightHeight)
    return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
  }
  
  /**
   * return the number of nodes in the tree
   * @return the number of nodes in this tree
   */
  public int size() {
    return size(root);
  }

  /**
   * return the number of nodes in the tree
   * @return the number of nodes in this tree
   */
  private int size(TreeNode<E> node) {
    if (node == null) return 0; 
    // Add all left and right nodes, return with +1 for root
    return size(node.getLeft()) + size(node.getRight()) + 1; 
  }
}