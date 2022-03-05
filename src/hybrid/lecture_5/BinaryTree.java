// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-26

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-03-01


package hybrid.lecture_5;

import java.util.NoSuchElementException;

/** A binary tree */
public class BinaryTree<T> {
    /** A binary tree node */
    private class BTNode {
        // Data fields
        T data;  // Data stored in the node
        BTNode left, right;  // References to the roots of left and right subtree

        // Constructors

        BTNode(T theData) { data = theData; }

        BTNode(T theData, BTNode leftChild, BTNode rightChild) {
            data = theData;
            left = leftChild;
            right = rightChild;
        }
    }

    // Data fields
    private BTNode root;  // Root node of the tree

    // Constructors

    public BinaryTree() {}  // Default constructor

    public BinaryTree(BinaryTree<T> other) { root = copy(other.root); }  // Copy constructor

    private BinaryTree(BTNode root) { this.root = root; }

    // Methods

    /** Creates a deep copy of a binary tree.
        @param root: root node of the original binary tree
        @return: root node of the copy
    */
    private BTNode copy(BTNode root) {
        if (root == null) { return null; }  // Base case
        return new BTNode(root.data, copy(root.left), copy(root.right));  // Recurrence relation
    }  // Time complexity: O(n)

    /** Tests whether the binary tree is empty.
        @return: {true} if the tree is empty; {false} otherwise
    */
    public final boolean isEmpty() { return root == null; }  // Time complexity: O(1)

    /** Tests whether the binary tree has any subtrees.
        @return: {true} if the tree has subtree; {false} otherwise
    */
    public final boolean hasSubtree() {
        if (root == null) { return false; }
        return root.left != null || root.right != null;
    }  // Time complexity: O(1)

    /** Returns the data stored in the root.
        @return: data stored in the root
        @throws NoSuchElementException: tree is empty.
    */
    public final T getData() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty tree"); }
        return root.data;
    }  // Time complexity: O(1)

    /** Returns the left subtree.
        @return: left subtree
        @throws NoSuchElementException: tree is empty.
    */
    public final BinaryTree<T> getLeftSubtree() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty tree"); }
        return new BinaryTree<>(root.left);
    }  // Time complexity: O(1)

    /** Returns the right subtree.
        @return: right subtree
        @throws NoSuchElementException: tree is empty.
    */
    public final BinaryTree<T> getRightSubtree() {
        if (isEmpty()) { throw new NoSuchElementException("Accessing empty tree"); }
        return new BinaryTree<>(root.right);
    }  // Time complexity: O(1)
}