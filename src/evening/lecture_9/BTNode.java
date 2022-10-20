// Created by Dayu Wang (dwang@stchas.edu) on 2021-02-12

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-10-19


package evening.lecture_9;

/** A binary tree node */
public class BTNode<T> {
    // Data fields
    public T data;  // Stores some data in the node.
    public BTNode<T> left, right;  // Stores references to the left and right child.

    // Constructors

    public BTNode(T data) { this.data = data; }

    public BTNode(T data, BTNode<T> left, BTNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}