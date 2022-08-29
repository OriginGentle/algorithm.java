package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/8/27-13:42
 */
public class Problem_0214_ShortestPalindrome {

    // 找最长的回文前缀
    public static String shortestPalindrome1(String s) {
        if (s == null || s.length() == 0)
            return s;

        char[] str = s.toCharArray();
        int n = str.length, max = -1;
        for (int i = 0; i < (n + 1) >> 1; i++) {
            int len1 = func(str, i, i);
            int len2 = func(str, i, i + 1);
            int len = Math.max(len1, len2);

            if (i - (len - 1) / 2 == 0)
                max = Math.max(len, max);
        }
        String end = s.substring(max);
        return new StringBuilder(end).reverse() + s.substring(0, max) + end;
    }

    public static int func(char[] str, int l, int r) {
        while (l >= 0 && r < str.length && str[l] == str[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    /*
    ====================================================================================================================
     */

    public static String shortestPalindrome2(String s) {
        if (s == null || s.length() == 0)
            return s;

        int n = s.length();
        String tmp = s + "#" + new StringBuilder(s).reverse();
        char[] str = tmp.toCharArray();
        int max = getNextArr(str);
        String end = max == n - 1 ? "" : s.substring(max + 1);
        return new StringBuilder(end).reverse() + s;
    }

    private static int getNextArr(char[] str) {
        int n = str.length;
        int[] next = new int[n];
        next[0] = -1;
        int i = 0, cn = -1;
        while (i < n - 1) {
            if (cn == -1 || str[i] == str[cn])
                next[++i] = ++cn;
            else
                cn = next[cn];
        }
        return next[n - 1];
    }

    public static void main(String[] args) {
        String s = "abbacd";
        String ans1 = shortestPalindrome1(s);
        String ans2 = shortestPalindrome2(s);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
