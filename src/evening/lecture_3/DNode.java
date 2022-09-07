// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-04

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-07


package evening.lecture_3;

/** A doubly-linked list node */
public class DNode<T> {
    // Data fields
    public T data;  // Stores some data in the node.
    public DNode<T> next, prev;  // Store references of the next and previous nodes in the list.

    // Constructor
    public DNode(T data) { this.data = data; }
}