// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-10-12


package evening.lecture_8;

public class BitwiseOperations {
    /** Tests whether a positive integer is power of 2.
        @param num: a positive integer to test
        @return: {true} if the integer is power of 2; {false} otherwise
    */
    public static boolean isPowerOfTwo(int num) { return (num & num - 1) == 0; }  // Time complexity: O(1)

    /** Finds the only single element in an array.
        @param arr: a non-empty array in which all elements appear exactly twice except for 1 which appears only once
        @return: only single element in the array
    */
    public static int singleNumberI(int[] arr) {
        int result = 0;
        for (int val : arr) { result ^= val; }
        return result;
    }  // Time complexity: O(n)

    /** Finds the only single element in an array.
        @param arr: a non-empty array in which all elements appear exactly 3 times except for 1 which appears only once
        @return: only single element in the array
    */
    public static int singleNumberII(int[] arr) {
        int result = 0;
        for (int k = 0; k < 32; k++) {
            int numOfOnes = 0;
            for (int val : arr) { numOfOnes += val >> k & 1; }
            if (numOfOnes % 3 != 0) { result |= 1 << k; }
        }
        return result;
    }  // Time complexity: O(n)

    /** Finds the 2 single elements in an array.
        @param arr: a non-empty array in which all elements appear exactly twice except for 2 which appear only once
        @return: 2 single elements in the array
    */
    public static int[] singleNumberIII(int[] arr) {
        int mark = 0, bit = 0;
        for (int val : arr) { mark ^= val; }
        while ((mark & 1) == 0) {
            mark >>= 1;
            bit++;
        }
        int[] result = { 0, 0 };
        for (int val : arr) { result[val >> bit & 1] ^= val; }
        return result;
    }  // Time complexity: O(n)
}