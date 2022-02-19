// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-18


package hybrid.lecture_4;

import java.util.Arrays;

public class Sorting {
    /** Sorts an array non-decreasingly using selection sort.
        @param arr: array to sort
    */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;  // Index of the minimum value in the rest of the array
            // Find the minimum value in the rest of the array
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) { min = j; }
            }
            // Swap if necessary.
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }  // Time complexity: O(n ^ 2)

    /** Sorts an array non-decreasingly using bubble sort.
        @param arr: array to sort
    */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) { break; }
        }
    }  // Time complexity: O(n ^ 2)

    /** Sorts an array non-decreasingly using insertion sort.
        @param arr: array to sort
    */
    public static void insertionSort(int[] arr) {
        for (int cur = 1; cur < arr.length; cur++) {
            int mark = arr[cur], j;
            for (j = cur; j > 0 && arr[j - 1] > mark; j--) { arr[j] = arr[j - 1]; }
            arr[j] = mark;
        }
    }  // Time complexity: O(n ^ 2)

    /** Merges two sorted arrays into a single sorted array.
        @param arr_1: first sorted array to merge
        @param arr_2: second sorted array to merge
        @param result: merged sorted array
    */
    private static void merge(int[] arr_1, int[] arr_2, int[] result) {
        int i = 0, j = 0, k = 0;
        while (i < arr_1.length && j < arr_2.length) {
            if (arr_1[i] <= arr_2[j]) { result[k++] = arr_1[i++]; }
            else { result[k++] = arr_2[j++]; }
        }
        while (i < arr_1.length) { result[k++] = arr_1[i++]; }
        while (j < arr_2.length) { result[k++] = arr_2[j++]; }
    }  // Time complexity: O(n)

    /** Sorts an array non-decreasingly using merge sort.
        @param arr: array to sort
    */
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) { return; }  // Base case
        int[] leftHalf = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightHalf = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        mergeSort(leftHalf);  // Recursion
        mergeSort(rightHalf);  // Recursion
        merge(leftHalf, rightHalf, arr);
    }  // Time complexity: O(n * log(n))
}