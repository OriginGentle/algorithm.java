package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/6/28-16:24
 */
public class Problem_0162_FindPeakElement {

    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int m = l + r >> 1;
            if (nums[m] > nums[m + 1]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r;
    }
}
