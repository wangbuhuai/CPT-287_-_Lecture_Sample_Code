// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-18


package hybrid.lecture_4;

import java.util.Arrays;

public class SortingProgram {
    public static void main(String[] args) {
        int[] arr_1 = { 8, 6, 4, 2, 5, 3, 7, 11, 11, 6, 0, 1 };
        int[] arr_2 = Arrays.copyOf(arr_1, arr_1.length);
        int[] arr_3 = Arrays.copyOf(arr_1, arr_1.length);
        int[] arr_4 = Arrays.copyOf(arr_1, arr_1.length);
        Sorting.selectionSort(arr_1);
        Sorting.bubbleSort(arr_2);
        Sorting.insertionSort(arr_3);
        Sorting.mergeSort(arr_4);
        System.out.println(Arrays.toString(arr_1));
        System.out.println(Arrays.toString(arr_2));
        System.out.println(Arrays.toString(arr_3));
        System.out.println(Arrays.toString(arr_4));
    }
}