// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-13

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-13


package hybrid.lecture_3;

import java.util.NoSuchElementException;

/** A linked-list based double-ended queue */
public class Linked_List_Deque<T> {
    /** A doubly-linked list node */
    private class DNode {
        // Data fields
        T data;  // Some data stored in the node
        DNode next, prev;  // References to the next and previous nodes

        // Constructor
        DNode(T theData) { data = theData; }
    }

    // Data fields
    private DNode front, rear;  // Nodes in the front and rear ends of the deque
    private int numOfItems;  // Number of elements stored in the deque

    // Constructors

    public Linked_List_Deque() {}  // Default constructor

    public Linked_List_Deque(Linked_List_Deque<T> other) {  // Copy constructor
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
    }

    // Methods

    /** Returns the size of the deque.
        @return: number of elements stored in the deque
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the deque is empty.
        @return: {true} if there are no elements stored in the deque; {false} otherwise
    */
    public boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the element in the front end of the deque.
        @return: value stored in the front node of the deque
        @throws NoSuchElementException: deque is empty.
    */
    public T peekFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return front.data;
    }  // Time complexity: O(1)

    /** Returns the element in the rear end of the deque.
        @return: value stored in the rear node of the deque
        @throws NoSuchElementException: deque is empty.
    */
    public T peekLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty deque"); }
        return rear.data;
    }  // Time complexity: O(1)

    /** Removes (and returns) the element in the front end of the deque.
        @return: value (removed) stored in the front node of the deque
        @throws  NoSuchElementException: deque is empty.
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

    /** Removes (and returns) the element in the rear end of the deque.
        @return: value (removed) stored in the rear node of the deque
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