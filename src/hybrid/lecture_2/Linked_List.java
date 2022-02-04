// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-04

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-04


package hybrid.lecture_2;

import java.util.NoSuchElementException;

/** A doubly-linked list with iterators */
public class Linked_List<T> implements Iterable<T> {
    /** A doubly-linked list node */
    private class DNode {
        T data;
        DNode next, prev;
        DNode(T value) { data = value; }
    }

    // Data fields
    private DNode head, tail;
    private int numOfItems;

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
    }

    // Methods

    /** Returns the size of the linked list.
        @return: number of elements stored in the linked list
    */
    public int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the linked list is empty.
        @return: {true} if the list is empty; {false} otherwise
    */
    public boolean isEmpty() { return numOfItems == 0; }  // Time complexity: O(1)

    /** Returns the value stored in the head of the linked list.
        @return: value stored in the head of the linked list
        @throws NoSuchElementException : list is empty.
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

    /** Adds a new element to the front of the linked list.
        @param item: new element to be added to the list
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

    /** Adds a new element to the end of the linked list.
        @param item: new element to be added to the list
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

    /** Deletes the first node (head) from the linked list.
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

    /** Deletes the last node (tail) from the linked list.
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

    /** Deletes all the items from the linked list. */
    public void clear() {
        head = tail = null;
        numOfItems = 0;
    }

    /** Generates a list iterator positioned at the beginning of the list.
        @return: a list iterator positioned at the beginning of the list.
    */
    @Override
    public List_Iterator<T> iterator() {
        return new List_Iterator<T>() {
            // Data fields
            private DNode nextNode = head, prevNode = null;

            /** Tests whether there exists a next element at current iterator position.
                return: {true} if there exists a next element at current iterator position; {false} otherwise
            */
            @Override
            public boolean hasNext() { return nextNode != null; }  // Time complexity: O(1)

            /** Tests whether there exists a previous element at current iterator position.
                @return: {true} if there exists a previous element at current iterator position; {false} otherwise
            */
            @Override
            public boolean hasPrevious() { return prevNode != null; }  // Time complexity: O(1)

            /** Moves the iterator forward and returns the element passed by.
                @return: element passed by in the iterator movement
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public T next() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                T value = nextNode.data;
                prevNode = nextNode;
                nextNode = nextNode.next;
                return value;
            }  // Time complexity: O(1)

            /** Moves the iterator backward and returns the element passed by.
                @return: element passed by in the iterator movement
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public T previous() {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                T value = prevNode.data;
                nextNode = prevNode;
                prevNode = prevNode.prev;
                return value;
            }  // Time complexity: O(1)

            /** Replaces the next element at current iterator position with the specified element.
                @param value: element with which to replace the next element at current iterator position
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public void setNext(T value) {
                if (!hasNext()) { throw new NoSuchElementException(); }
                nextNode.data = value;
                next();  // Moves the iterator forward.
            }  // Time complexity: O(1)

            /** Replaces the previous element at current iterator position with the specified element.
                @param value: element with which to replace the previous element at current iterator position
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public void setPrevious(T value) {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                prevNode.data = value;
                previous();
            }  // Time complexity: O(1)

            /** Removes (and returns) the next element at current iterator position.
                @return: element removed
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public T removeNext() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                T value = nextNode.data;
                if (numOfItems-- == 1) {
                    head = tail = prevNode = nextNode = null;
                } else if (prevNode == null) {  // The node to be removed is the head.
                    nextNode = nextNode.next;
                    nextNode.prev = null;
                    head = nextNode;
                } else if (nextNode.next == null) {  // The node to be removed is the tail.
                    nextNode = null;
                    prevNode.next = null;
                    tail = prevNode;
                } else {
                    prevNode.next = nextNode.next;
                    nextNode = nextNode.next;
                    nextNode.prev = prevNode;
                }
                return value;
            }

            /** Removes (and returns) the previous element at current iterator position.
                @return: element removed
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public T removePrevious() {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                T value = prevNode.data;
                if (numOfItems-- == 1) {
                    head = tail = nextNode = prevNode = null;
                } else if (prevNode.prev == null) {  // The node to be removed is the head.
                    prevNode = null;
                    nextNode.prev = null;
                    head = nextNode;
                } else if (nextNode == null) {  // The node to be removed is the tail.
                    prevNode = prevNode.prev;
                    prevNode.next = null;
                    tail = prevNode;
                } else {
                    nextNode.prev = prevNode.prev;
                    prevNode = prevNode.prev;
                    prevNode.next = nextNode;
                }
                return value;
            }  // Time complexity: O(1)

            /** Inserts an element at the current iterator position.
                @param value: element to insert
            */
            @Override
            public void add(T value) {
                if (!hasPrevious()) {  // The new item will be the new head of the list.
                    addFirst(value);
                    prevNode = head;
                    nextNode = head.next;
                } else if (!hasNext()) {  // The new item will be the new tail of the list.
                    addLast(value);
                    prevNode = tail;
                    nextNode = null;
                } else {
                    DNode newNode = new DNode(value);
                    newNode.prev = prevNode;
                    newNode.prev.next = newNode;
                    newNode.next = nextNode;
                    newNode.next.prev = newNode;
                    prevNode = newNode;
                    numOfItems++;
                }
            }  // Time complexity: O(1)
        };
    }
}