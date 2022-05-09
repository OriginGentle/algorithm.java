package com.leetcode.problem_1801_2000;

import java.util.Arrays;

/**
 * @author ycb
 * @since 2022/2/11-18:48
 */
public class Problem_1984_MinimumDifferenceBetweenHighestAndLowestOfKScores {

    public static int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[k - 1] - nums[0];
        for (int i = k; i < n; i++) {
            ans = Math.min(ans, nums[i] - nums[i - k + 1]);
        }
        return ans;
    }
}
