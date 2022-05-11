package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/5/9-16:15
 */
public class Problem_0153_FindMinimumInRotatedSortedArray {

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int l = 0;
        int r = nums.length - 1;
        int m = 0;
        int ans = Integer.MAX_VALUE;
        while (l <= r) {
            m = (l + r) / 2;
        }
        return 0;
    }
}
