package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/12/21-16:26
 */
public class Problem_0064_MinimumPathSum {

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col];
        dp[0] = grid[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[col - 1];
    }
}
