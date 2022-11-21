package com.leetcode.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/11/21-23:39
 */
public class Problem_0808_SoupServings {

    public static double soupServings(int n) {
        n = Math.min(200, (int) Math.ceil(n / 25.0));
        double[][] dp = new double[n + 10][n + 10];
        dp[0][0] = 0.5;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                double a = dp[Math.max(i - 4, 0)][j];
                double b = dp[Math.max(i - 3, 0)][Math.max(j - 1, 0)];
                double c = dp[Math.max(i - 2, 0)][Math.max(j - 2, 0)];
                double d = dp[Math.max(i - 1, 0)][Math.max(j - 3, 0)];
                dp[i][j] = 0.25 * (a + b + c + d);
            }
        }
        return dp[n][n];
    }
}
