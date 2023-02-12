package com.system.B_basic.day47;

/**
 * @author ycb
 * @date 2021/11/23-8:27
 * @description https://leetcode.com/problems/strange-printer/
 */
public class Code01_StrangePrinter {

    public static int strangePrinter1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1);
    }

    // 要想刷出str[L...R]的样子！
    // 返回最少的转数
    public static int process1(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        // 最差的情况：每个字符都刷一次
        int ans = R - L + 1;
        for (int K = L + 1; K <= R; K++) {
            ans = Math.min(ans, process1(str, L, K - 1) + process1(str, K, R) - (str[L] == str[K] ? 1 : 0));
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int strangePrinter2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        return process2(str, 0, N - 1, dp);
    }

    public static int process2(char[] str, int L, int R, int[][] dp) {
        if (dp[L][R] != 0) {
            return dp[L][R];
        }
        int ans = R - L + 1;
        if (L == R) {
            ans = 1;
        } else {
            for (int K = L + 1; K <= R; K++) {
                ans = Math.min(ans, process2(str, L, K - 1, dp) + process2(str, K, R, dp) - (str[L] == str[K] ? 1 : 0));
            }
        }
        dp[L][R] = ans;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int strangePrinter3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 1 : 2;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = R - L + 1;
                for (int k = L + 1; k <= R; k++) {
                    dp[L][R] = Math.min(dp[L][R], dp[L][k - 1] + dp[k][R] - (str[L] == str[k] ? 1 : 0));
                }
            }
        }
        return dp[0][N - 1];
    }
}
