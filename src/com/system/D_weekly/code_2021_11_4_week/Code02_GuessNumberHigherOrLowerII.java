package com.system.D_weekly.code_2021_11_4_week;

/**
 * @author ycb
 * @date 2021/11/25-8:50
 * @description https://leetcode.com/problems/guess-number-higher-or-lower-ii/
 */
public class Code02_GuessNumberHigherOrLowerII {

    public static int getMoneyAmount1(int n) {
        return process(1, n);
    }

    public static int process(int L, int R) {
        if (L == R) {
            return 0;
        }
        if (L == R - 1) {
            return L;
        }
        int min = Math.min(L + process(L + 1, R), R + process(L, R - 1));
        for (int i = L + 1; i < R; i++) {
            min = Math.min(min, i + (Math.max(process(L, i - 1), process(i + 1, R))));
        }
        return min;
    }

    /*
    ====================================================================================================================
     */

    public static int getMoneyAmount2(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n; i++) {
            dp[i][i + 1] = i;
        }
        for (int L = n - 2; L >= 1; L--) {
            for (int R = L + 2; R <= n; R++) {
                dp[L][R] = Math.min(L + dp[L + 1][R], R + dp[L][R - 1]);
                for (int i = L + 1; i < R; i++) {
                    dp[L][R] = Math.min(dp[L][R], i + (Math.max(dp[L][i - 1], dp[i + 1][R])));
                }
            }
        }
        return dp[1][n];
    }
}
