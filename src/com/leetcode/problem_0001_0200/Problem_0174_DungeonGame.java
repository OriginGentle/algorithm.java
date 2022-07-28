package com.leetcode.problem_0001_0200;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/7/28-08:27
 */
public class Problem_0174_DungeonGame {

    public static int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] cur : dp) {
            Arrays.fill(cur, Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int min = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    /*
    ====================================================================================================================
     */

    public int[][] tmp;
    public int n, m;

    public int calculateMinimumHP1(int[][] dungeon) {
        tmp = dungeon;
        n = dungeon.length;
        m = dungeon[0].length;
        int[][] dp = new int[n][m];
        for (int[] cur : dp) {
            Arrays.fill(cur, -1);
        }
        return process(0, 0, dp);
    }

    private int process(int i, int j, int[][] dp) {
        if (i >= n || j >= m) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == n - 1 && j == m - 1) {
            return tmp[i][j] >= 0 ? 0 : -tmp[i][j];
        }
        int min = Math.min(
                process(i + 1, j, dp),
                process(i, j + 1, dp))
                - tmp[i][j];
        int ans = Math.max(min, 0);
        dp[i][j] = ans;
        return ans;
    }
}
