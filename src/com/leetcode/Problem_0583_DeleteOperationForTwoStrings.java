package com.leetcode;

/**
 * @author ycb
 * @since 2021/9/25-17:31
 */
public class Problem_0583_DeleteOperationForTwoStrings {

    public static int minDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];
        for (int i = 0; i <= str1.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= str2.length; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {

            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }

    // str1[L1..R1]
    public static int process(char[] str1, int l1, int r1, char[] str2, int l2, int r2) {

        return 0;
    }
}
