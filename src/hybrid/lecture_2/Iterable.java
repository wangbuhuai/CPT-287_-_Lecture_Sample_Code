// Created by Dayu Wang (dwang@stchas.edu) on 2021-12-11

// Last updated by Dayu Wang (dwang@stchas.edu) on 2021-12-11


package hybrid.lecture_2;

/** An iterable class can be iterated by iterators. */
public interface Iterable<T> {
    /** Generates an iterator at the beginning of the data structure.
        @return: an iterator at the beginning of the data structure
    */
    Iterator<T> iterator();
}