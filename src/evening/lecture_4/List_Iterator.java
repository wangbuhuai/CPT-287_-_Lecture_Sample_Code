// Created by Dayu Wang (dwang@stchas.edu) on 2021-12-11

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-14


package evening.lecture_4;

import java.util.NoSuchElementException;

/** An iterator for sequential lists */
public interface List_Iterator<T> extends Iterator<T> {
    /** Tests whether there exists a previous element at current iterator position.
        @return: {true} if there exists a previous element at current iterator position; {false} otherwise
    */
    boolean hasPrevious();

    /** Moves the iterator backward one position and returns the element passed by.
        @return: element passed by in the iterator movement
        @throws NoSuchElementException: there is not a previous element at current iterator position.
    */
    T previous();

    /** Removes (and returns) the previous element at current iterator position.
        @return: element removed
        @throws NoSuchElementException: there is not a previous element at current iterator position.
    */
    T removePrevious();

    /** Replaces the next element at current iterator position with the specified element.
        @param value: element with which to replace the next element at current iterator position
        @throws NoSuchElementException: there is not a next element at current iterator position.
    */
    void setNext(T value);

    /** Replaces the previous element at current iterator position with the specified element.
        @param value: element with which to replace the previous element at current iterator position
        @throws NoSuchElementException: there is not a previous element at current iterator position.
    */
    void setPrevious(T value);

    /** Inserts an element at the current iterator position.
        @param value: element to insert
    */
    void add(T value);

    /** Moves the iterator to the beginning of the list. */
    void reset();
}