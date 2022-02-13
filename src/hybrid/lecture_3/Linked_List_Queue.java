// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-13

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-13


package hybrid.lecture_3;

import java.util.NoSuchElementException;

/** A linked-list based sequential queue */
public class Linked_List_Queue<T> {
    /** A singly-linked list node */
    private class Node {
        // Data fields
        T data;  // Some data stored in the node
        Node next;  // Reference to the next node

        // Constructor
        Node(T theData) { data = theData; }
    }

    // Data fields
    private Node front, rear;  // Nodes in the front and rear ends of the queue
    private int numOfItems;  // Number of elements stored in the queue

    // Constructors

    public Linked_List_Queue() {}  // Default constructor

    public Linked_List_Queue(Linked_List_Queue<T> other) {  // Copy constructor
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
    }

    // Methods

    /** Returns the size of the queue.
        @return: number of elements stored in the queue
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the queue is empty.
        @return: {true} if there are no elements stored in the queue; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the element in the front end of the queue.
        @return: value stored in the front node of the queue
        @throws NoSuchElementException: queue is empty.
    */
    public T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty queue"); }
        return front.data;
    }  // Time complexity: O(1)

    /** Removes (and returns) the element in the front end of the queue.
        @return: value (removed) stored in the front node of the queue
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