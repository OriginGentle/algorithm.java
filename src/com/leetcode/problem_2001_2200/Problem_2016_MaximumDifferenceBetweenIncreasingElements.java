package com.leetcode.problem_2001_2200;

/**
 * @author ycb
 * @since 2022/2/26-12:12
 */
public class Problem_2016_MaximumDifferenceBetweenIncreasingElements {

    public static int maximumDifference(int[] nums) {
        int n = nums.length;
        int ans = -1, preMin = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > preMin) {
                ans = Math.max(ans, nums[i] - preMin);
            } else {
                preMin = nums[i];
            }
        }
        return ans;
    }
}
