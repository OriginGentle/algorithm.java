package com.training.day30;

/**
 * @author ycb
 * @since 2021/10/16-21:44
 */
public class Problem_0091_DecodeWays {

    public static int numDecodings1(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0);
    }

    // 潜台词：str[0...index-1]已经转化完了，不用操心了
    // str[index....] 能转出多少有效的，返回方法数
    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        if (str[index] == '0') {
            return 0;
        }
        // index位置的字符单转
        int ways = process(str, index + 1);
        if (index + 1 == str.length) {
            return ways;
        }
        int num = (str[index] - '0') * 10 + (str[index + 1] - '0');
        if (num <= 26) {
            ways += process(str, index + 2);
        }
        return ways;
    }

    /*
    ====================================================================================================================
     */

    public static int numDecodings2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                dp[i] = dp[i + 1];
                if (i + 1 == str.length) {
                    continue;
                }
                int num = (str[i] - '0') * 10 + (str[i + 1] - '0');
                if (num <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    /*
    ====================================================================================================================
     */

    public static int numDecodings3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            } else if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < N) {
                    dp[i] += dp[i + 2];
                }
            } else if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }
}
