// Created by Dayu Wang (dwang@stchas.edu) on 2022-07-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-28


package evening.lecture_6;

import java.util.NoSuchElementException;

/** A double-ended sequential queue implemented using doubly-linked list */
public class LinkedListDeque<T> {
    /** A doubly-linked list node */
    private class DNode {
        // Data fields
        T data;  // Stores some data in the node.
        DNode next;  // Stores a reference to the next node in the list.
        DNode prev;  // Stores a reference to the previous node in the list.

        // Constructor
        DNode(T value) { data = value; }  // Time complexity: O(1)
    }

    // Data fields
    private DNode front;  // Stores a reference to the node at the front end of the deque.
    private DNode rear;  // Stores a reference to the node at the rear end of the deque.
    private int numOfItems;  // Stores the number of elements in the deque.

    // Constructors

    public LinkedListDeque() {}  // Default constructor

    public LinkedListDeque(LinkedListDeque<T> other) {  // Copy constructor
        numOfItems = other.numOfItems;
        if (numOfItems > 0) {
            front = rear = new DNode(other.front.data);
            DNode q = other.front.next;
            while (q != null) {
                rear.next = new DNode(q.data);
                rear.next.prev = rear;
                rear = rear.next;
                q = q.next;
            }
        }
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
    public T peekFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return front.data;
    }  // Time complexity: O(1)

    /** Returns the element at the rear end of the deque.
        @return: element at the rear end of the deque
        @throws NoSuchElementException: deque is empty.
    */
    public T peekLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return rear.data;
    }  // Time complexity: O(1)

    /** Removes (and returns) the element at the front end of the deque.
        @return: element removed
        @throws NoSuchElementException: deque is empty.
    */
    public T pollFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        T toBeRemoved = front.data;
        if (numOfItems-- == 1) { front = rear = null; }
        else {
            front = front.next;
            front.prev = null;
        }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Removes (and returns) the element at the rear end of the queue.
        @return: element removed
        @throws NoSuchElementException: deque is empty.
    */
    public T pollLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        T toBeRemoved = rear.data;
        if (numOfItems-- == 1) { front = rear = null; }
        else {
            rear = rear.prev;
            rear.next = null;
        }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Adds a new element to the front end of the deque.
        @param value: new element to add to the deque
    */
    public void offerFirst(T value) {
        if (numOfItems++ == 0) { front = rear = new DNode(value); }
        else {
            front.prev = new DNode(value);
            front.prev.next = front;
            front = front.prev;
        }
    }  // Time complexity: O(1)

    /** Adds a new element to the rear end of the deque.
        @param value: new element to add to the deque
    */
    public void offerLast(T value) {
        if (numOfItems++ == 0) { front = rear = new DNode(value); }
        else {
            rear.next = new DNode(value);
            rear.next.prev = rear;
            rear = rear.next;
        }
    }  // Time complexity: O(1)
}