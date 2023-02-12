package com.leetcode.questions.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/11/28-10:40
 */
public class Problem_0813_LargestSumOfAverages {

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] prefix = new double[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        double[] dp = new double[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = prefix[i] / i;
        }

        for (int j = 2; j <= k; j++) {
            for (int i = n; i >= j; i--) {
                for (int x = j - 1; x < i; x++) {
                    dp[i] = Math.max(dp[i], dp[x] + (prefix[i] - prefix[x]) / (i - x));
                }
            }
        }
        return dp[n];
    }
}
