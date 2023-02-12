package com.system.C_training.day24;

/**
 * @author ycb
 * @date 2021/10/9-8:24
 * @description 给定两个字符串str1和str2
 * 在str1中寻找一个最短子串，能包含str2的所有字符
 * 字符顺序无所谓，str1的这个最短子串也可以包含多余的字符
 * 返回这个最短包含子串
 */
public class Code05_MinWindowLength {

    public static int minLength(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return Integer.MAX_VALUE;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] map = new int[256];
        // 对str2做词频统计
        for (int i = 0; i < str2.length; i++) {
            map[str2[i]]++;
        }
        int all = str2.length;
        // [L,R-1]  R
        // [L,R) -->
        int L = 0;
        int R = 0;
        int minLen = Integer.MAX_VALUE;
        while (R != str1.length) {
            map[str1[R]]--;
            if (map[str1[R]] >= 0) {
                all--;
            }
            if (all == 0) { // 还完了
                while (map[str1[L]] < 0) {
                    map[str1[L++]]++;
                }
                minLen = Math.min(minLen, R - L + 1);
                all++;
                map[str1[L++]]++;
            }
            R++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        String str1 = "adabbca";
        String str2 = "acba";
        System.out.println(minLength(str1, str2));
    }
}
