package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/10/20-14:44
 */
public class Problem_0198_HouseRobber {

    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + nums[i], nums[i]));
        }
        return dp[nums.length - 1];
    }

    /*
    ====================================================================================================================
     */

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int pre1 = nums[0];
        int pre2 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(pre2, pre1 + nums[i]);
            pre1 = pre2;
            pre2 = cur;
        }
        return pre2;
    }
}
