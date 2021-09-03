package com.training.day12;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2021/8/31-10:22
 * @description https://leetcode.com/problems/permutation-in-string/
 * 给定长度为m的字符串aim，以及一个长度为n的字符串str
 * 问能否在str中找到一个长度为m的连续子串，
 * 使得这个子串刚好由aim的m个字符组成，顺序无所谓，
 * 返回任意满足条件的一个子串的起始位置，未找到返回-1
 */
public class Code01_ContainAllCharExactly {

    // 暴力解
    public static int containExactly1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        char[] aim = s2.toCharArray();
        Arrays.sort(aim);
        String aimSort = String.valueOf(aim);
        // 枚举s1的所有子串
        for (int L = 0; L < s1.length(); L++) {
            for (int R = L; R < s1.length(); R++) {
                char[] cur = s1.substring(L, R + 1).toCharArray();
                Arrays.sort(cur);
                String curSort = String.valueOf(cur);
                if (curSort.equals(aimSort)) {
                    return L;
                }
            }
        }
        return -1;
    }

    /*
    ====================================================================================================================
     */

    public static int containExactly2(String s, String a) {
        if (s == null || a == null || s.length() < a.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] aim = a.toCharArray();
        for (int L = 0; L <= str.length - aim.length; L++) {
            if (isCountEqual(str, L, aim)) {
                return L;
            }
        }
        return -1;
    }

    public static boolean isCountEqual(char[] str, int L, char[] aim) {
        int[] count = new int[256];
        for (int i = 0; i < aim.length; i++) {
            count[aim[i]]++;
        }
        for (int i = 0; i < aim.length; i++) {
            if (count[str[L + i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    /*
    ====================================================================================================================
     */

    // Map + 窗口
    public static int containExactly3(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        char[] str2 = s2.toCharArray();
        int M = str2.length;
        int[] count = new int[256];
        for (int i = 0; i < str2.length; i++) {
            count[str2[i]]++;
        }
        int all = M;
        char[] str1 = s1.toCharArray();
        int R = 0;
        // 最早的M个字符,窗口初步形成期 0 ~ M-1
        for (; R < M; R++) {
            if (count[str1[R]]-- > 0) {
                all--;
            }
        }
        // 窗口初步形成了，并没有判断有效无效，决定下一个位置一上来判断
        // 接下来的过程，窗口右进一个，左吐一个
        for (; R < s1.length(); R++) {
            if (all == 0) {
                return R - M;
            }
            if (count[str1[R]]-- > 0) {
                all--;
            }
            if (count[str1[R - M]]++ >= 0) {
                all++;
            }
        }
        return all == 0 ? R - M : -1;
    }

    public static String getRandomString(int possibilities, int maxSize) {
        char[] ans = new char[(int) (Math.random() + 1) * maxSize];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 20;
        int aimMaxSize = 10;
        int testTimes = 500000;
        System.out.println("test begin, test time : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strMaxSize);
            String aim = getRandomString(possibilities, aimMaxSize);
            int ans1 = containExactly1(str, aim);
            int ans2 = containExactly2(str, aim);
            int ans3 = containExactly3(str, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("test finish");
    }
}
