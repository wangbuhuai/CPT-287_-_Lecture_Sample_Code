// Created by Dayu Wang (dwang@stchas.edu) on 2021-12-11

// Last updated by Dayu Wang (dwang@stchas.edu) on 2021-12-12


package data_structures;

import java.util.Arrays;
import java.util.NoSuchElementException;

/** An advanced array */
public class Array_List<T> implements Iterable<T> {
    // Data fields
    private Object[] arr;  // A regular array to store the elements in the array list
    private int numOfElements;  // Number of elements stored in the array list
    private static final int DEFAULT_CAPACITY = 10;  // Default length of the regular array {arr}

    // Constructors

    public Array_List() { arr = new Object[DEFAULT_CAPACITY]; }  // Default constructor

    public Array_List(int capacity) { arr = new Object[capacity]; }  // Constructor with user-specified capacity

    public Array_List(Array_List<T> other) {  // Copy constructor
        arr = Arrays.copyOf(other.arr, other.arr.length);
        numOfElements = other.numOfElements;
    }

    // Methods

    /** Returns the number of elements in the array list.
        @return: number of elements in the array list
    */
    public final int size() { return numOfElements; }  // Time complexity: O(1)

    /** Tests whether the array list is empty.
        @return: {true} if the array list contains no elements; {false} otherwise
    */
    public final boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

    /** Returns the element at the specified position in the array list.
        @param index: index of the element to return
        @return: element at the specified position in the array list
        @throws IndexOutOfBoundsException: index is out of range (index < 0 or index >= size()).
    */
    @SuppressWarnings("unchecked")
    public final T get(int index) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        return (T)arr[index];
    }  // Time complexity: O(1)

    /** Replaces the element at the specified position in the array list with the specified element.
        @param index: index of the element to replace
        @param value: element to be stored at the specified position
        @throws IndexOutOfBoundsException: index is out of range (index < 0 or index >= size()).
    */
    public void set(int index, T value) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        arr[index] = value;
    }  // Time complexity: O(1)

    /** Doubles the current capacity of the regular array {arr} without changing the elements stored in it. */
    private void reserve() {
        Object[] newArr = new Object[2 * arr.length];
        System.arraycopy(arr, 0, newArr, 0, size());
        arr = newArr;
    }  // Time complexity: O(n)

    /** Appends the specified element to the end of the array list.
        @param value: element to be appended to the array list
    */
    public void add(T value) {
        if (size() == arr.length) { reserve(); }
        arr[numOfElements++] = value;
    }  // Average time complexity: O(1)

    /** Inserts the specified element at the specified position in the array list.
        Shifts the element currently at that position (if any) and any subsequent elements to the right.
        @param index: index at which the specified element is to be inserted
        @param value: element to be inserted
        @throws IndexOutOfBoundsException: index is out of range (index < 0 or index > size()).
    */
    public void add(int index, T value) {
        if (size() == arr.length) { reserve(); }
        System.arraycopy(arr, index, arr, index + 1, numOfElements++ - index);
        arr[index] = value;
    }  // Time complexity: O(n)

    /** Reduces the current capacity of the regular array {arr} by half without changing the elements stored in it. */
    private void shrink() {
        Object[] newArr = new Object[Math.max(arr.length / 2, DEFAULT_CAPACITY)];
        System.arraycopy(arr,0, newArr, 0, size());
        arr = newArr;
    }  // Time complexity: O(n)

    /** Removes the element at the specified position in the array list and shifts any subsequent elements to the left.
        @param index: index of the element to be removed
        @return: element that was removed from the array list
        @throws IndexOutOfBoundsException: index is out of range (index < 0 or index >= size()).
    */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        T toBeRemoved = (T)arr[index];
        System.arraycopy(arr, index + 1, arr, index, --numOfElements - index);
        if (size() < arr.length / 2 && arr.length > DEFAULT_CAPACITY) { shrink(); }
        return toBeRemoved;
    }  // Average time complexity: O(n - index)

    /** Finds the index of the first occurrence of the specified element in the array list.
        @param value: element to search for
        @return: index of the first occurrence of the specified element in the array list,
                 or {-1} if the array list does not contain the element
    */
    public int indexOf(T value) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(value)) { return i; }
        }
        return -1;
    }  // Time complexity: O(n)

    /** Tests whether the array list contains the specific element.
        @param value: element whose presence in the array list is to be tested
        @return: {true} if the array list contains the specified element; {false} otherwise
    */
    public boolean contains(T value) { return indexOf(value) != -1; }  // Time complexity: O(n)

    /** Generates a list iterator at the beginning of the array list.
        @return: a list iterator at the beginning of the array list
    */
    @Override
    public List_Iterator<T> iterator() {
        return new List_Iterator<T>() {
            // Data fields
            private int leftIndex = -1;  // Index of the left element at the current iterator position
            private int rightIndex = 0;  // Index of the right element at the current iterator position

            // Methods

            /** Tests whether there exists a next element at current iterator position.
                @return: {true} if there exists a next element at current iterator position; {false} otherwise
            */
            @Override
            public boolean hasNext() { return rightIndex != size(); }  // Time complexity: O(1)

            /** Tests whether there exists a previous element at current iterator position.
                @return: {true} if there exists a previous element at current iterator position; {false} otherwise
            */
            @Override
            public boolean hasPrevious() { return leftIndex != -1; }  // Time complexity: O(1)

            /** Moves the iterator forward and returns the element passed by.
                @return: element passed by in the iterator movement
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                leftIndex++;
                return (T)arr[rightIndex++];
            }  // Time complexity: O(1)

            /** Moves the iterator backward and returns the element passed by.
                @return: element passed by in the iterator movement
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            @SuppressWarnings("unchecked")
            public T previous() {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                rightIndex--;
                return (T)arr[leftIndex--];
            }  // Time complexity: O(1)

            /** Replaces the next element at current iterator position with the specified element.
                @param value: element with which to replace the next element at current iterator position
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public void setNext(T value) {
                if (!hasNext()) { throw new NoSuchElementException(); }
                arr[rightIndex] = value;
            }  // Time complexity: O(1)

            /** Replaces the previous element at current iterator position with the specified element.
                @param value: element with which to replace the previous element at current iterator position
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public void setPrevious(T value) {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                arr[leftIndex] = value;
            }

            /** Removes (and returns) the next element at current iterator position.
                @return: the element removed
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public T removeNext() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                return remove(rightIndex);
            }  // Average time complexity: O(n - rightIndex)

            /** Removes (and returns) the previous element at current iterator position.
                @return: the element removed
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public T removePrevious() {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                previous();
                return removeNext();
            }  // Average time complexity: O(n - leftIndex)

            /** Inserts an element at the current iterator position.
                @param value: element to insert
            */
            @Override
            public void add(T value) {
                Array_List.this.add(rightIndex, value);
                next();
            }  // Average time complexity: O(n - rightIndex)
        };
    }  // Time complexity: O(1)

    /** Returns a string representation of the array list.
        @return: a string representation of the array list
    */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder().append('[');
        for (int i = 0; i < numOfElements; i++) {
            out.append(get(i).toString());
            if (i != numOfElements - 1) { out.append(", "); }
        }
        return out.append(']').toString();
    }  // Time complexity: O(n)
}