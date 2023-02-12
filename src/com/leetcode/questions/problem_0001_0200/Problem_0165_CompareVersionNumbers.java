package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/7/5-14:53
 */
public class Problem_0165_CompareVersionNumbers {

    public static int compareVersion(String s, String t) {
        int n = s.length();
        int m = t.length();
        int si = 0, ti = 0;
        while (si < n || ti < m) {
            int sc = 0;
            for (; si < n && s.charAt(si) != '.'; si++) {
                sc = sc * 10 + s.charAt(si) - '0';
            }
            si++;

            int tc = 0;
            for (; ti < m && t.charAt(ti) != '.'; ti++) {
                tc = tc * 10 + t.charAt(ti) - '0';
            }
            ti++;

            if (sc != tc) {
                return sc > tc ? 1 : -1;
            }
        }
        return 0;
    }
}
