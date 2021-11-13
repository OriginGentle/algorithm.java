package com.training.day49;

import java.util.Arrays;

/**
 * @author ycb
 * @since 2021/11/12-8:28
 */
public class Problem_0377_CombinationSumIV {

    public static int ways(int[] arr, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int ways = 0;
        for (int num : arr) {
            ways += ways(arr, rest - num);
        }
        return ways;
    }

    /*
    ====================================================================================================================
     */

    public static int[] dp = new int[1001];

    public static int combinationSum4I(int[] nums, int target) {
        Arrays.fill(dp, 0, target + 1, -1);
        return process1(nums, target);
    }

    public static int process1(int[] arr, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (dp[rest] != -1) {
            return dp[rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = 1;
        } else {
            for (int num : arr) {
                ans += process1(arr, rest - num);
            }
        }
        dp[rest] = ans;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int combinationSum4II(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int rest = 1; rest <= target; rest++) {
            for (int i = 0; i < nums.length && nums[i] <= rest; i++) {
                dp[rest] += dp[rest - nums[i]];
            }
        }
        return dp[target];
    }
}
