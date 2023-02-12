package com.system.D_weekly.code_2022_06_3_week;

/**
 * @author ycb
 * @date 2022/6/26-14:05
 * @desc https://leetcode.cn/problems/selling-pieces-of-wood/
 */
public class Code02_SellingPiecesOfWood {

    public static long sellingWood1(int m, int n, int[][] prices) {
        long[][] values = new long[m + 1][n + 1];
        for (int[] p : prices) {
            values[p[0]][p[1]] = Math.max(values[p[0]][p[1]], p[2]);
        }
        return process1(m, n, values);
    }

    // values:所有木块的单一报价
    // m * n这块木板，只能水平分割、垂直分割的情况下，能获得的最大总钱数是多少？
    public static long process1(int m, int n, long[][] values) {
        if (m == 0 || n == 0) {
            return 0;
        }
        // m > 0 & n > 0木块还有面积！
        // 普遍分析
        // 可能性1：一刀也不切
        long ans = values[m][n];
        // 接下来的一系列可能性：水平方向上，都去试一试
        for (int split = 1; split < m; split++) {
            ans = Math.max(ans,
                    process1(split, n, values) + process1(m - split, n, values));
        }
        // 垂直方向上，都去试一试
        for (int split = 1; split < n; split++) {
            ans = Math.max(ans,
                    process1(m, split, values) + process1(m, n - split, values));
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static long sellingWood2(int m, int n, int[][] prices) {
        long[][] values = new long[m + 1][n + 1];
        for (int[] p : prices) {
            values[p[0]][p[1]] = Math.max(p[2], values[p[0]][p[1]]);
        }
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(m, n, values, dp);
    }

    public static long process2(int m, int n, long[][] values, long[][] dp) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        long ans = values[m][n];
        for (int i = 1; i < m; i++) {
            ans = Math.max(ans,
                    process2(i, n, values, dp) + process2(m - i, n, values, dp));
        }

        for (int i = 1; i < n; i++) {
            ans = Math.max(ans,
                    process2(m, i, values, dp) + process2(m, n - i, values, dp));
        }
        dp[m][n] = ans;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // 优化1: prices中的单块收益直接填入dp表即可，如果有更好的分割方案，更新掉
    // 优化2: 分割只需要枚举一半即可
    public static long sellingWood3(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];
        for (int[] p : prices) {
            dp[p[0]][p[1]] = Math.max(dp[p[0]][p[1]], p[2]);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 水平分割
                for (int k = 0; k <= i >> 1; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                // 垂直分割
                for (int k = 0; k <= j >> 1; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }

            }
        }
        return dp[m][n];
    }
}
