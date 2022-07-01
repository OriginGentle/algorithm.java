package com.weekly.code_2022_06_4_week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author ycb
 * @date 2022/7/1-13:34
 * @desc https://leetcode.cn/problems/minimum-window-subsequence/
 */
public class Code01_MinimumWindowSubsequence {

    public static String minWindow1(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int n = str.length;
        // key：字符
        // value：有序表
        HashMap<Character, TreeSet<Integer>> map = new HashMap<>();
        for (char ch : target) {
            map.put(ch, new TreeSet<>());
        }
        for (int i = 0; i < n; i++) {
            if (map.containsKey(str[i])) {
                map.get(str[i]).add(i);
            }
        }
        int len = Integer.MAX_VALUE;
        int l = -1, r = -1;
        for (int i = 0; i < n; i++) {
            if (str[i] == target[0]) {
                int right = right1(str, i, target, map);
                if (right != -1 && (right - i) < len) {
                    len = right - i;
                    l = i;
                    r = right;
                }
            }
        }
        return l == -1 ? "" : s.substring(l, r);
    }

    private static int right1(char[] str, int si, char[] target, HashMap<Character, TreeSet<Integer>> map) {
        int ti = 0;
        while (ti != target.length) {
            if (si == str.length) {
                return -1;
            }
            if (str[si] == target[ti]) {
                si++;
                ti++;
            } else {
                Integer next = map.get(target[ti]).ceiling(si);
                if (next == null)
                    return -1;
                else
                    si = next;
            }
        }
        return si;
    }

    /*
    ====================================================================================================================
     */

    public static String minWindow2(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int n = str.length;
        int[] last = new int[26];
        int[][] near = new int[n][26];
        for (int i = 0; i < n; i++) {
            Arrays.fill(near[i], -1);
        }
        for (int i = 0; i < n; i++) {
            int cur = str[i] - 'a';
            for (int j = last[cur]; j < i; j++) {
                near[j][cur] = i;
            }
            last[cur] = i;
        }
        int len = Integer.MAX_VALUE;
        int l = -1;
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (str[i] == target[0]) {
                int right = right2(str, i, target, near);
                if (right != -1 && (right - i) < len) {
                    len = right - i;
                    l = i;
                    r = right;
                }
            }
        }
        return l == -1 ? "" : s.substring(l, r);
    }

    private static int right2(char[] str, int si, char[] target, int[][] near) {
        int ti = 0;
        while (ti < target.length) {
            if (si == str.length) {
                return -1;
            }
            if (str[si] == target[ti]) {
                si++;
                ti++;
            } else {
                si = near[si][target[ti] - 'a'];
            }
            if (si == -1) {
                return -1;
            }
        }
        return si;
    }

    /*
    ====================================================================================================================
     */

    public static String minWindow3(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int len = Integer.MAX_VALUE;
        int l = -1, r = -1;
        int n = str.length;
        for (int si = 0; si < n; si++) {
            int res = process(str, target, si, 0);
            if (res != Integer.MIN_VALUE && (res - si) < len) {
                len = res - si;
                l = si;
                r = res;
            }
        }
        return l == -1 ? "" : s.substring(l, r);
    }

    public static int process(char[] str, char[] target, int si, int ti) {
        if (ti == target.length) {
            return si;
        }
        if (si == str.length) {
            return Integer.MAX_VALUE;
        }
        int p1 = process(str, target, si + 1, ti);
        int p2 = str[si] == target[ti] ? process(str, target, si + 1, ti + 1) : Integer.MAX_VALUE;
        return Math.min(p1, p2);
    }

    /*
    ====================================================================================================================
     */

    public static String minWindow4(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int n = str.length;
        int m = target.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int si = 0; si <= n; si++) {
            dp[si][m] = si;
        }
        for (int ti = 0; ti < m; ti++) {
            dp[n][ti] = Integer.MAX_VALUE;
        }
        for (int si = n - 1; si >= 0; si--) {
            for (int ti = m - 1; ti >= 0; ti--) {
                dp[si][ti] = dp[si + 1][ti];
                if (str[si] == target[ti]) {
                    dp[si][ti] = Math.min(dp[si][ti], dp[si + 1][ti + 1]);
                }
            }
        }
        int len = Integer.MAX_VALUE;
        int l = -1, r = -1;
        for (int i = 0; i < n; i++) {
            if (str[i] == target[0]) {
                int right = dp[i][0];
                if (right != Integer.MAX_VALUE && right - i < len) {
                    len = right - i;
                    l = i;
                    r = right;
                }
            }
        }
        return l == -1 ? "" : s.substring(l, r);
    }

    public static void main(String[] args) {
        String s = "xxaxxbxxcxx";
        // 0 8
        String t = "abc";
        String ans1 = minWindow1(s, t);
        String ans2 = minWindow2(s, t);
        String ans3 = minWindow3(s, t);
        String ans4 = minWindow4(s, t);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
        System.out.println(ans4);
    }
}
