package com.leetcode;

/**
 * @author ycb
 * @since 2022/1/22-8:40
 */
public class Problem_1332_RemovePalindromicSubsequences {

    public static int removePalindromeSub(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return 2;
            }
        }
        return 1;
    }
}
