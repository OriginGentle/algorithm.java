package com.system.D_weekly.code_2022_07_3_week;

/**
 * @author ycb
 * @date 2022/7/23-13:17
 * @desc 来自蔚来汽车
 * https://leetcode.cn/problems/valid-parenthesis-string/
 */
public class Code02_ValidParenthesisString {

    public static boolean checkValidString1(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        return process(str, 0, 0, dp);
    }

    public static boolean process(char[] str, int i, int c, int[][] dp) {
        if (i == str.length) {
            return c == 0;
        }
        if (c < 0) {
            return false;
        }
        if (dp[i][c] != 0) {
            return dp[i][c] == 1;
        }
        boolean ans = false;
        if (str[i] == '(') {
            ans = process(str, i + 1, c + 1, dp);
        } else if (str[i] == ')') {
            ans = process(str, i + 1, c - 1, dp);
        } else {
            ans |= process(str, i + 1, c + 1, dp);
            ans |= process(str, i + 1, c - 1, dp);
            ans |= process(str, i + 1, c, dp);
        }
        dp[i][c] = ans ? 1 : -1;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static boolean checkValidString2(String s) {
        char[] str = s.toCharArray();
        int max = 0, min = 0;
        for (char c : str) {
            if (c == '(') {
                max++;
                min++;
            } else {
                if (c == ')' && max == 0) {
                    return false;
                }
                max += c == ')' ? -1 : 1;
                if (min > 0) {
                    min--;
                }
            }
        }
        return min == 0;
    }
}
