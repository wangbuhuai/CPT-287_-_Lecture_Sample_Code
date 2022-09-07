// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-04

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-07


package evening.lecture_3;

/** A singly-linked list node */
public class Node<T> {
    // Data fields
    public T data;  // Stores some data in the node.
    public Node<T> next;  // Stores a reference to the next node in the list.

    // Constructor
    public Node(T data) { this.data = data; }
}