// Created by Dayu Wang (dwang@stchas.edu) on 2021-12-11

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-14


package evening.lecture_4;

/** A feature of iteration over some data structure */
@FunctionalInterface
public interface Iterable<T> {
    /** Generates an iterator at the beginning of the data structure.
        @return: an iterator at the beginning of the data structure
    */
    Iterator<T> iterator();
}