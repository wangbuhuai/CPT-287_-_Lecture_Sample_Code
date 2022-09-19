// Created by Dayu Wang (dwang@stchas.edu) on 2021-12-11

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-14


package evening.lecture_4;

import java.util.NoSuchElementException;

/** A general iterator */
public interface Iterator<T> {
    /** Tests whether there exists a next element at current iterator position.
        @return: {true} if there exists a next element at current iterator position; {false} otherwise
    */
    boolean hasNext();

    /** Moves the iterator forward one position and returns the element passed by.
        @return: element passed by in the iterator movement
        @throws NoSuchElementException: there is not a next element at current iterator position.
    */
    T next();

    /** Removes (and returns) the next element at current iterator position.
        @return: element removed
        @throws NoSuchElementException: there is not a next element at current iterator position.
    */
    T removeNext();
}