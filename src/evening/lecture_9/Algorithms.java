// Created by Dayu Wang (dwang@stchas.edu) on 2021-02-12

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-10-19


package evening.lecture_9;

import java.util.ArrayList;

public class Algorithms {
    /** Finds the height of a binary tree.
        @param root: reference to the root of a binary tree
        @return: height of the binary tree
    */
    public static <T> int height(BTNode<T> root) {
        if (root == null) { return 0; }
        return Math.max(height(root.left), height(root.right)) + 1;
    }  // Time complexity: O(n)

    /** Preorder traverses a binary tree.
        @param root: reference to the root of a binary tree
    */
    public static <T> void preorderTraversal(BTNode<T> root) {
        if (root != null) {
            System.out.print(root.data.toString() + ' ');
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }  // Time complexity: O(n)

    /** Inorder traverses a binary tree.
        @param root: reference to the root of a binary tree
    */
    public static <T> void inorderTraversal(BTNode<T> root) {
        if (root != null) {
            System.out.print("( ");
            inorderTraversal(root.left);
            System.out.print(root.data.toString() + ' ');
            inorderTraversal(root.right);
            System.out.print(") ");
        }
    }  // Time complexity: O(n)

    /** Postorder traverses a binary tree.
        @param root: reference to the root of a binary tree
    */
    public static <T> void postorderTraversal(BTNode<T> root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data.toString() + ' ');
        }
    }  // Time complexity: O(n)
}