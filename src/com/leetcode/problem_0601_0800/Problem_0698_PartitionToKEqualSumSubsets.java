package com.leetcode.problem_0601_0800;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/9/20-08:36
 */
public class Problem_0698_PartitionToKEqualSumSubsets {

    static int[] nums;
    static int part, n;
    static int[] dp;

    public static boolean canPartitionKSubsets(int[] arr, int k) {
        nums = arr;
        int sum = 0;
        for (int num : nums)
            sum += num;

        if (sum % k != 0)
            return false;

        Arrays.sort(nums);
        part = sum / k;
        n = nums.length;

        if (nums[n - 1] > part)
            return false;

        dp = new int[1 << n];
        return dfs(0, 0);
    }

    private static boolean dfs(int status, int preSum) {
        if (((1 << n) - 1) == status)
            return true;

        if (dp[status] != 0)
            return dp[status] == 1;

        boolean ans = false;
        for (int i = 0; i < n; i++) {
            if (preSum + nums[i] > part)
                break;

            if ((status & (1 << i)) == 0) {
                if (dfs(status | (1 << i), (preSum + nums[i]) % part))
                    ans = true;
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 2, 3, 4, 5};
        int k = 4;
        boolean ans = canPartitionKSubsets(arr, k);
        System.out.println(ans);
    }
}
