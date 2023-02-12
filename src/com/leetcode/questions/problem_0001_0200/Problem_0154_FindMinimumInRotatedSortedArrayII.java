package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/6/24-14:07
 */
public class Problem_0154_FindMinimumInRotatedSortedArrayII {

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int m = (l + r) >> 1;
            if (nums[m] < nums[r]) {
                r = m;
            } else if (nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r--;
            }
        }
        return nums[l];
    }
}
