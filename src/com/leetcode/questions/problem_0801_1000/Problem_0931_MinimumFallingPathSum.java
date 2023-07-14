package com.leetcode.questions.problem_0801_1000;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/7/14-14:27
 */
public class Problem_0931_MinimumFallingPathSum {

    public static int[][] arr;

    public static int minFallingPathSum1(int[][] matrix) {
        arr = matrix;
        int n = matrix.length;
        int ans = Integer.MAX_VALUE;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int col = 0; col < n; col++) {
            // 从下往上计算
            ans = Math.min(ans, dfs(n - 1, col, dp));
        }
        return ans;
    }

    private static int dfs(int row, int col, int[][] dp) {
        if (col < 0 || col >= arr.length) {
            return Integer.MAX_VALUE;
        }

        if (row == 0) {
            return arr[row][col];
        }

        if (dp[row][col] != Integer.MAX_VALUE) {
            return dp[row][col];
        }

        int p1 = dfs(row - 1, col - 1, dp);
        int p2 = dfs(row - 1, col, dp);
        int p3 = dfs(row - 1, col + 1, dp);

        int ans = Math.min(p1, Math.min(p2, p3)) + arr[row][col];
        dp[row][col] = ans;

        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int minFallingPathSum2(int[][] matrix) {
        int n = matrix.length;
        int[] dp = new int[n + 2];
        dp[0] = dp[n + 1] = Integer.MAX_VALUE;
        System.arraycopy(matrix[0], 0, dp, 1, n);
        for (int row = 1; row < n; row++) {
            int pre = dp[0];
            for (int col = 0; col < n; col++) {
                int tmp = pre;
                pre = dp[col + 1];
                dp[col + 1] = Math.min(tmp, Math.min(dp[col + 1], dp[col + 2])) + matrix[row][col];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dp[i]);
        }

        return ans;
    }
}
