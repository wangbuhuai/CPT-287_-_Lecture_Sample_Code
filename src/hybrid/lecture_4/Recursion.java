// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-18


package hybrid.lecture_4;

import java.util.Arrays;

public class Recursion {
    /** Sums up the segment of an array from index i (inclusive) to index j (inclusive).
        @param arr: array to sum up
        @param i: inclusive beginning index of the segment
        @param j: inclusive end index of the segment
        @return: sum of all the values in the segment
    */
    private static int sum(int[] arr, int i, int j) {
        if (i > j) { return 0; }  // Base case
        return arr[i] + sum(arr, i + 1, j);  // Recurrence relation
    }  // Time complexity: O(n)

    // Wrapper method
    public static int sum(int[] arr) { return sum(arr, 0, arr.length - 1); }  // Time complexity: O(n)

    /** Finds the length of the longest increasing segment in an array.
        @param arr: non-empty array to find the length of its longest increasing segment
        @return: length of the longest increasing segment of the array
    */
    public static int lenOfLIS(int[] arr) {
        if (arr.length == 1) { return 1; }  // Base case
        int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        int candidate_1 = lenOfLIS(left);
        int candidate_2 = lenOfLIS(right);
        int i, j;
        for (i = arr.length / 2; i >= 1; i--) {
            if (arr[i - 1] >= arr[i]) { break; }
        }
        for (j = arr.length / 2; j < arr.length - 1; j++) {
            if (arr[j + 1] <= arr[j]) { break; }
        }
        int candidate_3 = j - i + 1;
        return Math.max(candidate_1, Math.max(candidate_2, candidate_3));
    }  // Time complexity: O(n * log(n))

    /** Swaps the nodes in pairs in a singly-linked list.
        @param head: head node of the linked list
        @return: head node of the linked list after swap
    */
    public static ListNode swapInPairs(ListNode head) {
        if (head == null || head.next == null) { return head; }  // Base case
        else {
            // Recursively swap the rest of the list.
            ListNode afterFirstTwo = swapInPairs(head.next.next);
            // Then, swap the first two nodes.
            ListNode p = head.next;
            p.next = head;
            // Connect with the rest part.
            head.next = afterFirstTwo;
            return p;
        }
    }  // Time complexity: O(n)
}