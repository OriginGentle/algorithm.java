package com.system.D_weekly.code_2023_03_2_week;

/**
 * @author ycb
 * @date 2023/3/10-13:35
 * @desc 给出两个字符串 str1 和 str2
 * 返回同时以 str1 和 str2 作为子序列的最短字符串
 * 如果答案不止一个，则可以返回满足条件的任意一个答案。
 * 测试链接 : https://leetcode.cn/problems/shortest-common-supersequence/
 */
public class Code02_ShortestCommonSuperSequence {

    public static String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        char[] ans = new char[n + m - dp[n][m]];
        int ai = ans.length - 1, i = n, j = m;
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j - 1] + 1
                    && str1.charAt(i - 1) == str2.charAt(j - 1)) {
                ans[ai--] = str1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j]) {
                ans[ai--] = str1.charAt(i - 1);
                i--;
            } else {
                ans[ai--] = str2.charAt(j - 1);
                j--;
            }
        }

        for (; i > 0; i--) {
            ans[ai--] = str1.charAt(i - 1);
        }
        for (; j > 0; j--) {
            ans[ai--] = str2.charAt(j - 1);
        }
        return String.valueOf(ans);
    }
}
