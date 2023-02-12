package com.leetcode.questions.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/8/24-09:07
 */
public class Problem_0209_MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int sum = 0, l = 0, r = 0;
        int ans = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r];

            while (sum >= target) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l++];
            }
            r++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
