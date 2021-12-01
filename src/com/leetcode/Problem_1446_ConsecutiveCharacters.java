package com.leetcode;

/**
 * @author ycb
 * @since 2021/12/1-8:32
 */
public class Problem_1446_ConsecutiveCharacters {

    public static int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        char[] str = s.toCharArray();
        int l = 0, r = 0;
        while (r < str.length) {
            if (str[l] == str[r]) {
                ans = Math.max(ans, r - l + 1);
                r++;
            } else {
                l++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abbcccddddeeeeedcba";
        int ans = maxPower(s);
        System.out.println(ans);
    }
}
