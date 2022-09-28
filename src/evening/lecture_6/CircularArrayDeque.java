// Created by Dayu Wang (dwang@stchas.edu) on 2022-07-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-28


package evening.lecture_6;

import java.util.Arrays;
import java.util.NoSuchElementException;

/** A double-ended sequential queue implemented using circular array */
public class CircularArrayDeque<T> {
    // Data fields
    private Object[] data;  // Stores the elements in the deque.
    private int capacity;  // Stores the length of "data".
    private int numOfItems;  // Stores the number of elements in the deque.
    private int frontIndex;  // Stores the index to the element at the front end of the deque.
    private int rearIndex;  // Stores the index to the element at the rear end of the deque.
    private static final int DEFAULT_CAPACITY = 10;  // Default value of "capacity"

    // Constructors

    public CircularArrayDeque() {  // Default constructor
        capacity = DEFAULT_CAPACITY;
        data = new Object[capacity];
        rearIndex = capacity - 1;
    }  // Time complexity: O(1)

    public CircularArrayDeque(CircularArrayDeque<T> other) {  // Copy constructor
        capacity = other.capacity;
        numOfItems = other.numOfItems;
        frontIndex = other.frontIndex;
        rearIndex = other.rearIndex;
        data = Arrays.copyOf(other.data, capacity);
    }  // Time complexity: O(n)

    // Methods

    /** Returns the size of the deque.
        @return: number of elements stored in the deque
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the deque is empty.
        @return: {true} if there are no elements stored in the deque; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the element at the front end of the deque.
        @return: element at the front end of the deque
        @throws NoSuchElementException: deque is empty.
    */
    @SuppressWarnings("unchecked")
    public T peekFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return (T)data[frontIndex];
    }  // Time complexity: O(1)

    /** Returns the element at the rear end of the deque.
        @return: element at the rear end of the deque
        @throws NoSuchElementException: deque is empty.
    */
    @SuppressWarnings("unchecked")
    public T peekLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return (T)data[rearIndex];
    }  // Time complexity: O(1)

    /** Removes (and returns) the element at the front end of the deque.
        @return: element removed
        @throws NoSuchElementException: deque is empty.
    */
    @SuppressWarnings("unchecked")
    public T pollFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        T toBeRemoved = (T)data[frontIndex];
        frontIndex = (frontIndex + 1) % capacity;
        numOfItems--;
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Removes (and returns) the element at the rear end of the deque.
        @return: element removed
        @throws NoSuchElementException: deque is empty.
    */
    @SuppressWarnings("unchecked")
    public T pollLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        T toBeRemoved = (T)data[rearIndex];
        rearIndex = (rearIndex - 1 + capacity) % capacity;
        numOfItems--;
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Doubles the capacity of the deque and rearranges the elements in the deque. */
    private void reserve() {
        capacity *= 2;
        Object[] newData = new Object[capacity];
        for (int i = 0, k = frontIndex; i < size(); i++, k = (k + 1) % (capacity / 2)) { newData[i] = data[k]; }
        data = newData;
        frontIndex = 0;
        rearIndex = size() - 1;
    }  // Time complexity: O(n)

    /** Adds a new element to the front end of the deque.
        @param value: new element to add to the deque
    */
    public void offerFirst(T value) {
        if (size() == capacity) { reserve(); }
        frontIndex = (frontIndex - 1 + capacity) % capacity;
        data[frontIndex] = value;
        numOfItems++;
    }  // Time complexity: O(1)

    /** Adds a new element to the rear end of the deque.
        @param value: new element to add to the deque
    */
    public void offerLast(T value) {
        if (size() == capacity) { reserve(); }
        rearIndex = (rearIndex + 1) % capacity;
        data[rearIndex] = value;
        numOfItems++;
    }  // Time complexity: O(1)
}