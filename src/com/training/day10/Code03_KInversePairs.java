package com.training.day10;

/**
 * @author ycb
 * @date 2021/8/29-13:20
 * @description https://leetcode.com/problems/k-inverse-pairs-array/
 */
public class Code03_KInversePairs {

    public static int kInversePairs(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        // 斜率优化
        // dp[i][j] : 代表从0...i中随意排列，构成j个逆序对的方法数有多少个
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        int mod = 1000000007;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                if (j >= i) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }

}
