package com.leetcode;

/**
 * @Author ycb
 * @Date 2021/6/29-16:52
 */
public class Problem_0005_LongestPalindrome {

    // Manacher算法
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        // 12321   --> #1#2#3#2#1#
        char[] str = manacherString(s);
        // 准备回文半径数组
        int[] pArr = new int[str.length];
        int C = -1;
        // 最右回文边界的下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return null;
    }

    public static char[] manacherString(String str) {
        char[] charArray = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArray[index++];
        }
        return res;
    }
}
