package com.system.C_training.day04;

/**
 * @Author ycb
 * @Date 2021/8/1-17:29
 * @Description https://leetcode.com/problems/interleaving-string/
 */
public class Code07_InterleavingString {

    /**
     * dp[i][j] ：str1拿前i个字符，str2拿前j个字符，能否搞定str3前i+j个字符
     * str1: i个  0 ~ i - 1
     * str2: j个  0 ~ j - 1
     * str3: i + j个  0 ~ i + j - 1
     * <p>
     * 可能性1：str3[i+j-1] == str1[i-1] && dp[i-1][j] == true
     * 可能性2：str3[i+j-1] == str2[j-1] && dp[i][j-1] == true
     * 只要一个成立，就能搞定str3
     */

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str3.length != str1.length + str2.length) {
            return false;
        }
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= str2.length; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if ((str1[i - 1] == str3[i + j - 1] && dp[i - 1][j])
                        || (str2[j - 1] == str3[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }

}
