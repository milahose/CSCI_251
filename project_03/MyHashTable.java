/**
 * class MyHashTable. A simple HashTable. Handle collision by chain
 * 
 * @author Mila Hose
 * @version 6/28/20
 */
import java.util.ArrayList;

public class MyHashTable<K extends Comparable<K>, V> {
  private ArrayList<MyHashEntry<K, V>> table;
  private int count; // how many elements in table
  
  /**
   * Constructor. Constructor an empty MyHashTable with given number of Buckets
   * @param tableSize The number of Buckets of the table
   */
  public MyHashTable(int tableSize) {
    // create table
    this.table = new ArrayList<MyHashEntry<K, V>>(tableSize); 
    // initialize the table elements to null 
    for (int i = 0; i < tableSize; i++) {
      table.add(null);
    }
  }
  
  /**
   * constructor. Construct an empty MyHashTable with capacity 10 buckets
   */
  public MyHashTable() {
    this.table = new ArrayList<MyHashEntry<K, V>>(10);
  }
  
  /**
   * get the number of elements in the table
   * @return the number of elements in the table
   */
  public int size() {
    return count;
  }
  
  /**
   * clear the table
   */
  public void clear() {
    for (int i = 0; i < count; i++) {
      table.set(i, null); 
    }
  }
  
  /**
   * get the value with given key.
   * @param key The given key
   * @return the value that have the key matches the given key. If no such a value, return null
   */
  public V get(K key) {
    int bucket = hash(key);
    MyHashEntry<K, V> currentNode = table.get(bucket);
    
    while (currentNode != null) {
      if (currentNode.getKey().equals(key)) {
        return currentNode.getValue();
      }

      currentNode = currentNode.next;
    }

    return null;
  }
  
  /**
   * insert (key, value) pair into the table
   * @param key The key of the pair
   * @param value The value of the pair
   */
  public void insert(K key, V value) {
    int bucket = hash(key);
    MyHashEntry<K, V> nextNode;
    MyHashEntry<K, V> currentNode = table.get(bucket);
    MyHashEntry<K, V> newNode = new MyHashEntry<K, V>(key, value);

    if (currentNode == null) {
      newNode.next = null;
      table.set(bucket, newNode);
    } else {
      nextNode = currentNode.next;
      while (nextNode != null) {
        if (currentNode.getKey().equals(key)) {
          currentNode.setValue(value);
          return;
        }

        currentNode = nextNode;
        nextNode = currentNode.next;
      }

      count++;
      newNode.next = null;
      currentNode.next = newNode;
    }
  }
  
  /**
   * remove the value with given key from the table
   * @param key The given key
   * @return the value whose key matches the given key. If no such value, null is returned
   */
  public V remove(K key) {
    int bucket = hash(key);
    MyHashEntry<K, V> currentNode = table.get(bucket);
    MyHashEntry<K, V> prevNode = null;

    while (currentNode != null) {      
      if (currentNode.getKey().equals(key)) {
        if (prevNode != null) {
          prevNode.next = null;
        } else {
          table.set(bucket, null);
        }

        count--;
        return currentNode.getValue();
      }

      prevNode = currentNode;
      currentNode = currentNode.next;
    }

    return null;
  }
  
  /**
   * check if the table is empty,i.e. no entry
   * @return true if the table holds no elements; false otherwise
   */
  public boolean isEmpty() {
    return table.size() == 0;
  }
  
  /**
   * return a String representation of the table
   * @return a String representation of the table
   */
  public String toString() {
    String sTable = "\n";
    
    for (int i = 0; i < table.size(); i++) {
      MyHashEntry<K, V> currentNode = table.get(i);

      if (currentNode == null) {
        sTable += "Bucket " + i + ":\n";
      } else {
        sTable += "Bucket " + i + ": ";
        while (currentNode != null) {
          sTable += (currentNode.getValue() + " ");
          currentNode = currentNode.next;
        }
        sTable += "\n";
      }
    }

    return sTable;
  }

  /**
   * hash the index value based on element key
   * @return an Integer representing the table's index/bucket value
   */
  public int hash(K key) {
    return Integer.parseInt(key.toString()) % table.size();
  }
}