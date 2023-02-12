package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/11/25-22:35
 */
public class Problem_0795_NumberOfSubArraysWithBoundedMaximum {

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int ans = 0, L = -1, R = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                L = i;
            } else if (nums[i] > right) {
                R = i;
                L = -1;
            }

            if (L != -1)
                ans += L - R;
        }
        return ans;
    }
}
