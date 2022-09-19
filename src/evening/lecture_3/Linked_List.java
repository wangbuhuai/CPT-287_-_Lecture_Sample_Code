// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-04

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-07


package evening.lecture_3;

import java.util.NoSuchElementException;

/** A doubly-linked list without iterators */
public class Linked_List<T> {
    /** A doubly-linked list node */
    private class DNode {
        // Data fields
        T data;  // Stores some data in the node.
        DNode next, prev;  // Store references of the next and previous node in the list.

        // Constructor
        DNode(T data) { this.data = data; }  // Time complexity: O(1)
    }

    // Data fields
    private DNode head, tail;  // Store the first and last node in the list.
    private int numOfItems;  // Stores the number of elements in the list.

    // Constructors

    public Linked_List() {}  // Default constructor

    public Linked_List(Linked_List<T> other) {  // Copy constructor
        numOfItems = other.numOfItems;
        if (numOfItems != 0) {
            head = tail = new DNode(other.head.data);
            DNode q = other.head.next;
            while (q != null) {
                tail.next = new DNode(q.data);
                tail.next.prev = tail;
                tail = tail.next;
                q = q.next;
            }
        }
    }  // Time complexity: O(n)

    // Methods

    /** Returns the size of the linked list.
        @return: number of elements stored in the linked list
    */
    public int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the linked list is empty.
        @return: {true} if the list is empty; {false} otherwise
    */
    public boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the value stored in the head of the linked list.
        @return: value stored in the head of the linked list
        @throws NoSuchElementException: list is empty.
    */
    public T getFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty list"); }
        return head.data;
    }  // Time complexity: O(1)

    /** Returns the value stored in the tail of the linked list.
        @return: value stored in the tail of the linked list
        @throws NoSuchElementException: list is empty.
    */
    public T getLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty list"); }
        return tail.data;
    }  // Time complexity: O(1)

    /** Adds a new element to the front end of the linked list.
        @param item: new element to add to the list
    */
    public void addFirst(T item) {
        if (numOfItems++ == 0) { head = tail = new DNode(item); }
        else {
            DNode newNode = new DNode(item);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }  // Time complexity: O(1)

    /** Adds a new element to the rear end of the linked list.
        @param item: new element to add to the list
    */
    public void addLast(T item) {
        if (numOfItems++ == 0) { head = tail = new DNode(item); }
        else {
            DNode newNode = new DNode(item);
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }  // Time complexity: O(1)

    /** Deletes the first node (head) in the linked list.
        @return: item deleted
        @throws NoSuchElementException: list is empty.
    */
    public T removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty list"); }
        T deleted = head.data;
        if (numOfItems-- == 1) { head = tail = null; }
        else {
            head = head.next;
            head.prev = null;
        }
        return deleted;
    }  // Time complexity: O(1)

    /** Deletes the last node (tail) in the linked list.
        @return: the deleted
        @throws NoSuchElementException: list is empty.
    */
    public T removeLast() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty list"); }
        T deleted = tail.data;
        if (numOfItems-- == 1) { head = tail = null; }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return deleted;
    }  // Time complexity: O(1)

    /** Tests whether a target value appears in the linked list.
        @param target: value to search
        @return: {true} if target found in the list; {false} otherwise
    */
    public boolean contains(T target) {
        DNode p = head;
        while (p != null) {
            if (p.data.equals(target)) { return true; }
            p = p.next;
        }
        return false;
    }  // Time complexity: O(n)

    /** Deletes all the items in the linked list. */
    public void clear() {
        head = tail = null;
        numOfItems = 0;
    }  // Time complexity: O(1)

    /** Customizes the output format of the linked list.
        @return: a string representing the output format of the list
    */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        DNode p = head;
        while (p != null) {
            builder.append(p.data.toString());
            if (p.next != null) { builder.append(", "); }
            p = p.next;
        }
        return builder.append(']').toString();
    }  // Time complexity: O(n)
}