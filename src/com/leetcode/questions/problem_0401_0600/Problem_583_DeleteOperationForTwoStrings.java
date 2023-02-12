package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/12/30-16:28
 */
public class Problem_583_DeleteOperationForTwoStrings {

    public static int minDistance(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return 0;

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length, M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return (N + M) - 2 * dp[N - 1][M - 1];
    }
}
