// Created by Dayu Wang (dwang@stchas.edu) on 2022-09-21

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-21


package evening.lecture_5;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayListStack<T> {
    // Data field
    private final List<T> data;  // Stores the elements in the stack.

    // Constructors

    public ArrayListStack() { data = new ArrayList<>(); }  // Default constructor

    public ArrayListStack(ArrayListStack<T> other) { data = new ArrayList<>(other.data); }  // Copy constructor

    // Methods

    /** Returns the size of the stack.
        @return: number of elements stored in the stack
    */
    public final int size() { return data.size(); }  // Time complexity: O(1)

    /** Tests whether the stack is empty.
        @return: {true} if there are no elements stored in the stack; {false} otherwise
    */
    public final boolean isEmpty() { return data.isEmpty(); }  // Time complexity: O(1)

    /** Returns the element on top of the stack.
        @return: value stored in the top node of the stack
        @throws NoSuchElementException: stack is empty.
    */
    public T peek() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty stack"); }
        return data.get(data.size() - 1);
    }  // Time complexity: O(1)

    /** Removes (and returns) the element on top of the stack.
        @return: value (removed) stored in the top node of the stack
        @throws NoSuchElementException: stack is empty.
    */
    public T pop() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty stack"); }
        return data.remove(data.size() - 1);
    }  // Time complexity: O(1)

    /** Adds a new element onto the top of the stack.
        @param value: new element to add to the stack
    */
    public void push(T value) { data.add(value); }  // Time complexity: O(1)
}