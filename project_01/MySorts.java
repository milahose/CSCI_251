/*
  Name: Mila Hose
  Class: CSCI 251
  Assignment: Project One
  Date: 6/12/20
*/

public class MySorts {
  public static void insertSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int current = i;
      // Insert numbers[i] into sorted part 
      // stopping once numbers[i] in correct position
      while (current > 0 && arr[current] < arr[current - 1]) {
        // Swap current element with the one before it
        int temp = arr[current];
        arr[current] = arr[current - 1];
        arr[current - 1] = temp;
        current--;
      }
    }
  }

  public static void selectSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int smallestIndex = i;
      // Find index of smallest remaining element
      for (int j = i + 1; j < arr.length; j++) {
        // Swap current element with the one before int
        if (arr[j] < arr[smallestIndex]) {
          smallestIndex = j;
        }
      }

      int temp = arr[i];
      arr[i] = arr[smallestIndex];
      arr[smallestIndex] = temp;
    }
  }

  public static void quickSort(int[] arr) {
    quickSortRecursive(arr, 0, arr.length - 1);
  }

  public static void mergeSort(int[] arr) {
    mergeSortRecursive(arr, 0, arr.length - 1);
  }

  // merge two sorted portions of given array arr, namely, from start to middle
  // and from middle + 1 to end into one sorted portion, namely, from start to end
  public static void merge(int[] arr, int start, int middle, int end) {
    int mergedSize = end - start + 1; // Size of merged partition
    int mergePos = 0; // Initialize position to insert merged number 
    // Dynamically allocates temporary array for merged numbers
    int[] mergedNumbers = new int[mergedSize]; 
    int leftPos = start; // Initialize left partition position
    int rightPos = middle + 1; // Initialize right partition position
    
    // Add smallest element from left or right partition to merged numbers
    while (leftPos <= middle && rightPos <= end) {
      if (arr[leftPos] <= arr[rightPos]) {
        mergedNumbers[mergePos] = arr[leftPos];
        leftPos++;
      } else {
        mergedNumbers[mergePos] = arr[rightPos];
        rightPos++;
      }
      mergePos++;
    }
    
    // If left partition is not empty, add remaining elements to merged numbers
    while (leftPos <= middle) {
      mergedNumbers[mergePos] = arr[leftPos];
      leftPos++;
      mergePos++;
    }
    
    // If right partition is not empty, add remaining elements to merged numbers
    while (rightPos <= end) {
      mergedNumbers[mergePos] = arr[rightPos];
      rightPos++;
      mergePos++;
    }
    
    // Copy merge number back to numbers
    for (mergePos = 0; mergePos < mergedSize; mergePos++) {
      arr[start + mergePos] = mergedNumbers[mergePos];
    }
 }

  // merge sort recursive version
  // sort the portion of giving array arr, from begin to end
  public static void mergeSortRecursive(int[] arr, int begin, int end) {
    int middle;

    if (begin < end) {
      middle = (begin + end) / 2; // Find the midpoint in the partition

      // Recursively sort left and right partitions
      mergeSortRecursive(arr, begin, middle);
      mergeSortRecursive(arr, middle + 1, end);

      // Merge left and right partition in sorted order
      merge(arr, begin, middle, end);
    }
  }

  // find pivot point for quick start
  public static int pivot(int[] arr, int begin, int end) {
    /* Pick middle value as pivot */
    int midpoint = begin + (end - begin) / 2;
    int pivot = arr[midpoint];
    int low = begin;
    int high = end;
    boolean done = false;

    while (!done) {
      /* Increment low while arr[low] < pivot */
      while (arr[low] < pivot) {
        low++;
      }

      /* Decrement high while pivot < arr[high] */
      while (pivot < arr[high]) {
        high--;
      }

      /* If there are zero or one items remaining, 
      all numbers are partitioned. */
      if (low >= high) {
        done = true;
      } else {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
        low++;
        high--;
      }
    }

    // Return high, the last element in low partition
    return high;
  }

  // quick sort recursive version
  // sort the portion of giving array arr, from begin to end
  public static void quickSortRecursive(int[] arr, int begin, int end) {
    // Base case: If there are 1 or zero elements to sort,
    // partition is already sorted
    if (begin >= end) return;
    
    // Partition the data within the array. Value middle returned
    // from partitioning is location of last element in low partition.
    int middle = pivot(arr, begin, end);
    
    // Recursively sort low partition (begin to middle)
    // and high partition (middle + 1 to end)
    quickSortRecursive(arr, begin, middle);
    quickSortRecursive(arr, middle + 1, end);
  }
}