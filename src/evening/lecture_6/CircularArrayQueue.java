// Created by Dayu Wang (dwang@stchas.edu) on 2022-07-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-28


package evening.lecture_6;

import java.util.Arrays;
import java.util.NoSuchElementException;

/** A sequential queue implemented using circular array */
public class CircularArrayQueue<T> {
    // Data fields
    private Object[] data;  // Stores the elements in the queue.
    private int capacity;  // Stores the length of "data".
    private int numOfItems;  // Stores the number of elements in the queue.
    private int frontIndex;  // Stores the index to the element at the front end of the queue.
    private int rearIndex;  // Stores the index to the element at the rear end of the queue.
    private static final int DEFAULT_CAPACITY = 10;  // Default value of "capacity"

    // Constructors

    public CircularArrayQueue() {  // Default constructor
        capacity = DEFAULT_CAPACITY;
        data = new Object[capacity];
        rearIndex = capacity - 1;
    }  // Time complexity: O(1)

    public CircularArrayQueue(CircularArrayQueue<T> other) {  // Copy constructor
        capacity = other.capacity;
        numOfItems = other.numOfItems;
        frontIndex = other.frontIndex;
        rearIndex = other.rearIndex;
        data = Arrays.copyOf(other.data, capacity);
    }  // Time complexity: O(n)

    // Methods

    /** Returns the size of the queue.
        @return: number of elements stored in the queue
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the queue is empty.
        @return: {true} if there are no elements stored in the queue; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the element at the front end of the queue.
        @return: element at the front end of the queue
        @throws NoSuchElementException: queue is empty.
    */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        return (T)data[frontIndex];
    }  // Time complexity: O(1)

    /** Removes (and returns) the element at the front end of the queue.
        @return: element removed
        @throws NoSuchElementException: queue is empty.
    */
    @SuppressWarnings("unchecked")
    public T poll() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        T toBeRemoved = (T)data[frontIndex];
        frontIndex = (frontIndex + 1) % capacity;
        numOfItems--;
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Doubles the capacity of the queue and rearranges the elements in the queue. */
    private void reserve() {
        capacity *= 2;
        Object[] newData = new Object[capacity];
        for (int i = 0, k = frontIndex; i < size(); i++, k = (k + 1) % (capacity / 2)) { newData[i] = data[k]; }
        data = newData;
        frontIndex = 0;
        rearIndex = size() - 1;
    }  // Time complexity: O(n)

    /** Adds a new element to the rear end of the queue.
        @param value: new element to add to the queue
    */
    public void offer(T value) {
        if (size() == capacity) { reserve(); }
        rearIndex = (rearIndex + 1) % capacity;
        data[rearIndex] = value;
        numOfItems++;
    }  // Time complexity: O(1)
}