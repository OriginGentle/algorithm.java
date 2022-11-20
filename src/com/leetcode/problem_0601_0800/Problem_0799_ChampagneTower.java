package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/11/20-19:10
 */
public class Problem_0799_ChampagneTower {

    public double champagneTower(int poured, int row, int col) {
        double[][] dp = new double[row + 10][row + 10];
        dp[0][0] = poured;
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] <= 1)
                    continue;
                dp[i + 1][j] += (dp[i][j] - 1) / 2;
                dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;
            }
        }
        return Math.min(dp[row][col], 1);
    }
}
