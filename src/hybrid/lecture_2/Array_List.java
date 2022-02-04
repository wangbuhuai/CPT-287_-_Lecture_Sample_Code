// Created by Dayu Wang (dwang@stchas.edu) on 2021-12-11

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-04


package hybrid.lecture_2;

import java.util.Arrays;
import java.util.NoSuchElementException;

/** An advanced array with iterators */
public class Array_List<T> implements Iterable<T> {
    // Data fields
    private static final int DEFAULT_CAPACITY = 10;  // Default capacity for the regular array container
    private int capacity;  // Max size of the regular array container
    private int numOfItems;  // Number of elements stored in the array list
    private Object[] data;  // Using a regular array to store array elements

    // Constructors

    public Array_List() {  // Default constructor
        capacity = DEFAULT_CAPACITY;
        data = new Object[capacity];
    }

    public Array_List(int initialCapacity) {  // Constructor with user-specified capacity
        capacity = initialCapacity;
        data = new Object[capacity];
    }

    public Array_List(Array_List<T> other) {  // Copy constructor
        capacity = other.capacity;
        numOfItems = other.numOfItems;
        data = Arrays.copyOf(other.data, other.capacity);  // Deep copy
    }

    // Methods

    /** Returns the number of elements in the array list.
        @return: number of elements in the array list
    */
    public final int size() { return numOfItems; }  // Time complexity: O(1)

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
        return (T)data[index];
    }  // Time complexity: O(1)

    /** Replaces the element at the specified position in the array list with the specified value.
        @param index: index of the element to replace
        @param value: element to be stored at the specified position
        @throws IndexOutOfBoundsException: index is out of range (index < 0 or index >= size()).
    */
    public void set(int index, T value) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        data[index] = value;
    }  // Time complexity: O(1)

    /** Doubles the current capacity of the regular array container without changing the elements stored in it. */
    private void reserve() {
        capacity = 2 * data.length;
        Object[] newArr = new Object[capacity];
        System.arraycopy(data, 0, newArr, 0, size());
        data = newArr;
    }  // Time complexity: O(n)

    /** Appends the specified element to the end of the array list.
        @param value: element to be appended to the array list
        @return: always {true}, representing the element successfully appended
    */
    public boolean add(T value) {
        if (size() == capacity) { reserve(); }
        data[numOfItems++] = value;
        return true;
    }  // Average time complexity: O(1)

    /** Inserts the specified element at the specified position in the array list.
        Shifts the element currently at that position (if any) and any subsequent elements to the right.
        @param index: index at which the specified element is to be inserted
        @param value: element to be inserted
        @throws IndexOutOfBoundsException: index is out of range (index < 0 or index > size()).
    */
    public void insert(int index, T value) {
        if (size() == capacity) { reserve(); }
        System.arraycopy(data, index, data, index + 1, numOfItems++ - index);
        data[index] = value;
    }  // Time complexity: O(n)

    /** Removes the element at the specified position in the array list and shifts any subsequent elements to the left.
        @param index: index of the element to be removed
        @return: element that was removed from the array list
        @throws IndexOutOfBoundsException: index is out of range (index < 0 or index >= size()).
    */
    @SuppressWarnings("unchecked")
    public final T remove(int index) {
        if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException("Index out of bounds: " + index); }
        T toBeRemoved = (T)data[index];
        System.arraycopy(data, index + 1, data, index, --numOfItems - index);
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
    public final boolean contains(T value) { return indexOf(value) != -1; }  // Time complexity: O(n)

    /** Customizes the output format of the array list.
        @return: a string representing the output format of the array list
    */
    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        for (int i = 0; i < size(); i++) {
            builder.append(data[i].toString());
            if (i != size() - 1) { builder.append(", "); }
        }
        return builder.append(']').toString();
    }  // Time complexity: O(n)

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
                @throws NoSuchElementException : there is not a next element at current iterator position.
            */
            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                leftIndex++;
                return (T)data[rightIndex++];
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
                return (T)data[leftIndex--];
            }  // Time complexity: O(1)

            /** Replaces the next element at current iterator position with the specified element.
                @param value: element with which to replace the next element at current iterator position
                @throws NoSuchElementException: there is not a next element at current iterator position.
            */
            @Override
            public void setNext(T value) {
                if (!hasNext()) { throw new NoSuchElementException(); }
                data[rightIndex] = value;
            }  // Time complexity: O(1)

            /** Replaces the previous element at current iterator position with the specified element.
                @param value: element with which to replace the previous element at current iterator position
                @throws NoSuchElementException: there is not a previous element at current iterator position.
            */
            @Override
            public void setPrevious(T value) {
                if (!hasPrevious()) { throw new NoSuchElementException(); }
                data[leftIndex] = value;
            }

            /** Removes (and returns) the next element at current iterator position.
                @return: element removed
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
                insert(rightIndex, value);
                next();
            }  // Average time complexity: O(n - rightIndex)
        };
    }  // Time complexity: O(1)
}