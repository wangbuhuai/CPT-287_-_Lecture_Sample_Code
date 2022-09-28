// Created by Dayu Wang (dwang@stchas.edu) on 2022-07-01

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-28


package evening.lecture_6;

import java.util.NoSuchElementException;

/** A sequential queue implemented using singly-linked list */
public class LinkedListQueue<T> {
    /** A singly-linked list node */
    private class Node {
        // Data fields
        T data;  // Stores some data in the node.
        Node next;  // Stores a reference to the next node in the list.

        // Constructor
        Node(T value) { data = value; }  // Time complexity: O(1)
    }

    // Data fields
    private Node front;  // Stores a reference to the node at the front end of the queue.
    private Node rear;  // Stores a reference to the node at the rear end of the queue.
    private int numOfItems;  // Stores the number of elements in the queue.

    // Constructors

    public LinkedListQueue() {}  // Default constructor

    public LinkedListQueue(LinkedListQueue<T> other) {  // Copy constructor
        numOfItems = other.numOfItems;
        if (numOfItems > 0) {
            front = rear = new Node(other.front.data);
            Node q = other.front.next;
            while (q != null) {
                rear.next = new Node(q.data);
                rear = rear.next;
                q = q.next;
            }
        }
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
    public T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        return front.data;
    }  // Time complexity: O(1)

    /** Removes (and returns) the element at the front end of the queue.
        @return: element removed
        @throws NoSuchElementException: queue is empty.
    */
    public T poll() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        T toBeRemoved = front.data;
        if (numOfItems-- == 1) { front = rear = null; }
        else { front = front.next; }
        return toBeRemoved;
    }  // Time complexity: O(1)

    /** Adds a new element to the rear end of the queue.
        @param value: new element to add to the queue
    */
    public void offer(T value) {
        if (numOfItems++ == 0) { front = rear = new Node(value); }
        else {
            rear.next = new Node(value);
            rear = rear.next;
        }
    }  // Time complexity: O(1)
}