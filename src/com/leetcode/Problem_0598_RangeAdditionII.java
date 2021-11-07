package com.leetcode;

/**
 * @author ycb
 * @since 2021/11/7-13:31
 */
public class Problem_0598_RangeAdditionII {

    public static int maxCount(int m, int n, int[][] ops) {
        int minA = Integer.MAX_VALUE, minB = Integer.MAX_VALUE;
        for (int[] arr : ops) {
            minA = Math.min(minA, arr[0]);
            minB = Math.min(minB, arr[1]);
        }
        return minA * minB;
    }
}
