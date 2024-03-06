
// Kevin Pulikkottil
// 5/3/23
// JDK version 14.0.1
// this program uses hash table with chaining to organize items and resizes it when over 75% full. The hash function calculates an element's index based on its code and array length.
import java.util.LinkedList;
import java.util.List;

// Define a HashNumSet class that implements NumSet interface
public class HashNumSet<E extends Number> implements NumSet<E> {

    // Define constants for initial capacity and load factor
    private static final int INITIAL_CAPACITY = 2;
    private static final double LOAD_FACTOR = 0.75;

    // Declare instance variables
    private List<E>[] table;
    private int size;
    private int capacity;

    // Default constructor
    public HashNumSet() {
        this(INITIAL_CAPACITY);
    }

    // Constructor with initial capacity parameter
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HashNumSet(int initialCapacity) {
        table = new List[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
        capacity = initialCapacity;
    }

    // Add element to the hash set
    @Override
    public boolean add(E element) {
        // Check for null elements
        if (element == null) {
            throw new NullPointerException("element cannot be null");
        }

        // Calculate hash and get bucket
        int index = hash(element);
        List<E> bucket = table[index];
        if (bucket == null) {
            bucket = new LinkedList<>();
            table[index] = bucket;
        }

        // Check if element already exists
        for (E node : bucket) {
            if (node.equals(element)) {
                return false;
            }
        }

        // Add element and increase size
        bucket.add(element);
        size++;

        // Resize if necessary
        if (size > LOAD_FACTOR * capacity) {
            resize();
        }

        return true;
    }

    // Check if hash set contains element
    @Override
    public boolean contains(E element) {
        // Check if the element is null and throw a NullPointerException if it is
        if (element == null) {
            throw new NullPointerException("element cannot be null");
        }

        // Calculate the index in the table array where the element should be
        int index = hash(element);

        // Get the bucket (linked list) at the calculated index
        List<E> bucket = table[index];

        // If the bucket is null, the element is not in the set, so return false
        if (bucket == null) {
            return false;
        }

        // Iterate through the elements in the bucket
        for (E node : bucket) {
            // If the current element in the bucket equals the element being searched for,
            // the element is in the set, so return true
            if (node.equals(element)) {
                return true;
            }
        }

        // If the element was not found in the bucket, return false
        return false;
    }

    // Remove element from the hash set
    @Override
    public boolean remove(E element) {
        // Check if the element is null and throw a NullPointerException if it is
        if (element == null) {
            throw new NullPointerException("element cannot be null");
        }

        // Calculate the index in the table array where the element should be
        int index = hash(element);

        // Get the bucket (linked list) at the calculated index
        List<E> bucket = table[index];

        // If the bucket is null, the element is not in the set, so return false
        if (bucket == null) {
            return false;
        }

        // Iterate through the elements in the bucket
        for (int i = 0; i < bucket.size(); i++) {
            // If the current element in the bucket equals the element to be removed,
            // remove it from the bucket, decrement the size of the set, and return true
            if (bucket.get(i).equals(element)) {
                bucket.remove(i);
                size--;
                return true;
            }
        }

        // If the element was not found in the bucket, return false
        return false;
    }

    // Return current size of the hash set
    @Override
    public int size() {
        return size;
    }

    // Return current capacity of the hash set
    @Override
    public int capacity() {
        return capacity;
    }

    // Calculate hash for the given element
    private int hash(E element) {
        return element.hashCode() % capacity;
    }

    // Resize the hash set
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void resize() {
        // Create a new table with double capacity
        List<E>[] newTable = new List[capacity * 2];
        for (int i = 0; i < capacity * 2; i++) {
            newTable[i] = new LinkedList<>();
        }
        // Rehash and move elements to the new table
        for (List<E> bucket : table) {
            if (bucket != null) {
                for (E node : bucket) {
                    int index = hash(node);
                    List<E> newBucket = newTable[index];
                    if (newBucket == null) {
                        newBucket = new LinkedList<>();
                        newTable[index] = newBucket;
                    }
                    newBucket.add(node);
                }
            }
        }
        // Update the table and capacity
        table = newTable;
        capacity *= 2;
    }

    // Main method for testing
    public static void main(String[] args) {
        HashNumSet<Integer> numSet = new HashNumSet<>();
        numSet.add(5);
        numSet.add(3);
        numSet.add(7);
        // Print size and capacity
        System.out.println(numSet.size()); // 3
        System.out.println(numSet.capacity()); // 4
        numSet.remove(3);
        // Print updated size and capacity
        System.out.println(numSet.size()); // 2
        System.out.println(numSet.capacity()); // 4
    }
}
