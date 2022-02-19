// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-18


package hybrid.lecture_4;

public class BitwiseOperations {
    /** Tests whether a positive integer is power of 2.
        @param num: positive integer to test
        @return: {true} if the integer is power of 2; {false} otherwise
    */
    public static boolean isPowerOfTwo(int num) { return (num & num - 1) == 0; }  // Time complexity: O(1)

    /** Finds the only single number in an array.
        @param arr: non-empty array in which all elements appear exactly twice, except for one which appears only once.
        @return: only single element in the array
    */
    public static int singleNumberI(int[] arr) {
        int result = 0;
        for (int val : arr) { result ^= val; }
        return result;
    }  // Time complexity: O(n)
}