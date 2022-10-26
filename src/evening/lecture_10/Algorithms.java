// Created by Dayu Wang (dwang@stchas.edu) on 2022-10-19

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-10-26


package evening.lecture_10;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {
    /** Converts a binary search tree to a sorted list.
        @param root: a reference to the root of a binary search tree
        @return: a sorted list made from the binary search tree
    */
    public static <T> List<T> toSortedList(BTNode<T> root) {
        // Base case
        if (root == null) { return new ArrayList<>(); }

        // Inorder traversal
        List<T> result = toSortedList(root.left);
        result.add(root.data);
        result.addAll(toSortedList(root.right));
        return result;
    }  // Time complexity: O(n)

    /** Converts a sorted list (from index start to end) to a balanced binary search tree.
        @param sortedList: a sorted list
        @param start: beginning index of the segment
        @param end: end index of the segment
        @return: a reference to the root of the binary search tree
    */
    private static <T> BTNode<T> toBST(List<T> sortedList, int start, int end) {
        // Base case
        if (start > end) { return null; }

        // Divide-and-conquer
        int mid = (start + end) / 2;
        BTNode<T> leftChild = toBST(sortedList, start, mid - 1);
        BTNode<T> rightChild = toBST(sortedList, mid + 1, end);
        return new BTNode<>(sortedList.get(mid), leftChild, rightChild);
    }  // Time complexity: O(n)

    // Wrapper method
    public static <T> BTNode<T> toBST(List<T> sortedList) {
        return toBST(sortedList, 0, sortedList.size() - 1);
    }  // Time complexity: O(n)
}