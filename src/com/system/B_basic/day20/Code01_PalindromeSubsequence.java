package com.system.B_basic.day20;

/**
 * @Author ycb
 * @Date 2021/5/12-23:00
 * @Description 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如：str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 */
public class Code01_PalindromeSubsequence {

    // 原字符串逆序过来，二者的最长公共子序列就是最长回文子序列
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 尝试 --> 范围尝试(经验:特别注意讨论开头和结尾的可能性)
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1);
    }

    // str[L...R] 最长回文子序列长度返回
    public static int process1(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        // 不以L位置字符开头也不以R位置字符结尾
        int p1 = process1(str, L + 1, R - 1);
        // 以L位置字符开头，不以R位置字符结尾
        int p2 = process1(str, L, R - 1);
        // 不以L位置字符开图，以R位置字符结尾
        int p3 = process1(str, L + 1, R);
        // 既以L位置开头，也以R位置字符结尾
        int p4 = str[L] == str[R] ? 2 + process1(str, L + 1, R - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    /*
    ====================================================================================================================
     */

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 尝试 --> 范围尝试(经验:特别注意讨论开头和结尾的可能性)
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int p1 = dp[L + 1][R - 1];
                int p2 = dp[L][R - 1];
                int p3 = dp[L + 1][R];
                int p4 = str[L] == str[R] ? 2 + dp[L + 1][R - 1] : 0;
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }

    /*
    ====================================================================================================================
     */

    // 分析位置依赖 --> 进一步优化 --> 去掉重复依赖
    public static int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 尝试 --> 范围尝试(经验:特别注意讨论开头和结尾的可能性)
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }

}
