package com.leetcode.questions.problem_1401_1600;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/2/16-16:29
 */
public class Problem_1478_AllocateMailboxes {

    public static int minDistance(int[] houses, int k) {
        int n = houses.length;
        Arrays.sort(houses);

        int[][] sum = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i + 1][j - 1] + houses[j] - houses[i];
            }
        }

        // dp[i][j] : 0...i栋房子安排j个邮筒所得到的最小距离和
        // 那么很明显如果有n栋房子，n个邮筒，那么距离和就是 0
        // 即：dp[n - 1][n - 1] = 0
        // 还有一种状态：k > n 即邮筒数量大于房子数量，距离和也是0
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            dp[i][1] = sum[0][i];
            for (int j = 2; j <= k && j <= i + 1; ++j) {
                for (int i0 = 0; i0 < i; ++i0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i0][j - 1] + sum[i0 + 1][i]);
                }
            }
        }
        return dp[n - 1][k];
    }
}
