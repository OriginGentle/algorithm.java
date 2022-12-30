package com.leetcode.problem_1201_1400;

/**
 * @author ycb
 * @date 2022/12/30-16:18
 */
public class Problem_1328_BreakAPalindrome {

    public static String breakPalindrome(String s) {
        int n = s.length();
        if (n == 1) {
            return "a";
        }
        char[] str = s.toCharArray();
        for (int i = 0; i < (n >> 1); i++) {
            if (str[i] > 'a') {
                str[i] = 'a';
                return String.valueOf(str);
            }
        }
        str[n - 1] = 'b';
        return String.valueOf(str);
    }
}
