package com.leetcode;

/**
 * @author ycb
 * @since 2021/11/24-16:28
 */
public class Problem_0028_ImplementStrStr {

    // s1中能不能配出s2
    // 能配出:返回第一个匹配的位置
    // 配不出:返回-1
    public static int strStr(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() > s1.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0, y = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == s2.length() ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length <= 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0, i = 2;
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s1 = "";
        String s2 = "";
        System.out.println(strStr(s1, s2));
    }
}
