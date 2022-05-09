package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2022/3/2-8:31
 */
public class Problem_0096_UniqueBinarySearchTrees {

    public int numTrees1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /*
    ====================================================================================================================
     */

    // 卡特兰数
    public int numTrees2(int n) {
        long ans = 1;
        for (int i = 0; i < n; i++) {
            ans = ans * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) ans;
    }
}

